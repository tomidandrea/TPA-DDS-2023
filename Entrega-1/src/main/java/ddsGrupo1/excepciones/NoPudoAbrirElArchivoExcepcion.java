package dominio.excepciones;

public class NoPudoAbrirElArchivoExcepcion extends RuntimeException {
  public NoPudoAbrirElArchivoExcepcion(String mensaje) {
    super(mensaje);
  }
}
