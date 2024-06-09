package co.edu.uco.fink.crosscutting.exception.messageCatalog;

import co.edu.uco.fink.crosscutting.exception.messageCatalog.data.CodigoMensaje;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.data.Mensaje;

public interface MessageCatalog {

    void inicializar();

    String obtenerContenidoMensaje(final CodigoMensaje codigo, String... parametros);

    Mensaje obtenerMensaje(final CodigoMensaje codigo, String... parametros);
}
