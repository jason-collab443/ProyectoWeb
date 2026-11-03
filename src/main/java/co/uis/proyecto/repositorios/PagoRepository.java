package co.uis.proyecto.repositorios;


import co.uis.proyecto.entidades.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago, Integer> {}