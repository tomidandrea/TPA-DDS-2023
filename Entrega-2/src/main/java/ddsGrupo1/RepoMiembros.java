package ddsGrupo1;

import java.util.List;

public class RepoMiembros {

    private static RepoMiembros instance = null;

    public static RepoMiembros getInstance(){
        if(instance == null){
            instance = new RepoMiembros();
        }
        return instance;
    }
    List<Miembro> miembros;

    //get miembro por id

    public Miembro getMiembro(int id){
        return miembros.stream().filter(miembro -> miembro.compararId(id)).findFirst().get();
    }
}
