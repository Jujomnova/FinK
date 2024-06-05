package co.edu.uco.fink.data.dao.sql.postgresql;

import co.edu.uco.fink.crosscutting.exception.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.custom.DataFinKException;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.data.CodigoMensaje;
import co.edu.uco.fink.crosscutting.helpers.TextHelper;
import co.edu.uco.fink.data.dao.FincaDAO;
import co.edu.uco.fink.data.dao.sql.SQLconnection;
import co.edu.uco.fink.entity.FincaEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FincaPostgreSQLDAO extends SQLconnection implements FincaDAO {

    public FincaPostgreSQLDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public List<FincaEntity> consultar(FincaEntity entidad) {
        final var listaFincas = new ArrayList<FincaEntity>();
        final var sentenciaSql = new StringBuilder();
        sentenciaSql.append("SELECT identificador, nombre ");
        sentenciaSql.append("FROM finca ");
        sentenciaSql.append("ORDER BY nombre ASC");

        try (final PreparedStatement sentenciaPreparada = getConnection().prepareStatement(sentenciaSql.toString())){
            try (final ResultSet resultado = sentenciaPreparada.executeQuery()){
                while (resultado.next()){
                    FincaEntity fincaTMP = FincaEntity.Build(resultado.getInt("identificador"), resultado.getString("nombre"));
                    listaFincas.add(fincaTMP);
                }
            }
        } catch (final SQLException exception){
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);;
            var mensajeTecnico = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000023), "consultar", "Finca");
            throw new DataFinKException(mensajeTecnico, mensajeUsuario, exception);
        } catch (final DataFinKException exception){
            throw exception;
        } catch (final Exception exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);;
            var mensajeTecnico = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000024), "consultar", "Finca");
            throw new DataFinKException(mensajeTecnico, mensajeUsuario, exception);
        }
        return listaFincas;
    }
}
