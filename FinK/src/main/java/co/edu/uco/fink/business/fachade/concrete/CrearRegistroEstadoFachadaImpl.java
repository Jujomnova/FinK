package co.edu.uco.fink.business.fachade.concrete;

import co.edu.uco.fink.business.assembler.dto.concrete.RegistroEstadoAnimalDTODomainAssembler;
import co.edu.uco.fink.business.usecase.CrearRegistroEstado;
import co.edu.uco.fink.business.usecase.concrete.CrearRegistroEstadoImpl;
import co.edu.uco.fink.crosscutting.exception.FinKException;
import co.edu.uco.fink.crosscutting.exception.custom.BusinessFinkException;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.data.CodigoMensaje;
import co.edu.uco.fink.data.dao.factory.DAOfactory;
import co.edu.uco.fink.data.dao.factory.enums.Factory;
import co.edu.uco.fink.dto.animales.RegistroEstadoAnimalDTO;

public class CrearRegistroEstadoFachadaImpl {

    private final DAOfactory factory;

    public CrearRegistroEstadoFachadaImpl(){
        factory = DAOfactory.getFactory(Factory.POSTGRESQL);
    }

    public final void ejecutar(RegistroEstadoAnimalDTO registroEstadoAnimal){
        try{
            factory.iniciarTransaccion();
            var registroEstadoAnimalDomain = RegistroEstadoAnimalDTODomainAssembler.obtenerInstancia().ensamblarDominio(registroEstadoAnimal);
            CrearRegistroEstado useCase = new CrearRegistroEstadoImpl(factory);
            useCase.ejecutar(registroEstadoAnimalDomain);
            factory.confirmarTransaccion();
        } catch (FinKException exception){
            factory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            factory.cancelarTransaccion();

            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000033);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000034);

            throw new BusinessFinkException(mensajeTecnico, mensajeUsuario);
        } finally {
            factory.cerrarConexion();
        }
    }
}
