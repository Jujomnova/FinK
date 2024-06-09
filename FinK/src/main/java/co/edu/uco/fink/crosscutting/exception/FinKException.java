package co.edu.uco.fink.crosscutting.exception;

import co.edu.uco.fink.crosscutting.exception.Enums.Lugar;
import co.edu.uco.fink.crosscutting.helpers.ObjectHelper;
import co.edu.uco.fink.crosscutting.helpers.TextHelper;

public class FinKException extends RuntimeException{

    public static final long serialVersionUID = 1L;

    protected String mensajeUsuario;
    protected Lugar lugar;


    public FinKException(String mensajeTecnico, String mensajeUsuario, Lugar lugar, Throwable excepcionRaiz){
        super(mensajeTecnico, excepcionRaiz);
        setMensajeUsuario(mensajeUsuario);
        setLugar(lugar);
    }

    public FinKException(final String mensajeUsuario, final Lugar lugar){
        super(mensajeUsuario);
        setMensajeUsuario(mensajeUsuario);
        setLugar(lugar);
    }

    public FinKException(String mensajeTecnico, String mensajeUsuario, Lugar lugar){
        super(mensajeTecnico);
        setMensajeUsuario(mensajeUsuario);
        setLugar(lugar);
    }

    public final String getMensajeUsuario() {
        return mensajeUsuario;
    }

    private final void setMensajeUsuario(final String mensajeUsuario) {
        this.mensajeUsuario = TextHelper.applyTrim(mensajeUsuario);
    }

    public final Lugar getLugar() {
        return lugar;
    }

    private final void setLugar(final Lugar lugar) {
        this.lugar = ObjectHelper.getObjectHelper().getDefault(lugar, Lugar.DEFAULT);
    }
}
