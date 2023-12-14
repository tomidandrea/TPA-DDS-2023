package dominio.empresasYorganismos;

import dominio.clasesTecnicas.AdminEntidadOrganismo;
import dominio.clasesTecnicas.Usuario;
import dominio.comunidades.Miembro;
import dominio.entidades.Organizacion;
import dominio.entidades.ServicioTransporte;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class OrganismoDeControl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String nombre;
    @ManyToMany // los tratamos como manytomany aunque sean onetomany
    private List<ServicioTransporte> serviciosTransporte;
    @ManyToMany // idem para organizaciones
    private List<Organizacion> organizaciones;
    @OneToOne
    private AdminEntidadOrganismo responsable;

    public OrganismoDeControl(String nombre, List<ServicioTransporte> serviciosTransporte, List<Organizacion> organizaciones, AdminEntidadOrganismo responsable) {
        this.nombre = nombre;
        this.serviciosTransporte = serviciosTransporte;
        this.organizaciones = organizaciones;
        this.responsable = responsable;
    }


    public OrganismoDeControl() {

    }

    public void agregarServicios(List<ServicioTransporte> serviciosTransporte) {
        this.serviciosTransporte.addAll(serviciosTransporte);
    }

    public void agregarOrganizaciones(List<Organizacion> organizaciones) {
        this.organizaciones.addAll(organizaciones);
    }
}
