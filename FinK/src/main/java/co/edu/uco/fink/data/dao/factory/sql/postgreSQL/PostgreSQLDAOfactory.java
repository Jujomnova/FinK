package co.edu.uco.fink.data.dao.factory.sql.postgreSQL;

import co.edu.uco.fink.crosscutting.helpers.SQLHelper;
import co.edu.uco.fink.data.dao.*;
import co.edu.uco.fink.data.dao.factory.DAOfactory;
import co.edu.uco.fink.data.dao.sql.postgresql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class PostgreSQLDAOfactory extends DAOfactory {

    private Connection connection;

    public PostgreSQLDAOfactory() { obtenerConexion(); }

    protected void obtenerConexion(){
        try {
            String url = "jdbc:postgresql://viaduct.proxy.rlwy.net:45393/railway";
            String user = "postgres";
            String password = "iIjVOVebgsUlOjleMtLUpuzsWPKOMlmH";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void iniciarTransaccion() {
        SQLHelper.initTransaction(connection);
    }

    @Override
    public void confirmarTransaccion() {
        SQLHelper.commit(connection);
    }

    @Override
    public void cancelarTransaccion() {
        SQLHelper.rollback(connection);
    }

    @Override
    public void cerrarConexion() {
        SQLHelper.close(connection);
    }

    @Override
    public FincaDAO getFincaDAO() {
        return new FincaPostgreSQLDAO(connection);
    }

    @Override
    public EspecieDAO getEspecieDAO() {
        return new EspeciePostgreSQLDAO(connection);
    }

    @Override
    public RazaDAO getRazaDAO() {
        return new RazaPostgreSQLDAO(connection);
    }

    @Override
    public AnimalDAO getAnimalDAO() {
        return new AnimalPostgreSQLDAO(connection);
    }

    @Override
    public RegistroEstadoAnimalDAO getRegistroEstadoAnimalDAO() {
        return new RegistroEstadoAnimalPostgreSQLDAO(connection);
    }

    @Override
    public EstadoAnimalDAO getEstadoAnimalDAO() {
        return new EstadoAnimalPostgreSQLDAO(connection);
    }

    @Override
    public EmpleadoDAO getEmpleadoDAO() {
        return new EmpleadoPostgreSQLDAO(connection);
    }
}
