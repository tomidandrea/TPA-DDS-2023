package dominio.comunidades;

import dominio.clasesTecnicas.Usuario;

import java.time.LocalTime;
import java.util.List;

public class Comunidad {
    List<Miembro> miembros;
    List<Usuario> administradores;
    List<Incidente> incidentesAbiertos;
    String nombre;

    public String getNombre() {
        return nombre;
    }

    void notificarIncidentesAMiembros(List<Incidente> incidentes) {
        miembros.forEach(miembro ->
                miembro.getMedioPreferido().notificarIncidentes(miembro, incidentes, this));
    }

    private List<Incidente> obtenerIncidentesAbiertosDesde(LocalTime hora) {
        return null;
    }

}
