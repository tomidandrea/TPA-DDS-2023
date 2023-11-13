package Presentacion;

import com.google.gson.Gson;
import dominio.comunidades.Incidente;
import dominio.comunidades.RepoIncidentes;
import dominio.servicios.RepoServicios;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class PostIncidenteHandler implements Handler {
    @Override
    public void handle(Context context) throws Exception {
        String incidenteJson = context.body();
        System.out.println(incidenteJson);
        //filtro segun cnatidad de servicios recibidos
        // si recibo en el json mas de 1 servicio, seteo los mismos en la lista de agrupacion de incidente
        // si recibo 1 servicio, seteo el mismo en el servicio de incidente
        // si no recibo ningun servicio, no seteo nada

        IncidenteParser incidenteParser = new Gson().fromJson(incidenteJson, IncidenteParser.class);
        System.out.println(incidenteParser.getObservacion());
        System.out.println(incidenteParser.getServicios());
        context.status(201);
    }
}
