package ddsGrupo1;

public class Cuenta {
  String usuario;
  String contrasenia;

  public Cuenta(String usuario, String contrasenia) {
    this.usuario = usuario;
    Validador.getInstance().validarContrasenia(contrasenia);
    this.contrasenia = contrasenia;
  }

  public String getUsuario() {
    return this.usuario;
  }

  public String getContrasenia() {
    return this.contrasenia;
  }
}
