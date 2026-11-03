package co.uis.proyecto.servicios;

import co.uis.proyecto.entidades.Rol;
import co.uis.proyecto.repositorios.RolRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor
public class RolService {
  private final RolRepository repo;

  public List<Rol> listar(){ return repo.findAll(); }
  public Rol obtener(Integer id){ return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Rol no encontrado")); }

  @Transactional public Rol crear(Rol r){ return repo.save(r); }

  @Transactional
  public Rol actualizar(Integer id, Rol r){
    Rol a = obtener(id);
    a.setNombre(r.getNombre());
    a.setDescripcion(r.getDescripcion());
    a.setNivelAcceso(r.getNivelAcceso());
    a.setActivo(r.getActivo());
    return repo.save(a);
  }

  @Transactional public void eliminar(Integer id){ repo.deleteById(id); }
}