package co.edu.uco.fink.data.dao.factory;

import co.edu.uco.fink.crosscutting.exception.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.custom.DataFinKException;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.data.CodigoMensaje;
import co.edu.uco.fink.data.dao.*;
import co.edu.uco.fink.data.dao.factory.enums.Factory;
import co.edu.uco.fink.data.dao.factory.sql.postgreSQL.PostgreSQLDAOfactory;

public abstract class DAOfactory {

    public static final DAOfactory getFactory(final Factory factory){

        switch (factory) {
            case AZURE_SQL: {

                var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
                var mensajeTecnico = "No existe configurada una factoria de datos para una base de datos AZURE_SQL";

                throw new DataFinKException(mensajeTecnico, mensajeUsuario);
            }
            case SQL_SERVER: {

                var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
                var mensajeTecnico = "No existe configurada una factoria de datos para una base de datos SQL_SERVER";

                throw new DataFinKException(mensajeTecnico, mensajeUsuario);
            }
            case POSTGRESQL: {

                return new PostgreSQLDAOfactory();
            }
            case MYSQL: {
                var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
                var mensajeTecnico = "No existe configurada una factoria de datos para una base de datos MYSQL";

                throw new DataFinKException(mensajeTecnico, mensajeUsuario);
            }
            case ORACLE: {
                var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
                var mensajeTecnico = "No existe configurada una factoria de datos para una base de datos ORACLE";

                throw new DataFinKException(mensajeTecnico, mensajeUsuario);
            }
            default: {
                return new PostgreSQLDAOfactory();
            }
        }
    }

    protected abstract void obtenerConexion();

    public abstract void iniciarTransaccion();

    public abstract void confirmarTransaccion();

    public abstract void cancelarTransaccion();

    public abstract void cerrarConexion();

    public abstract FincaDAO getFincaDAO();

    public abstract EspecieDAO getEspecieDAO();

    public abstract RazaDAO getRazaDAO();

    public abstract AnimalDAO getAnimalDAO();

    public abstract RegistroEstadoAnimalDAO getRegistroEstadoAnimalDAO();

    public abstract EstadoAnimalDAO getEstadoAnimalDAO();

    public abstract EmpleadoDAO getEmpleadoDAO();
}
