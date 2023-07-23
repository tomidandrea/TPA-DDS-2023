package dominio.comunidades;

import dominio.servicios.Agrupacion;
import dominio.servicios.Servicio;

import java.time.LocalTime;
import java.util.List;

public class Incidente {
    Servicio servicio;
    Agrupacion agrupacion;
    String observacion;
    List<Comunidad> comunidades;
    LocalTime horarioApertura;
    LocalTime horarioCierre;
    EstadoIncidente estadoIncidente;

    public Incidente(Servicio servicio, Agrupacion agrupacion, String observacion, List<Comunidad> comunidades, LocalTime horarioApertura, LocalTime horarioCierre, EstadoIncidente estadoIncidente) {
        this.servicio = servicio;
        this.agrupacion = agrupacion;
        this.observacion = observacion;
        this.comunidades = comunidades;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
        this.estadoIncidente = estadoIncidente;
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

    public int calcularDiferencia(){
        return horarioApertura.compareTo(horarioCierre);
    }

}
