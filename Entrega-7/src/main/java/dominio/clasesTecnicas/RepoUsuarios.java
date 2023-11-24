package dominio.clasesTecnicas;

import java.util.List;

public class RepoUsuarios {
    private static RepoUsuarios instance = null;
    private List<Usuario> usuarios;

    private RepoUsuarios() {
    }

    public static RepoUsuarios getInstance() {
        if (instance == null) {
        instance = new RepoUsuarios();
        }
        return instance;
    }

    public List<Usuario> getUsuario() {
        return this.usuarios;
    }

    public Usuario findByUsername(String usuario) {
        return usuarios.stream().filter(u -> u.getUsuario().equals(usuario)).findFirst().orElse(null);
    }
}
