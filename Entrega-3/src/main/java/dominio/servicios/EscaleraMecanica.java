package dominio.servicios;

import dominio.Localizacion.Localizacion;

public class EscaleraMecanica extends MedioElevacion {
  //TODO usar nuevo constructor
  public EscaleraMecanica(Localizacion localizacion, String nombre, TramoMedioElevacion tramo) {
    this.nombre = nombre;
    this.setTramoRecorrido(tramo);
    this.setLocalizacion(localizacion);
  }

  public EscaleraMecanica(String nombre) {
    this.nombre = nombre;
  }
}
