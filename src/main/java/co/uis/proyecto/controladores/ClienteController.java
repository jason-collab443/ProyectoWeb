package co.uis.proyecto.controladores;

import co.uis.proyecto.entidades.Cliente;
import co.uis.proyecto.servicios.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {
  private final ClienteService service;

  @GetMapping public List<Cliente> listar(){ return service.listar(); }
  @GetMapping("/{id}") public Cliente obtener(@PathVariable Integer id){ return service.obtener(id); }
  @PostMapping @ResponseStatus(HttpStatus.CREATED) public Cliente crear(@RequestBody Cliente c){ return service.crear(c); }
  @PutMapping("/{id}") public Cliente actualizar(@PathVariable Integer id, @RequestBody Cliente c){ return service.actualizar(id, c); }
  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void eliminar(@PathVariable Integer id){ service.eliminar(id); }
}