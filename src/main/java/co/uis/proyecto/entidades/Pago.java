package co.uis.proyecto.entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="pagos")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Pago {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable=false, length=60)
  private String referencia;

  @Column(name="factura_numero", length=40)
  private String facturaNumero;

  @Column(nullable=false, length=40)
  private String metodo;

  @Column(nullable=false, length=30)
  private String fecha;
}