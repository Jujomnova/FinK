package co.edu.uco.fink.business.fachade;

import co.edu.uco.fink.dto.animales.EstadoAnimalDTO;

import java.util.List;

public interface ObtenerEstadoFachada {
    List<EstadoAnimalDTO> ejecutar();
}
