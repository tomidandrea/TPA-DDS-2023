package Presentacion.dto;

import dominio.comunidades.EstadoIncidente;
import dominio.comunidades.Incidente;
import dominio.servicios.Agrupacion;
import dominio.servicios.Servicio;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IncidenteDTO {
  private int id;
  private Servicio servicio;
  private Agrupacion agrupacion;
  private String observacion;

  private List<String> comunidades;

  private String horarioApertura;
  private String horarioCierre;

  private EstadoIncidente estadoIncidente;

  public IncidenteDTO(){

  }
  public IncidenteDTO(int id, Servicio servicio, Agrupacion agrupacion, String observacion,
                      List<String> comunidades, String horarioApertura, String horarioCierre,
                      EstadoIncidente estadoIncidente) {
    this.id = id;
    this.servicio = servicio;
    this.agrupacion = agrupacion;
    this.observacion = observacion;
    this.comunidades = comunidades;
    this.horarioApertura = horarioApertura;
    this.horarioCierre = horarioCierre;
    this.estadoIncidente = estadoIncidente;
  }
}
