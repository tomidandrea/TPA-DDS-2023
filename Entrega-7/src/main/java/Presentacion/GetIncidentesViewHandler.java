package Presentacion;

import dominio.comunidades.Incidente;
import dominio.comunidades.Miembro;
import dominio.comunidades.RepoIncidentes;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.*;

public class GetIncidentesViewHandler implements Handler {

    @Override
    public void handle(Context context) throws Exception {
        String sessionId = context.sessionAttribute("sessionId");

        Miembro miembroLogueado = (Miembro) SesionManager.get().obtenerAtributos(sessionId).get("usuario");
        Set<Incidente> incidentes = new HashSet<>(RepoIncidentes.getInstance().obtenerIncidentesPorMiembro(miembroLogueado));

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("incidentes", incidentes);

        // Add cache control headers to the response
        context.header("Cache-Control", "no-store, must-revalidate");
        context.header("Pragma", "no-cache");
        context.header("Expires", "0");

        context.render("templates/cierreIncidentes.mustache", model);
    }

}
