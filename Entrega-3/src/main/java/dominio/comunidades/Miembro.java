package dominio.comunidades;

import dominio.Localizacion.Localizacion;
import dominio.Notificacion.MedioDeComunicacion;
import dominio.clasesTecnicas.Usuario;
import dominio.clasesTecnicas.Validador;
import lombok.Getter;

public class Miembro extends Usuario {
    private static int cantidadMiembros;
    @Getter
    private String nombre;
    private int id;
    @Getter
    private String apellido;
    private String correoElectronico;
    private Interes interes;
    private Localizacion localizacion;
    private TipoMiembro tipoMiembro; //TODO consultar si puede ser afectado en algunas y observador en otras
    private MedioDeComunicacion medioPreferido;
    private String nroDeTelefono;
    //private List<LocalTime> horariosDeNotificacion;
    @Getter
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
