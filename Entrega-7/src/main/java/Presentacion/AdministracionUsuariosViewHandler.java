package Presentacion;

import Presentacion.dto.UsuarioDTO;
import dominio.comunidades.*;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.*;
import java.util.stream.Collectors;

public class AdministracionUsuariosViewHandler implements Handler {

    @Override
    public void handle(Context context) throws Exception {

        String sessionId = context.sessionAttribute("sessionId");
        Miembro miembroLogueado = (Miembro) SesionManager.get().obtenerAtributos(sessionId).get("miembro");

        Set<UsuarioDTO> usuariosAfectados = new HashSet<>();
        Set<UsuarioDTO> usuariosObservadores = new HashSet<>();
        List<Comunidad> comunidadesMiembroLogueado = RepoComunidades.getInstance().obtenerComunidadesAdminPor(miembroLogueado);
        comunidadesMiembroLogueado.forEach(c->System.out.println("COMUNIDADES:" + c.getId() + ", " + c.getNombre()));
        for (Comunidad comunidad : comunidadesMiembroLogueado) {
            Set<UsuarioDTO> u = new HashSet<>(comunidad.getAfectados().stream().map(c->this.mapUsuarioToDTO(c,TipoMiembro.AFECTADO,"Afectados",comunidad)).collect(Collectors.toList()));
            for (UsuarioDTO usuarioDTO : u) {
                if (usuariosAfectados.stream().noneMatch(c -> c.getId() == usuarioDTO.getId())){
                    usuariosAfectados.add(usuarioDTO);
                } else {
                    UsuarioDTO usuario = usuariosAfectados.stream().filter(c->c.getId() == usuarioDTO.getId()).findFirst().get();
                    usuario.getComunidades().add(comunidad);
                }
            }
        }
        usuariosAfectados.forEach(c->System.out.println("USUARIOS AFECTADOS:" + c.getId() + ", " + c.getNombre() + ", " + c.getApellido()));
        for (Comunidad comunidad : comunidadesMiembroLogueado) {
            Set<UsuarioDTO> u = new HashSet<>(comunidad.getObservadores().stream().map(c -> this.mapUsuarioToDTO(c, TipoMiembro.OBSERVADOR, "Observador",comunidad)).collect(Collectors.toList()));
            for (UsuarioDTO usuarioDTO : u) {
                if(usuariosObservadores.stream().noneMatch(c->c.getId() == usuarioDTO.getId())){
                    usuariosObservadores.add(usuarioDTO);
                } else {
                    UsuarioDTO usuario = usuariosObservadores.stream().filter(c->c.getId() == usuarioDTO.getId()).findFirst().get();
                    usuario.getComunidades().add(comunidad);
                }
            }
        }
        usuariosObservadores.forEach(c->System.out.println("USUARIOS OBSERVADORES:" +c.getId() + ", " + c.getNombre() + ", " + c.getApellido()));

        Set<UsuarioDTO> usuarios = new HashSet<>(usuariosAfectados);
        usuarios.addAll(usuariosObservadores);
        usuarios.forEach(c->System.out.println("USUARIOS:" + c.getId() + ", " + c.getNombre() + ", " + c.getApellido()));


        var model = new HashMap<String, Object>();
        model.put("usuarios", usuarios);
        context.render("templates/administracionUsuarios.mustache", model);
    }

    private UsuarioDTO mapUsuarioToDTO(Miembro miembro, TipoMiembro tipo, String tipoU, Comunidad comunidad) {
        List<Comunidad> comunidades = new ArrayList<>();
        comunidades.add(comunidad);
        return new UsuarioDTO(miembro.getId(), miembro.getNombre(), miembro.getApellido(), miembro.getCorreoElectronico(), miembro.getMedioPreferido(), miembro.getNroDeTelefono(), miembro.getInteres(), miembro.getLocalizacion(), tipo, comunidades);
    }
}