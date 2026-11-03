package co.uis.proyecto.servicios;

import co.uis.proyecto.entidades.InventarioMovimiento;
import co.uis.proyecto.repositorios.InventarioMovimientoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor
public class InventarioMovimientoService {
  private final InventarioMovimientoRepository repo;

  public List<InventarioMovimiento> listar( ){ return repo.findAll(); }
  public InventarioMovimiento obtener(Integer id){ return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Movimiento no encontrado")); }

  @Transactional public InventarioMovimiento crear(InventarioMovimiento m){ return repo.save(m); }

  @Transactional
  public InventarioMovimiento actualizar(Integer id, InventarioMovimiento m){
    InventarioMovimiento a = obtener(id);
    a.setProductoCodigo(m.getProductoCodigo());
    a.setTipo(m.getTipo());
    a.setCantidad(m.getCantidad());
    a.setFecha(m.getFecha());
    return repo.save(a);
  }

  @Transactional public void eliminar(Integer id){ repo.deleteById(id); }
}