package Presentacion;


import Persistencia.InstanciasMiembro;
import Presentacion.dto.LoginRequest;
import Presentacion.dto.LoginResponse;
import Utils.BDUtils;
import com.google.gson.Gson;
import com.sun.activation.registries.LogSupport;
import dominio.Localizacion.Provincia;
import dominio.Notificacion.MedioCorreo;
import dominio.clasesTecnicas.AdminEntidadOrganismo;
import dominio.clasesTecnicas.AdministradorSistema;
import dominio.clasesTecnicas.RepoUsuarios;
import dominio.clasesTecnicas.Usuario;
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
        System.out.println("Este es el body de la solicitud de logueo: " +  bodyLoginRequest);

        LoginRequest loginRequest = new Gson().fromJson(bodyLoginRequest, LoginRequest.class);
        System.out.println("Login: " + loginRequest);
        //TODO validar email y contraseña (ver si hay que ir a buscar al repo de miembros o a otro lado)
        //busco al miembro por email(tambien tiene que ser por contraseña y si no se valida devolver error en autenticacion)
        Usuario usuarioLogueado = RepoUsuarios.getInstance().obtenerUsuarioPorEmail(loginRequest.getEmail());
        System.out.println("El usuario logueado es: " + usuarioLogueado);

        String rol;
        if (usuarioLogueado != null) {
            //Una vez que tenemos al usuario creamos la sesion en el backend y respondemos con el id de la sesion al front
            SesionManager sesionManager = SesionManager.get();
            String idSesion = sesionManager.crearSesion("usuario", usuarioLogueado);
            if (usuarioLogueado instanceof Miembro) {
                // La instancia es de la subclase Miembro
                Miembro miembro = (Miembro) usuarioLogueado;
                // Realizar operaciones específicas del Miembro
                sesionManager.agregarAtributo(idSesion, "rol", "miembro");
                rol = "miembro";
                //busco las comunidades del miembro y las agrego a la sesion
                List<Comunidad> comunidades = RepoComunidades.getInstance().filtrarPorMiembro(miembro.getId());
                sesionManager.agregarAtributo(idSesion, "comunidades", comunidades);
                System.out.println("Las comunidades del miembro: " + miembro.getNombre() + " logueado son: " + comunidades);
                //obtengo comunidades que administra
                List<Comunidad> comunidadesAdministradas = RepoComunidades.getInstance().comunidadesAdministradasPor(miembro, comunidades);
                sesionManager.agregarAtributo(idSesion, "comunidades que administra", comunidadesAdministradas);
            } else if (usuarioLogueado instanceof AdminEntidadOrganismo) {
                // La instancia es de la subclase AdminEntidadOrganismo
                AdminEntidadOrganismo adminEntidadOrg = (AdminEntidadOrganismo) usuarioLogueado;
                // Realizar operaciones específicas de AdminEntidadOrganismo
                sesionManager.agregarAtributo(idSesion, "rol", "responsableEntidad");
                rol = "responsableEntidad";

            } else {
                //es administrador del sistema
                AdministradorSistema adminDelSistema = (AdministradorSistema) usuarioLogueado;
                sesionManager.agregarAtributo(idSesion, "rol", "AdminSistema");
                rol = "AdminSistema";
            }


            System.out.println("Rol del usuario: " + sesionManager.obtenerAtributos(idSesion).get("rol"));
            context.sessionAttribute("sessionId", idSesion);
            //devuelvo id de la sesion
            context.json(new Gson().toJson(new LoginResponse(idSesion,rol)));
            //context.redirect("/static/menuDeInicio.html");

        } else {
            // No se encontró ningún usuario con el ID proporcionado
            context.status(404);
        }

    }

}