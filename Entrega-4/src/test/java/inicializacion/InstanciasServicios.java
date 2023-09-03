package inicializacion;

import dominio.servicios.Banio;
import dominio.servicios.EscaleraMecanica;
import dominio.servicios.Servicio;
import dominio.servicios.TipoDeBanio;
import lombok.Getter;

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

  public InstanciasServicios(){
    banio("bañoEstacionA1", TipoDeBanio.HOMBRES, serviciosEstacionA1);
    banio("bañoEstacionA2", TipoDeBanio.MUJERES, serviciosEstacionA2);
    banio("bañoEstacionB2", TipoDeBanio.MUJERES, serviciosEstacionB1);
    banio("bañoEstacionB1", TipoDeBanio.HOMBRES, serviciosEstacionB2);
    escaleraMecanica(serviciosEstacionA1);
    escaleraMecanica(serviciosEstacionA2);
    escaleraMecanica(serviciosEstacionB1);
    escaleraMecanica(serviciosEstacionB2);
    banio("bañoCoto1H", TipoDeBanio.HOMBRES, serviciosCoto1);
    banio("bañoCoto1M", TipoDeBanio.MUJERES, serviciosCoto1);
    banio("bañoCoto2H", TipoDeBanio.MUJERES, serviciosCoto2);
    banio("bañoCoto2M", TipoDeBanio.HOMBRES, serviciosCoto2);
    banio("bañoDia1H", TipoDeBanio.HOMBRES, serviciosDia1);
    banio("bañoDia1M", TipoDeBanio.MUJERES, serviciosDia1);
    banio("bañoDia2H", TipoDeBanio.MUJERES, serviciosDia2);
    banio("bañoDia2M", TipoDeBanio.HOMBRES, serviciosDia2);
  }
    // Creo servicios Linea A
   private void banio(String nombre, TipoDeBanio tipo, List<Servicio> servicios) {
     Banio banio = new Banio(nombre, tipo);
     servicios.add(banio);
   }
   private void escaleraMecanica(List<Servicio> servicios){
     EscaleraMecanica escaleraMecanica = new EscaleraMecanica("Escalera entrada");
     servicios.add(escaleraMecanica);
  }

}
