package co.uis.proyecto.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity @Table(name="orden_compra")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class OrdenCompra {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable=false, length=40)
  private String numero;

  @Column(name="proveedor_nombre")
  private String proveedorNombre;

  @Column(nullable=false, length=30)
  private String fecha;

  @Column(nullable=false, precision=12, scale=2)
  private BigDecimal total;
}