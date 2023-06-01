package excepciones;

public class ContraseniaIncorrectaExcepcion extends RuntimeException {

  public ContraseniaIncorrectaExcepcion(int codigo) {
    super(convertir(codigo));
  }

  private static String convertir(int codigo) {
    switch (codigo){
      case 1:
        return "No cumple la cantidad minima de minusculas";
      case 2:
        return "No cumple la cantidad minima de mayusculas";
      case 3:
        return "No cumple la cantidad minima de digitos";
      case 4:
        return "No cumple la cantidad minima de caracteres";
      case 5:
        return "No cumple la cantidad minima de caracteres especiales";
      default:
        return "Contrasenia invalida";
    }
  }
}
