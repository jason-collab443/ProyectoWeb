package co.uis.proyecto.controladores;

import co.uis.proyecto.entidades.Rol;
import co.uis.proyecto.servicios.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RolController {
  private final RolService service;

  @GetMapping public List<Rol> listar( ){ return service.listar(); }
  @GetMapping("/{id}") public Rol obtener(@PathVariable Integer id){ return service.obtener(id); }
  @PostMapping @ResponseStatus(HttpStatus.CREATED) public Rol crear(@RequestBody Rol r){ return service.crear(r); }
  @PutMapping("/{id}") public Rol actualizar(@PathVariable Integer id, @RequestBody Rol r){ return service.actualizar(id, r); }
  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void eliminar(@PathVariable Integer id){ service.eliminar(id); }
}