package co.edu.uco.fink.data.dao;

import co.edu.uco.fink.data.dao.general.ConsultarDAO;
import co.edu.uco.fink.data.dao.general.CrearDAO;
import co.edu.uco.fink.entity.AnimalEntity;
import co.edu.uco.fink.entity.EstadoAnimalEntity;
import co.edu.uco.fink.entity.RegistroEstadoAnimalEntity;

public interface RegistroEstadoAnimalDAO extends CrearDAO<RegistroEstadoAnimalEntity>, ConsultarDAO<RegistroEstadoAnimalEntity> {
    void actualizarEstado(AnimalEntity animal, EstadoAnimalEntity estado);
}
