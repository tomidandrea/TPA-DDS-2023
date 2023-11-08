package dominio.Localizacion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
public class Provincia extends Localizacion {
  public Provincia(){}

  public Provincia(String nombre, Integer id, Centroide centroide) {
    super(nombre, id, centroide);
  }
  public Provincia(String nombre, Centroide centroide) {
    super(nombre, centroide);
  }
}

/*
{
    "provincias": [
        {
            "nombre": "Santiago del Estero",
            "id": "86",
            "centroide": {
                "lat": -27.782412,
                "lon": -63.252387
            }
        }
    ],
    "cantidad": 1,
    "total": 1,
    "inicio": 0,
    "parametros": { ... }
} */

