package co.uis.proyecto.servicios;

import co.uis.proyecto.entidades.OrdenCompra;
import co.uis.proyecto.repositorios.OrdenCompraRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor
public class OrdenCompraService {
  private final OrdenCompraRepository repo;

  public List<OrdenCompra> listar( ){ return repo.findAll(); }
  public OrdenCompra obtener(Integer id){ return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Orden de compra no encontrada")); }

  @Transactional public OrdenCompra crear(OrdenCompra o){ return repo.save(o); }

  @Transactional
  public OrdenCompra actualizar(Integer id, OrdenCompra o){
    OrdenCompra a = obtener(id);
    a.setNumero(o.getNumero());
    a.setProveedorNombre(o.getProveedorNombre());
    a.setFecha(o.getFecha());
    a.setTotal(o.getTotal());
    return repo.save(a);
  }

  @Transactional public void eliminar(Integer id){ repo.deleteById(id); }
}