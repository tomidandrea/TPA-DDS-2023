package dominio.entidades;

import java.util.ArrayList;
import java.util.List;

public class RepoEntidades {
    private static RepoEntidades instance = null;

    public static RepoEntidades getInstance(){
        if(instance == null){
            instance = new RepoEntidades();
        }
        return instance;
    }
    List<Organizacion> organizaciones = new ArrayList<>();
    List<ServicioTransporte> serviciosDeTransporte = new ArrayList<>();

    public void agregar(ServicioTransporte servicioTransporte) {
        serviciosDeTransporte.add(servicioTransporte);
    }
    public ServicioTransporte obtenerPorTipo(MedioDeTransporte tipo){
        return serviciosDeTransporte.stream().filter(servicio -> servicio.tipoTransporte == tipo).findFirst().get();
    }

    public void agregar(Organizacion organizacion) {
        organizaciones.add(organizacion);
    }

    public Organizacion obtenerPorNombre(String nombre){
        return organizaciones.stream().filter(organizacion -> organizacion.nombre == nombre).findFirst().get();
    }
    
    public List<Entidad> obtenerRankingEntidadesConMayorTiempoDeCierre(){
        return new ArrayList<>();
    }

    public List<Entidad> obtenerRankingEntidadesConMasIncidentes(){
        return new ArrayList<>();
    }
}
