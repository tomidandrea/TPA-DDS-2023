import Persistencia.*;
import Presentacion.GetRankingCIHandler;
import Utils.BDUtils;
import dominio.rankings.RankingCantidadIncidentes;
import dominio.rankings.RankingTiempoCierre;
import io.javalin.Javalin;

import javax.persistence.EntityManager;
import java.time.LocalDate;

public class Main {

  public static void main(String[] args) {

    Javalin app = Javalin.create(javalinConfig -> {
              javalinConfig.plugins.enableCors(cors -> {
                cors.add(it -> it.anyHost());
              });

              javalinConfig.staticFiles.add("/");
            })
            .get("/", ctx -> ctx.result("Hello World"))
            .start(4567);

    app.get("/api/rankingCI", new GetRankingCIHandler());

    /*EntityManager em = BDUtils.getEntityManager();
    BDUtils.comenzarTransaccion(em);
    InstanciasServicios servicios = new InstanciasServicios(em);
    InstanciasIncidentes incidentes = new InstanciasIncidentes(em, servicios);
    InstanciasEstablecimientos establecimientos = new InstanciasEstablecimientos(em, servicios);
    InstanciasEntidades entidades = new InstanciasEntidades(em, establecimientos);
    InstanciasMiembro miembros = new InstanciasMiembro(em);
    InstanciasComunidades comunidades = new InstanciasComunidades(em, incidentes, miembros, establecimientos, servicios);


    RankingCantidadIncidentes rankingCantidadIncidentes = new RankingCantidadIncidentes(LocalDate.now());
    RankingCantidadIncidentes rankingCantidadIncidentes2 = new RankingCantidadIncidentes(LocalDate.now());
    rankingCantidadIncidentes.obtenerRankingEntidadesConMasIncidentes();
    rankingCantidadIncidentes2.obtenerRankingEntidadesConMasIncidentes();

    em.persist(rankingCantidadIncidentes);
    em.persist(rankingCantidadIncidentes2);


    RankingTiempoCierre rankingTiempoCierre = new RankingTiempoCierre(LocalDate.now());
    rankingTiempoCierre.obtenerRankingEntidadesConMayorTiempoDeCierre();
    em.persist(rankingTiempoCierre);

    BDUtils.commit(em);
    em.close();

     */

  }

}