package dominio.rankings;

import Utils.BDUtils;
import dominio.clasesTecnicas.ResultadoTiempoCierre;
import dominio.servicios.RepoServicios;
import dominio.servicios.Servicio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;
import java.util.List;

@Getter
@Setter
public class RepoRankings {

    private List<Ranking> rankings;
    private static RepoRankings instance = null;
    private EntityManager em = BDUtils.getEntityManager();

    public static RepoRankings getInstance() {
        if(instance == null){
            instance = new RepoRankings();
        }
        return instance;
    }

    public RankingTiempoCierre obtenerRankingTCActual() {
        List<RankingTiempoCierre> resultados = em.createQuery("from RankingTiempoCierre").getResultList();
        return resultados.get(resultados.size() - 1);
    }

    public RankingCantidadIncidentes obtenerRankingCIActual() {
        List<RankingCantidadIncidentes> resultados = em.createQuery("from RankingCantidadIncidentes").getResultList();
        return resultados.get(resultados.size() - 1);
    }
}
