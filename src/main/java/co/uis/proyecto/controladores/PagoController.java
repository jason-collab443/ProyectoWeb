package co.uis.proyecto.controladores;

import co.uis.proyecto.entidades.Pago;
import co.uis.proyecto.servicios.PagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {
  private final PagoService service;

  @GetMapping public List<Pago> listar( ){ return service.listar(); }
  @GetMapping("/{id}") public Pago obtener(@PathVariable Integer id){ return service.obtener(id); }
  @PostMapping @ResponseStatus(HttpStatus.CREATED) public Pago crear(@RequestBody Pago p){ return service.crear(p); }
  @PutMapping("/{id}") public Pago actualizar(@PathVariable Integer id, @RequestBody Pago p){ return service.actualizar(id, p); }
  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void eliminar(@PathVariable Integer id){ service.eliminar(id); }
}