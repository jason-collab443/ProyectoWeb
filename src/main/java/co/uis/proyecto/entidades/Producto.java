package co.uis.proyecto.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity @Table(name="productos")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Producto {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable=false, length=120)
  private String nombre;

  private String descripcion;

  @Column(nullable=false, precision=12, scale=2)
  private BigDecimal precio;

  @Column(nullable=false)
  private Integer stock;
}