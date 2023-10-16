package dominio.entidades;

import dominio.clasesTecnicas.ResultadoCantidadIncidentes;
import dominio.clasesTecnicas.ResultadoTiempoCierre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class RepoEntidades {
    private static RepoEntidades instance = null;

    public static RepoEntidades getInstance(){
        if(instance == null){
            instance = new RepoEntidades();
        }
        return instance;
    }
    private List<Organizacion> organizaciones = new ArrayList<>();
    private List<ServicioTransporte> serviciosDeTransporte = new ArrayList<>();


    public void agregar(ServicioTransporte servicioTransporte) {
        serviciosDeTransporte.add(servicioTransporte);
    }
    public ServicioTransporte obtenerPorTipo(MedioDeTransporte tipo){
        return serviciosDeTransporte.stream().filter(servicio -> servicio.getTipoTransporte() == tipo).findFirst().get();
    }

    public void agregar(Organizacion organizacion) {
        organizaciones.add(organizacion);
    }

    public Organizacion obtenerOrgPorNombre(String nombre){
        return organizaciones.stream().filter(organizacion -> organizacion.nombre == nombre).findFirst().get();
    }

    public List<Entidad> obtenerEntidades(){
        List<Entidad> entidades = new ArrayList<>(organizaciones);
        entidades.addAll(serviciosDeTransporte);
        return entidades;
    }


}
