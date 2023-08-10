package dominio.servicios;


import dominio.Localizacion.Localizacion;
import lombok.Setter;

@Setter
public abstract class Servicio {
    private Localizacion localizacion;
    public String nombre;

    public String getNombre() {
        return nombre;
    }
}
