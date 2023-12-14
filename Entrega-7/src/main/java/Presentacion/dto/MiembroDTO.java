package Presentacion.dto;

import dominio.Localizacion.Localizacion;
import dominio.Notificacion.MedioDeComunicacion;
import dominio.comunidades.Comunidad;
import dominio.comunidades.Interes;
import dominio.comunidades.Miembro;
import dominio.comunidades.TipoMiembro;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MiembroDTO {
    private int id;
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String nroDeTelefono;
    private TipoMiembro tipo;

    public MiembroDTO(){

    }

    public MiembroDTO(Miembro miembro, TipoMiembro tipo) {
        this.id = miembro.getId();
        this.nombre = miembro.getNombre();
        this.apellido = miembro.getApellido();
        this.correoElectronico = miembro.getCorreoElectronico();
        this.nroDeTelefono = miembro.getNroDeTelefono();
        this.tipo = tipo;
    }
}