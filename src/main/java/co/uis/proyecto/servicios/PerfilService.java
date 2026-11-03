package co.uis.proyecto.servicios;

import co.uis.proyecto.entidades.Perfil;
import co.uis.proyecto.repositorios.PerfilRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor
public class PerfilService {
  private final PerfilRepository repo;

  public List<Perfil> listar( ){ return repo.findAll(); }

  public Perfil obtener(Integer id){
    return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Perfil no encontrado"));
  }

  @Transactional
  public Perfil crear(Perfil p){ return repo.save(p); }

  @Transactional
  public Perfil actualizar(Integer id, Perfil p){
    Perfil a = obtener(id);
    a.setAlias(p.getAlias());
    a.setTelefono(p.getTelefono());
    a.setDireccion(p.getDireccion());
    a.setFechaNacimiento(p.getFechaNacimiento());
    return repo.save(a);
  }

  @Transactional
  public void eliminar(Integer id){ repo.deleteById(id); }
}