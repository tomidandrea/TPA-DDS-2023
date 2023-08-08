import dominio.Notificacion.CorreoAPI;
import dominio.Notificacion.MedioCorreo;
import dominio.Notificacion.MedioDeComunicacion;
import dominio.comunidades.Comunidad;
import dominio.comunidades.Incidente;
import dominio.comunidades.Miembro;
import dominio.servicios.Baño;
import dominio.servicios.TipoDeBaño;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class TestCorreos {

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

    // Creo servicios
    Baño baño1 = new Baño("baño1", TipoDeBaño.HOMBRES);
    Baño baño2 = new Baño("baño2", TipoDeBaño.MUJERES);

    Baño baño3 = new Baño("baño3", TipoDeBaño.HOMBRES);
    Baño baño4 = new Baño("baño4", TipoDeBaño.MUJERES);

    Incidente incidente1 = new Incidente(baño1,
            restarDiasHorasMinutos(0, 16, 0),
            "El baño está sucio");

    Incidente incidente2 = new Incidente(baño2,
            restarDiasHorasMinutos(0, 2, 0),
            "Se rompió el inodoro");

    Incidente incidente3 = new Incidente(baño3,
            restarDiasHorasMinutos(5, 8, 0),
            "El baño está sucio");

    /*Incidente incidente4 = new Incidente(baño4,
            restarDiasHorasMinutos(0, 8, 0),
            restarDiasHorasMinutos(0, 9, 0));
*/


    CorreoAPI correoAPI = new CorreoAPI("agustinmsanjuan@gmail.com", ""); // borrar
    MedioCorreo medioCorreo = MedioCorreo.getInstance(correoAPI);
    LocalTime horario1 = LocalTime.of(LocalTime.now().getHour(), 0);
   //LocalTime horario2 = LocalTime.of(11, 20);
    LocalTime horario3 = LocalTime.of(15, 40);

    List<LocalTime> horariosJuan = List.of(horario1);
    List<LocalTime> horariosPedro = List.of(horario3);
    Miembro juan = new Miembro("Juan", "alejo.sandrini@gmail.com", medioCorreo, horariosJuan);
    Miembro pedro = new Miembro("Pedro", "alejo.sandrini@gmail.com", medioCorreo, horariosPedro);

    List<Incidente> incidentes = List.of(incidente1, incidente2, incidente3, incidente4);
    List<Miembro> miembros = List.of(juan, pedro);
    Comunidad comu = new Comunidad(miembros, incidentes, "comu");

    @Test
    public void testNotificarIncidentes() {
        comu.notificarIncidentes();
    }
}
