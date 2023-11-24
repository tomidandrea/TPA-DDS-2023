package Presentacion;

import com.google.gson.Gson;
import dominio.servicios.RepoServicios;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class GetServiciosHandler implements Handler {
    @Override
    public void handle(Context context) throws Exception {

        context.json(new Gson().toJson(RepoServicios.getInstance().obtenerServicios()));
    }
}
