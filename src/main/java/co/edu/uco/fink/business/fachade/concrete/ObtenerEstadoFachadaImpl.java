package co.edu.uco.fink.business.fachade.concrete;

import co.edu.uco.fink.business.assembler.dto.concrete.EstadoAnimalDTODomainAssembler;
import co.edu.uco.fink.business.fachade.ObtenerEstadoFachada;
import co.edu.uco.fink.business.usecase.ObtenerEstados;
import co.edu.uco.fink.business.usecase.concrete.ObtenerEstadosImpl;
import co.edu.uco.fink.crosscutting.exception.FinKException;
import co.edu.uco.fink.crosscutting.exception.custom.BusinessFinkException;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.data.CodigoMensaje;
import co.edu.uco.fink.crosscutting.helpers.TextHelper;
import co.edu.uco.fink.data.dao.factory.DAOfactory;
import co.edu.uco.fink.data.dao.factory.enums.Factory;
import co.edu.uco.fink.dto.animales.EstadoAnimalDTO;

import java.util.List;

public class ObtenerEstadoFachadaImpl implements ObtenerEstadoFachada {

    private final DAOfactory factory;

    public ObtenerEstadoFachadaImpl(){
        factory = DAOfactory.getFactory(Factory.POSTGRESQL);
    }

    @Override
    public List<EstadoAnimalDTO> ejecutar() {
        try {
            final ObtenerEstados useCase = new ObtenerEstadosImpl(factory);
            var resultados = useCase.ejecutar();
            return EstadoAnimalDTODomainAssembler.obtenerInstancia().ensamblarListaDTO(resultados);
        } catch (FinKException e){
            throw e;
        } catch (Exception e) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000045);
            var mensajeTecnico = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000046), "los tipos de estado");

            throw new BusinessFinkException(mensajeTecnico, mensajeUsuario);
        } finally {
            factory.cerrarConexion();
        }
    }
}
