package co.edu.uco.fink.crosscutting.exception.messageCatalog.custom;

import co.edu.uco.fink.crosscutting.exception.Enums.Lugar;
import co.edu.uco.fink.crosscutting.exception.FinKException;

public class EntityFinKException extends FinKException {

    private static final long serialVersionUID = 1L;
    private static final Lugar lugar = Lugar.ENTITY;

    public EntityFinKException(String mensajeUsuario) {
        super(mensajeUsuario, lugar);
    }

    public EntityFinKException(String mensajeTecnico, String mensajeUsuario) {
        super(mensajeTecnico, mensajeUsuario, lugar);
    }

    public EntityFinKException(final String mensajeTecnico, final String mensajeUsuario,
                                 final Throwable excepcionRaiz) {
        super(mensajeTecnico, mensajeUsuario, lugar, excepcionRaiz);
    }
}
