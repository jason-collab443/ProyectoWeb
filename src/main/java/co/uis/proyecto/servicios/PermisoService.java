package co.uis.proyecto.servicios;

import co.uis.proyecto.entidades.Permiso;
import co.uis.proyecto.repositorios.PermisoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor
public class PermisoService {
  private final PermisoRepository repo;

  public List<Permiso> listar( ){ return repo.findAll(); }
  public Permiso obtener(Integer id){ return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Permiso no encontrado")); }

  @Transactional public Permiso crear(Permiso p){ return repo.save(p); }

  @Transactional
  public Permiso actualizar(Integer id, Permiso p){
    Permiso a = obtener(id);
    a.setRecurso(p.getRecurso());
    a.setAccion(p.getAccion());
    a.setPermitido(p.getPermitido());
    a.setNotas(p.getNotas());
    return repo.save(a);
  }

  @Transactional public void eliminar(Integer id){ repo.deleteById(id); }
}