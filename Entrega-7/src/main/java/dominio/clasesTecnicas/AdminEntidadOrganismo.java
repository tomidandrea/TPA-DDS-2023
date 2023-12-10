package dominio.clasesTecnicas;

import dominio.empresasYorganismos.EntidadPropietaria;
import dominio.empresasYorganismos.OrganismoDeControl;

public class AdminEntidadOrganismo extends Usuario {

    EntidadPropietaria entidadPropietaria;
    OrganismoDeControl organismoDeControl;

    public AdminEntidadOrganismo(String usuario, String contrasenia, EntidadPropietaria entidadPropietaria) {
        super();
        this.entidadPropietaria = entidadPropietaria;
    }
    public AdminEntidadOrganismo(String usuario, String contrasenia, OrganismoDeControl organismoDeControl) {
        super();
        this.organismoDeControl = organismoDeControl;
    }

}
