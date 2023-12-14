package Presentacion;

import Presentacion.dto.ComunidadDTO;
import Presentacion.dto.MiembroDTO;
import Presentacion.dto.UsuarioDTO;
import com.google.gson.Gson;
import dominio.clasesTecnicas.Usuario;
import dominio.comunidades.*;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GetMiembrosComunidadAdministradaHandler implements Handler {

  @Override
  public void handle(Context context) throws Exception {
    String idSesion = context.pathParam("id_sesion");
    String idComunidad = context.pathParam("id");
    Comunidad comunidad = RepoComunidades.getInstance().obtenerComunidadPorId(Integer.parseInt(idComunidad));
    System.out.println("COMUNIDAD:" + comunidad.getId() + ", " + comunidad.getNombre());

    String filtro = context.queryParam("tipoMiembro");
    System.out.println(filtro);
    Set<MiembroDTO> miembroDTOS = new HashSet<>();
    if(filtro.equals("afectado") || filtro.equals("todos")){
      List<MiembroDTO> afectados = comunidad.getAfectados().stream().map(afectado-> new MiembroDTO(afectado, TipoMiembro.AFECTADO)).collect(Collectors.toList());
      miembroDTOS.addAll(afectados);
      System.out.println("Agrego afectados");
    }
    if(filtro.equals("observador") || filtro.equals("todos")){
     List<MiembroDTO> observadores = comunidad.getObservadores().stream().map(observador-> new MiembroDTO(observador, TipoMiembro.OBSERVADOR)).collect(Collectors.toList());
      miembroDTOS.addAll(observadores);
      System.out.println("Agrego observadores");
    }

    ComunidadDTO comunidadDTO = new ComunidadDTO(comunidad, miembroDTOS.stream().toList());

    String jsonString= new Gson().toJson(comunidadDTO);
    System.out.println(jsonString);
    context.json(jsonString);
  }

}
