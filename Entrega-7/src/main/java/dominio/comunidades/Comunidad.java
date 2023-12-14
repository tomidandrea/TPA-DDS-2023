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
import java.util.stream.Collectors;

@Getter
@Entity
public class Comunidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comunidad_id")
    private int id;

    private String nombre;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "comunidades_afectados",
            joinColumns = @JoinColumn(name = "comunidad_id"),
            inverseJoinColumns = @JoinColumn(name = "miembro_id")
    )
    private List<Miembro> afectados;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "comunidades_observadores",
            joinColumns = @JoinColumn(name = "comunidad_id"),
            inverseJoinColumns = @JoinColumn(name = "miembro_id")
    )
    private List<Miembro> observadores;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Miembro> administradores;


    @ManyToMany(mappedBy = "comunidades")
//    @JoinTable(name = "incidente_comunidad",
//        joinColumns = @JoinColumn(name = "comunidad_id"),
//        inverseJoinColumns = @JoinColumn(name = "incidente_id")
//    )
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



    public Comunidad(String nombre, List<Miembro> afectados, List<Miembro> observadores, List<Miembro> admins,
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

    public void quitarMiembro(Miembro miembro) {
        // filtro si existe miembro con ese id
        if (afectados.stream().anyMatch(m -> m.getId() == miembro.getId())) {
            afectados = afectados.stream()
                    .filter(afectado -> afectado.getId() != miembro.getId())
                    .collect(Collectors.toList());
        }
        if (observadores.stream().anyMatch(m -> m.getId() == miembro.getId())) {
            observadores = observadores.stream()
                    .filter(observador -> observador.getId() != miembro.getId())
                    .collect(Collectors.toList());
        }
        if (administradores.stream().anyMatch(m -> m.getId() == miembro.getId())) {
            administradores = administradores.stream()
                    .filter(admin -> admin.getId() != miembro.getId())
                    .collect(Collectors.toList());
        }
    }

    public void notificarIncidentes() {
        List<Miembro> miembrosANotificar = filtrarMiembrosEnHorario();
        List<Incidente> incidentes = ultimosIncidentesAbiertos();
        miembrosANotificar.forEach(miembro -> {
            System.out.println("Miembro a notificar: " + miembro.getNombre());
            List<Incidente> incidentesDentroRangoHorario = miembro.getNotificador()
                .incidenteDentroDeRangoHorario(incidentes, LocalTime.now());
            miembro.getMedioPreferido().notificarIncidentes(miembro, incidentesDentroRangoHorario, this.getNombre());
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
    public Boolean tieneAlMiembro(int id) {
        HashSet<Miembro> miembros = new HashSet<>(afectados);
        miembros.addAll(observadores);
        return miembros.stream().anyMatch(m -> m.getId() == id);
    }
    public List<Incidente> obtenerIncidentesAbiertosDesde(LocalTime hora) {
        return null;
    }

    public void removerIncidente(Incidente incidente){
        incidentesAbiertos.remove(incidente);
        RepoComunidades.getInstance().actualizar(this);
    }

    public void agregarIncidente(Incidente incidente){
                    incidentesAbiertos.add(incidente);
                    //RepoComunidades.getInstance().actualizar(this);
    }

    @Override
    public String toString() {
        return "Comunidad{" +
                "id = " + id +
                ", nombre = " + nombre +'\'' +
                '}';
    }
}
