package Persistencia;

import Utils.BDUtils;
import dominio.comunidades.*;
import dominio.establecimientos.Establecimiento;
import dominio.servicios.Servicio;
import lombok.Getter;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Getter
public class InstanciasComunidades {
  private Comunidad comunidad1;
  private Comunidad comunidad2;
  private Comunidad comunidad3;
  private Comunidad comunidad4;

  public InstanciasComunidades(EntityManager em, InstanciasIncidentes incidentes, InstanciasMiembro miembros,
                               InstanciasEstablecimientos establecimientos, InstanciasServicios servicios){
    List<Incidente> incidentesAbiertos1 = List.of(incidentes.getIncidenteAbierto1(),
        incidentes.getIncidenteAbierto2(), incidentes.getIncidenteHace5Dias());
    List<Incidente> incidentesAbiertos2 = List.of(incidentes.getIncidenteHace5Dias(),
            incidentes.getIncidenteAbierto3());

    List<Miembro> afectados1 = List.of(miembros.getMiembro1(), miembros.getMiembro2(), miembros.getMiembro3(), miembros.getMiembro4(), miembros.getMiembro5());
    List<Miembro> afectados2 = List.of(miembros.getMiembro1(), miembros.getMiembro2(), miembros.getMiembro5());
    List<Miembro> afectados3 = List.of(miembros.getMiembro1(), miembros.getMiembro3(), miembros.getMiembro4(), miembros.getMiembro6());
    List<Miembro> afectados4 = List.of(miembros.getMiembro1(), miembros.getMiembro3(), miembros.getMiembro6());

    List<Miembro> observadores1 = List.of(miembros.getMiembro7());
    List<Miembro> observadores2 = List.of(miembros.getMiembro4(), miembros.getMiembro3());
    List<Miembro> observadores3 = List.of(miembros.getMiembro2());
    List<Miembro> observadores4 = List.of(miembros.getMiembro7());

//    Administrador admin1 = new Administrador();
//    Administrador admin2 = new Administrador();
//
//    em.persist(admin1);
//    em.persist(admin2);

//    List<Administrador> administradores1 = List.of(admin1);
//    List<Administrador> administradores2 = List.of(admin2);
//    List<Administrador> administradores3 = List.of(admin1);
//    List<Administrador> administradores4 = List.of(admin2);

    List<Miembro> administradores1 = List.of(miembros.getMiembro1());
    List<Miembro> administradores2 = List.of(miembros.getMiembro2());
    List<Miembro> administradores3 = List.of(miembros.getMiembro1());
    List<Miembro> administradores4 = List.of(miembros.getMiembro2());

    List<Establecimiento> establecimientosObservados1 = List.of(establecimientos.getEstacionA1(),establecimientos.getSucursalDia2(),
        establecimientos.getSucursalCoto1(), establecimientos.getEstacionA2(), establecimientos.getSucursalCoto2());
    List<Establecimiento> establecimientosObservados2 = List.of(establecimientos.getEstacionA1(),establecimientos.getSucursalDia2(),
        establecimientos.getSucursalCoto1(), establecimientos.getEstacionA2());
    List<Establecimiento> establecimientosObservados3 = List.of(establecimientos.getSucursalCoto2(),establecimientos.getEstacionB2());
    List<Establecimiento> establecimientosObservados4 = List.of(establecimientos.getSucursalCoto2(),establecimientos.getEstacionB2());

    List<Servicio> serviciosEstandar1 = new ArrayList<>();
    serviciosEstandar1.addAll(servicios.getServiciosEstacionA1());
    serviciosEstandar1.addAll(servicios.getServiciosDia1());
    serviciosEstandar1.addAll(servicios.getServiciosDia2());
    List<Servicio> serviciosEstandar2 = new ArrayList<>();
    serviciosEstandar2.addAll(servicios.getServiciosEstacionA1());
    serviciosEstandar2.addAll(servicios.getServiciosDia1());
    serviciosEstandar2.addAll(servicios.getServiciosDia2());
    serviciosEstandar2.remove(0);
    List<Servicio> serviciosEstandar3 =  new ArrayList<>();
    serviciosEstandar3.addAll(servicios.getServiciosCoto2());
    List<Servicio> serviciosEstandar4 = new ArrayList<>();
    serviciosEstandar4.addAll(servicios.getServiciosCoto2());

    this.comunidad1 = new Comunidad("com1", afectados1, observadores1, administradores1,
            establecimientosObservados1, serviciosEstandar1, GradoDeConfianza.CON_RESERVAS, incidentesAbiertos1);
    em.persist(this.comunidad1);


    this.comunidad2 = new Comunidad("com2", afectados2, observadores2, administradores2,
            establecimientosObservados2, serviciosEstandar2, GradoDeConfianza.CON_RESERVAS, incidentesAbiertos2);
    em.persist(this.comunidad2);

    this.comunidad3 = new Comunidad("com3", afectados3, observadores3, administradores1,
            establecimientosObservados3, serviciosEstandar3, GradoDeConfianza.CONFIABLE_NIVEL_1, incidentesAbiertos1);
    em.persist(this.comunidad3);

    this.comunidad4 = new Comunidad("com4", afectados4, observadores4, administradores2,
            establecimientosObservados4, serviciosEstandar4, GradoDeConfianza.CONFIABLE_NIVEL_1, incidentesAbiertos2);
    em.persist(this.comunidad4);
  }
}
