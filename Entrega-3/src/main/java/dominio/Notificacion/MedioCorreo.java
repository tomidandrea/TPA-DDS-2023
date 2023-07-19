package dominio.Notificacion;

import dominio.comunidades.Comunidad;
import dominio.comunidades.Incidente;
import dominio.comunidades.Miembro;
import java.util.List;

public class MedioCorreo extends MedioDeComunicacion {
    CorreoAPI correoAPI;

    @Override
    public void notificarIncidentes(Miembro miembro, List<Incidente> incidentes, Comunidad comunidad) {
        adaptarMensaje(incidentes, comunidad);
        correoAPI.enviarCorreo(miembro.getCorreo(), mensajes);
    }
}
