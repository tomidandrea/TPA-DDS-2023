package dominio.comunidades;

import converters.MedioComunicacionString;
import dominio.Localizacion.Localizacion;
import dominio.Notificacion.MedioDeComunicacion;
import dominio.clasesTecnicas.Usuario;
import dominio.clasesTecnicas.Validador;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Getter
@Entity
@Table(name = "miembro")
@PrimaryKeyJoinColumn(name = "miembro_id")
public class Miembro extends Usuario {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "miembro_id")
//    private int id;

    @Transient
    private static int cantidadMiembros;
//    private String nombre;
//    private String apellido;
//    private String correoElectronico;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Interes interes;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "localizacion_id")
    private Localizacion localizacion;

    /*@Enumerated(EnumType.STRING)
    private TipoMiembro tipoMiembro; //TODO consultar si puede ser afectado en algunas y observador en otras
    */
    @Convert(converter = MedioComunicacionString.class)
    private MedioDeComunicacion medioPreferido;
    private String nroDeTelefono;
    @Getter
    @Embedded
    private Notificador notificador;


    public MedioDeComunicacion getMedioPreferido () {
        return medioPreferido;
    }

    public String getNroDeTelefono() {
        return nroDeTelefono;
    }

    public Miembro(){

    }

    public Miembro(String nombre, String apellido, String correoElectronico,
                   String usuario, String contrasenia, int id){
        super(nombre, apellido, id, correoElectronico);
        this.setUsuario(usuario);;
        Validador.validarContrasenia(contrasenia);
        this.setContrasenia(contrasenia);
//        this.id = id;
    }
    public Miembro(String nombre, String apellido, String correoElectronico, Localizacion localizacion,
                   MedioDeComunicacion medioPreferido, String nroDeTelefono,
                   Notificador notificador, String usuario, String contrasenia) {

        super(nombre, apellido, correoElectronico);
        this.interes = new Interes();
        this.localizacion = localizacion;
        this.medioPreferido = medioPreferido;
        this.nroDeTelefono = nroDeTelefono;
        this.notificador = notificador;
        //this.id = ++cantidadMiembros;
        this.setUsuario(usuario);
        Validador.validarContrasenia(contrasenia);
        this.setContrasenia(contrasenia);
    }

    //Para los tests
    public Miembro(String nombre, String apellido, String correoElectronico, //Localizacion localizacion,
                   MedioDeComunicacion medioPreferido, String nroDeTelefono,
                   Notificador notificador, String usuario, String contrasenia) {
//        this.nombre = nombre;
//        this.apellido = apellido;
        super(nombre, apellido, correoElectronico);
        //this.localizacion = localizacion;
        this.medioPreferido = medioPreferido;
        this.nroDeTelefono = nroDeTelefono;
        this.notificador = notificador;
        //this.id = ++cantidadMiembros;
        this.setUsuario(usuario);
        Validador.validarContrasenia(contrasenia);
        this.setContrasenia(contrasenia);
    }

    public boolean compararId(int id){
        return this.getId() == id;
    }

    public void sugerirRevision(Incidente incidente){
        //TODO
    }

    public boolean estaEnHorarioDeNotificacion() {
        return notificador.compararHorarioActual();
    }

    @Override
    public String toString() {
        return "Miembro{" +
                "id = " + this.getId() +
                ", nombre = " + this.getNombre() + " apellido = " + this.getApellido() + "correoElectronico = " + this.getCorreoElectronico() +'\'' +
                '}';
    }
}


