package dominio.establecimientos;
import dominio.Localizacion.Localizacion;
import dominio.servicios.Servicio;
import dominio.servicios.Agrupacion;
import dominio.comunidades.Incidente;
import dominio.comunidades.RepoIncidentes;
import lombok.Getter;

import javax.persistence.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Establecimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public String nombre;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "localizacion_id", referencedColumnName = "localizacion_id")
    public Localizacion localizacion;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Servicio> servicios;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Agrupacion> agrupaciones;

    public List<Duration> obtenerListaTiemposCierre(){
        return incidentesDelEstablecimiento().stream().map(i->i.obtenerTiempoCierre()).toList();
    }

    public int cantidadDeIncidentes(){
        return this.incidentesDelEstablecimiento().size();
    }

    public List<Incidente> incidentesDelEstablecimiento() {
        //TODO hacer que sume los incidentes de servicios simples y de agrupaciones
        if(agrupaciones == null){
            return RepoIncidentes.getInstance().obtenerIncidentesDe(servicios);
        }
        else return RepoIncidentes.getInstance().obtenerIncidentesDe(agrupaciones, servicios);
    }
}
