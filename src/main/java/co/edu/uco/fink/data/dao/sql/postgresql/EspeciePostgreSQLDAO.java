package co.edu.uco.fink.data.dao.sql.postgresql;

import co.edu.uco.fink.crosscutting.exception.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.custom.DataFinKException;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.data.CodigoMensaje;
import co.edu.uco.fink.crosscutting.helpers.TextHelper;
import co.edu.uco.fink.data.dao.EspecieDAO;
import co.edu.uco.fink.data.dao.sql.SQLconnection;
import co.edu.uco.fink.entity.EspecieEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspeciePostgreSQLDAO extends SQLconnection implements EspecieDAO {

    public EspeciePostgreSQLDAO(final Connection connection){
        super(connection);
    }

    @Override
    public List<EspecieEntity> consultar(EspecieEntity entidad) {
        final var listaEspecies = new ArrayList<EspecieEntity>();
        final var sentenciaSQL = new StringBuilder();

        sentenciaSQL.append("SELECT identificador, nombre ");
        sentenciaSQL.append("FROM especie ");
        sentenciaSQL.append("ORDER BY nombre ASC ");

        try (final PreparedStatement sentenciaPreparada = getConnection().prepareStatement(sentenciaSQL.toString())){
            try (final ResultSet resultado = sentenciaPreparada.executeQuery()){
                while (resultado.next()){
                    EspecieEntity EspecieTMP = EspecieEntity.Build(resultado.getInt("identificador"), resultado.getString("nombre"));
                    listaEspecies.add(EspecieTMP);
                }
            }
        } catch (SQLException exception){
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000023), "consultar", "Especie");
            throw new DataFinKException(mensajeTecnico, mensajeUsuario, exception);
        } catch (final DataFinKException exception){
            throw exception;
        } catch (final Exception exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000024), "consultar", "Especie");
            throw new DataFinKException(mensajeTecnico, mensajeUsuario, exception);
        }

        return listaEspecies;
    }
}
