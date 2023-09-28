import inicializacion.*;
import lombok.Getter;

@Getter
public class InicializadorTests {
  private static InicializadorTests instance = null;
  private TestsInstanciasServicios servicios;
  private TestsInstanciasEstablecimientos establecimientos;
  private TestsInstanciasEntidades entidades;
  private TestsInstanciasIncidentes incidentes;
  private TestsInstanciasComunidades comunidades;
  private TestsInstanciasMiembros miembros;

  public static InicializadorTests getInstance(){
    if(instance == null){
      instance = new InicializadorTests();
    }
    return instance;
  }
  private InicializadorTests(){
    this.servicios = new TestsInstanciasServicios();
    this.incidentes = new TestsInstanciasIncidentes(servicios);
    this.establecimientos = new TestsInstanciasEstablecimientos(servicios);
    this.entidades = new TestsInstanciasEntidades(establecimientos);
    this.miembros = new TestsInstanciasMiembros();
    this.comunidades = new TestsInstanciasComunidades(incidentes, miembros);
  }
}
