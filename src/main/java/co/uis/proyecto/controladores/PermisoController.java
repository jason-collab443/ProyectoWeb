package co.uis.proyecto.controladores;

import co.uis.proyecto.entidades.Permiso;
import co.uis.proyecto.servicios.PermisoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permisos")
@RequiredArgsConstructor
public class PermisoController {
  private final PermisoService service;

  @GetMapping public List<Permiso> listar( ){ return service.listar(); }
  @GetMapping("/{id}") public Permiso obtener(@PathVariable Integer id){ return service.obtener(id); }
  @PostMapping @ResponseStatus(HttpStatus.CREATED) public Permiso crear(@RequestBody Permiso p){ return service.crear(p); }
  @PutMapping("/{id}") public Permiso actualizar(@PathVariable Integer id, @RequestBody Permiso p){ return service.actualizar(id, p); }
  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void eliminar(@PathVariable Integer id){ service.eliminar(id); }
}