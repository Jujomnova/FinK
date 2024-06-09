package co.edu.uco.fink.business.fachade;

import co.edu.uco.fink.dto.animales.RegistroEstadoAnimalDTO;
import co.edu.uco.fink.dto.fincas.FincaDTO;

import java.util.List;

public interface ObtenerRegistrosEstadosFachada {
    List<RegistroEstadoAnimalDTO> ejeciutar(FincaDTO finca);
}
