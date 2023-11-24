package dominio.servicios;


import javax.persistence.*;

@Entity
@DiscriminatorValue("Banio")
public class Banio extends Servicio{
    @Enumerated(EnumType.STRING)
    private TipoDeBanio tipoDeBanio;
    public Banio(){

    }

    public Banio(//TODO Localizacion localizacion,
                 String nombre, TipoDeBanio tipoDeBanio) {
        this.nombre = nombre;
        this.tipoDeBanio = tipoDeBanio;
    }
    public Banio(TipoDeBanio tipoDeBanio) {
        this.tipoDeBanio = tipoDeBanio;
    }
}
