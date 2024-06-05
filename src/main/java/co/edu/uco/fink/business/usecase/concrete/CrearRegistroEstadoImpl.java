package co.edu.uco.fink.business.usecase.concrete;

import co.edu.uco.fink.business.assembler.entity.concrete.RegistroEstadoAnimalEntityDomainAssembler;
import co.edu.uco.fink.business.domain.RegistroEstadoAnimalDomain;
import co.edu.uco.fink.business.usecase.CrearRegistroEstado;
import co.edu.uco.fink.crosscutting.exception.Enums.Lugar;
import co.edu.uco.fink.crosscutting.exception.FinKException;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.data.CodigoMensaje;
import co.edu.uco.fink.crosscutting.helpers.TextHelper;
import co.edu.uco.fink.data.dao.factory.DAOfactory;
import co.edu.uco.fink.entity.AnimalEntity;
import co.edu.uco.fink.entity.RegistroEstadoAnimalEntity;

import java.util.List;
import java.util.Objects;

public class CrearRegistroEstadoImpl implements CrearRegistroEstado {

    private final DAOfactory factory;

    public CrearRegistroEstadoImpl(DAOfactory factory){
        this.factory = factory;
    }

    public int obtenerIdentificador(RegistroEstadoAnimalEntity registro){
        List<AnimalEntity> resultados = factory.getAnimalDAO().consultar(registro.getAnimal());
        int identificador = 0;

        for (AnimalEntity animal : resultados){
            if (Objects.equals(animal.getRaza().getEspecie().getNombre(), registro.getAnimal().getRaza().getEspecie().getNombre()) && Objects.equals(animal.getRaza().getNombre(), registro.getAnimal().getRaza().getNombre()) && animal.getCodigo() == registro.getAnimal().getCodigo()){
                identificador = animal.getIdentificador();
                break;
            }
        }

        return identificador;
    }

    public void verificarEstado(RegistroEstadoAnimalEntity registro){
        List<RegistroEstadoAnimalEntity> resultados = factory.getRegistroEstadoAnimalDAO().consultar(registro);

        for (RegistroEstadoAnimalEntity entidad : resultados){
            if (Objects.equals(entidad.getAnimal().getRaza().getEspecie().getNombre(), registro.getAnimal().getRaza().getEspecie().getNombre()) && Objects.equals(entidad.getAnimal().getRaza().getNombre(), registro.getAnimal().getRaza().getNombre()) && entidad.getAnimal().getCodigo() == registro.getAnimal().getCodigo()){
                if (Objects.equals(entidad.getEstado().getEstado(), "Fallecido") || Objects.equals(entidad.getEstado().getEstado(), "Vendido")){
                    String mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000027);
                    String mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000028);
                    throw new FinKException(mensajeTecnico, mensajeUsuario, Lugar.BUSINESS);
                } else if (Objects.equals(entidad.getEstado().getEstado(), registro.getEstado().getEstado())){
                    String mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000029);
                    String mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000030);
                    throw new FinKException(mensajeTecnico, mensajeUsuario, Lugar.BUSINESS);
                }
                break;
            }
        }
    }

    @Override
    public void ejecutar(RegistroEstadoAnimalDomain registroEstadoAnimal) {
        RegistroEstadoAnimalEntity registroEntity = RegistroEstadoAnimalEntityDomainAssembler.obtenerInstancia().ensamblarEntidad(registroEstadoAnimal);

        verificarEstado(registroEntity);

        registroEntity.getAnimal().setIdentificador(obtenerIdentificador(registroEntity));

        factory.getRegistroEstadoAnimalDAO().actualizarEstado(registroEntity.getAnimal(), registroEntity.getEstado());
    }
}
