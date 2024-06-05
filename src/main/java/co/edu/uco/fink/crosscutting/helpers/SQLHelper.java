package co.edu.uco.fink.crosscutting.helpers;

import co.edu.uco.fink.crosscutting.exception.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.custom.CrosscuttingFinKException;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.data.CodigoMensaje;

import java.sql.Connection;
import java.sql.SQLException;

public final class SQLHelper {

    private SQLHelper() {
        super();
    }

    public static final boolean IsNull (final Connection connection){
        return ObjectHelper.getObjectHelper().isNUll(connection);
    }

    public static final boolean isOpen(final Connection connection){
        try{
            return !IsNull(connection) && !connection.isClosed();
        } catch (SQLException exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00007);

            throw new CrosscuttingFinKException(mensajeTecnico, mensajeUsuario, exception);
        } catch (Exception exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00008);

            throw new CrosscuttingFinKException(mensajeTecnico, mensajeUsuario, exception);
        }
    }

    public static final void close(final Connection connection){
        try {
            if (!isOpen(connection)) {
                var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
                var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00009);

                throw new CrosscuttingFinKException(mensajeTecnico, mensajeUsuario);
            }

            connection.close();
        } catch (final CrosscuttingFinKException exception) {
            throw exception;
        } catch (SQLException exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000010);

            throw new CrosscuttingFinKException(mensajeTecnico, mensajeUsuario, exception);
        } catch (Exception exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000011);

            throw new CrosscuttingFinKException(mensajeTecnico, mensajeUsuario, exception);
        }
    }

    public static final void commit(final Connection connection){
        try {
            if (!isOpen(connection)) {
                var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
                var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000012);

                throw new CrosscuttingFinKException(mensajeTecnico, mensajeUsuario);
            }

            if(connection.getAutoCommit()) {
                var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
                var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000013);

                throw new CrosscuttingFinKException(mensajeTecnico, mensajeUsuario);
            }

            connection.commit();
        } catch (final CrosscuttingFinKException exception) {
            throw exception;
        } catch (SQLException exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000014);

            throw new CrosscuttingFinKException(mensajeTecnico, mensajeUsuario, exception);
        } catch (Exception exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000015);

            throw new CrosscuttingFinKException(mensajeTecnico, mensajeUsuario, exception);
        }
    }

    public static final void rollback(final Connection connection){
        try {
            if (!isOpen(connection)) {
                var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
                var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000016);

                throw new CrosscuttingFinKException(mensajeTecnico, mensajeUsuario);
            }

            if(connection.getAutoCommit()) {
                var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
                var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000017);

                throw new CrosscuttingFinKException(mensajeTecnico, mensajeUsuario);
            }

            connection.rollback();
        } catch (final CrosscuttingFinKException exception) {
            throw exception;
        } catch (SQLException exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000018);

            throw new CrosscuttingFinKException(mensajeTecnico, mensajeUsuario, exception);
        } catch (Exception exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000019);

            throw new CrosscuttingFinKException(mensajeTecnico, mensajeUsuario, exception);
        }
    }

    public static final void initTransaction(final Connection connection){
        try {
            if (!isOpen(connection)) {
                var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
                var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000020);

                throw new CrosscuttingFinKException(mensajeTecnico, mensajeUsuario);
            }

            connection.setAutoCommit(false);
        } catch (final CrosscuttingFinKException exception) {
            throw exception;
        } catch (SQLException exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000021);

            throw new CrosscuttingFinKException(mensajeTecnico, mensajeUsuario, exception);
        } catch (Exception exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000022);

            throw new CrosscuttingFinKException(mensajeTecnico, mensajeUsuario, exception);
        }
    }
}
