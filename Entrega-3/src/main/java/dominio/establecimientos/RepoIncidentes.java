package dominio.establecimientos;

import dominio.comunidades.Incidente;
import dominio.entidades.Entidad;
import dominio.servicios.Agrupacion;
import dominio.servicios.Servicio;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class RepoIncidentes {
    // singleton
    private static RepoIncidentes instance = null;

    public static RepoIncidentes getInstance(){
        if(instance == null){
            instance = new RepoIncidentes();
        }
        return instance;
    }

    List<Incidente> incidentes = new ArrayList<>();

    public List<Duration> obtenerTiempos(){
        return new ArrayList<>();
    }

    public List<Incidente> obtenerIncidentesDe(List<Agrupacion>agrupaciones,List <Servicio> servicios){
        return (List<Incidente>) incidentes.stream().filter(incidente->agrupaciones.contains(incidente.getAgrupacion()) || servicios.contains(incidente.getServicio()));
    }
}
