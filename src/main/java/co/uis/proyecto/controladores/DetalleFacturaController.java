package co.uis.proyecto.controladores;

import co.uis.proyecto.entidades.DetalleFactura;
import co.uis.proyecto.servicios.DetalleFacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-factura")
@RequiredArgsConstructor
public class DetalleFacturaController {
  private final DetalleFacturaService service;

  @GetMapping public List<DetalleFactura> listar( ){ return service.listar(); }
  @GetMapping("/{id}") public DetalleFactura obtener(@PathVariable Integer id){ return service.obtener(id); }
  @PostMapping @ResponseStatus(HttpStatus.CREATED) public DetalleFactura crear(@RequestBody DetalleFactura d){ return service.crear(d); }
  @PutMapping("/{id}") public DetalleFactura actualizar(@PathVariable Integer id, @RequestBody DetalleFactura d){ return service.actualizar(id, d); }
  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void eliminar(@PathVariable Integer id){ service.eliminar(id); }
}