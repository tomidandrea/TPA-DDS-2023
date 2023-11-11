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
        //Incidente incidente = new Gson().fromJson(incidenteJson, Incidente.class);
        //System.out.println("-------");
        //System.out.println(incidente.getObservacion());
        //RepoIncidentes.getInstance().persistirIncidente(incidente);
        context.status(201);
    }
}
