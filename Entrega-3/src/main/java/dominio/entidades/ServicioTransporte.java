package dominio.entidades;

public class ServicioTransporte {
    MedioDeTransporte tipoTransporte;
    Linea lineaDeTransporteIda;
    Linea lineaDeTransporteVuelta;

    public ServicioTransporte(MedioDeTransporte tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    public void setTipoTransporte(MedioDeTransporte tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }
}
