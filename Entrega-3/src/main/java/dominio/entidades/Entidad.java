package dominio.entidades;

import dominio.establecimientos.Establecimiento;
import dominio.establecimientos.Sucursal;
import lombok.Getter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.ListResourceBundle;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Entidad {
    public abstract Duration calcularPromedioTiempoCierre();
    public abstract Integer cantidadIncidentes();
    @Getter
    public String nombre;
    Integer CantidadDeIncidentes() {
        return 0;
    }
}
