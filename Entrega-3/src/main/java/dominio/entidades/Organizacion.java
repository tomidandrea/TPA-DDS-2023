package dominio.entidades;
import dominio.establecimientos.Sucursal;
import java.util.List;


public class Organizacion {
    String nombre;
    List<Sucursal> sucursales;

    public Organizacion(String nombre) {
        this.nombre = nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}