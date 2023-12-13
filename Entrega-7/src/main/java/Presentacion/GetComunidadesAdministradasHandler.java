package Presentacion;

import com.google.gson.Gson;
import dominio.comunidades.Comunidad;
import dominio.comunidades.Miembro;
import dominio.comunidades.RepoComunidades;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.HashSet;
import java.util.Set;

public class GetComunidadesAdministradasHandler implements Handler {

  @Override
  public void handle(Context context) throws Exception {
      String idSesion = context.pathParam("id_sesion");
      Miembro miembroLogueado = (Miembro) SesionManager.get().obtenerAtributos(idSesion).get("miembro");

      Set<Comunidad> comunidades = new HashSet<>(RepoComunidades.getInstance().obtenerComunidadesAdminPor(miembroLogueado));
      String jsonString= new Gson().toJson(comunidades);
      System.out.println(jsonString);
      context.json(jsonString);
  }
}
