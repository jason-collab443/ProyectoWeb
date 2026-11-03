package co.uis.proyecto.entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="clientes")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Cliente {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable=false, length=150)
  private String nombre;

  private String correo;
  private String telefono;

  @Column(name="fecha_registro", nullable=false, length=30)
  private String fechaRegistro;
}