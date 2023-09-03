package dominio.Localizacion;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Municipio extends Localizacion {
  @ManyToOne(cascade = CascadeType.REMOVE)
  private Provincia provincia;

  public Municipio(){}
  public Municipio(String nombre, Integer id, Centroide centroide, Provincia provincia) {
    super(nombre, id, centroide);
    this.provincia = provincia;
  }
}
