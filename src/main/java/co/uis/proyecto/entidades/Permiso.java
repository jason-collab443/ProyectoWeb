package co.uis.proyecto.entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="permisos")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Permiso {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable=false, length=120)
  private String recurso;

  @Column(nullable=false, length=60)
  private String accion;

  @Column(nullable=false)
  private Boolean permitido;

  private String notas;
}