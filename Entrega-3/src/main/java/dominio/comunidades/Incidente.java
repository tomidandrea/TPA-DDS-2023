package dominio.comunidades;

import dominio.servicios.Agrupacion;
import dominio.servicios.Servicio;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
public class Incidente {
    Servicio servicio;
    Agrupacion agrupacion;
    String observacion;
    List<Comunidad> comunidades;
    LocalDateTime horarioApertura;
    LocalDateTime horarioCierre;
    EstadoIncidente estadoIncidente;

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
    public Incidente(Servicio servicio, LocalDateTime horarioApertura, String obs){
        this.servicio = servicio;
        this.horarioApertura = horarioApertura;
        this.observacion = obs;
        RepoIncidentes repoIncidentes = RepoIncidentes.getInstance();
        repoIncidentes.agregar(this);
    }
    public Servicio getServicio() {
        return servicio;
    }

    public Agrupacion getAgrupacion() {
        return agrupacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public LocalDateTime getHorarioCierre() { return horarioCierre; }

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

    public LocalDateTime horarioApertura() {
        return horarioApertura;
    }

    @Override
    public String toString() {
        return "Incidente en el servicio: "+servicio.nombre+ ", abierto:"+
            DateTimeFormatter.ofPattern("dd-MM-yyyy 'a las' HH:mm:ss").format(horarioApertura);
    }
}
