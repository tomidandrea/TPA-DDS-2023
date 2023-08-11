import dominio.Notificacion.CorreoAPI;
import dominio.Notificacion.MedioCorreo;
import dominio.Notificacion.MedioDeComunicacion;
import dominio.comunidades.Comunidad;
import dominio.comunidades.Incidente;
import dominio.comunidades.Miembro;
import dominio.servicios.Baño;
import dominio.servicios.TipoDeBaño;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class TestCorreos {
  private Comunidad comunidad1;
  private Miembro manu;
  private Miembro pedro;
  private Miembro juan;
  @BeforeEach
  public void init(){
    InicializadorTests inicializador = InicializadorTests.getInstance();
    this.comunidad1 = inicializador.getComunidades().getComunidad1();
    comunidad1.getIncidentesAbiertos().forEach(incidente -> System.out.println(incidente.toString()));

    this.manu = inicializador.getMiembros().getManu();
    this.pedro = inicializador.getMiembros().getPedro();
    this.juan = inicializador.getMiembros().getJuan();
  }
    @Test
    public void testNotificarIncidentes() {
      comunidad1.notificarIncidentes();
    }

  @Test
  public void testRangoHorario() {
    List<Incidente> incidentes = comunidad1.getIncidentesAbiertos().stream()
        .filter(incidente -> incidente.horarioApertura().isAfter(LocalDateTime.now().minusHours(24))).toList();

    int cantidadIncidentesJuan = juan.getHorariosDeNotificacion().incidenteDentroDeRangoHorario(incidentes, LocalTime.now()).size();
    int cantidadIncidentesPedro = pedro.getHorariosDeNotificacion().incidenteDentroDeRangoHorario(incidentes, LocalTime.now()).size();
    int cantidadIncidentesManu = manu.getHorariosDeNotificacion().incidenteDentroDeRangoHorario(incidentes, LocalTime.now()).size();

    Assertions.assertEquals(1, cantidadIncidentesJuan);
    Assertions.assertEquals(2, cantidadIncidentesManu);
    Assertions.assertEquals(3, cantidadIncidentesPedro);
  }
}
