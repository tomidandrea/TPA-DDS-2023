package dominio.comunidades;

import dominio.servicios.Agrupacion;
import dominio.servicios.Servicio;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Incidente {
    Servicio servicio;
    Agrupacion agrupacion;
    String observacion;
    List<Comunidad> comunidades;
    LocalDateTime horarioApertura;
    LocalDateTime horarioCierre;
    EstadoIncidente estadoIncidente;

    public Incidente(Servicio servicio, Agrupacion agrupacion, String observacion, List<Comunidad> comunidades, LocalDateTime horarioApertura, LocalDateTime horarioCierre, EstadoIncidente estadoIncidente) {
        this.servicio = servicio;
        this.agrupacion = agrupacion;
        this.observacion = observacion;
        this.comunidades = comunidades;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
        this.estadoIncidente = estadoIncidente;
        comunidades.forEach(c->c.agregarIncidente(this));
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
}
