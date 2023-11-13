package Presentacion;

import Presentacion.dto.EntidadDTO;
import Presentacion.dto.RankingTCDTO;
import Presentacion.dto.ResultadoTCDTO;
import Utils.BDUtils;
import com.google.gson.Gson;
import dominio.clasesTecnicas.ResultadoTiempoCierre;
import dominio.entidades.Entidad;
import dominio.rankings.RankingTiempoCierre;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class GetRankingTCHandler implements Handler {
    private EntityManager em = BDUtils.getEntityManager();
    private RankingTiempoCierre ranking;

    public GetRankingTCHandler() {
        List<RankingTiempoCierre> resultados = em.createQuery("from RankingTiempoCierre").getResultList();
        this.ranking = resultados.get(resultados.size() - 1);
    }

    @Override
    public void handle(@NotNull Context context) throws Exception {
        List<ResultadoTCDTO> resultados = this.mapRankingTCToDTO(ranking).getResultadosDTO();
        context.json(new Gson().toJson(resultados));
    }

    private RankingTCDTO mapRankingTCToDTO(RankingTiempoCierre ranking) {
        List<ResultadoTCDTO> resultadosDTO = ranking.getResultados().stream().map(this::mapResultadoTCToDTO).collect(Collectors.toList());

        return new RankingTCDTO(resultadosDTO, ranking.getFecha());
    }

    private ResultadoTCDTO mapResultadoTCToDTO(ResultadoTiempoCierre resultadoTC) {
        EntidadDTO entidadDTO = this.mapEntidadToDTO(resultadoTC.getEntidad());

        return new ResultadoTCDTO(entidadDTO, resultadoTC.getTiempoDeCierre());
    }

    private EntidadDTO mapEntidadToDTO(Entidad entidad) {
        return new EntidadDTO(entidad.getId(), entidad.getNombre());
    }

}
