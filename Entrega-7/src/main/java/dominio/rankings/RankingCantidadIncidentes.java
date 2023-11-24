package dominio.rankings;

import dominio.clasesTecnicas.ResultadoCantidadIncidentes;
import dominio.entidades.Entidad;
import dominio.entidades.RepoEntidades;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Entity
@DiscriminatorValue("Cantidad incidentes")
public class RankingCantidadIncidentes extends Ranking {
  @ElementCollection
  @CollectionTable(name = "resultados_ranking_ci", joinColumns = @JoinColumn(name = "ranking_id"))
  @OrderColumn(name = "posicion", columnDefinition = "int default 1")
  private List<ResultadoCantidadIncidentes> resultados;

  public RankingCantidadIncidentes(){}

  public RankingCantidadIncidentes(LocalDate fecha) {
    super(fecha);
  }

  public void obtenerRankingEntidadesConMasIncidentes(){
    List<Entidad> entidades = RepoEntidades.getInstance().obtenerEntidades();
    this.resultados = entidades.stream()
            .map(e->new ResultadoCantidadIncidentes(e, e.cantidadIncidentes()))
            .collect(Collectors.toList());
    resultados.sort((r1, r2) -> r1.compararTiempo(r2));
    Collections.reverse(resultados);
  }
}
