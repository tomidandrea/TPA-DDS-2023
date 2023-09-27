package inicializacion;

import dominio.comunidades.*;
import dominio.establecimientos.Establecimiento;
import dominio.servicios.Servicio;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public class InstanciasComunidades {
  private Comunidad comunidad1;

  public InstanciasComunidades(InstanciasIncidentes incidentes, InstanciasMiembros miembros){
    List<Incidente> incidentesAbiertos = List.of(incidentes.getIncidenteAbierto1(),
        incidentes.getIncidenteAbierto2(), incidentes.getIncidenteHace5Dias(),
        incidentes.getIncidenteAbierto3());
    List<Miembro> miembros1 = List.of(miembros.getJuan(), miembros.getPedro(), miembros.getManu());
    List<Miembro> observadores1 = new ArrayList<>();
    List<Administrador> administradores1 = new ArrayList<>();
    List<Establecimiento> establecimientosObservados = new ArrayList<>();
    List<Servicio> serviciosObservados = new ArrayList<>();
    this.comunidad1 = new Comunidad("Blue Label", miembros1, observadores1, administradores1,
            establecimientosObservados, serviciosObservados, GradoDeConfianza.CONFIABLE_NIVEL_1, incidentesAbiertos);
  }
}
