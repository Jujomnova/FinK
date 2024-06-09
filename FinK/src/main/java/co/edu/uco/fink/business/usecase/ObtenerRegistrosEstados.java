package co.edu.uco.fink.business.usecase;

import co.edu.uco.fink.business.domain.FincaDomain;
import co.edu.uco.fink.business.domain.RegistroEstadoAnimalDomain;

import java.util.List;

public interface ObtenerRegistrosEstados {
    List<RegistroEstadoAnimalDomain> ejecutar(FincaDomain finca);
}
