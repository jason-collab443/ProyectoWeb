package co.uis.proyecto.controladores;

import co.uis.proyecto.entidades.Proveedor;
import co.uis.proyecto.servicios.ProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@RequiredArgsConstructor
public class ProveedorController {
  private final ProveedorService service;

  @GetMapping public List<Proveedor> listar( ){ return service.listar(); }
  @GetMapping("/{id}") public Proveedor obtener(@PathVariable Integer id){ return service.obtener(id); }
  @PostMapping @ResponseStatus(HttpStatus.CREATED) public Proveedor crear(@RequestBody Proveedor p){ return service.crear(p); }
  @PutMapping("/{id}") public Proveedor actualizar(@PathVariable Integer id, @RequestBody Proveedor p){ return service.actualizar(id, p); }
  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void eliminar(@PathVariable Integer id){ service.eliminar(id); }
}