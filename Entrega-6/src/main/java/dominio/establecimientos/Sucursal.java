package dominio.establecimientos;

import dominio.Localizacion.Localizacion;
import dominio.servicios.Agrupacion;
import dominio.servicios.Servicio;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Sucursal")
public class Sucursal extends Establecimiento{
    public Sucursal(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public Sucursal(String nombre, List<Servicio> servicios, Localizacion localizacion){
        this.nombre = nombre;
        this.servicios = servicios;
        this.localizacion = localizacion;
    }
    public Sucursal(String nombre, List<Servicio> servicios, Localizacion localizacion, List<Agrupacion> agrupaciones){
        this.nombre = nombre;
        this.servicios = servicios;
        this.localizacion = localizacion;
        this.agrupaciones = agrupaciones;
    }

    public Sucursal(String nombre, Localizacion localizacion, List<Agrupacion> agrupaciones){
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.agrupaciones = agrupaciones;
    }

    public Sucursal() {

    }
}
