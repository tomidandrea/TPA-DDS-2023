package Presentacion;

import com.google.gson.Gson;
import dominio.empresasYorganismos.EntidadPropietaria;
import dominio.empresasYorganismos.RepoEntidadesPropietarias;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class GetServiciosEntPropietariaHandler implements Handler {

    @Override
    public void handle(Context context) throws Exception {

        EntidadPropietaria entidadPropietaria = RepoEntidadesPropietarias.getInstance().obtenerEntidadPropPorId(1);

        context.json(new Gson().toJson(entidadPropietaria.serviciosDePropiedades()));
    }
}
