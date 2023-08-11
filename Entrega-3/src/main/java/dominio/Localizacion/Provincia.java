package dominio.Localizacion;


public class Provincia extends Localizacion {

  public Provincia(String nombre, Integer id, Centroide centroide) {
    super(nombre, id, centroide);
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

