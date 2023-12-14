package Presentacion;

import Presentacion.dto.IncidenteDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dominio.clasesTecnicas.HibernateProxyTypeAdapter;
import dominio.comunidades.*;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetUsuarioRolHandler implements Handler {
    @Override
    public void handle(Context context) throws Exception {
        String idSesion = context.pathParam("id_sesion");
        Map<String,Object> session = SesionManager.get().obtenerAtributos(idSesion);
        String rol = (String) session.get("rol");

        String jsonString= new Gson().toJson(rol);
        System.out.println(jsonString);
        context.json(jsonString);
    }
}
