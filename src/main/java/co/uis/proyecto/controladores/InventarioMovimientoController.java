package co.uis.proyecto.controladores;

import co.uis.proyecto.entidades.InventarioMovimiento;
import co.uis.proyecto.servicios.InventarioMovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario-movimientos")
@RequiredArgsConstructor
public class InventarioMovimientoController {
  private final InventarioMovimientoService service;

  @GetMapping public List<InventarioMovimiento> listar( ){ return service.listar(); }
  @GetMapping("/{id}") public InventarioMovimiento obtener(@PathVariable Integer id){ return service.obtener(id); }
  @PostMapping @ResponseStatus(HttpStatus.CREATED) public InventarioMovimiento crear(@RequestBody InventarioMovimiento m){ return service.crear(m); }
  @PutMapping("/{id}") public InventarioMovimiento actualizar(@PathVariable Integer id, @RequestBody InventarioMovimiento m){ return service.actualizar(id, m); }
  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void eliminar(@PathVariable Integer id){ service.eliminar(id); }
}