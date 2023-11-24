package dominio.rankings;

import dominio.clasesTecnicas.ResultadoCantidadIncidentes;
import dominio.clasesTecnicas.ResultadoTiempoCierre;
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
@DiscriminatorValue("Tiempo cierre")
public class RankingTiempoCierre extends Ranking{
  @ElementCollection
  @CollectionTable(name = "resultados_ranking_tc", joinColumns = @JoinColumn(name = "ranking_id"))
  @OrderColumn(name = "posicion", columnDefinition = "int default 1")
  private List<ResultadoTiempoCierre> resultados;

  public RankingTiempoCierre(){}

  public RankingTiempoCierre(LocalDate fecha) {
    super(fecha);
  }

  public void obtenerRankingEntidadesConMayorTiempoDeCierre(){
    List<Entidad> entidades = RepoEntidades.getInstance().obtenerEntidades();
    this.resultados = entidades.stream()
            .map(e->new ResultadoTiempoCierre(e, e.calcularPromedioTiempoCierre()))
            .collect(Collectors.toList());
    resultados.sort((r2, r1) -> r2.compararTiempo(r1));
    Collections.reverse(resultados);
    resultados.forEach(r-> System.out.println(r.getEntidad().getNombre() + " "+r.getTiempoDeCierre()));
  }
}
