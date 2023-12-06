package Presentacion;

import Utils.BDUtils;
import dominio.comunidades.Incidente;
import dominio.comunidades.RepoComunidades;
import dominio.comunidades.RepoIncidentes;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PostIncidenteViewHandler implements Handler {
    @Override
    public void handle(Context context) throws Exception {
        // Handle the form submission here
        List<Incidente> incidentes = RepoIncidentes.getInstance().obtenerIncidentes();
        // Get the ID of the chosen incident from the form data
        String chosenIncidenteId = context.formParam("incidenteId");
        System.out.println("chosenIncidenteId = " + chosenIncidenteId);
        // Find the incident with the given ID
        Optional<Incidente> chosenIncidente = incidentes.stream()
            .filter(incidente -> Objects.equals(incidente.getId(), Integer.parseInt(chosenIncidenteId)))
            .findFirst();

        // If the incident was found, close it
        chosenIncidente.ifPresent(incidente -> {
            System.out.println("Cerrando incidente " + incidente.getId());
            incidente.cerrar();
        });

        context.redirect("/incidentes", HttpStatus.forStatus(303));
    }
}
