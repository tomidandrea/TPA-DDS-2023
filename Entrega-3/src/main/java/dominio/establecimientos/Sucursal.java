package dominio.establecimientos;

import dominio.servicios.Servicio;

import java.util.List;

public class Sucursal extends Establecimiento{

    public Sucursal(List<Servicio> servicios) {
        this.servicios = servicios;
    }
}
