package dominio.comunidades;

import java.util.List;

public class RepoComunidades {
    private static RepoComunidades instance = null;

    public static RepoComunidades getInstance(){
        if(instance == null){
            instance = new RepoComunidades();
        }
        return instance;
    }
    public List<Comunidad> comunidades;
}
