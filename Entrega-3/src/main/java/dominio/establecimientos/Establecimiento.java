package dominio.establecimientos;
import dominio.Localizacion.Localizacion;
import dominio.servicios.Servicio;
import dominio.servicios.Agrupacion;
import dominio.comunidades.Incidente;
import dominio.comunidades.RepoIncidentes;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public abstract class Establecimiento {
    private String nombre;
    private Localizacion localizacion;
    private List<Servicio> servicios;
    private List<Agrupacion> agrupaciones;

    public List<Duration> obtenerListaTiemposCierre(){
        return (List<Duration>) this.incidentesDelEstablecimiento().stream().map(i->i.obtenerTiempoCierre());
    }

    public int cantidadDeIncidentes(){
        return this.incidentesDelEstablecimiento().size();
    }

    public List<Incidente> incidentesDelEstablecimiento() {
        return RepoIncidentes.getInstance().obtenerIncidentesDe(agrupaciones, servicios);
    }
}
