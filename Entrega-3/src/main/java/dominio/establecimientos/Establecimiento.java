package dominio.establecimientos;
import dominio.Localizacion.Localizacion;
import dominio.servicios.Servicio;
import dominio.servicios.Agrupacion;
import dominio.comunidades.Incidente;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public abstract class Establecimiento {
    private String nombre;
    private Localizacion localizacion;
    private List<Servicio> servicios;
    private List<Agrupacion> agrupaciones;

    public List<Integer> promedioCierreEnServicio(){
        return (List<Integer>) this.incidentesDelEstablecimiento().stream().map(i->i.calcularDiferencia());
    }

    public int cantidadDeIncidentes(){
        return this.incidentesDelEstablecimiento().size();
    }

    public List<Incidente> incidentesDelEstablecimiento() {
        return RepoIncidentes.getInstance().obtenerIncidentesDe(agrupaciones, servicios);
    }
}
