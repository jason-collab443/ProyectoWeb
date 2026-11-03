package co.uis.proyecto.controladores;

import co.uis.proyecto.entidades.OrdenCompra;
import co.uis.proyecto.servicios.OrdenCompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes-compra")
@RequiredArgsConstructor
public class OrdenCompraController {
  private final OrdenCompraService service;

  @GetMapping public List<OrdenCompra> listar( ){ return service.listar(); }
  @GetMapping("/{id}") public OrdenCompra obtener(@PathVariable Integer id){ return service.obtener(id); }
  @PostMapping @ResponseStatus(HttpStatus.CREATED) public OrdenCompra crear(@RequestBody OrdenCompra o){ return service.crear(o); }
  @PutMapping("/{id}") public OrdenCompra actualizar(@PathVariable Integer id, @RequestBody OrdenCompra o){ return service.actualizar(id, o); }
  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void eliminar(@PathVariable Integer id){ service.eliminar(id); }
}