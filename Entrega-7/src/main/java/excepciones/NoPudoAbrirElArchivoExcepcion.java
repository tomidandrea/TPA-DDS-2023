package excepciones;

public class NoPudoAbrirElArchivoExcepcion extends RuntimeException {
  public NoPudoAbrirElArchivoExcepcion() {
    super("No se pudo abrir el archivo");
  }
}
