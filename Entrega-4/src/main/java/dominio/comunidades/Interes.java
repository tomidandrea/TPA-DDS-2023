package dominio.comunidades;

import dominio.servicios.Servicio;
import org.hibernate.mapping.Join;

import javax.persistence.*;
import java.util.List;
@Entity
public class Interes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interes_id")
    private int id;
    @ManyToMany
    private List<Servicio> servicios;

    public Interes(){}
    public Interes(List<Servicio> servicios) {
        this.servicios = servicios;
    }
}
