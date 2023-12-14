package Presentacion.dto;

import dominio.comunidades.Comunidad;
import dominio.comunidades.EstadoIncidente;
import dominio.comunidades.Incidente;
import dominio.servicios.Agrupacion;
import dominio.servicios.Servicio;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ComunidadDTO {
  private int id;
  private String nombre;
  private List<MiembroDTO> miembros;

  public ComunidadDTO(){

  }

  public ComunidadDTO(Comunidad comunidad){
    this.id = comunidad.getId();
    this.nombre = comunidad.getNombre();
  }

  public ComunidadDTO(Comunidad comunidad, List<MiembroDTO> miembros){
    this.id = comunidad.getId();
    this.nombre = comunidad.getNombre();
    this.miembros = miembros;
  }
}
