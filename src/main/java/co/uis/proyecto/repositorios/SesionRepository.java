package co.uis.proyecto.repositorios;

import co.uis.proyecto.entidades.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SesionRepository extends JpaRepository<Sesion, Integer> {}