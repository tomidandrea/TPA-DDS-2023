package dominio.entidades;

import dominio.establecimientos.Establecimiento;
import dominio.establecimientos.Estacion;
import dominio.establecimientos.Sucursal;
import lombok.Getter;

import javax.persistence.*;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Entidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entidad_id")
    private int id;

    public abstract Duration calcularPromedioTiempoCierre();
    public abstract Integer cantidadIncidentes();
    @Getter
    public String nombre;

    public Duration getTiemposCierre(List<Establecimiento> establecimientos) {
        List<Duration> tiempos = establecimientos.stream()
            .map(Establecimiento::obtenerListaTiemposCierre)
            .flatMap(List::stream)
            .collect(Collectors.toList());

        Optional<Duration> tiempoTotalOptional = tiempos.stream()
            .reduce(Duration::plus);

        return tiempoTotalOptional.orElse(Duration.ZERO)
            .dividedBy(tiempos.size() == 0 ? 1 : tiempos.size());
    }
}
