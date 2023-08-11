package dominio.Localizacion;

public class Departamento extends Localizacion {
    private Provincia provincia;

    public Departamento(String nombre, Integer id, Centroide centroide, Provincia provincia) {
        super(nombre, id, centroide);
        this.provincia = provincia;
    }
}
