package dominio.clasesTecnicas;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
public abstract class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Transient
  private String usuario;
  @Transient
  private String contrasenia;

  private String nombre;
  private String apellido;
  private String correoElectronico;

  /*public Cuenta(String usuario, String contrasenia) {
    this.usuario = usuario;
    Validador.getInstance().validarContrasenia(contrasenia);
    this.contrasenia = contrasenia;
  }*/

  public Usuario(){
  }

  public Usuario(String nombre, String apellido, String correoElectronico) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.correoElectronico = correoElectronico;

  }

  public Usuario(String nombre, String apellido, int id, String correoElectronico) {
    this.id = id;
    this.nombre = nombre;
    this.apellido = apellido;
    this.correoElectronico = correoElectronico;
  }

  public Usuario(String usuario, String contrasenia, String nombre, String apellido, String correoElectronico) {
    this.usuario = usuario;
    this.contrasenia = contrasenia;
    this.nombre = nombre;
    this.apellido = apellido;
    this.correoElectronico = correoElectronico;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public void setContrasenia(String contrasenia) {
    this.contrasenia = contrasenia;
  }

  @Override
  public String toString() {
    return "Usuario{" +
            "id = " + this.getId() +
            ", nombre = " + this.getNombre() + " apellido = " + this.getApellido() + "correoElectronico = " + this.getCorreoElectronico() +'\'' +
            '}';
  }
}
