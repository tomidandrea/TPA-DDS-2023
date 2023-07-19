package dominio.Notificacion;

import dominio.comunidades.Comunidad;
import dominio.comunidades.Incidente;
import dominio.comunidades.Miembro;

import java.util.List;

public abstract class MedioDeComunicacion {
     String mensajes;
     public abstract void notificarIncidentes(Miembro miembro, List<Incidente> incidentes, Comunidad comunidad);
     public void adaptarMensaje(List<Incidente> incidentes, Comunidad comunidad) {
          // Formatea el mensaje (los adapta a la API de correo como un string)
          // para cada incidente en incidentes, se concatena el mensaje con un "\n"
          // TODO: implementar para agrupaciones
          incidentes.forEach(incidente -> {
               String servicio = incidente.getServicio().getNombre();
               String observacion = incidente.getObservacion();
               String nombreComunidad = comunidad.getNombre();
               mensajes = "Se ha reportado un incidente en la comunidad " + nombreComunidad + ".\n" + "Servicio: " + servicio + "\n" + observacion;
               mensajes += "\n";
          });
     }

}
