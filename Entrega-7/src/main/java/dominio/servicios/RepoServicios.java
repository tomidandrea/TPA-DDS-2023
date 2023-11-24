package dominio.servicios;

import Utils.BDUtils;
import dominio.comunidades.Comunidad;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RepoServicios {
    private List<Servicio> servicios;
    private static RepoServicios instance = null;
    private EntityManager em = BDUtils.getEntityManager();

    private RepoServicios(){
        servicios = new ArrayList<>();
    }

    public static RepoServicios getInstance(){
        if(instance == null){
            instance = new RepoServicios();
        }
        return instance;
    }

    public List<Servicio> obtenerServicios() {
        return em
                .createQuery("from Servicio")
                .getResultList();
    }

    public Servicio obtenerServicioPorId(int id) {
        return em.find(Servicio.class, id);
    }

    public List<Servicio> serviciosQueFaltan(List<Servicio> servicios){
        return new ArrayList<>();
    }
}
