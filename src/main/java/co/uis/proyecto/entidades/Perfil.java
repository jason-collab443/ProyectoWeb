package co.uis.proyecto.entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="perfiles")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Perfil {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String alias;
  private String telefono;
  private String direccion;

  @Column(name="fecha_nacimiento")
  private String fechaNacimiento;
}