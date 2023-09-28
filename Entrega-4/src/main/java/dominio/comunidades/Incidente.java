package dominio.comunidades;

import dominio.servicios.Agrupacion;
import dominio.servicios.Servicio;
import lombok.Getter;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Entity
public class Incidente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "incidente_id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "servicio_id", referencedColumnName = "servicio_id")
    private Servicio servicio;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agrupacion_id")
    private Agrupacion agrupacion;
    private String observacion;

    @ManyToMany(mappedBy = "incidentesAbiertos", cascade = CascadeType.ALL)
    private List<Comunidad> comunidades;

    private LocalDateTime horarioApertura;
    private LocalDateTime horarioCierre;
    private EstadoIncidente estadoIncidente;

    public Incidente(Servicio servicio, Agrupacion agrupacion, String observacion,
                     List<Comunidad> comunidades, LocalDateTime horarioApertura,
                     LocalDateTime horarioCierre, EstadoIncidente estadoIncidente) {
        this.servicio = servicio;
        this.agrupacion = agrupacion;
        this.observacion = observacion;
        this.comunidades = comunidades;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
        this.estadoIncidente = estadoIncidente;
        comunidades.forEach(c->c.agregarIncidente(this));
    }
    // Constructor para test ranking
    public Incidente(Servicio servicio, LocalDateTime horarioApertura, LocalDateTime horarioCierre){
        this.servicio = servicio;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
        RepoIncidentes repoIncidentes = RepoIncidentes.getInstance();
        repoIncidentes.agregar(this);
    }
    public Incidente(Agrupacion agrupacion, LocalDateTime horarioApertura, LocalDateTime horarioCierre){
        this.agrupacion = agrupacion;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
        RepoIncidentes repoIncidentes = RepoIncidentes.getInstance();
        repoIncidentes.agregar(this);
    }

    public Incidente(Servicio servicio, LocalDateTime horarioApertura, String obs){
        this.servicio = servicio;
        this.horarioApertura = horarioApertura;
        this.observacion = obs;
        RepoIncidentes repoIncidentes = RepoIncidentes.getInstance();
        repoIncidentes.agregar(this);
    }

    public Incidente() {

    }

    public Duration obtenerTiempoCierre(){
        return Duration.between(horarioApertura, horarioCierre);
    }

    public void cerrar(){
        comunidades.forEach(c->c.removerIncidente(this));
    }

    public boolean tieneEstado(EstadoIncidente estado){
        return this.estadoIncidente.equals(estado);
    }

    public boolean cerradoUltimaSemana(){
       return Duration.between(this.getHorarioCierre() , LocalDateTime.now()).minusDays(7).isNegative();
    }

    @Override
    public String toString() {
        return "Incidente en el servicio: "+servicio.nombre+ ", abierto:"+
            DateTimeFormatter.ofPattern("dd-MM-yyyy 'a las' HH:mm:ss").format(horarioApertura);
    }
}
