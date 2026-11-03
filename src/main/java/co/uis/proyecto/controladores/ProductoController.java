package co.uis.proyecto.controladores;

import co.uis.proyecto.entidades.Producto;
import co.uis.proyecto.servicios.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {
  private final ProductoService service;

  @GetMapping public List<Producto> listar( ){ return service.listar(); }
  @GetMapping("/{id}") public Producto obtener(@PathVariable Integer id){ return service.obtener(id); }
  @PostMapping @ResponseStatus(HttpStatus.CREATED) public Producto crear(@RequestBody Producto p){ return service.crear(p); }
  @PutMapping("/{id}") public Producto actualizar(@PathVariable Integer id, @RequestBody Producto p){ return service.actualizar(id, p); }
  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void eliminar(@PathVariable Integer id){ service.eliminar(id); }
}