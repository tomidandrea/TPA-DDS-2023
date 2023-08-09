package dominio.comunidades;

import dominio.clasesTecnicas.Usuario;

import java.time.LocalDateTime;
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

    public Comunidad(List<Miembro> miembros, List<Incidente> incidentesAbiertos, String nombre) {
        this.miembros = miembros;
        this.incidentesAbiertos = incidentesAbiertos;
        this.nombre = nombre;
    }

    public void notificarIncidentes() {
        List<Miembro> miembrosANotificar = filtrarMiembrosEnHorario();
        List<Incidente> incidentes = ultimosIncidentesAbiertos();
        miembrosANotificar.forEach(miembro -> {
            System.out.println("Miembro a notificar: " + miembro.getNombre());
            miembro.getMedioPreferido().notificarIncidentes(miembro, incidentes, this);
        });
    }

    // filtramos incidentes abiertos de las ultimas 24 horas
    public List<Incidente> ultimosIncidentesAbiertos() {
        return incidentesAbiertos.stream().
                filter(
                        incidente -> incidente.horarioApertura().isAfter(LocalDateTime.now().minusHours(24))).toList();
    }

    private List<Miembro> filtrarMiembrosEnHorario() {
        return miembros.stream().filter(miembro -> miembro.estaEnHorarioDeNotificacion()).toList();
    }

    public List<Incidente> obtenerIncidentesAbiertosDesde(LocalTime hora) {
        return null;
    }

    public void removerIncidente(Incidente incidente){
        incidentesAbiertos.remove(incidente);
    }

    public void agregarIncidente(Incidente incidente){
        incidentesAbiertos.add(incidente);
    }

}
