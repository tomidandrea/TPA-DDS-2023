package Persistencia;

import Utils.BDUtils;
import dominio.servicios.*;
import lombok.Getter;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Getter
public class InstanciasServicios {
  private List<Servicio> serviciosEstacionA1 = new ArrayList<>();
  private List<Servicio> serviciosEstacionA2 = new ArrayList<>();
  private List<Servicio> serviciosEstacionB1 = new ArrayList<>();
  private List<Servicio> serviciosEstacionB2 = new ArrayList<>();
  private List<Servicio> serviciosCoto1 = new ArrayList<>();
  private List<Servicio> serviciosDia1 = new ArrayList<>();
  private List<Servicio> serviciosCoto2 = new ArrayList<>();
  private List<Servicio> serviciosDia2 = new ArrayList<>();

  private List<Agrupacion> agrupacionesCoto1 = new ArrayList<>();

  public InstanciasServicios(EntityManager em){
    banio("bañoEstacionA1", TipoDeBanio.HOMBRES, serviciosEstacionA1, em);
    banio("bañoEstacionA2", TipoDeBanio.MUJERES, serviciosEstacionA2, em);
    banio("bañoEstacionB2", TipoDeBanio.MUJERES, serviciosEstacionB1, em);
    banio("bañoEstacionB1", TipoDeBanio.HOMBRES, serviciosEstacionB2, em);

    escaleraMecanica(serviciosEstacionA1, em);
    escaleraMecanica(serviciosEstacionA2, em);
    escaleraMecanica(serviciosEstacionB1, em);
    escaleraMecanica(serviciosEstacionB2, em);

    banio("bañoCoto1H", TipoDeBanio.HOMBRES, serviciosCoto1, em);
    banio("bañoCoto1M", TipoDeBanio.MUJERES, serviciosCoto1, em);
    banio("bañoCoto2H", TipoDeBanio.MUJERES, serviciosCoto2, em);
    banio("bañoCoto2M", TipoDeBanio.HOMBRES, serviciosCoto2, em);
    banio("bañoDia1H", TipoDeBanio.HOMBRES, serviciosDia1, em);
    banio("bañoDia1M", TipoDeBanio.MUJERES, serviciosDia1,em);
    banio("bañoDia2H", TipoDeBanio.MUJERES, serviciosDia2,em);
    banio("bañoDia2M", TipoDeBanio.HOMBRES, serviciosDia2,em);

    agrupacion(agrupacionesCoto1, List.of(serviciosCoto1.get(0), serviciosCoto1.get(1)), em);
  }
    // Creo servicios Linea A
   private void banio(String nombre, TipoDeBanio tipo, List<Servicio> servicios, EntityManager em) {
     Banio banio = new Banio(nombre, tipo);
     em.persist(banio);
     servicios.add(banio);
   }
   private void escaleraMecanica(List<Servicio> servicios, EntityManager em){
     EscaleraMecanica escaleraMecanica = new EscaleraMecanica("Escalera entrada");
     em.persist(escaleraMecanica);
     servicios.add(escaleraMecanica);
  }
  private void agrupacion(List<Agrupacion> agrupaciones, List<Servicio> serviciosAgrupados, EntityManager em){
    Agrupacion agrupacion = new Agrupacion(serviciosAgrupados);
    em.persist(agrupacion);
    agrupaciones.add(agrupacion);
  }

}
