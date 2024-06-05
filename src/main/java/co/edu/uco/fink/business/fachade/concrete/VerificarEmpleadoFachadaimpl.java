package co.edu.uco.fink.business.fachade.concrete;

import co.edu.uco.fink.business.assembler.dto.concrete.EmpleadoDTODomainAssembler;
import co.edu.uco.fink.business.assembler.dto.concrete.FincaDTODomainAssembler;
import co.edu.uco.fink.business.usecase.VerificarEmpleado;
import co.edu.uco.fink.business.usecase.concrete.VerificarEmpleadoImpl;
import co.edu.uco.fink.crosscutting.exception.FinKException;
import co.edu.uco.fink.crosscutting.exception.custom.BusinessFinkException;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.data.CodigoMensaje;
import co.edu.uco.fink.data.dao.factory.DAOfactory;
import co.edu.uco.fink.data.dao.factory.enums.Factory;
import co.edu.uco.fink.dto.fincas.EmpleadoDTO;

public class VerificarEmpleadoFachadaimpl {

    public DAOfactory factory;

    public VerificarEmpleadoFachadaimpl(){
        factory = DAOfactory.getFactory(Factory.POSTGRESQL);
    }

    public final EmpleadoDTO ejecutar(EmpleadoDTO empleado){
        try{
            var empleadoDomain = EmpleadoDTODomainAssembler.obtenerInstancia().ensamblarDominio(empleado);

            final VerificarEmpleado useCase = new VerificarEmpleadoImpl(factory);
            var newEmpleadoDomain = useCase.ejecutar(empleadoDomain);

            return EmpleadoDTODomainAssembler.obtenerInstancia().ensamblarDTO(newEmpleadoDomain);

        } catch (FinKException exception){
            throw exception;
        } catch (Exception exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000031);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000032);

            throw new BusinessFinkException(mensajeTecnico, mensajeUsuario);
        } finally {
            factory.cerrarConexion();
        }
    }

}
