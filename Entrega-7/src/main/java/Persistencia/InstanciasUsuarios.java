package Persistencia;

import dominio.clasesTecnicas.AdminEntidadOrganismo;
import dominio.clasesTecnicas.AdministradorSistema;
import dominio.clasesTecnicas.Usuario;
import dominio.empresasYorganismos.EntidadPropietaria;
import lombok.Getter;

import javax.persistence.EntityManager;
@Getter
public class InstanciasUsuarios {
    private AdministradorSistema adminSistema;
    private AdminEntidadOrganismo adminEntidadPropietaria;
    private AdminEntidadOrganismo adminOrganismoDeControl;

    public InstanciasUsuarios(EntityManager em) {
        this.adminSistema = new AdministradorSistema("admin", "23campeonDelMundo#3", "David", "Argento");
        this.adminEntidadPropietaria = new AdminEntidadOrganismo("adminEntidadPropietaria", "23campeonDelMundo#3", "Marcos", "Puerta");
        em.persist(adminSistema);
        em.persist(adminEntidadPropietaria);
    }
}
