package co.uis.proyecto.servicios;

import co.uis.proyecto.entidades.Cliente;
import co.uis.proyecto.repositorios.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor
public class ClienteService {
  private final ClienteRepository repo;

  public List<Cliente> listar(){ return repo.findAll(); }
  public Cliente obtener(Integer id){ return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado")); }

  @Transactional public Cliente crear(Cliente c){ return repo.save(c); }

  @Transactional
  public Cliente actualizar(Integer id, Cliente c){
    Cliente a = obtener(id);
    a.setNombre(c.getNombre());
    a.setCorreo(c.getCorreo());
    a.setTelefono(c.getTelefono());
    a.setFechaRegistro(c.getFechaRegistro());
    return repo.save(a);
  }

  @Transactional public void eliminar(Integer id){ repo.deleteById(id); }
}