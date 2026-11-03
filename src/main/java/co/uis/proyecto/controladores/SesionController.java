package co.uis.proyecto.controladores;

import co.uis.proyecto.entidades.Sesion;
import co.uis.proyecto.servicios.SesionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sesiones")
@RequiredArgsConstructor
public class SesionController {
  private final SesionService service;

  @GetMapping public List<Sesion> listar( ){ return service.listar(); }
  @GetMapping("/{id}") public Sesion obtener(@PathVariable Integer id){ return service.obtener(id); }
  @PostMapping @ResponseStatus(HttpStatus.CREATED) public Sesion crear(@RequestBody Sesion s){ return service.crear(s); }
  @PutMapping("/{id}") public Sesion actualizar(@PathVariable Integer id, @RequestBody Sesion s){ return service.actualizar(id, s); }
  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void eliminar(@PathVariable Integer id){ service.eliminar(id); }
}