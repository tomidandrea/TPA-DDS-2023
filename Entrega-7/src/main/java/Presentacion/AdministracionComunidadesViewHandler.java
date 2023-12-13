package Presentacion;

import Presentacion.dto.UsuarioDTO;
import dominio.comunidades.*;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.*;
import java.util.stream.Collectors;

public class AdministracionComunidadesViewHandler implements Handler {

    @Override
    public void handle(Context context) throws Exception {

        String sessionId = context.sessionAttribute("sessionId");
        Miembro miembroLogueado = (Miembro) SesionManager.get().obtenerAtributos(sessionId).get("usuario");

        Set<Comunidad> comunidades = new HashSet<>(RepoComunidades.getInstance().obtenerComunidadesAdminPor(miembroLogueado));

        boolean resultado = !comunidades.isEmpty();

        var model = new HashMap<String, Object>();
        model.put("comunidades", comunidades);
        model.put("esAdmin", resultado);
        context.render("templates/administracionComunidades.mustache", model);
    }
}