package co.uis.proyecto.repositorios;

import co.uis.proyecto.entidades.DetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Integer> {}