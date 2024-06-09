package co.edu.uco.fink.data.dao.sql.postgresql;

import co.edu.uco.fink.crosscutting.exception.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.custom.DataFinKException;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.data.CodigoMensaje;
import co.edu.uco.fink.crosscutting.helpers.TextHelper;
import co.edu.uco.fink.data.dao.EmpleadoDAO;
import co.edu.uco.fink.data.dao.sql.SQLconnection;
import co.edu.uco.fink.entity.EmpleadoEntity;
import co.edu.uco.fink.entity.FincaEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoPostgreSQLDAO extends SQLconnection implements EmpleadoDAO {

    public EmpleadoPostgreSQLDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public List<EmpleadoEntity> consultar(EmpleadoEntity entidad) {
        return List.of();
    }


    @Override
    public List<FincaEntity> verificarEmpleado(EmpleadoEntity empleado) {
        final var listaFincas = new ArrayList<FincaEntity>();
        final var sentenciaSQL = new StringBuilder();

        sentenciaSQL.append("SELECT F.id, F.nombre ");
        sentenciaSQL.append("FROM finca F JOIN empleado E ");
        sentenciaSQL.append("ON E.finca = F.nombre ");
        sentenciaSQL.append("WHERE E.documento = ? AND E.clave = ? ");

        try (final PreparedStatement sentenciaPreparada = getConnection().prepareStatement(sentenciaSQL.toString())){
            sentenciaPreparada.setInt(1, empleado.getDocumento());
            sentenciaPreparada.setString(2, empleado.getClave());

            try (final ResultSet resultado = sentenciaPreparada.executeQuery()){
                while (resultado.next()){
                    FincaEntity fincaTemp = FincaEntity.Build(resultado.getInt("id"), resultado.getString("nombre"));
                    listaFincas.add(fincaTemp);
                }
            }
        } catch (final SQLException exception){
            var mensajeUsuario = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002));
            var mensajeTecnico = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002), "SELECT", "tareas", "tarea");
            throw new DataFinKException(mensajeTecnico, mensajeUsuario, exception);
        } catch (final DataFinKException exception){
            throw exception;
        } catch (final Exception exception) {
            var mensajeUsuario = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002));
            var mensajeTecnico = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002), "SELECT", "tareas", "tarea");
            throw new DataFinKException(mensajeTecnico, mensajeUsuario, exception);
        }
        return listaFincas;
    }

    @Override
    public List<EmpleadoEntity> obtenerEstado(EmpleadoEntity empleado) {
        final var listaEmpleados = new ArrayList<EmpleadoEntity>();
        final var sentenciaSQL = new StringBuilder();

        sentenciaSQL.append("SELECT id, documento, estado ");
        sentenciaSQL.append("FROM empleado ");
        sentenciaSQL.append("WHERE documento = ? ");

        try (final PreparedStatement sentenciaPreparada = getConnection().prepareStatement(sentenciaSQL.toString())){
            sentenciaPreparada.setInt(1, empleado.getDocumento());

            try (final ResultSet resultado = sentenciaPreparada.executeQuery()){
                while (resultado.next()){
                    EmpleadoEntity empleadoTemp = EmpleadoEntity.Build(resultado.getInt("id"));
                    empleadoTemp.setDocumento(resultado.getInt("documento"));
                    empleadoTemp.setEstado(resultado.getString("estado"));
                    listaEmpleados.add(empleadoTemp);
                }
            }
        } catch (final SQLException exception){
            var mensajeUsuario = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002));
            var mensajeTecnico = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000023), "consultar", "empleados");
            throw new DataFinKException(mensajeTecnico, mensajeUsuario, exception);
        } catch (final DataFinKException exception){
            throw exception;
        } catch (final Exception exception) {
            var mensajeUsuario = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002));
            var mensajeTecnico = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000024), "consultar", "empleados");
            throw new DataFinKException(mensajeTecnico, mensajeUsuario, exception);
        }
        return listaEmpleados;
    }
}
