package inicializacion;

import dominio.Notificacion.CorreoAPI;
import dominio.Notificacion.MedioCorreo;
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
    LocalTime horario3 = LocalTime.of(15, 40);

    List<LocalTime> horariosJuan = List.of(horario1);
    List<LocalTime> horariosPedro = List.of(horario3);
    this.juan = new Miembro("Juan", "Label", "alejo.sandrini@gmail.com",
        TipoMiembro.AFECTADO, medioCorreo, "12345678", horariosJuan,
        "juancito123", "blueLabel23");
    this.pedro = new Miembro("Pedro", "Pascal", "alejo.sandrini@gmail.com",
        TipoMiembro.AFECTADO, medioCorreo, "12345687", horariosPedro,
        "ppascal321", "grogulover41");
  }
}
