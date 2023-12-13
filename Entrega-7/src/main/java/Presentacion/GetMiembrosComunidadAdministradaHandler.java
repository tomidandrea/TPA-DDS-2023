package Presentacion;

import Presentacion.dto.UsuarioDTO;
import com.google.gson.Gson;
import dominio.comunidades.Comunidad;
import dominio.comunidades.Miembro;
import dominio.comunidades.RepoComunidades;
import dominio.comunidades.TipoMiembro;
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

    Set<UsuarioDTO> usuariosAfectados = new HashSet<>();
    Set<UsuarioDTO> usuariosObservadores = new HashSet<>();

    Set<UsuarioDTO> u = new HashSet<>(comunidad.getAfectados().stream().map(c->this.mapUsuarioToDTO(c, TipoMiembro.AFECTADO, comunidad)).collect(Collectors.toList()));
    for (UsuarioDTO usuarioDTO : u) {
      if (usuariosAfectados.stream().noneMatch(c -> c.getId() == usuarioDTO.getId())){
        usuariosAfectados.add(usuarioDTO);
      } else {
        UsuarioDTO usuario = usuariosAfectados.stream().filter(c->c.getId() == usuarioDTO.getId()).findFirst().get();
        usuario.getComunidades().add(comunidad);
      }
    }

    Set<UsuarioDTO> u2 = new HashSet<>(comunidad.getObservadores().stream().map(c->this.mapUsuarioToDTO(c, TipoMiembro.OBSERVADOR, comunidad)).collect(Collectors.toList()));
    for (UsuarioDTO usuarioDTO : u2) {
      if (usuariosObservadores.stream().noneMatch(c -> c.getId() == usuarioDTO.getId())){
        usuariosObservadores.add(usuarioDTO);
      } else {
        UsuarioDTO usuario = usuariosObservadores.stream().filter(c->c.getId() == usuarioDTO.getId()).findFirst().get();
        usuario.getComunidades().add(comunidad);
      }
    }

    Set<UsuarioDTO> usuarios = new HashSet<>(usuariosAfectados);
    usuarios.addAll(usuariosObservadores);


    String jsonString= new Gson().toJson(usuarios);
    System.out.println(jsonString);
    context.json(jsonString);
  }

  private UsuarioDTO mapUsuarioToDTO(Miembro miembro, TipoMiembro tipo, Comunidad comunidad) {
    List<Comunidad> comunidades = new ArrayList<>();
    comunidades.add(comunidad);
    return new UsuarioDTO(miembro.getId(), miembro.getNombre(), miembro.getApellido(), miembro.getCorreoElectronico(), miembro.getMedioPreferido(), miembro.getNroDeTelefono(), miembro.getInteres(), miembro.getLocalizacion(), tipo, comunidades);
  }
}
