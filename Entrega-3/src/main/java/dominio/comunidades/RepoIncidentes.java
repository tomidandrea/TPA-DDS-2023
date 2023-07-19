package dominio.comunidades;

import java.util.List;

public class RepoIncidentes {
    RepoIncidentes instance = new RepoIncidentes();
    List<Incidente> incidentes;

    public RepoIncidentes getInstance() {
        return instance;
    }
}
