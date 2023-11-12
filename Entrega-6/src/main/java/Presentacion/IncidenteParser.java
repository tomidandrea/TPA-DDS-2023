package Presentacion;

import dominio.servicios.Servicio;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IncidenteParser {
    String observacion;
    List<String> servicios;

    public IncidenteParser(String observacion, List<String> servicios) {
        this.observacion = observacion;
        this.servicios = servicios;
    }
}
