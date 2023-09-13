package dominio.comunidades;

import converters.MedioComunicacionString;
import dominio.Localizacion.Localizacion;
import dominio.Notificacion.MedioDeComunicacion;
import dominio.clasesTecnicas.Usuario;
import dominio.clasesTecnicas.Validador;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Miembro extends Usuario {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "miembro_id")
    private int id;

    @Transient
    private static int cantidadMiembros;
    private String nombre;
    private String apellido;
    private String correoElectronico;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Interes interes;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "localizacion_id")
    private Localizacion localizacion;

    @Enumerated(EnumType.STRING)
    private TipoMiembro tipoMiembro; //TODO consultar si puede ser afectado en algunas y observador en otras
    @Convert(converter = MedioComunicacionString.class)
    private MedioDeComunicacion medioPreferido;
    private String nroDeTelefono;
    //private List<LocalTime> horariosDeNotificacion;
    @Getter
    @Embedded
    private Notificador notificador;

    public String getCorreo() {
        return correoElectronico;
    }

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
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.setUsuario(usuario);;
        Validador.validarContrasenia(contrasenia);
        this.setContrasenia(contrasenia);
        this.id = id;
    }
    public Miembro(String nombre, String apellido, String correoElectronico, //TODO: Localizacion localizacion,
                   TipoMiembro tipoMiembro, MedioDeComunicacion medioPreferido, String nroDeTelefono,
                   //List<LocalTime> horariosDeNotificacion,
                   Notificador notificador, String usuario, String contrasenia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.localizacion = localizacion;
        this.tipoMiembro = tipoMiembro;
        this.medioPreferido = medioPreferido;
        this.nroDeTelefono = nroDeTelefono;
        //this.horariosDeNotificacion = horariosDeNotificacion;
        this.notificador = notificador;
        this.id = ++cantidadMiembros;
        this.setUsuario(usuario);
        Validador.validarContrasenia(contrasenia);
        this.setContrasenia(contrasenia);
    }
    public Miembro(String nombre, String apellido, String correoElectronico, Localizacion localizacion,
                   TipoMiembro tipoMiembro, MedioDeComunicacion medioPreferido, String nroDeTelefono,
                   //List<LocalTime> horariosDeNotificacion,
                   Notificador notificador, String usuario, String contrasenia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.localizacion = localizacion;
        this.tipoMiembro = tipoMiembro;
        this.medioPreferido = medioPreferido;
        this.nroDeTelefono = nroDeTelefono;
        //this.horariosDeNotificacion = horariosDeNotificacion;
        this.notificador = notificador;
        this.id = ++cantidadMiembros;
        this.setUsuario(usuario);
        Validador.validarContrasenia(contrasenia);
        this.setContrasenia(contrasenia);
    }

    public boolean compararId(int id){
        return this.id == id;
    }

    public void sugerirRevision(Incidente incidente){
        //TODO
    }

    public boolean estaEnHorarioDeNotificacion() {
        return notificador.compararHorarioActual();
    }

}