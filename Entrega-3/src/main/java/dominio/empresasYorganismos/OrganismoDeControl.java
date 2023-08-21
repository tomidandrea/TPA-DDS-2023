package dominio.empresasYorganismos;

import dominio.clasesTecnicas.Usuario;
import dominio.comunidades.Miembro;
import dominio.entidades.Organizacion;
import dominio.entidades.ServicioTransporte;

import java.util.List;

public class OrganismoDeControl {

    String nombre;
    List<ServicioTransporte> serviciosTransporte;
    List<Organizacion> organizaciones;
    Usuario responsable;

    public OrganismoDeControl(String nombre, List<ServicioTransporte> serviciosTransporte, List<Organizacion> organizaciones, Miembro responsable) {
        this.nombre = nombre;
        this.serviciosTransporte = serviciosTransporte;
        this.organizaciones = organizaciones;
        this.responsable = responsable;
    }


}
