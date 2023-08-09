package inicializacion;

import dominio.Notificacion.CorreoAPI;
import dominio.Notificacion.MedioCorreo;
import dominio.comunidades.Miembro;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;
@Getter
public class InstanciasMiembros {
  private Miembro juan;
  private Miembro pedro;

  public InstanciasMiembros(){
    CorreoAPI correoAPI = new CorreoAPI("agustinmsanjuan@gmail.com", ""); // borrar
    MedioCorreo medioCorreo = MedioCorreo.getInstance(correoAPI);
    LocalTime horario1 = LocalTime.of(LocalTime.now().getHour(), 0);
    LocalTime horario3 = LocalTime.of(15, 40);

    List<LocalTime> horariosJuan = List.of(horario1);
    List<LocalTime> horariosPedro = List.of(horario3);
    this.juan = new Miembro("Juan", "alejo.sandrini@gmail.com", medioCorreo, horariosJuan);
    this.pedro = new Miembro("Pedro", "alejo.sandrini@gmail.com", medioCorreo, horariosPedro);
  }
}
