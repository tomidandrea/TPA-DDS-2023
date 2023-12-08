package dominio.comunidades;

import Utils.BDUtils;
import dominio.servicios.Servicio;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class RepoMiembros {

    private static RepoMiembros instance = null;
    private EntityManager em = BDUtils.getEntityManager();

    public static RepoMiembros getInstance(){
        if(instance == null){
            instance = new RepoMiembros();
        }
        return instance;
    }
    List<Miembro> miembros = new ArrayList<>();

    //get miembro por id

    public Miembro getMiembro(int id_miembro){

        //return miembros.stream().filter(miembro -> miembro.compararId(id)).findFirst().get();

        return em.createQuery("from Miembro where id = :id_miembro ", Miembro.class)
                .setParameter("id_miembro",id_miembro)
                .getResultList().get(0);
    }

    public Miembro obtenerMiembroPorEmail(String email) {
        return em.createQuery("from Miembro where correoElectronico = :email", Miembro.class)
                .setParameter("email", email)
                .getResultList().get(0);
    }

    public void agregar(Miembro miembro) {
        miembros.add(miembro);
    }

}
