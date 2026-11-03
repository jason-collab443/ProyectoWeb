package co.uis.proyecto.entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="roles")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Rol {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable=false, length=60)
  private String nombre;

  private String descripcion;

  @Column(name="nivel_acceso", nullable=false)
  private Integer nivelAcceso;

  @Column(nullable=false)
  private Boolean activo;
}