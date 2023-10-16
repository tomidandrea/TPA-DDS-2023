package Persistencia;

import Utils.BDUtils;
import dominio.Localizacion.Centroide;
import dominio.Localizacion.Provincia;
import dominio.Notificacion.MedioCorreo;
import dominio.comunidades.Interes;
import dominio.comunidades.Miembro;
import dominio.comunidades.Notificador;
import lombok.Getter;

import javax.persistence.EntityManager;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class InstanciasMiembro {
    private Miembro miembro1;
    private Miembro miembro2;
    private Miembro miembro3;
    private Miembro miembro4;
    private Miembro miembro5;
    private Miembro miembro6;
    private Miembro miembro7;

    public InstanciasMiembro(EntityManager em){
        List<Provincia> provincias = crearProvincias(em);
        List<Notificador> notificadores = crearNotificadores(em);

        this.miembro1 = new Miembro("Blue", "Label", "blue@label", provincias.get(1),
                new MedioCorreo(), "1234", notificadores.get(0),
                "blueLabel", "23campeonDelMundo#3");
        this.miembro2 = new Miembro("Azul","Lebal", "azul@lebal", provincias.get(0),
                new MedioCorreo(), "1234", notificadores.get(1),
                "azuLebal", "23campeonDelMundo#3");
        this.miembro3 = new Miembro("Rodolfo", "Gonzalez","rodo@gmail.com", provincias.get(2),
                new MedioCorreo(), "1234", notificadores.get(2),
                "rodoooG", "23campeonDelMundo#3");
        this.miembro4 = new Miembro("Chef", "Curry", "sthep@hotmail.com", provincias.get(2),
                new MedioCorreo(), "1234", notificadores.get(3),
                "curry34", "23campeonDelMundo#3");
        this.miembro5 = new Miembro("Lucho", "Gonzalez", "lucho@gmail", provincias.get(1),
                new MedioCorreo(), "1234", notificadores.get(4),
                "luchoGG", "23campeonDelMundo#3");
        this.miembro6 = new Miembro("Lionel","Mezzi", "lio@gmail", provincias.get(1),
                new MedioCorreo(), "1234", notificadores.get(5),
                "marciano10", "23campeonDelMundo#3");
        this.miembro7 = new Miembro("Pepe", "Argento", "pepe@argento", provincias.get(0),
                new MedioCorreo(), "1234", notificadores.get(6),
                "pepeArg", "23campeonDelMundo#3");
        em.persist(miembro1.getInteres());
        em.persist(miembro2.getInteres());
        em.persist(miembro3.getInteres());
        em.persist(miembro4.getInteres());
        em.persist(miembro5.getInteres());
        em.persist(miembro6.getInteres());
        em.persist(miembro7.getInteres());

        em.persist(miembro1);
        em.persist(miembro2);
        em.persist(miembro3);
        em.persist(miembro4);
        em.persist(miembro5);
        em.persist(miembro6);
        em.persist(miembro7);
    }

    private List<Provincia> crearProvincias(EntityManager em){
        Centroide centroide1 = new Centroide(34.0, 58.0);
        Provincia provincia1 = new Provincia("Buenos Aires", centroide1);
        em.persist(provincia1);
        Centroide centroide2 = new Centroide(24.0, 64.0);
        Provincia provincia2 = new Provincia("Salta", centroide2);
        em.persist(provincia2);
        Centroide centroide3 = new Centroide(54.0, 67.0);
        Provincia provincia3 = new Provincia("Tierra del fuego", centroide3);
        em.persist(provincia3);
        return List.of(provincia1, provincia2, provincia3);
    }

    private List<Notificador> crearNotificadores(EntityManager em){
        List<LocalTime> horarios1 = new ArrayList<LocalTime>();
        horarios1.add(LocalTime.now());
        horarios1.add(LocalTime.now().plusHours(5));
        Collections.sort(horarios1);
        Notificador notificador1 = new Notificador(horarios1);

        List<LocalTime> horarios2 = new ArrayList<LocalTime>();
        horarios2.add(LocalTime.now().plusHours(1));
        Notificador notificador2 = new Notificador(horarios2);

        List<LocalTime> horarios3 = new ArrayList<LocalTime>();
        horarios3.add(LocalTime.now().plusHours(2));
        horarios3.add(LocalTime.now().plusHours(6));
        Collections.sort(horarios3);
        Notificador notificador3 = new Notificador(horarios3);

        List<LocalTime> horarios4 = new ArrayList<LocalTime>();
        horarios4.add(LocalTime.now());
        horarios4.add(LocalTime.now().plusHours(5));
        Collections.sort(horarios4);
        Notificador notificador4 = new Notificador(horarios4);

        List<LocalTime> horarios5 = new ArrayList<LocalTime>();
        horarios5.add(LocalTime.now().plusHours(1));
        Notificador notificador5 = new Notificador(horarios5);

        List<LocalTime> horarios6 = new ArrayList<LocalTime>();
        horarios6.add(LocalTime.now().plusHours(2));
        horarios6.add(LocalTime.now().plusHours(6));
        Collections.sort(horarios6);
        Notificador notificador6 = new Notificador(horarios6);

        List<LocalTime> horarios7 = new ArrayList<LocalTime>();
        horarios6.add(LocalTime.now().plusHours(2));
        Notificador notificador7 = new Notificador(horarios7);

        return List.of(notificador1, notificador2, notificador3, notificador4,
                notificador5, notificador6, notificador7);
    }
}
