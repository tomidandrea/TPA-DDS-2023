package dominio.Notificacion;

import dominio.comunidades.Comunidad;
import dominio.comunidades.Incidente;
import dominio.comunidades.Miembro;

import java.util.List;

public class MedioWhatsapp extends MedioDeComunicacion{
    WhatsappAPI whatsappAPI;
    public void notificarIncidentes(Miembro miembro, List<Incidente> incidentes, Comunidad comunidad) {
        adaptarMensaje(incidentes, comunidad);
        whatsappAPI.enviarMensaje(miembro.getNroDeTelefono(), mensajes);
    }
}
