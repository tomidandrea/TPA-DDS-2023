package dominio;

import java.util.ArrayList;
import java.util.List;

public class RepoMiembros {

    private static RepoMiembros instance = null;

    public static RepoMiembros getInstance(){
        if(instance == null){
            instance = new RepoMiembros();
        }
        return instance;
    }
    List<Miembro> miembros = new ArrayList<>();

    //get miembro por id

    public Miembro getMiembro(int id){
        return miembros.stream().filter(miembro -> miembro.compararId(id)).findFirst().get();
    }

    public void agregar(Miembro miembro) {
        miembros.add(miembro);
    }
}
