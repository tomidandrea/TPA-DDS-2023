package dominio.clasesTecnicas;

import dominio.empresasYorganismos.EntidadPropietaria;
import dominio.empresasYorganismos.OrganismoDeControl;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
public class AdminEntidadOrganismo extends Usuario {
    private LocalDate fechaInicioAdministracion;

    public AdminEntidadOrganismo(){

    }

    public AdminEntidadOrganismo(String usuario, String contrasenia, String nombre, String apellido) {
        super(usuario, contrasenia, nombre, apellido);
        this.fechaInicioAdministracion = LocalDate.now();
    }

}
