package dominio.clasesTecnicas;

import Utils.BDUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class RepoUsuarios {
    private static RepoUsuarios instance = null;
    private List<Usuario> usuarios;

    private EntityManager em = BDUtils.getEntityManager();

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

    public Usuario obtenerUsuarioPorEmail(String email) {
        return em.createQuery("from Usuario where correoElectronico = :email", Usuario.class)
                .setParameter("email", email)
                .getResultList().get(0);
    }
}
