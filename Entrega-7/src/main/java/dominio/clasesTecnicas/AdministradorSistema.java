package dominio.clasesTecnicas;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class AdministradorSistema extends Usuario {
    private int cantidadEntidadesCargadas; //Para que tenga algun atributo

    public AdministradorSistema(){
        this.cantidadEntidadesCargadas = 0;
    }
    public AdministradorSistema(String usuario, String contrasenia, String nombre, String apellido, String correoElectronico) {
        super(usuario, contrasenia, nombre, apellido, correoElectronico);
    }
}
