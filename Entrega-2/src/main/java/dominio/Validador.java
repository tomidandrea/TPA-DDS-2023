package dominio;

import excepciones.ContraseniaIncorrectaExcepcion;
import excepciones.ContraseniaComunException;
import excepciones.NoPudoAbrirElArchivoExcepcion;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class Validador {
  public static Validador instance = new Validador();

  public static Validador getInstance() {
    return instance;
  }
  // CONFIGURACIÓN
  static int minCaracteres = 8;
  static int minMayusc = 1;
  static int minMinusc = 2;
  static int minDigitos = 2;
  static int minEspeciales = 1;

  static void cumpleMinusculas(String contrasenia) {
    if (contrasenia
        .chars()
        .filter(letra -> Character.isLowerCase(letra))
        .count()
        >= minMinusc) {
      return;
    }
    throw new ContraseniaIncorrectaExcepcion(1);
  }

  static void cumpleMayusculas(String contrasenia) {
    if (contrasenia
        .chars()
        .filter(letra -> Character.isUpperCase(letra))
        .count()
        >= minMayusc) {
      return;
    }
    throw new ContraseniaIncorrectaExcepcion(2);
  }

  static void cumpleMinDigitos(String contrasenia) {
    if (contrasenia
        .chars()
        .filter(caracter -> Character.isDigit(caracter))
        .count()
        >= minDigitos) {
      return;
    }
    throw new ContraseniaIncorrectaExcepcion(3);
  }

  static void cumpleMinEspeciales(String contrasenia) {
    if (contrasenia
        .chars()
        .filter(caracter -> esDigitoEspecial(caracter))
        .count()
        >= minEspeciales) {
      return;
    }
    throw new ContraseniaIncorrectaExcepcion(5);
  }

  static boolean esDigitoEspecial(int c) {
    return (c >= 33 && c <= 46) || c == 64;
  }

  static void cumpleMinimoCaracteres(String contrasenia) {
    if (contrasenia.length() >= minCaracteres) {
      return;
    }
    throw new ContraseniaIncorrectaExcepcion(4);
  }

  static void noestaEntreLasTop10Mil(String contrasenia) {
    List<String> contraseniasComunes;

    try {
      String contraseniasComunesArchivo =
          new File("resources/top10milContrasenias.txt").getAbsolutePath();
      contraseniasComunes =
          readAllLines(Paths.get(contraseniasComunesArchivo), StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new NoPudoAbrirElArchivoExcepcion();
    }

    if (contraseniasComunes.stream().anyMatch(contra -> contra.equals(contrasenia))) {
      throw new ContraseniaComunException();
    }
  }

  public static void validarContrasenia(String contrasenia) {
    noestaEntreLasTop10Mil(contrasenia);
    cumpleMinimoCaracteres(contrasenia);
    cumpleMinusculas(contrasenia);
    cumpleMayusculas(contrasenia);
    cumpleMinDigitos(contrasenia);
    cumpleMinEspeciales(contrasenia);
  }
}
