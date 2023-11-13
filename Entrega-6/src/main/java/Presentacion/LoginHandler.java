package Presentacion;


import Persistencia.InstanciasMiembro;
import Presentacion.dto.LoginRequest;
import Presentacion.dto.LoginResponse;
import Utils.BDUtils;
import com.google.gson.Gson;
import com.sun.activation.registries.LogSupport;
import dominio.Localizacion.Provincia;
import dominio.Notificacion.MedioCorreo;
import dominio.comunidades.Miembro;
import dominio.comunidades.Notificador;
import dominio.comunidades.RepoMiembros;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import java.util.List;

public class LoginHandler implements Handler {

    private final RepoMiembros repoMiembros;

    public LoginHandler() {
        this.repoMiembros = new RepoMiembros();
    }

    @Override
    public void handle(@NotNull Context context) throws Exception {
        //validamos user/pass y buscamos datos de ese usuario para agregar en la sesión

        String bodyLoginRequest = context.body();
        System.out.println("Este es el body: " +  bodyLoginRequest);
        //TODO  deserializar no funciona
        //LoginRequest loginRequest = context.bodyAsClass(LoginRequest.class);
        //LoginRequest loginRequest = new Gson().fromJson( bodyLoginRequest, LoginRequest.class);
        //System.out.println("Login: " + loginRequest);
        //TODO validar email y contraseña (ver si hay que ir a buscar al repo de miembros o a otro lado)

        //Una vez que tenemos al miembro creamos la sesion en el backend y respondemos con el id de la sesion al front
        SesionManager sesionManager = SesionManager.get();
        String idSesion = sesionManager.crearSesion("usuario", "miembro");   // TODO en el sring miembro en realidad tiene que ir el objeto miembro

        //Agreggamos los atributos que querramos tambien guardar en la sesion
        //sesionManager.agregarAtributo(idSesion, "fechaInicio", new Date());
        //sesionManager.agregarAtributo(idSesion, "rol", repoRoles.getByUser(idUser));

        context.json(new Gson().toJson(new LoginResponse(idSesion)));

    }

}