package dominio.empresasYorganismos;

import dominio.clasesTecnicas.AdminEntidadOrganismo;
import dominio.clasesTecnicas.Usuario;
import dominio.comunidades.Incidente;
import dominio.comunidades.Miembro;
import dominio.entidades.Organizacion;
import dominio.entidades.ServicioTransporte;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class EntidadPropietaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    @ManyToMany(cascade = CascadeType.ALL) // en realidad es OneToMany, pero en la bd los tratamos como ManyToMany
    private List<Organizacion> organizaciones;
    @ManyToMany(cascade = CascadeType.ALL) // idem para servicios
    private List<ServicioTransporte> serviciosTransporte;
    @Setter
    @OneToOne
    private Usuario responsable;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Miembro> suscriptores;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Incidente> incidentesAbiertos;
    //TODO: tendriamos que fijarnos que al cerrar un incidente tambien se fije estos - Alejo


    public EntidadPropietaria(String nombre, List<ServicioTransporte> serviciosTransporte, List<Organizacion> organizaciones, List<Incidente> incidentesAbiertos, AdminEntidadOrganismo responsable) {
        this.nombre = nombre;
        this.serviciosTransporte = serviciosTransporte;
        this.organizaciones = organizaciones;
        this.incidentesAbiertos = incidentesAbiertos;
        this.responsable = responsable;
    }


    public EntidadPropietaria() {
    }

    public EntidadPropietaria(String nombre, List<ServicioTransporte> serviciosTransporte, List<Organizacion> organizaciones, Miembro responsable) {
    }

    public void notificarSuscriptores(List<Incidente> incidentes){
        suscriptores.forEach(suscriptor -> suscriptor.getMedioPreferido().notificarIncidentes(suscriptor, incidentes, this.nombre));
    }

    public void agregarServicios(List<ServicioTransporte> serviciosTransporte) {
        serviciosTransporte.addAll(serviciosTransporte);
    }

    public void agregarOrganizaciones(List<Organizacion> organizaciones) {
        organizaciones.addAll(organizaciones);
    }
}
