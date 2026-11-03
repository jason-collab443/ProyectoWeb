package co.uis.proyecto.entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="proveedores")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Proveedor {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable=false, length=150)
  private String nombre;

  private String contacto;
  private String telefono;
  private String ciudad;
}