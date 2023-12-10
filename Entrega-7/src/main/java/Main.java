import Persistencia.*;
import Presentacion.*;
import Presentacion.PostCargaEntidadesViewHandler;
import Utils.BDUtils;
import dominio.rankings.RankingCantidadIncidentes;
import dominio.rankings.RankingTiempoCierre;
import io.javalin.Javalin;
import io.javalin.config.SizeUnit;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import io.javalin.http.Handler;

public class Main {

  public static void main(String[] args) {

    Javalin app = Javalin.create(javalinConfig -> {
                javalinConfig.plugins.enableCors(cors -> {
                cors.add(it -> it.anyHost());
              });

              javalinConfig.staticFiles.add("/");

                javalinConfig.jetty.multipartConfig.cacheDirectory("/resources"); //where to write files that exceed the in memory limit
                javalinConfig.jetty.multipartConfig.maxFileSize(100, SizeUnit.MB); //the maximum individual file size allowed
                javalinConfig.jetty.multipartConfig.maxInMemoryFileSize(10, SizeUnit.MB); //the maximum file size to handle in memory
                javalinConfig.jetty.multipartConfig.maxTotalRequestSize(1, SizeUnit.GB); //the maximum size of the entire multipart request
            })
            .get("/", ctx -> ctx.result("Hello World"))
            .start(4567);

    //pesado
    app.get("/api/rankingCI", new GetRankingCIHandler());
    app.get("/api/rankingTC", new GetRankingTCHandler());
    app.get("/api/servicios", new GetServiciosHandler());
    app.post("/api/incidentes", new PostIncidenteHandler());
    app.post("/api/login", new LoginHandler());
    app.post("/api/logout", new LogoutHandler());
    app.get("/api/{id_sesion}/incidentes", new GetIncidentesPorEstadoHandler());

    //liviano
    app.get("/rankingCI", new RankingCIViewHandler());
    app.get("/rankingTC", new RankingTCViewHandler());
    app.get("/incidentes", new GetIncidentesViewHandler());
    app.post("/incidentes", new PostIncidenteViewHandler());
    app.get("/cargaCSV", new GetCargaEntidadesViewHandler());
    app.post("/cargaCSV", new PostCargaEntidadesViewHandler());
    app.get("/comunidades", new AdministracionComunidadesViewHandler());
    app.get("/comunidades/{id}", new AdministracionUsuariosViewHandler());


    /*EntityManager em = BDUtils.getEntityManager();
    BDUtils.comenzarTransaccion(em);
    InstanciasServicios servicios = new InstanciasServicios(em);
    InstanciasIncidentes incidentes = new InstanciasIncidentes(em, servicios);
    InstanciasEstablecimientos establecimientos = new InstanciasEstablecimientos(em, servicios);
    InstanciasEntidades entidades = new InstanciasEntidades(em, establecimientos);
    InstanciasMiembro miembros = new InstanciasMiembro(em);
    InstanciasComunidades comunidades = new InstanciasComunidades(em, incidentes, miembros, establecimientos, servicios);
    InstanciasEntidadesOrganismos entidadesOrganismos = new InstanciasEntidadesOrganismos(em, establecimientos);
    InstanciasUsuarios usuarios = new InstanciasUsuarios(em, entidadesOrganismos);


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