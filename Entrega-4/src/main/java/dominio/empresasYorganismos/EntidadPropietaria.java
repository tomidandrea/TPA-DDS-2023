package dominio.empresasYorganismos;

import dominio.clasesTecnicas.Usuario;
import dominio.comunidades.Miembro;
import dominio.entidades.Organizacion;
import dominio.entidades.ServicioTransporte;

import javax.persistence.*;
import java.util.List;

@Entity
public class EntidadPropietaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    @OneToMany
    private List<Organizacion> organizaciones;
    @OneToMany
    private List<ServicioTransporte> serviciosTransporte;
    //@OneToOne
    @Transient
    private Usuario responsable;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Miembro> suscriptores;

    public EntidadPropietaria(String nombre, List<ServicioTransporte> serviciosTransporte, List<Organizacion> organizaciones, Miembro responsable) {
        this.nombre = nombre;
        this.serviciosTransporte = serviciosTransporte;
        this.organizaciones = organizaciones;
        this.responsable = responsable;
    }

    public EntidadPropietaria() {

    }

    public void notificarSuscriptores(){
        //TODO para cada supcriptor notificar nuevo incidente
    }
}
