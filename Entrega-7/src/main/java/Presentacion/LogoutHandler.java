package Presentacion;

import Presentacion.dto.LoginRequest;
import Presentacion.dto.LoginResponse;
import com.google.gson.Gson;
import dominio.comunidades.Comunidad;
import dominio.comunidades.Miembro;
import dominio.comunidades.RepoComunidades;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class LogoutHandler implements Handler {

    public void handle(@NotNull Context context) throws Exception {

        //obtengo el id que quiero eliminar (directamente es el body de la request)
        String id_sesion = context.body();
        System.out.println("id a eliminiar: " + id_sesion);

        SesionManager sesionManager = SesionManager.get();

        //elimino la sesion del sesion manager
        sesionManager.eliminarSesion(id_sesion);

        //devuelvo estado ok
        context.status(200);

    }

}
