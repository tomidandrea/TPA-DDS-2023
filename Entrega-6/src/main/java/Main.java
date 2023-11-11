import Presentacion.GetServiciosHandler;
import Presentacion.GetRankingCIHandler;
import Presentacion.GetRankingTCHandler;
import Presentacion.PostIncidenteHandler;
import io.javalin.Javalin;

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

    //app.get("/api/rankingCI", new GetRankingCIHandler());
    //app.get("/api/rankingTC", new GetRankingTCHandler());
    app.get("/api/servicios", new GetServiciosHandler());
    app.post("/api/incidentes", new PostIncidenteHandler());

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