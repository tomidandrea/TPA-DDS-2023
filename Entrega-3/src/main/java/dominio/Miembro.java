package dominio;

public class Miembro extends Cuenta{
    private String nombre;

    private int id;
    private String apellido;
    private String correoElectronico;

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
}
