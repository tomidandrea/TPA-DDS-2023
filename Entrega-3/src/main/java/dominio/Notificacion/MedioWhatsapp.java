package dominio.Notificacion;

import dominio.comunidades.Comunidad;
import dominio.comunidades.Incidente;
import dominio.comunidades.Miembro;

import java.util.List;

public class MedioWhatsapp extends MedioDeComunicacion{
    WhatsappAPI whatsappAPI;
    String mensajes;
    public void notificarIncidentes(Miembro miembro, List<Incidente> incidentes, Comunidad comunidad) {
        // Formatea el mensaje (los adapta a la API de correo como un string)
        // para cada incidente en incidentes, se concatena el mensaje con un "\n"
        // TODO: implementar para agrupaciones

        incidentes.forEach(incidente -> {
            String servicio = incidente.getServicio().getNombre();
            String nombreComunidad = comunidad.getNombre();
            String observacion = incidente.getObservacion();
            mensajes = "Se ha reportado un incidente en la comunidad " + nombreComunidad + ".\n" + "Servicio: " + servicio + "\n" + observacion;
            mensajes += "\n";
        });

        whatsappAPI.enviarMensaje(miembro.getNroDeTelefono(), mensajes);
    }
}
