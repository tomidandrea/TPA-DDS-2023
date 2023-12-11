package dominio.empresasYorganismos;

import dominio.clasesTecnicas.AdminEntidadOrganismo;
import dominio.clasesTecnicas.Usuario;
import dominio.comunidades.Incidente;
import dominio.comunidades.Miembro;
import dominio.entidades.Organizacion;
import dominio.entidades.ServicioTransporte;
import dominio.servicios.Servicio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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
    private AdminEntidadOrganismo responsable;
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

    public EntidadPropietaria(String nombre, List<ServicioTransporte> serviciosTransporte, List<Organizacion> organizaciones, AdminEntidadOrganismo responsable) {
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

    public List<Servicio> serviciosDePropiedades() {

        HashSet<Servicio> s = new HashSet<>(serviciosTransporte.stream().
                flatMap(st -> st.servicios().stream())
                .collect(Collectors.toList()));
        HashSet<Servicio> o = new HashSet<>(organizaciones.stream().
                flatMap(organizacion -> organizacion.servicios().stream())
                .collect(Collectors.toList()));

        HashSet<Servicio> serviciosEntProp = new HashSet<>(s);
        serviciosEntProp.addAll(o);

        return serviciosEntProp.stream().toList();
    }
}