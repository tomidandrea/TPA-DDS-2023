package dominio.Localizacion;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Departamento extends Localizacion {
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "provincia_id", referencedColumnName = "localizacion_id")
    private Provincia provincia;

    public Departamento(){}
    public Departamento(String nombre, Integer id, Centroide centroide, Provincia provincia) {
        super(nombre, id, centroide);
        this.provincia = provincia;
    }
}
