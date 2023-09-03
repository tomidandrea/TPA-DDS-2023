package dominio.entidades;

import dominio.establecimientos.Establecimiento;
import dominio.establecimientos.Sucursal;
import lombok.Getter;

import javax.persistence.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.ListResourceBundle;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Entidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public abstract Duration calcularPromedioTiempoCierre();
    public abstract Integer cantidadIncidentes();
    @Getter
    public String nombre;
}
