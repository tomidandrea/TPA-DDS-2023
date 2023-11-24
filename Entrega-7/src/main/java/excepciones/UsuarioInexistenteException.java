package excepciones;

public class UsuarioInexistenteException extends RuntimeException {
    public UsuarioInexistenteException() {
        super("Usuario inexistente");
    }
}
