package Presentacion;

import Presentacion.dto.ComunidadDTO;
import Presentacion.dto.IncidenteDTO;
import com.google.gson.Gson;
import dominio.comunidades.Comunidad;
import dominio.comunidades.Miembro;
import dominio.comunidades.RepoComunidades;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GetComunidadesAdministradasHandler implements Handler {

  @Override
  public void handle(Context context) throws Exception {
    String idSesion = context.pathParam("id_sesion");
    Miembro miembroLogueado = (Miembro) SesionManager.get().obtenerAtributos(idSesion).get("usuario");

    Set<Comunidad> comunidades = new HashSet<>(RepoComunidades.getInstance().obtenerComunidadesAdminPor(miembroLogueado));
    List<ComunidadDTO> comunidadDTOs = new ArrayList<>();
    for(Comunidad comunidad:comunidades){
      ComunidadDTO comunidadDTO = new ComunidadDTO(comunidad);
      // Agregar el DTO a la lista
      comunidadDTOs.add(comunidadDTO);
    }
    String jsonString= new Gson().toJson(comunidadDTOs);
    System.out.println(jsonString);
    context.json(jsonString);
  }
}
