package ddsGrupo1.entidades;

import java.util.ArrayList;
import java.util.List;

public class RepoOrganizaciones {
    //singleton
    private static RepoOrganizaciones instance = null;

    public static RepoOrganizaciones getInstance(){
        if(instance == null){
            instance = new RepoOrganizaciones();
        }
        return instance;
    }
    List<Organizacion> organizaciones = new ArrayList<>();

    public void agregar(Organizacion organizacion) {
        organizaciones.add(organizacion);
    }

    public Organizacion obtenerPorNombre(String nombre){
        return organizaciones.stream().filter(organizacion -> organizacion.nombre == nombre).findFirst().get();
    }
}
