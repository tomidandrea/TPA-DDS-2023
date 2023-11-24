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

    public void agregarComunidad(Comunidad comu) {
        comunidades.add(comu);
    }
}
