package co.uis.proyecto.repositorios;

import co.uis.proyecto.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {}