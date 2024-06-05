package co.edu.uco.fink.crosscutting.exception.messageCatalog;

import co.edu.uco.fink.crosscutting.exception.messageCatalog.custom.CrosscuttingFinKException;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.data.CodigoMensaje;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.data.Mensaje;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.impl.MessageCatalogBase;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.impl.MessageCatalogExternalService;
import co.edu.uco.fink.crosscutting.helpers.ObjectHelper;
import co.edu.uco.fink.crosscutting.helpers.TextHelper;

public class MessageCatalogStrategy {

    private static final MessageCatalog base = new MessageCatalogBase();
    private static final MessageCatalog externalService = new MessageCatalogExternalService();

    static {
        inicializar();
    }

    private MessageCatalogStrategy() {
        super();
    }

    public static void inicializar() {
        base.inicializar();
        externalService.inicializar();
    }

    private static final MessageCatalog getStrategy(final boolean isBase) {
        return isBase ? base : externalService;
    }

    public static final Mensaje getMensaje(final CodigoMensaje codigo, final String... parametros) {

        if (ObjectHelper.getObjectHelper().isNUll(codigo)) {
            var mensajeUsuario = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002));
            var mensajeTecnico = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00003));
            throw new CrosscuttingFinKException(mensajeTecnico, mensajeUsuario);
        }

        return getStrategy(codigo.isBase()).obtenerMensaje(codigo, parametros);
    }

    public static final String getContenidoMensaje(final CodigoMensaje codigo, final String... parametros) {
        return getMensaje(codigo, parametros).getContenido();
    }
}
