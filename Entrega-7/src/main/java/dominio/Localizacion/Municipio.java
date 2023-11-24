package dominio.Localizacion;

import javax.persistence.*;

@Entity
public class Municipio extends Localizacion {
  @ManyToOne(cascade = CascadeType.REMOVE)
  @JoinColumn(name = "provincia_id", referencedColumnName = "localizacion_id")
  private Provincia provincia;

  public Municipio(){}
  public Municipio(String nombre, Integer id, Centroide centroide, Provincia provincia) {
    super(nombre, id, centroide);
    this.provincia = provincia;
  }
}
