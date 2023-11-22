package Presentacion;

import Presentacion.dto.IncidenteDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import dominio.comunidades.Comunidad;
import dominio.comunidades.EstadoIncidente;
import dominio.comunidades.Incidente;
import dominio.comunidades.RepoIncidentes;
import dominio.servicios.RepoServicios;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GetIncidentesPorEstadoHandler implements Handler {
    @Override
    public void handle(Context context) throws Exception {
        String filtro = context.queryParam("estado");
        List<Incidente> incidentes = new ArrayList<>();

        switch(filtro){
            case "abierto":
                incidentes = RepoIncidentes.getInstance().obtenerIncidentesPorEstado(EstadoIncidente.ABIERTO);
                break;
            case "cerrado":
                incidentes = RepoIncidentes.getInstance().obtenerIncidentesPorEstado(EstadoIncidente.CERRADO);
                break;
            default:
                incidentes = RepoIncidentes.getInstance().obtenerIncidentes();
                break;
            }
        List<IncidenteDTO> incidenteDTOs = new ArrayList<>();
        for (Incidente incidente : incidentes) {
            IncidenteDTO incidenteDTO = new IncidenteDTO();
            incidenteDTO.setId(incidente.getId());
            incidenteDTO.setServicio(incidente.getServicio());
            incidenteDTO.setAgrupacion(incidente.getAgrupacion());
            incidenteDTO.setObservacion(incidente.getObservacion());
            incidenteDTO.setComunidades(incidente.getComunidades().stream().map(Comunidad::getNombre).toList());
            // Mapeo de horarioApertura a String
            LocalDateTime horarioApertura = incidente.getHorarioApertura();
            incidenteDTO.setHorarioApertura(horarioApertura != null ? horarioApertura.toString() : null);
            LocalDateTime horarioCierre = incidente.getHorarioCierre();
            incidenteDTO.setHorarioCierre(horarioCierre != null ? horarioCierre.toString() : null);
            incidenteDTO.setEstadoIncidente(incidente.getEstadoIncidente());
            // Agregar el DTO a la lista
            incidenteDTOs.add(incidenteDTO);
        }
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(incidentes);
//        System.out.println(jsonString);
        String jsonString= new Gson().toJson(incidenteDTOs);
        System.out.println(jsonString);
        context.json(jsonString);
        }


        }
