package dominio;

public class Miembro extends Cuenta{
    private String nombre;
    private String apellido;
    private String correoElectronico;

    public Miembro(String nombre, String apellido, String correoElectronico,
         String usuario, String contrasenia){
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.setUsuario(usuario);;
        Validador.validarContrasenia(contrasenia);
        this.setContrasenia(contrasenia);
    }
}
