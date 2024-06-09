package co.edu.uco.fink.business.assembler.dto.concrete;

import co.edu.uco.fink.business.assembler.dto.DTODomainAssembler;
import co.edu.uco.fink.business.domain.EspecieDomain;
import co.edu.uco.fink.business.domain.FincaDomain;
import co.edu.uco.fink.crosscutting.helpers.ObjectHelper;
import co.edu.uco.fink.dto.animales.EspecieDTO;
import co.edu.uco.fink.dto.fincas.FincaDTO;

import java.util.ArrayList;
import java.util.List;

public class EspecieDTODomainAssembler implements DTODomainAssembler<EspecieDomain, EspecieDTO> {

    private static final DTODomainAssembler<EspecieDomain, EspecieDTO> instancia = new EspecieDTODomainAssembler();

    public static final DTODomainAssembler<EspecieDomain, EspecieDTO> obtenerInstancia() {
        return instancia;
    }

    @Override
    public EspecieDomain ensamblarDominio(EspecieDTO dto) {
        var EspecieDTOTemp = ObjectHelper.getObjectHelper().getDefault(dto, EspecieDTO.Build());
        return EspecieDomain.Crear(EspecieDTOTemp.getIdentificador(), EspecieDTOTemp.getNombre());
    }

    @Override
    public EspecieDTO ensamblarDTO(EspecieDomain dominio) {
        var EspecieDomainTemp = ObjectHelper.getObjectHelper().getDefault(dominio, EspecieDomain.Crear());
        return new EspecieDTO(EspecieDomainTemp.getIdentificador(), EspecieDomainTemp.getNombre());
    }

    @Override
    public List<EspecieDTO> ensamblarListaDTO(List<EspecieDomain> listaDominios) {
        var listaDominiosTmp = ObjectHelper.getObjectHelper().getDefault(listaDominios, new ArrayList<>());
        var resultados = new ArrayList<EspecieDTO>();

        for (var especieDomain : listaDominiosTmp) {
            var especieDTOTmp = ensamblarDTO((EspecieDomain) especieDomain);
            resultados.add(especieDTOTmp);
        }

        return resultados;
    }

    @Override
    public List<EspecieDomain> ensamblarListaDominios(List<EspecieDTO> listaDtos) {
        var listaDtosTmp = ObjectHelper.getObjectHelper().getDefault(listaDtos, new ArrayList<EspecieDTO>());
        var resultados = new ArrayList<EspecieDomain>();

        for (EspecieDTO especieDto : listaDtosTmp) {
            var especieDomainTmp = ensamblarDominio(especieDto);
            resultados.add(especieDomainTmp);
        }
        return resultados;
    }
}
