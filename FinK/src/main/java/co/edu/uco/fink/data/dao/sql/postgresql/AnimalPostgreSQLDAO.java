package co.edu.uco.fink.data.dao.sql.postgresql;

import co.edu.uco.fink.crosscutting.exception.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.custom.DataFinKException;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.data.CodigoMensaje;
import co.edu.uco.fink.crosscutting.helpers.NumericHelper;
import co.edu.uco.fink.crosscutting.helpers.TextHelper;
import co.edu.uco.fink.data.dao.AnimalDAO;
import co.edu.uco.fink.data.dao.factory.DAOfactory;
import co.edu.uco.fink.data.dao.sql.SQLconnection;
import co.edu.uco.fink.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AnimalPostgreSQLDAO extends SQLconnection implements AnimalDAO {

    public AnimalPostgreSQLDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public List<AnimalEntity> consultar(AnimalEntity entidad) {
        final var listaAnimales = new ArrayList<AnimalEntity>();
        final var sentenciaSql = new StringBuilder();
        sentenciaSql.append("SELECT A.id AS animalID, R.identificador AS razaID, R.nombre AS razaNombre, E.identificador AS especieID, E.nombre especieNombre, A.codigo AS animalCodigo, F.id AS fincaID, F.nombre AS fincaNombre ");
        sentenciaSql.append("FROM animal A JOIN raza R ");
        sentenciaSql.append("ON A.raza = concat_ws('-', R.nombre, R.especie) ");
        sentenciaSql.append("JOIN especie E ");
        sentenciaSql.append("ON R.especie = E.nombre ");
        sentenciaSql.append("JOIN finca F ");
        sentenciaSql.append("ON A.finca = F.nombre ");
        sentenciaSql.append("ORDER BY A.codigo ASC");

        try (final PreparedStatement sentenciaPreparada = getConnection().prepareStatement(sentenciaSql.toString())){
            try (final ResultSet resultado = sentenciaPreparada.executeQuery()){
                while (resultado.next()){
                    AnimalEntity AnimalTMP = AnimalEntity.Build(resultado.getInt("animalID"), RazaEntity.Build(resultado.getInt("razaID"), resultado.getString("razaNombre"), EspecieEntity.Build(resultado.getInt("especieID"), resultado.getString("especieNombre"))), resultado.getInt("animalCodigo"), FincaEntity.Build(resultado.getInt("fincaID"), resultado.getString("fincaNombre")));
                    listaAnimales.add(AnimalTMP);
                }
            }
        } catch (final SQLException exception){
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000023), "consultar", "Animal");
            throw new DataFinKException(mensajeTecnico, mensajeUsuario, exception);
        } catch (final DataFinKException exception){
            throw exception;
        } catch (final Exception exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000024), "consultar", "Animal");
            throw new DataFinKException(mensajeTecnico, mensajeUsuario, exception);
        }
        return listaAnimales;
    }
}
