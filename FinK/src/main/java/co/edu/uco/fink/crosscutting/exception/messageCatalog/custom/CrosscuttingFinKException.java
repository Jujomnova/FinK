package co.edu.uco.fink.crosscutting.exception.messageCatalog.custom;

import co.edu.uco.fink.crosscutting.exception.Enums.Lugar;
import co.edu.uco.fink.crosscutting.exception.FinKException;

public class CrosscuttingFinKException extends FinKException {

    private static final long serialVersionUID = 1L;
    private static final Lugar lugar = Lugar.CROSSCUTTING;


    public CrosscuttingFinKException(String mensajeUsuario) {
        super(mensajeUsuario, lugar);
    }

    public CrosscuttingFinKException(String mensajeTecnico, String mensajeUsuario) {
        super(mensajeTecnico, mensajeUsuario, lugar);
    }

    public CrosscuttingFinKException(final String mensajeTecnico, final String mensajeUsuario,
                                 final Throwable excepcionRaiz) {
        super(mensajeTecnico, mensajeUsuario, lugar, excepcionRaiz);
    }
}
