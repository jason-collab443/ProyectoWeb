package co.uis.proyecto.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity @Table(name="facturas")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Factura {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable=false, length=40)
  private String numero;

  @Column(name="cliente_nombre")
  private String clienteNombre;

  @Column(nullable=false, length=30)
  private String fecha;

  @Column(nullable=false, precision=12, scale=2)
  private BigDecimal total;
}