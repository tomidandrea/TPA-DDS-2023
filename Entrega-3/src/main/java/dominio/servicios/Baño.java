package dominio.servicios;


import dominio.Localizacion.Localizacion;

public class Baño extends Servicio{
    private TipoDeBaño tipoDeBaño;

    public Baño(//TODO Localizacion localizacion,
                String nombre, TipoDeBaño tipoDeBaño) {
        this.nombre = nombre;
        this.tipoDeBaño = tipoDeBaño;
    }
    public Baño(TipoDeBaño tipoDeBaño) {
        this.tipoDeBaño = tipoDeBaño;
    }
}
