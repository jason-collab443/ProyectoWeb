package co.uis.proyecto.repositorios;

import co.uis.proyecto.entidades.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Integer> {}