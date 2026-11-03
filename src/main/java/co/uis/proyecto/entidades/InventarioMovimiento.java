package co.uis.proyecto.entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="inventario_movimientos")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class InventarioMovimiento {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name="producto_codigo", length=40)
  private String productoCodigo;

  @Column(nullable=false, length=20)
  private String tipo; // ENTRADA/SALIDA

  @Column(nullable=false)
  private Integer cantidad;

  @Column(nullable=false, length=30)
  private String fecha;
}