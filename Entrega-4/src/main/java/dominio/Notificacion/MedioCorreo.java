package dominio.Notificacion;

import dominio.comunidades.Comunidad;
import dominio.comunidades.Incidente;
import dominio.comunidades.Miembro;

import javax.persistence.Converter;
import java.util.List;

public class MedioCorreo extends MedioDeComunicacion {
    private static MedioCorreo instance = null;
    CorreoAPI correoAPI = null;

    public static MedioCorreo getInstance(CorreoAPI correoAPI) {
        if (instance == null) {
            instance = new MedioCorreo();
            instance.correoAPI = correoAPI;
        }
        return instance;
    }

    @Override
    public void notificarIncidentes(Miembro miembro, List<Incidente> incidentes, String origen) {
        adaptarMensaje(incidentes, origen);
        //correoAPI.enviarCorreo(miembro.getCorreo(), mensajes); TODO:descomentar para enviar mail
    }

    @Override
    public String toString() {
        return "MedioCorreo";
    }
}
