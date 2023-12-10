package Persistencia;

import dominio.clasesTecnicas.AdminEntidadOrganismo;
import dominio.clasesTecnicas.AdministradorSistema;
import dominio.clasesTecnicas.Usuario;
import dominio.empresasYorganismos.EntidadPropietaria;

import javax.persistence.EntityManager;

public class InstanciasUsuarios {
    private AdministradorSistema adminSistema;
    private AdminEntidadOrganismo adminEntidadPropietaria;
    private AdminEntidadOrganismo adminOrganismoDeControl;

    public InstanciasUsuarios(EntityManager em, InstanciasEntidadesOrganismos entidadesOrganismos) {
        EntidadPropietaria entidadPropietaria = entidadesOrganismos.getEntidadPropietaria();
        this.adminSistema = new AdministradorSistema("admin", "23campeonDelMundo#3");
        this.adminEntidadPropietaria = new AdminEntidadOrganismo("adminEntidadPropietaria", "23campeonDelMundo#3", entidadPropietaria);
        em.persist(adminSistema);
    }
}
