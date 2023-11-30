package Presentacion;

import Presentacion.dto.EntidadDTO;
import Presentacion.dto.RankingTCDTO;
import Presentacion.dto.ResultadoCIDTO;
import Presentacion.dto.ResultadoTCDTO;
import Utils.BDUtils;
import com.google.gson.Gson;
import dominio.clasesTecnicas.ResultadoTiempoCierre;
import dominio.entidades.Entidad;
import dominio.rankings.RankingTiempoCierre;
import dominio.rankings.RepoRankings;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class GetRankingTCHandler implements Handler {
    private RankingTiempoCierre ranking;

    public GetRankingTCHandler() {
        this.ranking = RepoRankings.getInstance().obtenerRankingTCActual();
    }

    @Override
    public void handle(@NotNull Context context) throws Exception {
        List<ResultadoTCDTO> resultados = this.mapRankingTCToDTO(ranking).getResultadosDTO();
        context.json(new Gson().toJson(resultados));
    }

    private RankingTCDTO mapRankingTCToDTO(RankingTiempoCierre ranking) {
        List<ResultadoTCDTO> resultadosDTO = ranking.getResultados().stream().map(this::mapResultadoTCToDTO).collect(Collectors.toList());
        Integer posicion = 1;
        for(ResultadoTCDTO resultado : resultadosDTO) {
            resultado.setPosicion(posicion);
            posicion++;
        }
        return new RankingTCDTO(resultadosDTO, ranking.getFecha());
    }

    private ResultadoTCDTO mapResultadoTCToDTO(ResultadoTiempoCierre resultadoTC) {
        EntidadDTO entidadDTO = this.mapEntidadToDTO(resultadoTC.getEntidad());
        long tiempoAsSeconds = (int) resultadoTC.getTiempoDeCierre().toSeconds();
        return new ResultadoTCDTO(entidadDTO, tiempoAsSeconds);
    }

    private EntidadDTO mapEntidadToDTO(Entidad entidad) {
        return new EntidadDTO(entidad.getId(), entidad.getNombre());
    }

}
