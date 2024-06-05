package co.edu.uco.fink.crosscutting.exception.messageCatalog.custom;

import co.edu.uco.fink.crosscutting.exception.Enums.Lugar;
import co.edu.uco.fink.crosscutting.exception.FinKException;

public class ControllerFinKException extends FinKException {

    private static final long serialVersionUID = 1L;
    private static final Lugar lugar = Lugar.CONTROLLER;


    public ControllerFinKException(String mensajeUsuario) {
        super(mensajeUsuario, lugar);
    }

    public ControllerFinKException(String mensajeTecnico, String mensajeUsuario) {
        super(mensajeTecnico, mensajeUsuario, lugar);
    }

    public ControllerFinKException(final String mensajeTecnico, final String mensajeUsuario,
                                 final Throwable excepcionRaiz) {
        super(mensajeTecnico, mensajeUsuario, lugar, excepcionRaiz);
    }
}
