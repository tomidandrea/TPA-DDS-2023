package Presentacion;

import Presentacion.dto.EntidadDTO;
import Presentacion.dto.ResultadoTCDTO;
import dominio.clasesTecnicas.ResultadoTiempoCierre;
import dominio.entidades.Entidad;
import dominio.rankings.RepoRankings;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class RankingTCViewHandler implements Handler {

    @Override
    public void handle(Context context) throws Exception {
        List<ResultadoTCDTO> resultadosRanking = RepoRankings.getInstance().obtenerRankingTCActual().getResultados().stream().map(this::mapResultadoTCToDTO).collect(Collectors.toList());;
        Integer posicion = 1;
        for(ResultadoTCDTO resultado : resultadosRanking) {
            resultado.setPosicion(posicion);
            posicion++;
        }
        var model = new HashMap<String, Object>();
        model.put("resultadosRanking", resultadosRanking);
        context.render("templates/vistasRankings/RankingTC.mustache", model);
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

