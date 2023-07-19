package dominio.entidades;
import dominio.establecimientos.Sucursal;
import java.util.List;


public class Organizacion {
    String nombre;
    List<Sucursal> sucursales;

    public Organizacion setNombre(String nombre) {
        this.nombre = nombre;
    }
}