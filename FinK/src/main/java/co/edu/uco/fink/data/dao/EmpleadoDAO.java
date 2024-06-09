package co.edu.uco.fink.data.dao;

import co.edu.uco.fink.data.dao.general.ConsultarDAO;
import co.edu.uco.fink.entity.EmpleadoEntity;
import co.edu.uco.fink.entity.FincaEntity;

import java.util.List;

public interface EmpleadoDAO extends ConsultarDAO<EmpleadoEntity> {

    List<FincaEntity> verificarEmpleado(EmpleadoEntity empleado);

    List<EmpleadoEntity> obtenerEstado(EmpleadoEntity empleado);
}
