import dominio.entidades.RepoEntidades;
import inicializacion.*;
import lombok.Getter;

@Getter
public class InicializadorTests {
  private static InicializadorTests instance = null;
  private InstanciasServicios servicios;
  private InstanciasEstablecimientos establecimientos;
  private InstanciasEntidades entidades;
  private InstanciasIncidentes incidentes;
  private InstanciasComunidades comunidades;
  private InstanciasMiembros miembros;

  public static InicializadorTests getInstance(){
    if(instance == null){
      instance = new InicializadorTests();
    }
    return instance;
  }
  private InicializadorTests(){
    this.servicios = new InstanciasServicios();
    this.incidentes = new InstanciasIncidentes(servicios);
    this.establecimientos = new InstanciasEstablecimientos(servicios);
    this.entidades = new InstanciasEntidades(establecimientos);
    this.miembros = new InstanciasMiembros();
    this.comunidades = new InstanciasComunidades(incidentes, miembros);
  }
}
