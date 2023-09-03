package dominio.servicios;


import dominio.Localizacion.Localizacion;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "servicio_id")
    private int id;
    @OneToOne
    private Localizacion localizacion;
    public String nombre;

    public String getNombre() {
        return nombre;
    }
}
