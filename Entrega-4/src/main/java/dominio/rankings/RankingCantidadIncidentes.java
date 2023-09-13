package dominio.rankings;

import dominio.clasesTecnicas.ResultadoCantidadIncidentes;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Entity
public class RankingCantidadIncidentes {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ranking_id")
  private int id;
  private LocalDate fecha;
  @ElementCollection
  @CollectionTable(name = "resultados_ranking_ci", joinColumns = @JoinColumn(name = "ranking_id"))
  @OrderColumn(name = "posicion", columnDefinition = "int default 1")
  private List<ResultadoCantidadIncidentes> resultados;

  public RankingCantidadIncidentes() { }

  public RankingCantidadIncidentes(LocalDate fecha, List<ResultadoCantidadIncidentes> resultados) {
    this.fecha = fecha;
    this.resultados = resultados;
  }
}
