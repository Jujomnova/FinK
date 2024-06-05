package co.edu.uco.fink.business.assembler.entity.concrete;

import co.edu.uco.fink.business.assembler.dto.DTODomainAssembler;
import co.edu.uco.fink.business.assembler.dto.concrete.RegistroEstadoAnimalDTODomainAssembler;
import co.edu.uco.fink.business.assembler.entity.EntityDomainAssembler;
import co.edu.uco.fink.business.domain.AnimalDomain;
import co.edu.uco.fink.business.domain.EstadoAnimalDomain;
import co.edu.uco.fink.business.domain.RegistroEstadoAnimalDomain;
import co.edu.uco.fink.crosscutting.helpers.ObjectHelper;
import co.edu.uco.fink.dto.animales.RegistroEstadoAnimalDTO;
import co.edu.uco.fink.entity.AnimalEntity;
import co.edu.uco.fink.entity.EstadoAnimalEntity;
import co.edu.uco.fink.entity.RegistroEstadoAnimalEntity;

import java.util.ArrayList;
import java.util.List;

public class RegistroEstadoAnimalEntityDomainAssembler implements EntityDomainAssembler<RegistroEstadoAnimalDomain, RegistroEstadoAnimalEntity> {

    private static final EntityDomainAssembler<RegistroEstadoAnimalDomain, RegistroEstadoAnimalEntity> instancia = new RegistroEstadoAnimalEntityDomainAssembler();
    private static final EntityDomainAssembler<AnimalDomain, AnimalEntity> animalAssembler = new AnimalEntityDomainAssembler();
    private static final EntityDomainAssembler<EstadoAnimalDomain, EstadoAnimalEntity> estadoAssembler = new EstadoAnimalEntityDomainAssembler();

    public static final EntityDomainAssembler<RegistroEstadoAnimalDomain, RegistroEstadoAnimalEntity> obtenerInstancia() {
        return instancia;
    }

    @Override
    public RegistroEstadoAnimalDomain ensamblarDominio(RegistroEstadoAnimalEntity Entity) {
        var RegistroEstadoAnimalEntityTemp = ObjectHelper.getObjectHelper().getDefault(Entity, RegistroEstadoAnimalEntity.Build(0));
        AnimalDomain AnimalDomain = animalAssembler.ensamblarDominio(RegistroEstadoAnimalEntityTemp.getAnimal());
        EstadoAnimalDomain EstadoAnimalDomain = estadoAssembler.ensamblarDominio(RegistroEstadoAnimalEntityTemp.getEstado());
        return RegistroEstadoAnimalDomain.Crear(RegistroEstadoAnimalEntityTemp.getIdentificador(), AnimalDomain, EstadoAnimalDomain, RegistroEstadoAnimalEntityTemp.getFechaActualizacion());
    }

    @Override
    public RegistroEstadoAnimalEntity ensamblarEntidad(RegistroEstadoAnimalDomain dominio) {
        var RegistroEstadoAnimalDomainTemp = ObjectHelper.getObjectHelper().getDefault(dominio, RegistroEstadoAnimalDomain.Crear());
        AnimalEntity AnimalEntity= animalAssembler.ensamblarEntidad(RegistroEstadoAnimalDomainTemp.getAnimal());
        EstadoAnimalEntity EstadoAnimalEntity = estadoAssembler.ensamblarEntidad(RegistroEstadoAnimalDomainTemp.getEstado());
        return new RegistroEstadoAnimalEntity(RegistroEstadoAnimalDomainTemp.getIdentificador(), AnimalEntity, EstadoAnimalEntity, RegistroEstadoAnimalDomainTemp.getFechaActualizacion());
    }

    @Override
    public List<RegistroEstadoAnimalDomain> ensamblarListaDominios(List<RegistroEstadoAnimalEntity> listaEntitys) {
        var listaEntitysTmp = ObjectHelper.getObjectHelper().getDefault(listaEntitys, new ArrayList<RegistroEstadoAnimalEntity>());
        var resultados = new ArrayList<RegistroEstadoAnimalDomain>();

        for (RegistroEstadoAnimalEntity RegistroEstadoAnimalEntity : listaEntitysTmp) {
            var RegistroEstadoAnimalDomainTmp = ensamblarDominio(RegistroEstadoAnimalEntity);
            resultados.add(RegistroEstadoAnimalDomainTmp);
        }
        return resultados;
    }
    
}
