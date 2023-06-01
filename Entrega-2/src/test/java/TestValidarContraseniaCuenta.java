import ddsGrupo1.Cuenta;
import ddsGrupo1.Miembro;
import ddsGrupo1.excepciones.ContraseniaComunException;
import ddsGrupo1.excepciones.ContraseniaIncorrectaExcepcion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TestValidarContraseniaCuenta {
  @Test
  public void cuentaTieneContraseniaDebil() {
    ContraseniaIncorrectaExcepcion excepcion = Assertions.assertThrows(
        ContraseniaIncorrectaExcepcion.class, this::crearCuentaConContraseniaDebil, "Contrasenia invalida");
  }

  @Test
  public void cuentaTieneContraseniaFuerte() {
    Assertions.assertEquals(this.crearCuentaConContraseniaFuerte().getUsuario(), "francicerchia");
    Assertions.assertEquals(this.crearCuentaConContraseniaFuerte().getContrasenia(), "ChefCurry30!");
  }

  @Test
  public void cuentaTieneContraseniaComun(){
    ContraseniaComunException excepcion = Assertions.assertThrows(
        ContraseniaComunException.class, this::crearCuentaConContraseniaComun, "Contrasenia invalida, esta entre las 10 mil mas comunes");
  }

  private Cuenta crearCuentaConContraseniaDebil() {
    Cuenta cuentaContraseniaDebil = new Miembro("","","","alejiti", "aghjd", 1);
    return cuentaContraseniaDebil;
  }

  private Cuenta crearCuentaConContraseniaFuerte() {
    Cuenta cuentaContraseniaFuerte = new Miembro("","","","francicerchia", "ChefCurry30!", 2);
    return cuentaContraseniaFuerte;
  }

  private Cuenta crearCuentaConContraseniaComun(){
    Cuenta cuentaContraseniaDebil = new Miembro("","","","alejiti", "hola", 3);
    return cuentaContraseniaDebil;
  }
}
