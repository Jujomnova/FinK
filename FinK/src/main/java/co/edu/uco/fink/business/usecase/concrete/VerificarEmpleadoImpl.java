package co.edu.uco.fink.business.usecase.concrete;

import co.edu.uco.fink.business.assembler.entity.concrete.EmpleadoEntityDomainAssembler;
import co.edu.uco.fink.business.domain.EmpleadoDomain;
import co.edu.uco.fink.business.usecase.VerificarEmpleado;
import co.edu.uco.fink.crosscutting.exception.Enums.Lugar;
import co.edu.uco.fink.crosscutting.exception.FinKException;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.data.CodigoMensaje;
import co.edu.uco.fink.crosscutting.helpers.TextHelper;
import co.edu.uco.fink.data.dao.factory.DAOfactory;
import co.edu.uco.fink.entity.EmpleadoEntity;
import co.edu.uco.fink.entity.FincaEntity;

import java.util.List;
import java.util.Objects;

public class VerificarEmpleadoImpl implements VerificarEmpleado {

    private final DAOfactory factory;

    public VerificarEmpleadoImpl(final DAOfactory factory){ this.factory = factory; }

    public final void obtenerEstado(EmpleadoEntity empleado){
        List<EmpleadoEntity> resultado = factory.getEmpleadoDAO().obtenerEstado(empleado);

        for (EmpleadoEntity e : resultado){
            if (e.getDocumento() == empleado.getDocumento() && Objects.equals(e.getEstado(), "Inactivo")){
                    String mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000043);
                    String mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000044);

                    throw new FinKException(mensajeTecnico, mensajeUsuario, Lugar.BUSINESS);
                }

        }
    }

    public final FincaEntity validarCredenciales(EmpleadoEntity empleado){

        if (empleado.getDocumento() < 1){
            String mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000041);
            String mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000042);

            throw new FinKException(mensajeTecnico, mensajeUsuario, Lugar.BUSINESS);
        }

        List<FincaEntity> resultado = factory.getEmpleadoDAO().verificarEmpleado(empleado);

        FincaEntity fincares = null;

        for (FincaEntity finca : resultado){
            fincares = finca;
        }

        if (Objects.equals(fincares, null)){
            String mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000039);
            String mensajeTecnico = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000040));

            throw new FinKException(mensajeTecnico, mensajeUsuario, Lugar.BUSINESS);
        }

        return fincares;
    }

    @Override
    public EmpleadoDomain ejecutar(EmpleadoDomain empleado) {
        EmpleadoEntity empleadoEntity = EmpleadoEntityDomainAssembler.obtenerInstancia().ensamblarEntidad(empleado);

        FincaEntity finca = validarCredenciales(empleadoEntity);

        empleadoEntity.getFinca().setId(finca.getId());
        empleadoEntity.getFinca().setNombre(finca.getNombre());
        empleadoEntity.setClave("");

        obtenerEstado(empleadoEntity);

        return EmpleadoEntityDomainAssembler.obtenerInstancia().ensamblarDominio(empleadoEntity);
    }
}
