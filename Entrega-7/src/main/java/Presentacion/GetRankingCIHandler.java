package Presentacion;

import Presentacion.dto.EntidadDTO;
import Presentacion.dto.RankingCIDTO;
import Presentacion.dto.ResultadoCIDTO;
import Utils.BDUtils;
import com.google.gson.Gson;
import dominio.clasesTecnicas.ResultadoCantidadIncidentes;
import dominio.entidades.Entidad;
import dominio.rankings.RankingCantidadIncidentes;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class GetRankingCIHandler implements Handler {

    private EntityManager em = BDUtils.getEntityManager();
    private RankingCantidadIncidentes ranking;

    public GetRankingCIHandler() {
        List<RankingCantidadIncidentes> resultados = em.createQuery("from RankingCantidadIncidentes").getResultList();
        this.ranking = resultados.get(resultados.size() - 1);
    }

    @Override
    public void handle(@NotNull Context context) throws Exception {
        List<ResultadoCIDTO> resultados = this.mapRankingCIToDTO(ranking).getResultadosDTO();
        context.json(new Gson().toJson(resultados));
    }

    private RankingCIDTO mapRankingCIToDTO(RankingCantidadIncidentes ranking) {
        List<ResultadoCIDTO> resultadosDTO = ranking.getResultados().stream().map(this::mapResultadoCIToDTO).collect(Collectors.toList());

        return new RankingCIDTO(resultadosDTO, ranking.getFecha());
    }

    private ResultadoCIDTO mapResultadoCIToDTO(ResultadoCantidadIncidentes resultadoCI) {
        EntidadDTO entidadDTO = this.mapEntidadToDTO(resultadoCI.getEntidad());

        return new ResultadoCIDTO(entidadDTO, resultadoCI.getCantidadIncidentes());
    }

    private EntidadDTO mapEntidadToDTO(Entidad entidad) {
        return new EntidadDTO(entidad.getId(), entidad.getNombre());
    }

}

