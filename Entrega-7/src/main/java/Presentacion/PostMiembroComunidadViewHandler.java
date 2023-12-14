package Presentacion;

import dominio.comunidades.Comunidad;
import dominio.comunidades.Miembro;
import dominio.comunidades.RepoComunidades;
import dominio.comunidades.RepoMiembros;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;

import javax.persistence.EntityManager;

public class PostMiembroComunidadViewHandler implements Handler {
    @Override
    public void handle(Context context) throws Exception {
        String miembroId = context.formParam("miembroId");
        String comunidadId = context.pathParam("id");
        System.out.println("miembroId = " + miembroId);
        System.out.println("comunidadId = " + comunidadId);

        Miembro miembro = RepoMiembros.getInstance().getMiembro(Integer.parseInt(miembroId));
        System.out.println("miembro = " + miembro.getNombre());
        // Eliminamos al miembro de la comunidad
        Comunidad comunidad = RepoComunidades.getInstance().obtenerComunidadPorId(Integer.parseInt(comunidadId));
        comunidad.quitarMiembro(miembro);
        //atualizo db
        EntityManager em = Utils.BDUtils.getEntityManager();
        Utils.BDUtils.comenzarTransaccion(em);
        em.merge(comunidad);
        Utils.BDUtils.commit(em);
        em.close();
        context.redirect("/comunidades/" + comunidadId);

    }

}
