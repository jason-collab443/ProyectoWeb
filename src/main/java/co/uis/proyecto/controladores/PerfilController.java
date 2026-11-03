package co.uis.proyecto.controladores;

import co.uis.proyecto.entidades.Perfil;
import co.uis.proyecto.servicios.PerfilService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfiles")
@RequiredArgsConstructor
public class PerfilController {
  private final PerfilService service;

  @GetMapping public List<Perfil> listar( ){ return service.listar(); }
  @GetMapping("/{id}") public Perfil obtener(@PathVariable Integer id){ return service.obtener(id); }
  @PostMapping @ResponseStatus(HttpStatus.CREATED) public Perfil crear(@RequestBody Perfil p){ return service.crear(p); }
  @PutMapping("/{id}") public Perfil actualizar(@PathVariable Integer id, @RequestBody Perfil p){ return service.actualizar(id, p); }
  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void eliminar(@PathVariable Integer id){ service.eliminar(id); }
}