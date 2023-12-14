package dominio.comunidades;

import dominio.clasesTecnicas.Usuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
public class Administrador extends Usuario {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
