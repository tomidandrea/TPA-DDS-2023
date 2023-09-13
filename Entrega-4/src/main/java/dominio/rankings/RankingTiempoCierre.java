package dominio.rankings;

import dominio.clasesTecnicas.ResultadoCantidadIncidentes;
import dominio.clasesTecnicas.ResultadoTiempoCierre;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Entity
public class RankingTiempoCierre {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ranking_id")
  private int id;
  private LocalDate fecha;
  @ElementCollection
  @CollectionTable(name = "resultados_ranking_tc", joinColumns = @JoinColumn(name = "ranking_id"))
  @OrderColumn(name = "posicion", columnDefinition = "int default 1")
  private List<ResultadoTiempoCierre> resultados;

  public RankingTiempoCierre() { }

  public RankingTiempoCierre(LocalDate fecha, List<ResultadoTiempoCierre> resultados) {
    this.fecha = fecha;
    this.resultados = resultados;
  }
}
