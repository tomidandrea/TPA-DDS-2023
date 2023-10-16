package dominio.Localizacion;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Centroide {
    //TODO: poner en private
    public Double lat;
    public Double lon;

    public Centroide(){}
    public Centroide(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }
}
