package ddsGrupo1.empresasYorganismos;

import ddsGrupo1.Miembro;
import ddsGrupo1.entidades.Organizacion;
import ddsGrupo1.entidades.ServicioTransporte;

import java.util.List;

public class EntidadPropietaria {
    List<Organizacion> organizaciones;
    List<ServicioTransporte> serviciosTransporte;
    Miembro responsable;

    public EntidadPropietaria(List<Organizacion> organizaciones, List<ServicioTransporte> serviciosTransporte, Miembro responsable) {
        this.organizaciones = organizaciones;
        this.serviciosTransporte = serviciosTransporte;
        this.responsable = responsable;
    }
}
