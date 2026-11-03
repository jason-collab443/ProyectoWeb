package co.uis.proyecto.repositorios;


import co.uis.proyecto.entidades.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Integer> {}