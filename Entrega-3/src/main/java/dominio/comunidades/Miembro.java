package dominio.comunidades;

import dominio.Localizacion.Localizacion;
import dominio.Notificacion.MedioCorreo;
import dominio.Notificacion.MedioDeComunicacion;
import dominio.clasesTecnicas.Usuario;
import dominio.clasesTecnicas.Validador;

import java.time.LocalTime;
import java.util.List;

public class Miembro extends Usuario {
    private String nombre;
    private int id;
    private String apellido;
    private String correoElectronico;
    private Interes interes;
    private Localizacion localizacion;
    private TipoMiembro tipoMiembro;
    private MedioDeComunicacion medioPreferido;
    private String nroDeTelefono;
    private List<LocalTime> horariosDeNotificacion;

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

    public boolean compararId(int id){
        return this.id == id;
    }

    public void sugerirRevision(Incidente incidente){
        //TODO
    }

    public boolean estaEnHorarioDeNotificacion() {
        return horariosDeNotificacion.stream().anyMatch(horario -> horario.getHour() == LocalTime.now().getHour());
    }

}
