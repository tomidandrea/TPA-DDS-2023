package dominio.empresasYorganismos;

import dominio.clasesTecnicas.Usuario;
import dominio.comunidades.Miembro;
import dominio.entidades.Organizacion;
import dominio.entidades.ServicioTransporte;

import javax.persistence.*;
import java.util.List;

@Entity
public class OrganismoDeControl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String nombre;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ServicioTransporte> serviciosTransporte;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Organizacion> organizaciones;
    //@OneToOne
    @Transient
    private Usuario responsable;

    public OrganismoDeControl(String nombre, List<ServicioTransporte> serviciosTransporte, List<Organizacion> organizaciones, Miembro responsable) {
        this.nombre = nombre;
        this.serviciosTransporte = serviciosTransporte;
        this.organizaciones = organizaciones;
        this.responsable = responsable;
    }


    public OrganismoDeControl() {

    }
}
