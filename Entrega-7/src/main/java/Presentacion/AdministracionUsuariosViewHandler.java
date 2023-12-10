package Presentacion;

import Presentacion.dto.UsuarioDTO;
import dominio.comunidades.Comunidad;
import dominio.comunidades.Miembro;
import dominio.comunidades.RepoComunidades;
import dominio.comunidades.TipoMiembro;
import io.javalin.http.Handler;
import io.javalin.http.Context;

import java.util.*;
import java.util.stream.Collectors;

public class AdministracionUsuariosViewHandler implements Handler {

    public void handle(Context context) throws Exception{

        String idComunidad = context.pathParam("id");

        Set<UsuarioDTO> usuariosAfectados = new HashSet<>();
        Set<UsuarioDTO> usuariosObservadores = new HashSet<>();
        Comunidad comunidad = RepoComunidades.getInstance().obtenerComunidadPorId(Integer.parseInt(idComunidad));
        System.out.println("COMUNIDAD:" + comunidad.getId() + ", " + comunidad.getNombre());

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
//        for (Comunidad comunidad : comunidadesMiembroLogueado) {
//            Set<UsuarioDTO> u = new HashSet<>(comunidad.getAfectados().stream().map(c->this.mapUsuarioToDTO(c, TipoMiembro.AFECTADO,"Afectados",comunidad)).collect(Collectors.toList()));
//            for (UsuarioDTO usuarioDTO : u) {
//                if (usuariosAfectados.stream().noneMatch(c -> c.getId() == usuarioDTO.getId())){
//                    usuariosAfectados.add(usuarioDTO);
//                } else {
//                    UsuarioDTO usuario = usuariosAfectados.stream().filter(c->c.getId() == usuarioDTO.getId()).findFirst().get();
//                    usuario.getComunidades().add(comunidad);
//                }
//            }
//        }
//        usuariosAfectados.forEach(c->System.out.println("USUARIOS AFECTADOS:" + c.getId() + ", " + c.getNombre() + ", " + c.getApellido()));
//        for (Comunidad comunidad : comunidadesMiembroLogueado) {
//            Set<UsuarioDTO> u = new HashSet<>(comunidad.getObservadores().stream().map(c -> this.mapUsuarioToDTO(c, TipoMiembro.OBSERVADOR, "Observador",comunidad)).collect(Collectors.toList()));
//            for (UsuarioDTO usuarioDTO : u) {
//                if(usuariosObservadores.stream().noneMatch(c->c.getId() == usuarioDTO.getId())){
//                    usuariosObservadores.add(usuarioDTO);
//                } else {
//                    UsuarioDTO usuario = usuariosObservadores.stream().filter(c->c.getId() == usuarioDTO.getId()).findFirst().get();
//                    usuario.getComunidades().add(comunidad);
//                }
//            }
//        }
//        usuariosObservadores.forEach(c->System.out.println("USUARIOS OBSERVADORES:" +c.getId() + ", " + c.getNombre() + ", " + c.getApellido()));

        Set<UsuarioDTO> usuarios = new HashSet<>(usuariosAfectados);
        usuarios.addAll(usuariosObservadores);
        usuarios.forEach(c->System.out.println("USUARIOS:" + c.getId() + ", " + c.getNombre() + ", " + c.getApellido()));

        var model = new HashMap<String, Object>();
        model.put("usuarios", usuarios);
        context.render("templates/administracionUsuarios.mustache", model);
    }

    private UsuarioDTO mapUsuarioToDTO(Miembro miembro, TipoMiembro tipo, Comunidad comunidad) {
        List<Comunidad> comunidades = new ArrayList<>();
        comunidades.add(comunidad);
        return new UsuarioDTO(miembro.getId(), miembro.getNombre(), miembro.getApellido(), miembro.getCorreoElectronico(), miembro.getMedioPreferido(), miembro.getNroDeTelefono(), miembro.getInteres(), miembro.getLocalizacion(), tipo, comunidades);
    }
}