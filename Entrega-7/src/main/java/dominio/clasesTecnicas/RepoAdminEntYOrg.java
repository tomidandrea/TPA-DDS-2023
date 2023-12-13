package dominio.clasesTecnicas;

import Utils.BDUtils;
import dominio.comunidades.Miembro;

import javax.persistence.EntityManager;
import java.util.List;

public class RepoAdminEntYOrg {
    private static RepoAdminEntYOrg instance = null;
    private List<AdminEntidadOrganismo> administradores;

    private EntityManager em = BDUtils.getEntityManager();

    private RepoAdminEntYOrg() {
    }

    public static RepoAdminEntYOrg getInstance() {
        if (instance == null) {
        instance = new RepoAdminEntYOrg();
        }
        return instance;
    }

    public List<AdminEntidadOrganismo> getAdministradores() {
        return this.administradores;
    }

    public AdminEntidadOrganismo getAdministrador(int id_admin) {

        return em.createQuery("from AdminEntidadOrganismo where id = :id_admin ", AdminEntidadOrganismo.class)
                .setParameter("id_admin",id_admin)
                .getResultList().get(0);
    }
}
