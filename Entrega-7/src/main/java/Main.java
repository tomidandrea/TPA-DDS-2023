import Persistencia.*;
import Presentacion.*;
import Utils.BDUtils;
import dominio.rankings.RankingCantidadIncidentes;
import dominio.rankings.RankingTiempoCierre;
import io.javalin.Javalin;
import io.javalin.openapi.plugin.OpenApiConfiguration;
import io.javalin.openapi.plugin.OpenApiPlugin;

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
    //pesado
    app.get("/api/rankingCI", new GetRankingCIHandler());
    app.get("/api/rankingTC", new GetRankingTCHandler());
    app.get("/api/servicios", new GetServiciosHandler());
    app.post("/api/incidentes", new PostIncidenteHandler());
    app.post("/api/login", new LoginHandler());
    app.get("/api/incidentes", new GetIncidentesPorEstadoHandler());

    //liviano
    app.get("/rankingCI", new RankingCIViewHandler());
    app.get("/rankingTC", new RankingTCViewHandler());

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
    em.close();*/

  }

}