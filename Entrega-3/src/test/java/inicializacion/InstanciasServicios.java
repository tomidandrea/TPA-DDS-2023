package inicializacion;

import dominio.servicios.Baño;
import dominio.servicios.EscaleraMecanica;
import dominio.servicios.Servicio;
import dominio.servicios.TipoDeBaño;
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
    banio("bañoEstacionA1", TipoDeBaño.HOMBRES, serviciosEstacionA1);
    banio("bañoEstacionA2",TipoDeBaño.MUJERES, serviciosEstacionA2);
    banio("bañoEstacionB2",TipoDeBaño.MUJERES, serviciosEstacionB1);
    banio("bañoEstacionB1",TipoDeBaño.HOMBRES, serviciosEstacionB2);
    escaleraMecanica(serviciosEstacionA1);
    escaleraMecanica(serviciosEstacionA2);
    escaleraMecanica(serviciosEstacionB1);
    escaleraMecanica(serviciosEstacionB2);
    banio("bañoCoto1H", TipoDeBaño.HOMBRES, serviciosCoto1);
    banio("bañoCoto1M",TipoDeBaño.MUJERES, serviciosCoto1);
    banio("bañoCoto2H",TipoDeBaño.MUJERES, serviciosCoto2);
    banio("bañoCoto2M",TipoDeBaño.HOMBRES, serviciosCoto2);
    banio("bañoDia1H", TipoDeBaño.HOMBRES, serviciosDia1);
    banio("bañoDia1M",TipoDeBaño.MUJERES, serviciosDia1);
    banio("bañoDia2H",TipoDeBaño.MUJERES, serviciosDia2);
    banio("bañoDia2M",TipoDeBaño.HOMBRES, serviciosDia2);
  }
    // Creo servicios Linea A
   private void banio(String nombre, TipoDeBaño tipo, List<Servicio> servicios) {
     Baño banio = new Baño(nombre, tipo);
     servicios.add(banio);
   }
   private void escaleraMecanica(List<Servicio> servicios){
     EscaleraMecanica escaleraMecanica = new EscaleraMecanica("Escalera entrada");
     servicios.add(escaleraMecanica);
  }

}
