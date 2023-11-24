package dominio.establecimientos;
import dominio.Localizacion.Localizacion;
import dominio.servicios.Agrupacion;
import dominio.servicios.Servicio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("Estacion")
public class Estacion extends Establecimiento {
    public Estacion() {

    }

    public List<Servicio> serviciosFaltantes() {
        //TODO ver en el repo que servicios faltan
        return new ArrayList<>();
    }

    public Estacion(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public Estacion(String nombre, List<Servicio> servicios, Localizacion localizacion){
        this.nombre = nombre;
        this.servicios = servicios;
        this.localizacion = localizacion;
    }

    public Estacion(String nombre, List<Servicio> servicios, Localizacion localizacion, List<Agrupacion> agrupaciones){
        this.nombre = nombre;
        this.servicios = servicios;
        this.localizacion = localizacion;
        this.agrupaciones = agrupaciones;
    }

    public Estacion(String nombre, Localizacion localizacion, List<Agrupacion> agrupaciones){
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.agrupaciones = agrupaciones;
    }
}
