package inicializacion;

import dominio.comunidades.Incidente;
import dominio.comunidades.RepoIncidentes;
import dominio.servicios.Agrupacion;
import dominio.servicios.Servicio;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
public class TestsInstanciasIncidentes {
  public RepoIncidentes repoIncidentes = RepoIncidentes.getInstance();
  private Incidente incidenteAbierto1;
  private Incidente incidenteAbierto2;
  private Incidente incidenteAbierto3;
  private Incidente incidenteHace5Dias;
  public static LocalDateTime restarDiasHorasMinutos(int dias, int horas, int minutos) {
    // Paso 1: Obtener la fecha actual
    LocalDateTime fechaActual = LocalDateTime.now();

    // Paso 2: Restar los días, horas y minutos
    LocalDateTime fechaResultante = fechaActual.minusDays(dias)
        .minusHours(horas)
        .minusMinutes(minutos);

    // Paso 3: Retornar el resultado
    return fechaResultante;
  }

  public TestsInstanciasIncidentes(TestsInstanciasServicios servicios){
    incidentesSucursalCoto1(servicios.getServiciosCoto1(), servicios.getAgrupacionesCoto1());
    incidentesSucursalCoto2(servicios.getServiciosCoto2());
    // Incidentes del Dia (menor tiempo promedio)
    incidentesSucursalDia1(servicios.getServiciosDia1());
    incidentesSucursalDia2(servicios.getServiciosDia2());
    // Incidentes del subte A - ida y vuelta (tiempo promedio intermedio)
    incidentesEstacionA1(servicios.getServiciosEstacionA1());
    incidentesEstacionA2(servicios.getServiciosEstacionA2());
    // Incidentes del subte B - ida y vuelta (tiempo promedio intermedio)
    incidentesEstacionB1(servicios.getServiciosEstacionB1());
    incidentesEstacionB2(servicios.getServiciosEstacionB2());

    incidenteAbierto1 = new Incidente(
        servicios.getServiciosEstacionA1().get(0),
        restarDiasHorasMinutos(0, 16, 0),
        "El baño está sucio");
    incidenteAbierto2 = new Incidente(
        servicios.getServiciosEstacionA2().get(0),
        restarDiasHorasMinutos(0, 2, 0),
        "Se rompió el inodoro");
    incidenteAbierto3 = new Incidente(
        servicios.getServiciosEstacionA1().get(1),
        restarDiasHorasMinutos(0, 23, 0),
        "Se rompió escalera mecánica");

    incidenteHace5Dias = new Incidente(
        servicios.getServiciosCoto1().get(0),
        restarDiasHorasMinutos(5, 8, 0),
        "El baño está sucio");
  }

  private void incidentesSucursalCoto1(List<Servicio> servicios, List<Agrupacion> agrupaciones){
    Incidente incidente1 = new Incidente(
        servicios.get(0),
        restarDiasHorasMinutos(6, 16, 0),
        restarDiasHorasMinutos(1, 16, 30));

    Incidente incidente2 = new Incidente(
        servicios.get(1),
        restarDiasHorasMinutos(7, 16, 0),
        restarDiasHorasMinutos(7, 8, 30));

    //Incidente de agrupacion
    Incidente incidente3 = new Incidente(
            agrupaciones.get(0),
            restarDiasHorasMinutos(6, 16, 0),
            restarDiasHorasMinutos(1, 8, 30));

  }

  private void incidentesSucursalCoto2(List<Servicio> servicios){
    Incidente incidente3 = new Incidente(
        servicios.get(0),
        restarDiasHorasMinutos(6, 8, 0),
        restarDiasHorasMinutos(3, 8, 30));
    Incidente incidente4 = new Incidente(
        servicios.get(1),
        restarDiasHorasMinutos(8, 16, 0),
        restarDiasHorasMinutos(7, 8, 30));

  }

  private void incidentesSucursalDia1(List<Servicio> servicios){
    Incidente incidente5 = new Incidente(
        servicios.get(0),
        restarDiasHorasMinutos(9, 8, 0),
        restarDiasHorasMinutos(8, 0, 0));
    Incidente incidente6 = new Incidente(
        servicios.get(1),
        restarDiasHorasMinutos(9, 8, 0),
        restarDiasHorasMinutos( 6, 0, 0));

  }

  private void incidentesSucursalDia2(List<Servicio> servicios){
    Incidente incidente7 = new Incidente(
        servicios.get(0),
        restarDiasHorasMinutos(9, 8, 0),
        restarDiasHorasMinutos(8, 1, 30));
    Incidente incidente8 = new Incidente(
        servicios.get(1),
        restarDiasHorasMinutos(9, 12, 30),
        restarDiasHorasMinutos(9, 8, 0));

  }
  private void incidentesEstacionA1(List<Servicio> servicios){
    Incidente incidente9 = new Incidente(
        servicios.get(0),
        restarDiasHorasMinutos(3, 8, 0),
        restarDiasHorasMinutos(2, 0, 0));
    Incidente incidente10 = new Incidente(
        servicios.get(1),
        restarDiasHorasMinutos(0, 6, 0),
        restarDiasHorasMinutos(0, 2, 0));

  }
  private void incidentesEstacionA2(List<Servicio> servicios){
    Incidente incidente11 = new Incidente(
        servicios.get(0),
        restarDiasHorasMinutos(1, 16, 0),
        restarDiasHorasMinutos(0, 0, 30));
    Incidente incidente12 = new Incidente(
        servicios.get(1),
        restarDiasHorasMinutos(1, 16, 0),
        restarDiasHorasMinutos(1, 0, 30));

  }
  private void incidentesEstacionB1(List<Servicio> servicios){
    Incidente incidente13 = new Incidente(
        servicios.get(0),
        restarDiasHorasMinutos(9, 16, 0),
        restarDiasHorasMinutos(8, 16, 30));
    Incidente incidente14 = new Incidente(
        servicios.get(1),
        restarDiasHorasMinutos(9, 8, 0),
        restarDiasHorasMinutos(7, 16, 30));

  }
  private void incidentesEstacionB2(List<Servicio> servicios){
    Incidente incidente15 = new Incidente(
        servicios.get(0),
        restarDiasHorasMinutos(9, 8, 0),
        restarDiasHorasMinutos(8, 0, 0));
    Incidente incidente16 = new Incidente(
        servicios.get(1),
        restarDiasHorasMinutos(9, 8, 0),
        restarDiasHorasMinutos(8, 0, 30));

  }

}
