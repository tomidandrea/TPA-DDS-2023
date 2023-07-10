package dominio;

public abstract class Cuenta {
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
