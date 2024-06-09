package co.edu.uco.fink.crosscutting.exception.custom;

import co.edu.uco.fink.crosscutting.exception.FinKException;
import co.edu.uco.fink.crosscutting.exception.Enums.Lugar;

public final class BusinessFinkException extends FinKException {

	private static final long serialVersionUID = 1L;
	private static final Lugar lugar = Lugar.BUSINESS;

	public BusinessFinkException(final String mensajeUsuario) {
		super(mensajeUsuario, lugar);
	}

	public BusinessFinkException(final String mensajeTecnico, final String mensajeUsuario) {
		super(mensajeTecnico, mensajeUsuario, lugar);
	}

	public BusinessFinkException(final String mensajeTecnico, final String mensajeUsuario,
			final Throwable excepcionRaiz) {
		super(mensajeTecnico, mensajeUsuario, lugar, excepcionRaiz);
	}
}
