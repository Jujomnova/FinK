package co.edu.uco.fink.business.assembler.entity.concrete;

import co.edu.uco.fink.business.assembler.entity.EntityDomainAssembler;
import co.edu.uco.fink.business.domain.EstadoAnimalDomain;
import co.edu.uco.fink.crosscutting.helpers.ObjectHelper;
import co.edu.uco.fink.entity.EstadoAnimalEntity;

import java.util.ArrayList;
import java.util.List;

public class EstadoAnimalEntityDomainAssembler implements EntityDomainAssembler<EstadoAnimalDomain, EstadoAnimalEntity> {

    private static final EntityDomainAssembler<EstadoAnimalDomain, EstadoAnimalEntity> instancia = new EstadoAnimalEntityDomainAssembler();

    public static final EntityDomainAssembler<EstadoAnimalDomain, EstadoAnimalEntity> obtenerInstancia() {
        return instancia;
    }


    @Override
    public EstadoAnimalDomain ensamblarDominio(EstadoAnimalEntity Entity) {
        var EstadoAnimalEntityTemp = ObjectHelper.getObjectHelper().getDefault(Entity, EstadoAnimalEntity.Build(0));
        return EstadoAnimalDomain.Crear(EstadoAnimalEntityTemp.getIdentificador(), EstadoAnimalEntityTemp.getEstado());
    }

    @Override
    public EstadoAnimalEntity ensamblarEntidad(EstadoAnimalDomain dominio) {
        var EstadoAnimalDomainTemp = ObjectHelper.getObjectHelper().getDefault(dominio, EstadoAnimalDomain.Crear());
        return new EstadoAnimalEntity(EstadoAnimalDomainTemp.getIdentificador(), EstadoAnimalDomainTemp.getEstado());
    }

    @Override
    public List<EstadoAnimalDomain> ensamblarListaDominios(List<EstadoAnimalEntity> listaEntitys) {
        var listaEntitysTmp = ObjectHelper.getObjectHelper().getDefault(listaEntitys, new ArrayList<EstadoAnimalEntity>());
        var resultados = new ArrayList<EstadoAnimalDomain>();

        for (EstadoAnimalEntity EstadoAnimalEntity : listaEntitysTmp) {
            var EstadoAnimalDomainTmp = ensamblarDominio(EstadoAnimalEntity);
            resultados.add(EstadoAnimalDomainTmp);
        }
        return resultados;
    }
}
