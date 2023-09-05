package dominio.clasesTecnicas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
public abstract class Usuario {
  /*@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
*/
  private String usuario;
  private String contrasenia;

  /*public Cuenta(String usuario, String contrasenia) {
    this.usuario = usuario;
    Validador.getInstance().validarContrasenia(contrasenia);
    this.contrasenia = contrasenia;
  }*/

  public String getUsuario() {
    return this.usuario;
  }

  public String getContrasenia() {
    return this.contrasenia;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public void setContrasenia(String contrasenia) {
    this.contrasenia = contrasenia;
  }
}
