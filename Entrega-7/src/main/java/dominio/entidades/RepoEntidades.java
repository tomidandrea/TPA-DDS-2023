package dominio.entidades;

import Utils.BDUtils;
import dominio.clasesTecnicas.ResultadoCantidadIncidentes;
import dominio.clasesTecnicas.ResultadoTiempoCierre;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class RepoEntidades {
    private static RepoEntidades instance = null;
    private EntityManager em = BDUtils.getEntityManager();
    public static RepoEntidades getInstance(){
        if(instance == null){
            instance = new RepoEntidades();
        }
        return instance;
    }
    private List<Organizacion> organizaciones = new ArrayList<>();
    private List<ServicioTransporte> serviciosDeTransporte = new ArrayList<>();


    public void agregar(ServicioTransporte servicioTransporte) {

        serviciosDeTransporte.add(servicioTransporte);
    }
    public List<ServicioTransporte> obtenerPorID(Integer id){
        //return serviciosDeTransporte.stream().filter(servicio -> servicio.getTipoTransporte() == tipo).toList();
         return  em.createQuery("from ServicioTransporte where id = :id", ServicioTransporte.class)
                 .setParameter("id",id)
                 .getResultList();
    }

    public void agregar(Organizacion organizacion) {
        organizaciones.add(organizacion);
    }

    public List<Organizacion> obtenerOrgPorID(Integer id){
       // return organizaciones.stream().filter(organizacion -> organizacion.nombre == nombre).findFirst().get();
        return em.createQuery("from Organizacion where id = :id", Organizacion.class)
                .setParameter("id",id)
                .getResultList();
    }

    public List<Entidad> obtenerEntidades(){
        List<Entidad> entidades = new ArrayList<>(organizaciones);
        entidades.addAll(serviciosDeTransporte);
        return entidades;
    }


}
