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
  private Miembro manu;

  public InstanciasMiembros(){
    CorreoAPI correoAPI = new CorreoAPI("agustinmsanjuan@gmail.com", ""); //TODO borrar
    MedioCorreo medioCorreo = MedioCorreo.getInstance(correoAPI);
    LocalTime horario1 = LocalTime.of(LocalTime.now().getHour(), 0);
    LocalTime horario2 = LocalTime.of(LocalTime.now().minusHours(4).getHour(), 0);
    LocalTime horario3 = LocalTime.of(LocalTime.now().plusHours(2).getHour(), 0);

    List<LocalTime> horariosJuan = List.of(horario2, horario1);
    Horario horarioJuan = new Horario(horariosJuan);
    List<LocalTime> horariosPedro = List.of(horario1);
    Horario horarioPedro = new Horario(horariosPedro);
    List<LocalTime> horariosManu = List.of(horario1, horario3);
    Horario horarioManu = new Horario(horariosManu);
    this.juan = new Miembro("Juan", "Label", "prueba@gmail.com",
        TipoMiembro.AFECTADO, medioCorreo, "12345678", horarioJuan,
        "juancito123", "blueLabel#23");
    this.pedro = new Miembro("Pedro", "Pascal", "prueba@gmail.com",
        TipoMiembro.AFECTADO, medioCorreo, "12345687", horarioPedro,
        "ppascal321", "groguloveR$41");
    this.manu = new Miembro("Manu", "Ginobili", "prueba@gmail.com",
        TipoMiembro.AFECTADO, medioCorreo, "12345677", horarioManu,
        "manu.gino", "spursPlayer20!");
  }
}
