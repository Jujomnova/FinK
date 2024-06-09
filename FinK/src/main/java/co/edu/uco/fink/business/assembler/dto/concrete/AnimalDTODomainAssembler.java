package co.edu.uco.fink.business.assembler.dto.concrete;

import co.edu.uco.fink.business.assembler.dto.DTODomainAssembler;
import co.edu.uco.fink.business.domain.AnimalDomain;
import co.edu.uco.fink.business.domain.FincaDomain;
import co.edu.uco.fink.business.domain.RazaDomain;
import co.edu.uco.fink.crosscutting.helpers.ObjectHelper;
import co.edu.uco.fink.dto.animales.AnimalDTO;
import co.edu.uco.fink.dto.animales.RazaDTO;
import co.edu.uco.fink.dto.fincas.FincaDTO;

import java.util.ArrayList;
import java.util.List;

public class AnimalDTODomainAssembler implements DTODomainAssembler<AnimalDomain, AnimalDTO> {

    private static final DTODomainAssembler<AnimalDomain, AnimalDTO> instancia = new AnimalDTODomainAssembler();
    private static final DTODomainAssembler<RazaDomain, RazaDTO> razaAssembler = new RazaDTODomainAssembler();
    private static final DTODomainAssembler<FincaDomain, FincaDTO> fincaAssembler = new FincaDTODomainAssembler();


    public static final DTODomainAssembler<AnimalDomain, AnimalDTO> obtenerInstancia() {
        return instancia;
    }

    @Override
    public AnimalDomain ensamblarDominio(AnimalDTO dto) {
        var AnimalDTOTemp = ObjectHelper.getObjectHelper().getDefault(dto, AnimalDTO.Build());
        RazaDomain razaDomain = razaAssembler.ensamblarDominio(AnimalDTOTemp.getRaza());
        FincaDomain fincaDomain = fincaAssembler.ensamblarDominio(AnimalDTOTemp.getFinca());
        return AnimalDomain.Crear(AnimalDTOTemp.getIdentificador(), razaDomain, AnimalDTOTemp.getCodigo(), fincaDomain);
    }

    @Override
    public AnimalDTO ensamblarDTO(AnimalDomain dominio) {
        var AnimalDomainTemp = ObjectHelper.getObjectHelper().getDefault(dominio, AnimalDomain.Crear());
        RazaDTO razaDTO= razaAssembler.ensamblarDTO(AnimalDomainTemp.getRaza());
        FincaDTO fincaDTO = fincaAssembler.ensamblarDTO(AnimalDomainTemp.getFinca());
        return new AnimalDTO(AnimalDomainTemp.getIdentificador(), razaDTO, AnimalDomainTemp.getCodigo(), fincaDTO);
    }

    @Override
    public List<AnimalDTO> ensamblarListaDTO(List<AnimalDomain> listaDominios) {
        var listaDominiosTmp = ObjectHelper.getObjectHelper().getDefault(listaDominios, new ArrayList<>());
        var resultados = new ArrayList<AnimalDTO>();

        for (var AnimalDomain : listaDominiosTmp) {
            var AnimalDTOTmp = ensamblarDTO((AnimalDomain) AnimalDomain);
            resultados.add(AnimalDTOTmp);
        }

        return resultados;
    }

    @Override
    public List<AnimalDomain> ensamblarListaDominios(List<AnimalDTO> listaDtos) {
        var listaDtosTmp = ObjectHelper.getObjectHelper().getDefault(listaDtos, new ArrayList<AnimalDTO>());
        var resultados = new ArrayList<AnimalDomain>();

        for (AnimalDTO AnimalDto : listaDtosTmp) {
            var AnimalDomainTmp = ensamblarDominio(AnimalDto);
            resultados.add(AnimalDomainTmp);
        }
        return resultados;
    }
}
