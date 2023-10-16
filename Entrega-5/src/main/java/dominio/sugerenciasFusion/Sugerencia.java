package dominio.sugerenciasFusion;

import dominio.comunidades.Comunidad;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
public class Sugerencia {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "sugerencia_id")
  private int id;
  @ManyToOne(cascade = CascadeType.REMOVE)
  @JoinColumn(name = "comunidad1_id")
  private Comunidad comunidad1;
  @ManyToOne(cascade = CascadeType.REMOVE)
  @JoinColumn(name = "comunidad2_id")
  private Comunidad comunidad2;
  private LocalDate fecha;

  public Sugerencia(){ }

  public Sugerencia(Comunidad comunidad1, Comunidad comunidad2, LocalDate fecha) {
    this.comunidad1 = comunidad1;
    this.comunidad2 = comunidad2;
    this.fecha = fecha;
  }
}
