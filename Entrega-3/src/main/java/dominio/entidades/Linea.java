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
    private List<Localizacion> localizaciones;

    public List<Estacion> getEstaciones() {
        return estaciones;
    }
}
