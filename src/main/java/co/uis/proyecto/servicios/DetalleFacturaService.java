package co.uis.proyecto.servicios;

import co.uis.proyecto.entidades.DetalleFactura;
import co.uis.proyecto.repositorios.DetalleFacturaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor
public class DetalleFacturaService {
  private final DetalleFacturaRepository repo;

  public List<DetalleFactura> listar( ){ return repo.findAll(); }
  public DetalleFactura obtener(Integer id){ return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Detalle de factura no encontrado")); }

  @Transactional public DetalleFactura crear(DetalleFactura d){ return repo.save(d); }

  @Transactional
  public DetalleFactura actualizar(Integer id, DetalleFactura d){
    DetalleFactura a = obtener(id);
    a.setFacturaNumero(d.getFacturaNumero());
    a.setProductoNombre(d.getProductoNombre());
    a.setCantidad(d.getCantidad());
    a.setPrecioUnitario(d.getPrecioUnitario());
    return repo.save(a);
  }

  @Transactional public void eliminar(Integer id){ repo.deleteById(id); }
}