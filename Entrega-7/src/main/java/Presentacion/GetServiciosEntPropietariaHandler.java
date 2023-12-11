package Presentacion;

import com.google.gson.Gson;
import dominio.clasesTecnicas.AdminEntidadOrganismo;
import dominio.comunidades.Miembro;
import dominio.empresasYorganismos.EntidadPropietaria;
import dominio.empresasYorganismos.RepoEntidadesPropietarias;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class GetServiciosEntPropietariaHandler implements Handler {

    @Override
    public void handle(Context context) throws Exception {
        String sessionId = context.sessionAttribute("sessionId");
        AdminEntidadOrganismo adminLogueado = (AdminEntidadOrganismo) SesionManager.get().obtenerAtributos(sessionId).get("usuario");

        EntidadPropietaria entidadPropietaria = RepoEntidadesPropietarias.getInstance().obtenerEntidadPropACargoDe(adminLogueado);

        context.json(new Gson().toJson(entidadPropietaria.serviciosDePropiedades()));
    }
}
