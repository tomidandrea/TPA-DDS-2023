package inicializacion;

import dominio.comunidades.Comunidad;
import dominio.comunidades.Incidente;
import dominio.comunidades.Miembro;
import lombok.Getter;

import java.util.List;
@Getter
public class InstanciasComunidades {
  private Comunidad comunidad1;

  public InstanciasComunidades(InstanciasIncidentes incidentes, InstanciasMiembros miembros){
    List<Incidente> incidentesAbiertos = List.of(incidentes.getIncidenteAbierto1(),
        incidentes.getIncidenteAbierto2(), incidentes.getIncidenteCerrado1());
    List<Miembro> miembros1 = List.of(miembros.getJuan(), miembros.getPedro());
    this.comunidad1 = new Comunidad(miembros1, incidentesAbiertos, "Blue Label");
  }
}
