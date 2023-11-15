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
    String idSesion;

    public IncidenteParser(String observacion, List<String> servicios, String idSesion) {
        this.observacion = observacion;
        this.servicios = servicios;
        this.idSesion = idSesion;
    }
}
