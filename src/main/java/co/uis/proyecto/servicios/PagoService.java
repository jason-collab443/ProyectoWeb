package co.uis.proyecto.servicios;

import co.uis.proyecto.entidades.Pago;
import co.uis.proyecto.repositorios.PagoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor
public class PagoService {
  private final PagoRepository repo;

  public List<Pago> listar( ){ return repo.findAll(); }
  public Pago obtener(Integer id){ return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Pago no encontrado")); }

  @Transactional public Pago crear(Pago p){ return repo.save(p); }

  @Transactional
  public Pago actualizar(Integer id, Pago p){
    Pago a = obtener(id);
    a.setReferencia(p.getReferencia());
    a.setFacturaNumero(p.getFacturaNumero());
    a.setMetodo(p.getMetodo());
    a.setFecha(p.getFecha());
    return repo.save(a);
  }

  @Transactional public void eliminar(Integer id){ repo.deleteById(id); }
}