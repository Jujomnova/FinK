package co.edu.uco.fink.business.assembler.dto.concrete;

import co.edu.uco.fink.business.assembler.dto.DTODomainAssembler;
import co.edu.uco.fink.business.domain.EstadoAnimalDomain;
import co.edu.uco.fink.business.domain.EstadoAnimalDomain;
import co.edu.uco.fink.crosscutting.helpers.ObjectHelper;
import co.edu.uco.fink.dto.animales.EstadoAnimalDTO;

import java.util.ArrayList;
import java.util.List;

public class EstadoAnimalDTODomainAssembler implements DTODomainAssembler<EstadoAnimalDomain, EstadoAnimalDTO> {

    private static final DTODomainAssembler<EstadoAnimalDomain, EstadoAnimalDTO> instancia = new EstadoAnimalDTODomainAssembler();

    public static final DTODomainAssembler<EstadoAnimalDomain, EstadoAnimalDTO> obtenerInstancia() {
        return instancia;
    }


    @Override
    public EstadoAnimalDomain ensamblarDominio(EstadoAnimalDTO dto) {
        var EstadoAnimalDTOTemp = ObjectHelper.getObjectHelper().getDefault(dto, EstadoAnimalDTO.Build());
        return EstadoAnimalDomain.Crear(EstadoAnimalDTOTemp.getIdentificador(), EstadoAnimalDTOTemp.getEstado());
    }

    @Override
    public EstadoAnimalDTO ensamblarDTO(EstadoAnimalDomain dominio) {
        var EstadoAnimalDomainTemp = ObjectHelper.getObjectHelper().getDefault(dominio, EstadoAnimalDomain.Crear());
        return new EstadoAnimalDTO(EstadoAnimalDomainTemp.getIdentificador(), EstadoAnimalDomainTemp.getEstado());
    }

    @Override
    public List<EstadoAnimalDTO> ensamblarListaDTO(List<EstadoAnimalDomain> listaDominios) {
        var listaDominiosTmp = ObjectHelper.getObjectHelper().getDefault(listaDominios, new ArrayList<>());
        var resultados = new ArrayList<EstadoAnimalDTO>();

        for (var EstadoAnimalDomain : listaDominiosTmp) {
            var EstadoAnimalDTOTmp = ensamblarDTO((EstadoAnimalDomain) EstadoAnimalDomain);
            resultados.add(EstadoAnimalDTOTmp);
        }

        return resultados;
    }

    @Override
    public List<EstadoAnimalDomain> ensamblarListaDominios(List<EstadoAnimalDTO> listaDtos) {
        var listaDtosTmp = ObjectHelper.getObjectHelper().getDefault(listaDtos, new ArrayList<EstadoAnimalDTO>());
        var resultados = new ArrayList<EstadoAnimalDomain>();

        for (EstadoAnimalDTO EstadoAnimalDto : listaDtosTmp) {
            var EstadoAnimalDomainTmp = ensamblarDominio(EstadoAnimalDto);
            resultados.add(EstadoAnimalDomainTmp);
        }
        return resultados;
    }
}
