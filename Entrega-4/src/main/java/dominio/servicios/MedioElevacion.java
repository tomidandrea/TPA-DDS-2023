package dominio.servicios;

import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class MedioElevacion extends Servicio{
    @Setter
    @Enumerated(EnumType.STRING)
    private TramoMedioElevacion tramoRecorrido;
}
