package ddsGrupo1.entidades;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepoServiciosDeTransporte {
    private static RepoServiciosDeTransporte instance = null;

    public static RepoServiciosDeTransporte getInstance(){
        if(instance == null){
            instance = new RepoServiciosDeTransporte();
        }
        return instance;
    }

    List<ServicioTransporte> serviciosDeTransporte = new ArrayList<>();

    public void agregar(ServicioTransporte servicioTransporte) {
        serviciosDeTransporte.add(servicioTransporte);
    }
        public ServicioTransporte obtenerPorTipo(MedioDeTransporte tipo){
        return serviciosDeTransporte.stream().filter(servicio -> servicio.tipoTransporte == tipo).findFirst().get();
    }
}
