package Presentacion;

import com.google.gson.Gson;
import dominio.clasesTecnicas.AdminEntidadOrganismo;
import dominio.empresasYorganismos.EntidadPropietaria;
import dominio.empresasYorganismos.RepoEntidadesPropietarias;
import dominio.servicios.RepoServicios;
import dominio.servicios.Servicio;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.List;

public class GetServiciosHandler implements Handler {
    @Override
    public void handle(Context context) throws Exception {

        String sessionId = context.sessionAttribute("sessionId");
        String rol = (String) SesionManager.get().obtenerAtributos(sessionId).get("rol");

        List<Servicio> servicios = new ArrayList<>();

        if(rol.equals("miembro")) {
            servicios = RepoServicios.getInstance().obtenerServicios();

        } else if (rol.equals("responsableEntidad")) {

            AdminEntidadOrganismo adminLogueado = (AdminEntidadOrganismo) SesionManager.get().obtenerAtributos(sessionId).get("usuario");
            EntidadPropietaria entidadPropietaria = RepoEntidadesPropietarias.getInstance().obtenerEntidadPropACargoDe(adminLogueado);
            servicios = entidadPropietaria.serviciosDePropiedades();

        } else {
            context.status(404);
        }

        context.json(new Gson().toJson(servicios));
    }
}
