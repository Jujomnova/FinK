package co.edu.uco.fink.business.assembler.dto;

import java.util.List;

public interface DTODomainAssembler<D, T> {
    D ensamblarDominio(T dto);

    T ensamblarDTO(D dominio);

    List<T> ensamblarListaDTO(List<D> listaDominios);

    List<D> ensamblarListaDominios(List<T> listaDtos);
}
