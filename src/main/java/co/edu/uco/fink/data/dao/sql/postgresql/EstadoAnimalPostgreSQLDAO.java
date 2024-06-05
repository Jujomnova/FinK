package co.edu.uco.fink.data.dao.sql.postgresql;

import co.edu.uco.fink.crosscutting.exception.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.custom.DataFinKException;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.data.CodigoMensaje;
import co.edu.uco.fink.crosscutting.helpers.TextHelper;
import co.edu.uco.fink.data.dao.EstadoAnimalDAO;
import co.edu.uco.fink.data.dao.sql.SQLconnection;
import co.edu.uco.fink.entity.EspecieEntity;
import co.edu.uco.fink.entity.EstadoAnimalEntity;
import co.edu.uco.fink.entity.RazaEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoAnimalPostgreSQLDAO extends SQLconnection implements EstadoAnimalDAO {

    public EstadoAnimalPostgreSQLDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public List<EstadoAnimalEntity> consultar(EstadoAnimalEntity entidad) {
        final var listaEstados = new ArrayList<EstadoAnimalEntity>();
        final var sentenciaSQL = new StringBuilder();

        sentenciaSQL.append("SELECT id, estado ");
        sentenciaSQL.append("FROM estadoanimal ");
        try (final PreparedStatement sentenciaPreparada = getConnection().prepareStatement(sentenciaSQL.toString())){
            try (final ResultSet resultado = sentenciaPreparada.executeQuery()){
                while (resultado.next()){
                    EstadoAnimalEntity EstadoTMP = EstadoAnimalEntity.Build(resultado.getInt("id"), resultado.getString("estado"));
                    listaEstados.add(EstadoTMP);
                }
            }
        } catch (SQLException exception){
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000023), "cosultar", "EstadoAnimal");
            throw new DataFinKException(mensajeTecnico, mensajeUsuario, exception);
        } catch (final DataFinKException exception){
            throw exception;
        } catch (final Exception exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000024), "consultar", "EstadoAnimal");
            throw new DataFinKException(mensajeTecnico, mensajeUsuario, exception);
        }

        return listaEstados;
    }
}
