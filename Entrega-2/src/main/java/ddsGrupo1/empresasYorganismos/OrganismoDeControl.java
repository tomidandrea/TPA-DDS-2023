package ddsGrupo1.empresasYorganismos;

import ddsGrupo1.Miembro;
import ddsGrupo1.entidades.Organizacion;
import ddsGrupo1.entidades.ServicioTransporte;

import java.util.List;

public class OrganismoDeControl {

    String nombre;
    List<ServicioTransporte> serviciosTransporte;
    List<Organizacion> organizaciones;
    Miembro responsable;

    public OrganismoDeControl(String nombre, List<ServicioTransporte> serviciosTransporte, List<Organizacion> organizaciones, Miembro responsable) {
        this.nombre = nombre;
        this.serviciosTransporte = serviciosTransporte;
        this.organizaciones = organizaciones;
        this.responsable = responsable;
    }


}
