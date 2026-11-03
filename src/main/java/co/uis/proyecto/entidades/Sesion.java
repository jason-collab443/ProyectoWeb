package co.uis.proyecto.entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="sesiones")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Sesion {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable=false, length=100)
  private String usuario;

  @Column(nullable=false, length=255)
  private String token;

  private String ip;

  @Column(nullable=false, length=30)
  private String inicio;
}