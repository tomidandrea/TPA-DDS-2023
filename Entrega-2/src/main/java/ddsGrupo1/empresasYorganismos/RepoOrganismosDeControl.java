package ddsGrupo1.empresasYorganismos;

import java.util.ArrayList;
import java.util.List;

public class RepoOrganismosDeControl {
    // singleton}
    private static RepoOrganismosDeControl instance = null;

    public static RepoOrganismosDeControl getInstance(){
        if(instance == null){
            instance = new RepoOrganismosDeControl();
        }
        return instance;
    }

    List<OrganismoDeControl> organismosDeControl = new ArrayList<>();

    public void agregar(OrganismoDeControl organismoDeControl) {
        organismosDeControl.add(organismoDeControl);
    }
}
