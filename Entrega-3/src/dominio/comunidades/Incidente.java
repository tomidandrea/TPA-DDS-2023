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

}
