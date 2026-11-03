package co.uis.proyecto.servicios;

import co.uis.proyecto.entidades.Sesion;
import co.uis.proyecto.repositorios.SesionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor
public class SesionService {
  private final SesionRepository repo;

  public List<Sesion> listar(){ return repo.findAll(); }
  public Sesion obtener(Integer id){ return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Sesión no encontrada")); }

  @Transactional public Sesion crear(Sesion s){ return repo.save(s); }

  @Transactional
  public Sesion actualizar(Integer id, Sesion s){
    Sesion a = obtener(id);
    a.setUsuario(s.getUsuario());
    a.setToken(s.getToken());
    a.setIp(s.getIp());
    a.setInicio(s.getInicio());
    return repo.save(a);
  }

  @Transactional public void eliminar(Integer id){ repo.deleteById(id); }
}