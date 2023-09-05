package dominio.Notificacion;

import dominio.comunidades.Comunidad;
import dominio.comunidades.Incidente;
import dominio.comunidades.Miembro;

import java.util.List;

public class MedioWhatsapp extends MedioDeComunicacion{

    private static MedioWhatsapp instance = null;
    WhatsappAPI whatsappAPI;

    public static MedioWhatsapp getInstance(WhatsappAPI whatsappAPI) {
        if (instance == null) {
            instance = new MedioWhatsapp();
            instance.whatsappAPI = whatsappAPI;
        }
        return instance;
    }
    public void notificarIncidentes(Miembro miembro, List<Incidente> incidentes, Comunidad comunidad) {
        adaptarMensaje(incidentes, comunidad);
        whatsappAPI.enviarMensaje(miembro.getNroDeTelefono(), mensajes);
    }

    @Override
    public String toString() {
        return "MedioWhatsapp";
    }
}
