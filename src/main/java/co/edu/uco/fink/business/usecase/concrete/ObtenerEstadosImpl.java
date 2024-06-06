package co.edu.uco.fink.business.usecase.concrete;

import co.edu.uco.fink.business.assembler.entity.concrete.EstadoAnimalEntityDomainAssembler;
import co.edu.uco.fink.business.domain.EstadoAnimalDomain;
import co.edu.uco.fink.business.usecase.ObtenerEstados;
import co.edu.uco.fink.data.dao.factory.DAOfactory;
import co.edu.uco.fink.entity.EstadoAnimalEntity;

import java.util.List;

public class ObtenerEstadosImpl implements ObtenerEstados {

    private final DAOfactory factory;

    public ObtenerEstadosImpl(DAOfactory factory) {
        this.factory = factory;
    }

    @Override
    public List<EstadoAnimalDomain> ejecutar() {
        return EstadoAnimalEntityDomainAssembler.obtenerInstancia().ensamblarListaDominios(factory.getEstadoAnimalDAO().consultar(EstadoAnimalEntity.Build(0)));
    }
}
