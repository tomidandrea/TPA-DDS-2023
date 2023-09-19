package dominio.rankings;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ranking_id")
    private int id;
    private LocalDate fecha;

    public Ranking(){ }

    public Ranking(LocalDate fecha){
        this.fecha = fecha;
    }
}
