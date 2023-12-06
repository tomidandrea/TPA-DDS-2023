package Presentacion;


import Persistencia.InstanciasMiembro;
import Presentacion.dto.LoginRequest;
import Presentacion.dto.LoginResponse;
import Utils.BDUtils;
import com.google.gson.Gson;
import com.sun.activation.registries.LogSupport;
import dominio.Localizacion.Provincia;
import dominio.Notificacion.MedioCorreo;
import dominio.comunidades.*;
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

        LoginRequest loginRequest = new Gson().fromJson(bodyLoginRequest, LoginRequest.class);
        System.out.println("Login: " + loginRequest);
        //TODO validar email y contraseña (ver si hay que ir a buscar al repo de miembros o a otro lado)
        //busco al miembro por email(tambien tiene que ser por contraseña y si no se valida devolver error en autenticacion)
        Miembro miembroLogueado = repoMiembros.getInstance().obtenerMiembroPorEmail(loginRequest.getEmail());
        System.out.println("El miebro logueado es: " + miembroLogueado);
        //Una vez que tenemos al miembro creamos la sesion en el backend y respondemos con el id de la sesion al front
        SesionManager sesionManager = SesionManager.get();
        String idSesion = sesionManager.crearSesion("miembro", miembroLogueado);

        //Agreggamos los atributos que querramos tambien guardar en la sesion
        //busco las comunidades del miembro y las agrego a la sesion

        List<Comunidad> comunidades = RepoComunidades.getInstance().filtrarPorMiembro(miembroLogueado.getId());
        sesionManager.agregarAtributo(idSesion, "comunidades", comunidades);
        System.out.println("Las comunidades del miembro: " + miembroLogueado.getNombre() + " logueado son: " + comunidades);

        //sesionManager.agregarAtributo(idSesion, "rol", repoRoles.getByUser(idUser));
        context.sessionAttribute("sessionId", idSesion);
        //devuelvo id de la sesion
        context.json(new Gson().toJson(new LoginResponse(idSesion)));

    }

}