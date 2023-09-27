import Persistencia.*;
import Utils.BDUtils;
import dominio.Localizacion.Centroide;
import dominio.Localizacion.Localizacion;
import dominio.Localizacion.Municipio;
import dominio.Localizacion.Provincia;
import dominio.Notificacion.MedioCorreo;
import dominio.Notificacion.MedioDeComunicacion;
import dominio.clasesTecnicas.ResultadoCantidadIncidentes;
import dominio.clasesTecnicas.ResultadoTiempoCierre;
import dominio.comunidades.*;
import dominio.entidades.*;
import dominio.establecimientos.Establecimiento;
import dominio.establecimientos.Estacion;
import dominio.establecimientos.Sucursal;
import dominio.rankings.RankingCantidadIncidentes;
import dominio.rankings.RankingTiempoCierre;
import dominio.servicios.*;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    EntityManager em = BDUtils.getEntityManager();
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

/*
    RankingTiempoCierre rankingTiempoCierre = new RankingTiempoCierre(LocalDate.now());
    rankingTiempoCierre.obtenerRankingEntidadesConMayorTiempoDeCierre();
    em.persist(rankingTiempoCierre);
*/
    BDUtils.commit(em);
    em.close();
  }

}