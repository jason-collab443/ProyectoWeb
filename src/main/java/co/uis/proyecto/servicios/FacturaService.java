package co.uis.proyecto.servicios;

import co.uis.proyecto.entidades.Factura;
import co.uis.proyecto.repositorios.FacturaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor
public class FacturaService {
  private final FacturaRepository repo;

  public List<Factura> listar( ){ return repo.findAll(); }
  public Factura obtener(Integer id){ return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Factura no encontrada")); }

  @Transactional public Factura crear(Factura f){ return repo.save(f); }

  @Transactional
  public Factura actualizar(Integer id, Factura f){
    Factura a = obtener(id);
    a.setNumero(f.getNumero());
    a.setClienteNombre(f.getClienteNombre());
    a.setFecha(f.getFecha());
    a.setTotal(f.getTotal());
    return repo.save(a);
  }

  @Transactional public void eliminar(Integer id){ repo.deleteById(id); }
}