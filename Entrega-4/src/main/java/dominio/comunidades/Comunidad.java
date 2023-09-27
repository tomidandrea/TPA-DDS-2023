package dominio.comunidades;

import dominio.clasesTecnicas.Usuario;
import dominio.establecimientos.Establecimiento;
import dominio.servicios.Servicio;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Getter
@Entity
public class Comunidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comunidad_id")
    private int id;

    private String nombre;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "comunidades_incidentes",
            joinColumns = @JoinColumn(name = "comunidad_id"),
            inverseJoinColumns = @JoinColumn(name = "miembro_id")
    )
    private List<Miembro> afectados;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "comunidades_incidentes",
            joinColumns = @JoinColumn(name = "comunidad_id"),
            inverseJoinColumns = @JoinColumn(name = "miembro_id")
    )
    private List<Miembro> observadores;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Administrador> administradores;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "comunidades_incidentes",
        joinColumns = @JoinColumn(name = "comunidad_id"),
        inverseJoinColumns = @JoinColumn(name = "incidente_id")
    )
    private List<Incidente> incidentesAbiertos;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "comunidades_establecimientos_observados",
            joinColumns = @JoinColumn(name = "comunidad_id"),
            inverseJoinColumns = @JoinColumn(name = "establecimiento_id")
    )
    private List<Establecimiento> establecimientosObservados;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "comunidades_servicios_observados",
            joinColumns = @JoinColumn(name = "comunidad_id"),
            inverseJoinColumns = @JoinColumn(name = "servicio_id")
    )
    private List<Servicio> serviciosEstandar;

    @Enumerated(EnumType.STRING)
    private GradoDeConfianza gradoDeConfianza;

    public Comunidad() {

    }

    public String getNombre() {
        return nombre;
    }



    public Comunidad(String nombre, List<Miembro> afectados, List<Miembro> observadores, List<Administrador> admins,
                     List<Establecimiento> establecimientosObservados, List<Servicio> serviciosEstandar,
                     GradoDeConfianza gradoDeConfianza, List<Incidente> incidentes) {
        this.nombre = nombre;
        this.establecimientosObservados = establecimientosObservados;
        this.serviciosEstandar = serviciosEstandar;
        this.gradoDeConfianza = gradoDeConfianza;
        this.afectados = afectados;
        this.observadores = observadores;
        this.incidentesAbiertos = incidentes;
        this.administradores = admins;
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
                        incidente -> incidente.getHorarioApertura().isAfter(LocalDateTime.now().minusHours(24))).toList();
    }

    private List<Miembro> filtrarMiembrosEnHorario() {
        HashSet<Miembro> miembros = new HashSet<>(afectados);
        miembros.addAll(observadores);
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
