package co.edu.uco.fink.business.usecase;

import co.edu.uco.fink.business.domain.EstadoAnimalDomain;

import java.util.List;

public interface ObtenerEstados {
    List<EstadoAnimalDomain> ejecutar();
}
