package dominio.servicios;

import javax.persistence.*;
import java.util.List;

@Entity
public class Agrupacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    private List<Servicio> servicios;

    public Agrupacion(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public Agrupacion() {

    }
}
