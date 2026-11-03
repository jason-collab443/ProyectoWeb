package co.uis.proyecto.controladores;

import co.uis.proyecto.entidades.Factura;
import co.uis.proyecto.servicios.FacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
public class FacturaController {
  private final FacturaService service;

  @GetMapping public List<Factura> listar( ){ return service.listar(); }
  @GetMapping("/{id}") public Factura obtener(@PathVariable Integer id){ return service.obtener(id); }
  @PostMapping @ResponseStatus(HttpStatus.CREATED) public Factura crear(@RequestBody Factura f){ return service.crear(f); }
  @PutMapping("/{id}") public Factura actualizar(@PathVariable Integer id, @RequestBody Factura f){ return service.actualizar(id, f); }
  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void eliminar(@PathVariable Integer id){ service.eliminar(id); }
}