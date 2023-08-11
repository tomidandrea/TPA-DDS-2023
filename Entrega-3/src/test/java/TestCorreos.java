import dominio.Notificacion.CorreoAPI;
import dominio.Notificacion.MedioCorreo;
import dominio.Notificacion.MedioDeComunicacion;
import dominio.comunidades.Comunidad;
import dominio.comunidades.Incidente;
import dominio.comunidades.Miembro;
import dominio.servicios.Baño;
import dominio.servicios.TipoDeBaño;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class TestCorreos {
  private Comunidad comunidad1;
  @BeforeEach
  public void init(){
    InicializadorTests inicializador = InicializadorTests.getInstance();
    this.comunidad1 = inicializador.getComunidades().getComunidad1();
  }
    @Test
    public void testNotificarIncidentes() {
      comunidad1.getIncidentesAbiertos().forEach(incidente -> System.out.println(incidente.toString()));
      comunidad1.notificarIncidentes();
    }
}
