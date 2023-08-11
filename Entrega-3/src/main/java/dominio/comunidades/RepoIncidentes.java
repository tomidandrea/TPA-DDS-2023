package dominio.comunidades;

import dominio.servicios.Agrupacion;
import dominio.servicios.Servicio;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RepoIncidentes {
    static RepoIncidentes instance = new RepoIncidentes();
    List<Incidente> incidentes = new ArrayList<>();

    public static RepoIncidentes getInstance() {
        return instance;
    }

    public List<Incidente> obtenerIncidentesPorEstado(EstadoIncidente estado){
        return (List<Incidente>) incidentes.stream().filter(i->i.tieneEstado(estado));
    }

    public void agregar(Incidente incidente) {
        incidentes.add(incidente);
    }

    public List<Duration> obtenerTiempos(){
        return new ArrayList<>();
    }

    public List<Incidente> obtenerIncidentesDe(List<Agrupacion>agrupaciones, List <Servicio> servicios){
        return incidentes.stream()
                .filter(i-> i.getHorarioCierre()!=null && i.cerradoUltimaSemana())
                .filter(incidente->(agrupaciones.contains(incidente.getAgrupacion()) || servicios.contains(incidente.getServicio()))).toList();
    }

    public List<Incidente> obtenerIncidentesDe(List <Servicio> servicios){
        return incidentes.stream()
                .filter(i-> i.getHorarioCierre()!=null && i.cerradoUltimaSemana())
                .filter(incidente->servicios.contains(incidente.getServicio())).toList();
    }


}
