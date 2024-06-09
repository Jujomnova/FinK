package co.edu.uco.fink.data.dao.sql.postgresql;

import co.edu.uco.fink.crosscutting.exception.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.custom.DataFinKException;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.data.CodigoMensaje;
import co.edu.uco.fink.crosscutting.helpers.TextHelper;
import co.edu.uco.fink.data.dao.RazaDAO;
import co.edu.uco.fink.data.dao.sql.SQLconnection;
import co.edu.uco.fink.entity.EspecieEntity;
import co.edu.uco.fink.entity.RazaEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RazaPostgreSQLDAO extends SQLconnection implements RazaDAO {

    public RazaPostgreSQLDAO(final Connection connection){
        super(connection);
    }

    @Override
    public List<RazaEntity> consultar(RazaEntity entidad) {
        final var listaRazas = new ArrayList<RazaEntity>();
        final var sentenciaSQL = new StringBuilder();

        sentenciaSQL.append("SELECT R.identificador, R.nombre, E.identificador, E.nombre ");
        sentenciaSQL.append("FROM raza R JOIN especie E ");
        sentenciaSQL.append("ON R.especie = E.nombre ");
        sentenciaSQL.append("ORDER BY R.nombre ASC ");

        try (final PreparedStatement sentenciaPreparada = getConnection().prepareStatement(sentenciaSQL.toString())){
            try (final ResultSet resultado = sentenciaPreparada.executeQuery()){
                while (resultado.next()){
                    RazaEntity RazaTMP = RazaEntity.Build(resultado.getInt("identificador"), resultado.getString("nombre"), EspecieEntity.Build(resultado.getInt("identificador"), resultado.getString("nombre")));
                    listaRazas.add(RazaTMP);
                }
            }
        } catch (SQLException exception){
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);;
            var mensajeTecnico = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000023), "consultar", "Raza");
            throw new DataFinKException(mensajeTecnico, mensajeUsuario, exception);
        } catch (final DataFinKException exception){
            throw exception;
        } catch (final Exception exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);;
            var mensajeTecnico = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000024), "consultar", "Raza");
            throw new DataFinKException(mensajeTecnico, mensajeUsuario, exception);
        }

        return listaRazas;
    }
}
