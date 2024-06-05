package co.edu.uco.fink.business.usecase;

import co.edu.uco.fink.business.domain.EmpleadoDomain;

public interface VerificarEmpleado {
    EmpleadoDomain ejecutar(EmpleadoDomain empleado);
}
