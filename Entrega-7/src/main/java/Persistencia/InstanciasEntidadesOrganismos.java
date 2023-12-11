package Persistencia;

import dominio.empresasYorganismos.EntidadPropietaria;
import dominio.empresasYorganismos.OrganismoDeControl;
import dominio.entidades.Organizacion;
import lombok.Getter;

import javax.persistence.EntityManager;
import java.util.List;

public class InstanciasEntidadesOrganismos {
    @Getter
    private EntidadPropietaria entidadPropietaria;
    private OrganismoDeControl organismoDeControl;

    public InstanciasEntidadesOrganismos(EntityManager em, InstanciasEstablecimientos establecimientos, InstanciasUsuarios usuarios) {
        InstanciasEntidades instanciasEntidades = new InstanciasEntidades(em, establecimientos);
        List<Organizacion> organizaciones = List.of(instanciasEntidades.getCoto(), instanciasEntidades.getDia());
        //TODO: ver de completar nulls
        this.entidadPropietaria = new EntidadPropietaria("EntidadPropietaria", null ,organizaciones, null, usuarios.getAdminEntidadPropietaria());
        em.persist(entidadPropietaria);
        //this.organismoDeControl = new OrganismoDeControl("OrganismoDeControl", ...);
    }

}