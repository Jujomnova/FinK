package co.edu.uco.fink.business.assembler.dto.concrete;

import co.edu.uco.fink.business.assembler.dto.DTODomainAssembler;
import co.edu.uco.fink.business.domain.*;
import co.edu.uco.fink.crosscutting.helpers.ObjectHelper;
import co.edu.uco.fink.dto.animales.AnimalDTO;
import co.edu.uco.fink.dto.animales.EstadoAnimalDTO;
import co.edu.uco.fink.dto.animales.RazaDTO;
import co.edu.uco.fink.dto.animales.RegistroEstadoAnimalDTO;

import java.util.ArrayList;
import java.util.List;

public class RegistroEstadoAnimalDTODomainAssembler implements DTODomainAssembler<RegistroEstadoAnimalDomain, RegistroEstadoAnimalDTO> {

    private static final DTODomainAssembler<RegistroEstadoAnimalDomain, RegistroEstadoAnimalDTO> instancia = new RegistroEstadoAnimalDTODomainAssembler();
    private static final DTODomainAssembler<AnimalDomain, AnimalDTO> animalAssembler = new AnimalDTODomainAssembler();
    private static final DTODomainAssembler<EstadoAnimalDomain, EstadoAnimalDTO> estadoAssembler = new EstadoAnimalDTODomainAssembler();

    public static final DTODomainAssembler<RegistroEstadoAnimalDomain, RegistroEstadoAnimalDTO> obtenerInstancia() {
        return instancia;
    }


    @Override
    public RegistroEstadoAnimalDomain ensamblarDominio(RegistroEstadoAnimalDTO dto) {
        var RegistroEstadoAnimalDTOTemp = ObjectHelper.getObjectHelper().getDefault(dto, RegistroEstadoAnimalDTO.Build());
        AnimalDomain AnimalDomain = animalAssembler.ensamblarDominio(RegistroEstadoAnimalDTOTemp.getAnimal());
        EstadoAnimalDomain EstadoAnimalDomain = estadoAssembler.ensamblarDominio(RegistroEstadoAnimalDTOTemp.getEstado());
        return RegistroEstadoAnimalDomain.Crear(RegistroEstadoAnimalDTOTemp.getIdentificador(), AnimalDomain, EstadoAnimalDomain, RegistroEstadoAnimalDTOTemp.getFechaActualizacion());
    }

    @Override
    public RegistroEstadoAnimalDTO ensamblarDTO(RegistroEstadoAnimalDomain dominio) {
        var RegistroEstadoAnimalDomainTemp = ObjectHelper.getObjectHelper().getDefault(dominio, RegistroEstadoAnimalDomain.Crear());
        AnimalDTO AnimalDTO= animalAssembler.ensamblarDTO(RegistroEstadoAnimalDomainTemp.getAnimal());
        EstadoAnimalDTO EstadoAnimalDTO = estadoAssembler.ensamblarDTO(RegistroEstadoAnimalDomainTemp.getEstado());
        return new RegistroEstadoAnimalDTO(RegistroEstadoAnimalDomainTemp.getIdentificador(), AnimalDTO, EstadoAnimalDTO, RegistroEstadoAnimalDomainTemp.getFechaActualizacion());
    }

    @Override
    public List<RegistroEstadoAnimalDTO> ensamblarListaDTO(List<RegistroEstadoAnimalDomain> listaDominios) {
        var listaDominiosTmp = ObjectHelper.getObjectHelper().getDefault(listaDominios, new ArrayList<>());
        var resultados = new ArrayList<RegistroEstadoAnimalDTO>();

        for (var RegistroEstadoAnimalDomain : listaDominiosTmp) {
            var RegistroEstadoAnimalDTOTmp = ensamblarDTO((RegistroEstadoAnimalDomain) RegistroEstadoAnimalDomain);
            resultados.add(RegistroEstadoAnimalDTOTmp);
        }

        return resultados;
    }

    @Override
    public List<RegistroEstadoAnimalDomain> ensamblarListaDominios(List<RegistroEstadoAnimalDTO> listaDtos) {
        var listaDtosTmp = ObjectHelper.getObjectHelper().getDefault(listaDtos, new ArrayList<RegistroEstadoAnimalDTO>());
        var resultados = new ArrayList<RegistroEstadoAnimalDomain>();

        for (RegistroEstadoAnimalDTO RegistroEstadoAnimalDto : listaDtosTmp) {
            var RegistroEstadoAnimalDomainTmp = ensamblarDominio(RegistroEstadoAnimalDto);
            resultados.add(RegistroEstadoAnimalDomainTmp);
        }
        return resultados;
    }
}
