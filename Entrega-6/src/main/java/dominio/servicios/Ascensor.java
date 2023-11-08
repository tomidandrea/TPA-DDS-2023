package dominio.servicios;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Ascensor")
public class Ascensor extends MedioElevacion {
}
