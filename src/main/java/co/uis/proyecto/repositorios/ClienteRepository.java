package co.uis.proyecto.repositorios;

import co.uis.proyecto.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {}