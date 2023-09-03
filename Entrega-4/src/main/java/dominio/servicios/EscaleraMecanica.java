package dominio.servicios;

import dominio.Localizacion.Localizacion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("EscaleraMecanica")
public class EscaleraMecanica extends MedioElevacion {
  //TODO usar nuevo constructor
  public EscaleraMecanica(Localizacion localizacion, String nombre, TramoMedioElevacion tramo) {
    this.nombre = nombre;
    this.setTramoRecorrido(tramo);
    this.setLocalizacion(localizacion);
  }

  public EscaleraMecanica(){}
  public EscaleraMecanica(String nombre) {
    this.nombre = nombre;
  }
}
