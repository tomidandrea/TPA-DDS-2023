package dominio.Localizacion;

public abstract class Localizacion {
    public String nombre;
    public Integer id;
    public Centroide centroide;

    public Localizacion(String nombre, Integer id, Centroide centroide) {
        this.nombre = nombre;
        this.id = id;
        this.centroide = centroide;
    }
}
