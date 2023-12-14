package Presentacion;

import Presentacion.dto.MiembroDTO;
import Utils.BDUtils;
import com.google.gson.Gson;
import dominio.comunidades.Comunidad;
import dominio.comunidades.Incidente;
import dominio.comunidades.RepoComunidades;
import dominio.comunidades.RepoIncidentes;
import dominio.servicios.Agrupacion;
import dominio.servicios.RepoServicios;
import dominio.servicios.Servicio;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class PostComunidadMiembroHandler implements Handler {
    @Override
    public void handle(Context context) throws Exception {
        String miembroJson = context.body();
        String idComunidad = context.pathParam("id");
        Comunidad comunidad = RepoComunidades.getInstance().obtenerComunidadPorId(Integer.parseInt(idComunidad));
        System.out.println(miembroJson);
        System.out.println("COMUNIDAD:" + comunidad.getId() + ", " + comunidad.getNombre());

        MiembroDTO miembroDTO = new Gson().fromJson(miembroJson, MiembroDTO.class);
        comunidad.removerMiembro(miembroDTO.getId(),miembroDTO.getTipo());

        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);
        em.merge(comunidad);
        BDUtils.commit(em);
        em.close();

        context.status(201);
    }
}
