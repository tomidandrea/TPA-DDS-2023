package dominio.establecimientos;
import dominio.servicios.Servicio;

import java.util.ArrayList;
import java.util.List;

public class Estacion extends Establecimiento {

    public List<Servicio> serviciosFaltantes() {
        return new ArrayList<>();
    }

    public Estacion(List<Servicio> servicios) {
        this.servicios = servicios;
    }
}
