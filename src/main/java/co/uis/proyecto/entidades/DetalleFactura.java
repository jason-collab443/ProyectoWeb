package co.uis.proyecto.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity @Table(name="detalle_factura")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class DetalleFactura {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name="factura_numero", length=40)
  private String facturaNumero;

  @Column(name="producto_nombre", length=150)
  private String productoNombre;

  @Column(nullable=false)
  private Integer cantidad;

  @Column(name="precio_unitario", nullable=false, precision=12, scale=2)
  private BigDecimal precioUnitario;
}