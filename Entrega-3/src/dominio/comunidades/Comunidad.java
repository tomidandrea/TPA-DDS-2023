package dominio.comunidades;

import dominio.clasesTecnicas.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Comunidad {
    List<Miembro> miembros;
    List<Usuario> administradores;
    List<Incidente> incidentesAbiertos;


    void notificarIncidentesAMiembros() {

    }

    private List<Incidente> obtenerIncidentesAbiertosDesde(LocalTime hora) {

    }

}
