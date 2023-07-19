package dominio.establecimientos;
import dominio.Localizacion.Localizacion;
import dominio.servicios.Servicio;
import dominio.servicios.Agrupacion;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public abstract class Establecimiento {
    private String nombre;
    private Localizacion localizacion;
    private List<Servicio> servicios;
    private List<Agrupacion> agrupaciones;

    public List<Duration> promedioCierreEnServicio(){
        return new ArrayList<>();
    }

    public int cantidadDeIncidentes(){
        return 0;
    }
}
