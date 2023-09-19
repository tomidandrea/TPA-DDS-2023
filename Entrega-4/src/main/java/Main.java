import Utils.BDUtils;
import dominio.Localizacion.Centroide;
import dominio.Localizacion.Localizacion;
import dominio.Localizacion.Municipio;
import dominio.Localizacion.Provincia;
import dominio.Notificacion.MedioCorreo;
import dominio.Notificacion.MedioDeComunicacion;
import dominio.clasesTecnicas.ResultadoCantidadIncidentes;
import dominio.clasesTecnicas.ResultadoTiempoCierre;
import dominio.comunidades.Incidente;
import dominio.comunidades.Miembro;
import dominio.comunidades.Notificador;
import dominio.comunidades.TipoMiembro;
import dominio.entidades.*;
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
    Centroide centroide = new Centroide(12.0, 34.0);
    Provincia provincia = new Provincia("Buenos Aires", centroide);
    em.persist(provincia);
    Banio banio = new Banio("Baño primer piso", TipoDeBanio.HOMBRES);
    em.persist(banio);
    EscaleraMecanica escaleraMecanica = new EscaleraMecanica(provincia, "Escalera bajada desde calle", TramoMedioElevacion.CALLE_A_ACCESO);
    em.persist(escaleraMecanica);
    LocalDateTime horarioApertura = LocalDateTime.now();
    Incidente incidente = new Incidente(banio, horarioApertura, "Canilla pierde agua");
    em.persist(incidente);

    List<LocalTime> horarios = new ArrayList<>();
    horarios.add(LocalTime.now());
    horarios.add(LocalTime.now().plusHours(5));
    Notificador notificador = new Notificador(horarios);
    //em.persist(notificador);

    Miembro miembro = new Miembro("Pepe", "Argento", "pepe@argento", provincia,
        TipoMiembro.AFECTADO, new MedioCorreo(), "1234", notificador,
        "pepeArg", "23campeonDelMundo#3");
    em.persist(miembro);
    ServicioTransporte subteA = subteA(em, provincia);
    Organizacion coto = coto(em, provincia);

    RepoEntidades repoEntidades = RepoEntidades.getInstance();
    repoEntidades.agregar(subteA);
    repoEntidades.agregar(coto);

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
  }

  private static Organizacion coto(EntityManager em, Provincia provincia) {
    Banio banio1 = new Banio("Baño coto sucursal 1", TipoDeBanio.MUJERES);
    em.persist(banio1);
    Banio banio2 = new Banio("Baño coto sucursal 2", TipoDeBanio.MUJERES);
    em.persist(banio2);
    Incidente incidente1 = new Incidente(
        banio1,
        restarDiasHorasMinutos(6, 16, 0),
        restarDiasHorasMinutos(1, 16, 30));
    em.persist(incidente1);
    Incidente incidente2 = new Incidente(
        banio2,
        restarDiasHorasMinutos(6, 16, 0),
        restarDiasHorasMinutos(6, 8, 30));
    em.persist(incidente2);
    List<Servicio> servicios1 = new ArrayList<>();
    servicios1.add(banio1);
    List<Servicio> servicios2 = new ArrayList<>();
    servicios2.add(banio2);
    Sucursal sucursalCoto1 = new Sucursal(servicios1);
    em.persist(sucursalCoto1);
    Sucursal sucursalCoto2 = new Sucursal(servicios2);
    em.persist(sucursalCoto2);
    List<Sucursal> sucursalesCoto = new ArrayList<>();
    sucursalesCoto.add(sucursalCoto1);
    sucursalesCoto.add(sucursalCoto2);
    Organizacion coto = new Organizacion("SUPERMECADO_COTO", sucursalesCoto);
    em.persist(coto);
    return coto;
  }

  private static ServicioTransporte subteA(EntityManager em, Provincia provincia){
    Banio banio = new Banio("Baño subteA", TipoDeBanio.MUJERES);
    em.persist(banio);
    Incidente incidente1 = new Incidente(
        banio,
        restarDiasHorasMinutos(6, 16, 0),
        restarDiasHorasMinutos(5, 16, 30));
    em.persist(incidente1);
    EscaleraMecanica escaleraMecanica = new EscaleraMecanica(provincia, "Escalera bajada desde calle subteA", TramoMedioElevacion.CALLE_A_ACCESO);
    em.persist(escaleraMecanica);
    List<Servicio> serviciosEstacionA1 = new ArrayList<>();
    serviciosEstacionA1.add(banio);
    serviciosEstacionA1.add(escaleraMecanica);
    Estacion estacion1 = new Estacion(serviciosEstacionA1);
    em.persist(estacion1);
    List<Servicio> serviciosEstacionA2 = new ArrayList<>();
    Estacion estacion2 = new Estacion(serviciosEstacionA2);
    em.persist(estacion2);
    List<Estacion> estacionesIda = new ArrayList<>();
    estacionesIda.add(estacion1);
    estacionesIda.add(estacion2);
    Linea lineaAIda = new Linea("A - Ida", estacionesIda);
    em.persist(lineaAIda);
    List<Estacion> estacionesVuelta = new ArrayList<>();
    estacionesVuelta.add(estacion2);
    estacionesVuelta.add(estacion1);
    Linea lineaAVuelta = new Linea("A - Vuelta", estacionesVuelta);
    em.persist(lineaAVuelta);
    ServicioTransporte subteA = new ServicioTransporte(MedioDeTransporte.SUBTE, "subteA", lineaAIda,lineaAVuelta);
    em.persist(subteA);
    return subteA;
  }

  public static LocalDateTime restarDiasHorasMinutos(int dias, int horas, int minutos) {
    LocalDateTime fechaActual = LocalDateTime.now();
    LocalDateTime fechaResultante = fechaActual.minusDays(dias)
        .minusHours(horas)
        .minusMinutes(minutos);
    return fechaResultante;
  }

}