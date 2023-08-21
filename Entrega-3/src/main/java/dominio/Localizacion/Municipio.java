package dominio.Localizacion;

public class Municipio extends Localizacion {

  private Provincia provincia;

  public Municipio(String nombre, Integer id, Centroide centroide) {
    super(nombre, id, centroide);
  }
}
