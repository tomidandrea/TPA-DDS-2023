package dominio.entidades;

import dominio.Localizacion.Localizacion;
import dominio.establecimientos.Establecimiento;
import dominio.establecimientos.Estacion;
import dominio.servicios.Servicio;

import java.util.List;

public class Linea {
    private String nombre;
    private Estacion estacionOrigen;
    private Estacion estacionDestino;
    private List<Estacion> estaciones;
    private List<Localizacion> localizacionesDeEstacion; //Para consultar mas rapido las localizaciones

    // constructor para test rankings
    public Linea(String nombre, List<Estacion> estaciones){
        this.nombre = nombre;
        this.estaciones = estaciones;
    }

    public Linea(String nombre, Estacion estacionOrigen, Estacion estacionDestino,
                 List<Estacion> estaciones) {
        this.nombre = nombre;
        this.estacionOrigen = estacionOrigen;
        this.estacionDestino = estacionDestino;
        this.estaciones = estaciones;
        estaciones.forEach(e-> localizacionesDeEstacion.add(e.getLocalizacion()));
    }

    public List<Estacion> getEstaciones() {
        return estaciones;
    }
}
