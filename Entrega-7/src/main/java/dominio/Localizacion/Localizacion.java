package dominio.Localizacion;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
public abstract class Localizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "localizacion_id")
    private int id;
    public String nombre;
    @Embedded
    public Centroide centroide;

    public Localizacion(){}
    public Localizacion(String nombre, Integer id, Centroide centroide) {
        this.nombre = nombre;
        this.id = id;
        this.centroide = centroide;
    }
    public Localizacion(String nombre, Centroide centroide) {
        this.nombre = nombre;
        this.centroide = centroide;
    }
}
