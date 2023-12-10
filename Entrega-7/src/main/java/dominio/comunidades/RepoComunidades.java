package dominio.comunidades;

import Utils.BDUtils;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class RepoComunidades {
    private EntityManager em = BDUtils.getEntityManager();
    private static RepoComunidades instance = null;
    private List<Comunidad> comunidades = new ArrayList<>();

    public static RepoComunidades getInstance(){
        if(instance == null){
            instance = new RepoComunidades();
        }
        return instance;
    }

    public RepoComunidades() {
        this.comunidades = em.createQuery("from Comunidad").getResultList();
    }

    public List<Comunidad> getComunidades() {
        return comunidades;
    }

    public List<Comunidad> filtrarPorMiembro(int idMiembro) {
        return comunidades.stream().filter(c -> c.tieneAlMiembro(idMiembro)).toList();
    }
    public List<Comunidad> obtenerComunidadesPor(Miembro miembro){
        return em.createQuery("SELECT c FROM Comunidad c WHERE :miembro MEMBER OF c.observadores OR :miembro MEMBER OF c.afectados", Comunidad.class)
                .setParameter("miembro", miembro)
                .getResultList();
    }

    public List<Comunidad> obtenerComunidadesAdminPor(Miembro miembro){
        return em.createQuery("SELECT c FROM Comunidad c WHERE :miembro MEMBER OF c.administradores", Comunidad.class)
                .setParameter("miembro", miembro)
                .getResultList();
    }

    public Comunidad obtenerComunidadPorId(int comunidad_id){
        return em.createQuery("SELECT c FROM Comunidad c WHERE c.id = :comunidad_id", Comunidad.class)
                .setParameter("comunidad_id", comunidad_id)
                .getSingleResult();
    }

    public void agregarComunidad(Comunidad comu) {
        comunidades.add(comu);
    }

    public void actualizar(Comunidad comunidad) {
        em.getTransaction().begin();
        em.merge(comunidad);
        em.getTransaction().commit();
    }
}
