package co.edu.uco.fink.business.assembler.dto.concrete;

import co.edu.uco.fink.business.assembler.dto.DTODomainAssembler;
import co.edu.uco.fink.business.domain.EspecieDomain;
import co.edu.uco.fink.business.domain.FincaDomain;
import co.edu.uco.fink.business.domain.RazaDomain;
import co.edu.uco.fink.crosscutting.helpers.ObjectHelper;
import co.edu.uco.fink.dto.animales.EspecieDTO;
import co.edu.uco.fink.dto.animales.RazaDTO;
import co.edu.uco.fink.dto.fincas.FincaDTO;

import java.util.ArrayList;
import java.util.List;

public class RazaDTODomainAssembler implements DTODomainAssembler<RazaDomain, RazaDTO> {

    private static final DTODomainAssembler<RazaDomain, RazaDTO> instancia = new RazaDTODomainAssembler();
    private static final DTODomainAssembler<EspecieDomain, EspecieDTO> especieAssembler = new EspecieDTODomainAssembler();

    public static final DTODomainAssembler<RazaDomain, RazaDTO> obtenerInstancia() {
        return instancia;
    }


    @Override
    public RazaDomain ensamblarDominio(RazaDTO dto) {
        var RazaDTOTemp = ObjectHelper.getObjectHelper().getDefault(dto, RazaDTO.Build());
        EspecieDomain especiedomain = especieAssembler.ensamblarDominio(RazaDTOTemp.getEspecie());
        return RazaDomain.Crear(RazaDTOTemp.getIdentificador(), RazaDTOTemp.getNombre(), especiedomain);
    }

    @Override
    public RazaDTO ensamblarDTO(RazaDomain dominio) {
        var EspecieDomainTemp = ObjectHelper.getObjectHelper().getDefault(dominio, RazaDomain.Crear());
        EspecieDTO especieDTO = especieAssembler.ensamblarDTO(RazaDomain.Crear().getEspecie());
        return new RazaDTO(EspecieDomainTemp.getIdentificador(), EspecieDomainTemp.getNombre(), especieDTO);
    }

    @Override
    public List<RazaDTO> ensamblarListaDTO(List<RazaDomain> listaDominios) {
        var listaDominiosTmp = ObjectHelper.getObjectHelper().getDefault(listaDominios, new ArrayList<>());
        var resultados = new ArrayList<RazaDTO>();

        for (var razaDomain : listaDominiosTmp) {
            var razaDTOTmp = ensamblarDTO((RazaDomain) razaDomain);
            resultados.add(razaDTOTmp);
        }

        return resultados;
    }

    @Override
    public List<RazaDomain> ensamblarListaDominios(List<RazaDTO> listaDtos) {
        var listaDtosTmp = ObjectHelper.getObjectHelper().getDefault(listaDtos, new ArrayList<RazaDTO>());
        var resultados = new ArrayList<RazaDomain>();

        for (RazaDTO RazaDto : listaDtosTmp) {
            var RazaDomainTmp = ensamblarDominio(RazaDto);
            resultados.add(RazaDomainTmp);
        }
        return resultados;
    }
}
