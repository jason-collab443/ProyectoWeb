package co.uis.proyecto.repositorios;

import co.uis.proyecto.entidades.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {}