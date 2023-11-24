package dominio.servicios;


import dominio.Localizacion.Localizacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "servicio_id")
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "localizacion_id", referencedColumnName = "localizacion_id")
    private Localizacion localizacion;
    public String nombre;

    public String getNombre() {
        return nombre;
    }
}
