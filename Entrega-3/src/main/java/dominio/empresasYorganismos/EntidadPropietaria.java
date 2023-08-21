package dominio.empresasYorganismos;

import dominio.comunidades.Miembro;
import dominio.entidades.Organizacion;
import dominio.entidades.ServicioTransporte;

import java.util.List;

public class EntidadPropietaria {
    private String nombre;
    private List<Organizacion> organizaciones;
    private List<ServicioTransporte> serviciosTransporte;
    private Miembro responsable;
    private List<Miembro> suscriptores;

    public EntidadPropietaria(String nombre, List<ServicioTransporte> serviciosTransporte, List<Organizacion> organizaciones, Miembro responsable) {
        this.nombre = nombre;
        this.serviciosTransporte = serviciosTransporte;
        this.organizaciones = organizaciones;
        this.responsable = responsable;
    }

    public void notificarSuscriptores(){
        //TODO para cada supcriptor notificar nuevo incidente
    }
}
