package Presentacion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import dominio.comunidades.EstadoIncidente;
import dominio.comunidades.Incidente;
import dominio.comunidades.RepoIncidentes;
import dominio.servicios.RepoServicios;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.List;

public class GetIncidentesPorEstadoHandler implements Handler {
    @Override
    public void handle(Context context) throws Exception {
        String filtro = context.queryParam("estado");
        List<Incidente> incidentes;

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
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(incidentes);
        System.out.println(jsonString);
        context.json(new Gson().toJson(incidentes));
        }


        }
