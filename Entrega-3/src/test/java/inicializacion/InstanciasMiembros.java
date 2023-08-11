package inicializacion;

import dominio.Notificacion.CorreoAPI;
import dominio.Notificacion.MedioCorreo;
import dominio.comunidades.Horario;
import dominio.comunidades.Miembro;
import dominio.comunidades.TipoMiembro;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;
@Getter
public class InstanciasMiembros {
  private Miembro juan;
  private Miembro pedro;

  public InstanciasMiembros(){
    CorreoAPI correoAPI = new CorreoAPI("agustinmsanjuan@gmail.com", ""); //TODO borrar
    MedioCorreo medioCorreo = MedioCorreo.getInstance(correoAPI);
    LocalTime horario1 = LocalTime.of(LocalTime.now().getHour(), 0);
    LocalTime horario2 = LocalTime.of(LocalTime.now().minusHours(4).getHour(), 0);
    LocalTime horario3 = LocalTime.of(LocalTime.now().getHour(), 0);

    List<LocalTime> horariosJuan = List.of(horario2, horario1);
    Horario horarioJuan = new Horario(horariosJuan);
    List<LocalTime> horariosPedro = List.of(horario3);
    Horario horarioPedro = new Horario(horariosPedro);
    this.juan = new Miembro("Juan", "Label", "alejo.sandrini@gmail.com",
        TipoMiembro.AFECTADO, medioCorreo, "12345678", horarioJuan,
        "juancito123", "blueLabel#23");
    this.pedro = new Miembro("Pedro", "Pascal", "alejo.sandrini@gmail.com",
        TipoMiembro.AFECTADO, medioCorreo, "12345687", horarioPedro,
        "ppascal321", "groguloveR$41");
  }
}
