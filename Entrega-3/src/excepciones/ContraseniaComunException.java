package excepciones;

public class ContraseniaComunException extends RuntimeException {
  public ContraseniaComunException() {
    super("Contrasenia invalida, esta entre las 10 mil mas comunes");
  }
}
