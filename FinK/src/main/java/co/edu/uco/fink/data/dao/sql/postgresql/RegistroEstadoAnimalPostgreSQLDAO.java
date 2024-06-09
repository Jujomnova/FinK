package co.edu.uco.fink.data.dao.sql.postgresql;

import co.edu.uco.fink.crosscutting.exception.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.custom.DataFinKException;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.data.CodigoMensaje;
import co.edu.uco.fink.crosscutting.helpers.NumericHelper;
import co.edu.uco.fink.crosscutting.helpers.TextHelper;
import co.edu.uco.fink.data.dao.RegistroEstadoAnimalDAO;
import co.edu.uco.fink.data.dao.sql.SQLconnection;
import co.edu.uco.fink.entity.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RegistroEstadoAnimalPostgreSQLDAO extends SQLconnection implements RegistroEstadoAnimalDAO {

    public RegistroEstadoAnimalPostgreSQLDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public List<RegistroEstadoAnimalEntity> consultar(RegistroEstadoAnimalEntity entidad) {
        final var listaRegistros = new ArrayList<RegistroEstadoAnimalEntity>();
        final var sentenciaSql = new StringBuilder();
        sentenciaSql.append("SELECT RE.id AS registroID, A.id AS animalID, R.identificador AS razaID, R.nombre AS razaNombre, ES.identificador AS especieID, ES.nombre AS especieNombre, A.codigo AS animalCodigo, F.id AS fincaID, F.nombre AS fincaNombre, E.id AS estadoID, E.estado, RE.fechaactualizacion ");
        sentenciaSql.append("FROM registroestadoanimal RE JOIN animal A ");
        sentenciaSql.append("ON RE.animal = concat_ws('-', A.raza, A.codigo, A.finca) ");
        sentenciaSql.append("JOIN raza R ");
        sentenciaSql.append("ON A.raza = concat_ws('-', R.nombre, R.especie) ");
        sentenciaSql.append("JOIN especie ES ");
        sentenciaSql.append("ON R.especie = ES.nombre ");
        sentenciaSql.append("JOIN finca F ");
        sentenciaSql.append("ON A.finca = concat_ws('-', F.nombre) ");
        sentenciaSql.append("JOIN estadoanimal E ");
        sentenciaSql.append("ON RE.estado = E.estado ");
        sentenciaSql.append("ORDER BY RE.fechaactualizacion DESC ");

        try (final PreparedStatement sentenciaPreparada = getConnection().prepareStatement(sentenciaSql.toString())){
            try (final ResultSet resultado = sentenciaPreparada.executeQuery()){
                while (resultado.next()){
                    RegistroEstadoAnimalEntity registroTMP = RegistroEstadoAnimalEntity.Build(resultado.getInt("registroID"), AnimalEntity.Build(resultado.getInt("animalID"), RazaEntity.Build(resultado.getInt("razaID"), resultado.getString("razaNombre"), EspecieEntity.Build(resultado.getInt("especieID"), resultado.getString("especieNombre"))), resultado.getInt("animalCodigo"), FincaEntity.Build(resultado.getInt("fincaID"), resultado.getString("fincaNombre"))), EstadoAnimalEntity.Build(resultado.getInt("estadoID"), resultado.getString("estado")), resultado.getObject("fechaactualizacion", LocalDateTime.class));
                    listaRegistros.add(registroTMP);
                }
            }
        } catch (final SQLException exception){
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000023),"consultar", "RegistroEstadoAnimal");
            throw new DataFinKException(mensajeTecnico, mensajeUsuario, exception);
        } catch (final DataFinKException exception){
            throw exception;
        } catch (final Exception exception) {
            var mensajeUsuario = "No ha sido posible llevar a cabo la consulta de las Fincas. Por favor intente de nuevo y en caso de persistir el problema comuníquese con el administrador de la app tiendaChepito";
            var mensajeTecnico = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000024), "consultar", "RegistroEstadoAnimal");
            throw new DataFinKException(mensajeTecnico, mensajeUsuario, exception);
        }
        return listaRegistros;
    }

    @Override
    public void crear(RegistroEstadoAnimalEntity entidad) {
        final var sentenciaSql = new StringBuilder();
        sentenciaSql.append("INSERT INTO registroestadoanimal(animal, estado, fechaactualizacion) ");
        sentenciaSql.append("VALUES ((SELECT string_agg(concat_ws('-', raza, codigo, finca), '') ");
        sentenciaSql.append("            FROM animal WHERE animal.id = ?), ?, ?) ");

        try (final PreparedStatement sentenciaPreparada = getConnection().prepareStatement(sentenciaSql.toString())) {
            sentenciaPreparada.setInt(1, entidad.getAnimal().getIdentificador());
            sentenciaPreparada.setString(2, entidad.getEstado().getEstado());
            sentenciaPreparada.setTimestamp(3, Timestamp.valueOf(entidad.getFechaActualizacion()));

            sentenciaPreparada.executeUpdate();
        } catch (final SQLException exception) {
            var mensajeUsuario = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002));
            var mensajeTecnico = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000023),"crear", "RegistroEstadoAnimal");
            throw new DataFinKException(mensajeTecnico, mensajeUsuario, exception);
        } catch (final Exception exception) {
            var mensajeUsuario = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002));
            var mensajeTecnico = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000024),"crear", "RegistroEstadoAnimal");
            throw new DataFinKException(mensajeTecnico, mensajeUsuario, exception);
        }
    }

    @Override
    public void actualizarEstado(AnimalEntity animal, EstadoAnimalEntity estado) {
        RegistroEstadoAnimalEntity nuevoRegistro = RegistroEstadoAnimalEntity.Build(NumericHelper.ZERO, animal, estado, LocalDateTime.now());

        crear(nuevoRegistro);
    }
}
