package co.edu.uco.fink.business.assembler.dto.concrete;

import co.edu.uco.fink.business.assembler.dto.DTODomainAssembler;
import co.edu.uco.fink.business.domain.EmpleadoDomain;
import co.edu.uco.fink.business.domain.FincaDomain;
import co.edu.uco.fink.crosscutting.helpers.ObjectHelper;
import co.edu.uco.fink.dto.fincas.EmpleadoDTO;
import co.edu.uco.fink.dto.fincas.FincaDTO;

import java.util.ArrayList;
import java.util.List;

public final class EmpleadoDTODomainAssembler implements DTODomainAssembler<EmpleadoDomain, EmpleadoDTO> {

    private static final DTODomainAssembler<EmpleadoDomain, EmpleadoDTO> instancia = new EmpleadoDTODomainAssembler();
    private static final DTODomainAssembler<FincaDomain, FincaDTO> fincaAssembler = new FincaDTODomainAssembler();


    public static final DTODomainAssembler<EmpleadoDomain, EmpleadoDTO> obtenerInstancia() {
        return instancia;
    }

    @Override
    public final EmpleadoDomain ensamblarDominio(final EmpleadoDTO dto) {
        var empleadoDTOTemp = ObjectHelper.getObjectHelper().getDefault(dto, EmpleadoDTO.build());
        FincaDomain fincadomain = fincaAssembler.ensamblarDominio(empleadoDTOTemp.getFinca());
        return EmpleadoDomain.crear(empleadoDTOTemp.getIdentificador(), empleadoDTOTemp.getDocumento(), empleadoDTOTemp.getClave(), empleadoDTOTemp.getEstado(), fincadomain);
    }

    @Override
    public final EmpleadoDTO ensamblarDTO(final EmpleadoDomain dominio) {
        var empleadoDomainTemp = ObjectHelper.getObjectHelper().getDefault(dominio, EmpleadoDomain.crear());
        FincaDTO fincaDTO = fincaAssembler.ensamblarDTO(empleadoDomainTemp.getFinca());
        return new EmpleadoDTO(empleadoDomainTemp.getIdentificador(), empleadoDomainTemp.getDocumento(), empleadoDomainTemp.getClave(), empleadoDomainTemp.getEstado(), fincaDTO);
    }

    @Override
    public List<EmpleadoDTO> ensamblarListaDTO(List<EmpleadoDomain> listaDominios) {
        var listaDominiosTmp = ObjectHelper.getObjectHelper().getDefault(listaDominios, new ArrayList<>());
        var resultados = new ArrayList<EmpleadoDTO>();

        for (var empleadoDomain : listaDominiosTmp) {
            var empleadoDTOTmp = ensamblarDTO((EmpleadoDomain) empleadoDomain);
            resultados.add(empleadoDTOTmp);
        }

        return resultados;
    }

    @Override
    public List<EmpleadoDomain> ensamblarListaDominios(List<EmpleadoDTO> listaDtos) {
        var listaDtosTmp = ObjectHelper.getObjectHelper().getDefault(listaDtos, new ArrayList<EmpleadoDTO>());
        var resultados = new ArrayList<EmpleadoDomain>();

        for (EmpleadoDTO empleadoDto : listaDtosTmp) {
            var empleadoDomainTmp = ensamblarDominio(empleadoDto);
            resultados.add(empleadoDomainTmp);
        }
        return resultados;
    }
}
