package co.edu.uco.fink.business.fachade.concrete;

import co.edu.uco.fink.business.assembler.dto.concrete.RegistroEstadoAnimalDTODomainAssembler;
import co.edu.uco.fink.business.usecase.ObtenerRegistrosEstados;
import co.edu.uco.fink.business.usecase.concrete.ObtenerRegistrosEstadosimpl;
import co.edu.uco.fink.crosscutting.exception.FinKException;
import co.edu.uco.fink.crosscutting.exception.custom.BusinessFinkException;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.data.CodigoMensaje;
import co.edu.uco.fink.crosscutting.helpers.TextHelper;
import co.edu.uco.fink.data.dao.factory.DAOfactory;
import co.edu.uco.fink.data.dao.factory.enums.Factory;
import co.edu.uco.fink.dto.animales.RegistroEstadoAnimalDTO;

import java.util.List;

public class ObtenerRegistrosEstadosFachadaImpl {
    public final DAOfactory factory;

    public ObtenerRegistrosEstadosFachadaImpl(){
        factory = DAOfactory.getFactory(Factory.POSTGRESQL);
    }

    public List<RegistroEstadoAnimalDTO> ejecutar() {
        try {
            final ObtenerRegistrosEstados useCase = new ObtenerRegistrosEstadosimpl(factory);
            var resultados = useCase.ejecutar();
            return RegistroEstadoAnimalDTODomainAssembler.obtenerInstancia().ensamblarListaDTO(resultados);
        } catch (FinKException e){
            throw e;
        } catch (Exception e) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000045);
            var mensajeTecnico = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000046), "los animales y sus estados");

            throw new BusinessFinkException(mensajeTecnico, mensajeUsuario);
        } finally {
            factory.cerrarConexion();
        }
    }
}
