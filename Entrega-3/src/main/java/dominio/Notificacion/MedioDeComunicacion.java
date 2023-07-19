package dominio.Notificacion;

import dominio.comunidades.Comunidad;
import dominio.comunidades.Incidente;
import dominio.comunidades.Miembro;

import java.util.List;

public abstract class MedioDeComunicacion {
     public abstract void notificarIncidentes(Miembro miembro, List<Incidente> incidentes, Comunidad comunidad);

}
