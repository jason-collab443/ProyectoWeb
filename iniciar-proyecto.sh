#!/usr/bin/env bash
set -Eeuo pipefail

PROJECT_DIR="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)"
FRONTEND_DIR="$PROJECT_DIR/web/proyecto-app-front"
backend_pid=""
frontend_pid=""

cleanup() {
  trap - EXIT INT TERM
  for pid in "$frontend_pid" "$backend_pid"; do
    if [[ -n "$pid" ]]; then
      kill -TERM -- "-$pid" 2>/dev/null || kill -TERM "$pid" 2>/dev/null || true
    fi
  done
  for pid in "$frontend_pid" "$backend_pid"; do
    if [[ -n "$pid" ]]; then
      wait "$pid" 2>/dev/null || true
    fi
  done
}
trap cleanup EXIT
trap 'exit 130' INT
trap 'exit 143' TERM

for command_name in npm curl setsid javac readlink; do
  if ! command -v "$command_name" >/dev/null 2>&1; then
    echo "Falta '$command_name'. Instálalo antes de iniciar el proyecto." >&2
    exit 1
  fi
done

if [[ -z "${JAVA_HOME:-}" || ! -x "${JAVA_HOME:-}/bin/javac" ]]; then
  javac_path="$(readlink -f "$(command -v javac)")"
  export JAVA_HOME="$(dirname -- "$(dirname -- "$javac_path")")"
fi
java_version="$("$JAVA_HOME/bin/javac" -version 2>&1)"
java_major="${java_version##* }"
java_major="${java_major%%.*}"
if [[ ! "$java_major" =~ ^[0-9]+$ ]] || (( java_major < 17 )); then
  echo "Se necesita un JDK 17 o superior (encontrado: $java_version)." >&2
  exit 1
fi

if [[ ! -f "$PROJECT_DIR/mvnw" || ! -f "$FRONTEND_DIR/package.json" ]]; then
  echo "No se encontraron el backend y el frontend junto a este script." >&2
  exit 1
fi

port_busy() {
  ( : > "/dev/tcp/127.0.0.1/$1" ) >/dev/null 2>&1 ||
    ( : > "/dev/tcp/::1/$1" ) >/dev/null 2>&1
}
if port_busy 4200; then
  echo "El puerto 4200 ya está ocupado. Cierra el frontend que lo usa y vuelve a intentar." >&2
  exit 1
fi

backend_running=false
if port_busy 8080; then
  if curl --noproxy '*' --max-time 2 --silent --fail http://localhost:8080/api/pagos >/dev/null; then
    backend_running=true
    echo "El backend ya está activo en el puerto 8080; se reutilizará."
  else
    echo "El puerto 8080 está ocupado por otro servicio. Ciérralo y vuelve a intentar." >&2
    exit 1
  fi
fi

if [[ ! -x "$FRONTEND_DIR/node_modules/.bin/ng" ]]; then
  echo "Instalando dependencias del frontend..."
  (cd "$FRONTEND_DIR" && npm ci)
fi

wait_for_url() {
  local name="$1" url="$2" pid="$3" max_seconds="$4"
  local second
  for ((second = 0; second < max_seconds; second++)); do
    if curl --noproxy '*' --max-time 1 --silent --fail "$url" >/dev/null; then
      return 0
    fi
    if ! kill -0 "$pid" 2>/dev/null; then
      echo "$name terminó antes de estar listo. Revisa los mensajes anteriores." >&2
      return 1
    fi
    sleep 1
  done
  echo "$name no respondió a tiempo en $url." >&2
  return 1
}

if [[ "$backend_running" == false ]]; then
  echo "Iniciando backend con $java_version..."
  cd "$PROJECT_DIR"
  setsid bash ./mvnw -Dmaven.test.skip=true spring-boot:run &
  backend_pid=$!
  wait_for_url "El backend" "http://localhost:8080/api/pagos" "$backend_pid" 300
fi

echo "Iniciando frontend..."
cd "$FRONTEND_DIR"
CI=true setsid npm start -- --host localhost --port 4200 &
frontend_pid=$!
wait_for_url "El frontend" "http://localhost:4200/" "$frontend_pid" 120

echo
echo "Proyecto listo: http://localhost:4200/pagos"
echo "Pulsa Ctrl+C para detener el frontend y el backend."

if [[ -n "$backend_pid" ]]; then
  wait -n "$backend_pid" "$frontend_pid"
else
  wait "$frontend_pid"
fi
