package Persistencia;

import Utils.BDUtils;
import dominio.entidades.*;
import dominio.establecimientos.Estacion;
import dominio.establecimientos.Sucursal;
import lombok.Getter;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Getter
public class InstanciasEntidades {
  private ServicioTransporte subteA;
  private ServicioTransporte subteB;
  private Organizacion coto;
  private Organizacion dia;
  private ServicioTransporte ecobike;
  private ServicioTransporte ecobici;
  private ServicioTransporte colectivo110;
  private ServicioTransporte colectivo111;

  public InstanciasEntidades(EntityManager em, InstanciasEstablecimientos establecimientos){
    Linea lineaAIda = lineaAIda(establecimientos);
    em.persist(lineaAIda);
    Linea lineaAVuelta = lineaAVuelta(establecimientos);
    em.persist(lineaAVuelta);
    Linea lineaBIda = lineaBIda(establecimientos);
    em.persist(lineaBIda);
    Linea lineaBVuelta = lineaBVuelta(establecimientos);
    em.persist(lineaBVuelta);

    List<Sucursal> sucursalesCoto = sucursalesCoto(establecimientos);
    List<Sucursal> sucursalesDia = sucursalesDia(establecimientos);

    this.subteA = new ServicioTransporte(MedioDeTransporte.SUBTE, "subteA", lineaAIda,lineaAVuelta);
    this.subteB = new ServicioTransporte(MedioDeTransporte.SUBTE, "subteb", lineaBIda,lineaBVuelta);
    this.coto = new Organizacion("SUPERMECADO_COTO", sucursalesCoto);
    this.dia = new Organizacion("SUPERMERCADO_DIA", sucursalesDia);
    this.ecobike = new ServicioTransporte(MedioDeTransporte.ECOBICI, "Ecobike Itau", lineaAIda,lineaBVuelta);
    this.ecobici = new ServicioTransporte(MedioDeTransporte.ECOBICI, "BA Ecobici", lineaAIda,lineaAVuelta);
    this.colectivo110 = new ServicioTransporte(MedioDeTransporte.COLECTIVO, "colectivo 110", lineaBIda,lineaBVuelta);
    this.colectivo111 = new ServicioTransporte(MedioDeTransporte.COLECTIVO, "colectivo 111", lineaBIda,lineaAVuelta);

    RepoEntidades repoEntidades = RepoEntidades.getInstance();
    repoEntidades.agregar(coto);
    repoEntidades.agregar(dia);
    repoEntidades.agregar(subteA);
    repoEntidades.agregar(subteB);
    repoEntidades.agregar(ecobike);
    repoEntidades.agregar(ecobici);
    repoEntidades.agregar(colectivo110);
    repoEntidades.agregar(colectivo111);

    em.persist(coto);
    em.persist(dia);
    em.persist(subteA);
    em.persist(subteB);
    em.persist(ecobike);
    em.persist(ecobici);
    em.persist(colectivo110);
    em.persist(colectivo111);
  }

  private Linea lineaAIda(InstanciasEstablecimientos establecimientos){
    List<Estacion> estaciones = new ArrayList<>();
    estaciones.add(establecimientos.getEstacionA1());
    estaciones.add(establecimientos.getEstacionA2());
    return new Linea("A - Ida", estaciones);
  }

  private Linea lineaAVuelta(InstanciasEstablecimientos establecimientos){
    List<Estacion> estaciones = new ArrayList<>();
    estaciones.add(establecimientos.getEstacionA2());
    estaciones.add(establecimientos.getEstacionA1());
    return new Linea("A - Vuelta", estaciones);
  }

  private Linea lineaBIda(InstanciasEstablecimientos establecimientos){
    List<Estacion> estaciones = new ArrayList<>();
    estaciones.add(establecimientos.getEstacionB1());
    estaciones.add(establecimientos.getEstacionB2());
    return new Linea("B - Ida", estaciones);
  }

  private Linea lineaBVuelta(InstanciasEstablecimientos establecimientos){
    List<Estacion> estaciones = new ArrayList<>();
    estaciones.add(establecimientos.getEstacionB2());
    estaciones.add(establecimientos.getEstacionB1());
    return new Linea("B - Vuelta", estaciones);
  }

  private List<Sucursal> sucursalesCoto(InstanciasEstablecimientos establecimientos){
    List<Sucursal> sucursalesCoto = new ArrayList<>();
    sucursalesCoto.add(establecimientos.getSucursalCoto1());
    sucursalesCoto.add(establecimientos.getSucursalCoto2());
    return sucursalesCoto;
  }

  private List<Sucursal> sucursalesDia(InstanciasEstablecimientos establecimientos){
    List<Sucursal> sucursalesDia = new ArrayList<>();
    sucursalesDia.add(establecimientos.getSucursalDia1());
    sucursalesDia.add(establecimientos.getSucursalDia2());
    return sucursalesDia;
  }

}
