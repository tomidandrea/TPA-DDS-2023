package dominio.servicios;


public class Baño extends Servicio{
    private TipoDeBaño tipoDeBaño;

    public Baño(String nombre, TipoDeBaño tipoDeBaño) {
        this.nombre = nombre;
        this.tipoDeBaño = tipoDeBaño;
    }
    public Baño(TipoDeBaño tipoDeBaño) {
        this.tipoDeBaño = tipoDeBaño;
    }
}
