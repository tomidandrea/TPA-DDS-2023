package dominio.comunidades;

import dominio.clasesTecnicas.Usuario;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Entity
public class Comunidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    private List<Miembro> miembros;

    @OneToMany
    private List<Usuario> administradores;

    @ManyToMany
    private List<Incidente> incidentesAbiertos;

    private String nombre;

    public Comunidad() {

    }

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
            List<Incidente> incidentesDentroRangoHorario = miembro.getNotificador()
                .incidenteDentroDeRangoHorario(incidentes, LocalTime.now());
            miembro.getMedioPreferido().notificarIncidentes(miembro, incidentesDentroRangoHorario, this);
        });
    }

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
