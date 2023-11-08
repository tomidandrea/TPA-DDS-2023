package dominio.entidades;

import dominio.Localizacion.Localizacion;
import dominio.establecimientos.Establecimiento;
import dominio.establecimientos.Estacion;
import dominio.servicios.Servicio;

import javax.persistence.*;
import java.util.List;

@Entity
public class Linea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    @OneToOne(cascade = CascadeType.ALL)
    private Estacion estacionOrigen;
    @OneToOne(cascade = CascadeType.ALL)
    private Estacion estacionDestino;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Estacion> estaciones;
    @ManyToMany(cascade = CascadeType.ALL)
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

    public Linea() {

    }

    public List<Estacion> getEstaciones() {
        return estaciones;
    }
}
