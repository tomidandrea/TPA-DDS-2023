package Web.Controllers;

import dominio.clasesTecnicas.RepoUsuarios;
import dominio.clasesTecnicas.Usuario;
import excepciones.UsuarioInexistenteException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class LoginController {
    public ModelAndView index(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        model.put("login_error", request.session().attribute("login_error"));
        return new ModelAndView(model, "LogIn.hbs");
    }

    public ModelAndView post(Request request, Response response) {
        String usuario = request.queryParams("nombre");
        String password = request.queryParams("password");
        Usuario usuarioEncontrado;
        request.session().attribute("usuario_logueado", null);
        try {
            usuarioEncontrado = RepoUsuarios.getInstance().findByUsername(usuario);
        } catch (UsuarioInexistenteException e) {
            //request.session().attribute("login_error", true);
            response.redirect("/login");
            return null;
        }

        if (!usuarioEncontrado.getContrasenia().equals(password)) {
            request.session().attribute("login_error", true);
            response.redirect("/login");
            return null;
        }

        request.session().attribute("usuario_logueado", usuario);
        switch (usuarioEncontrado.getTipoUsuario()) {
            case MIEMBRO:
                request.session().attribute("tipo_usuario", "miembro");
                break;
            case ORGANIZACION:
                request.session().attribute("tipo_usuario", "organizacion");
                break;
            case ADMINISTRADOR:
                request.session().attribute("tipo_usuario", "administrador");
                break;
            case AGENTE_SECTORIAL:
                request.session().attribute("tipo_usuario", "agente_sectorial");
                break;
        }
        request.session().attribute("login_error", false);
        response.redirect("/home");
        return null;
    }
}
