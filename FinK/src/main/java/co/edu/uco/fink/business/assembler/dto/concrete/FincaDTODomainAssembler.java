package co.edu.uco.fink.business.assembler.dto.concrete;

import co.edu.uco.fink.business.assembler.dto.DTODomainAssembler;
import co.edu.uco.fink.business.domain.FincaDomain;
import co.edu.uco.fink.crosscutting.helpers.ObjectHelper;
import co.edu.uco.fink.dto.fincas.FincaDTO;

import java.util.ArrayList;
import java.util.List;

public class FincaDTODomainAssembler implements DTODomainAssembler<FincaDomain, FincaDTO> {

    private static final DTODomainAssembler<FincaDomain, FincaDTO> instancia = new FincaDTODomainAssembler();

    public static final DTODomainAssembler<FincaDomain, FincaDTO> obtenerInstancia() {
        return instancia;
    }

    @Override
    public FincaDomain ensamblarDominio(FincaDTO dto) {
        var FincaDTOTemp = ObjectHelper.getObjectHelper().getDefault(dto, FincaDTO.build());
        return FincaDomain.Crear(FincaDTOTemp.getId(), FincaDTOTemp.getNombre());
    }

    @Override
    public FincaDTO ensamblarDTO(FincaDomain dominio) {
        var FincaDomainTemp = ObjectHelper.getObjectHelper().getDefault(dominio, FincaDomain.Crear());
        return new FincaDTO(FincaDomainTemp.getId(), FincaDomainTemp.getNombre());
    }

    @Override
    public List<FincaDTO> ensamblarListaDTO(List<FincaDomain> listaDominios) {
        var listaDominiosTmp = ObjectHelper.getObjectHelper().getDefault(listaDominios, new ArrayList<>());
        var resultados = new ArrayList<FincaDTO>();

        for (var fincaDomain : listaDominiosTmp) {
            var fincaDTOTmp = ensamblarDTO((FincaDomain) fincaDomain);
            resultados.add(fincaDTOTmp);
        }

        return resultados;
    }

    @Override
    public List<FincaDomain> ensamblarListaDominios(List<FincaDTO> listaDtos) {
        var listaDtosTmp = ObjectHelper.getObjectHelper().getDefault(listaDtos, new ArrayList<FincaDTO>());
        var resultados = new ArrayList<FincaDomain>();

        for (FincaDTO FincaDto : listaDtosTmp) {
            var fincaDomainTmp = ensamblarDominio(FincaDto);
            resultados.add(fincaDomainTmp);
        }
        return resultados;
    }
}
