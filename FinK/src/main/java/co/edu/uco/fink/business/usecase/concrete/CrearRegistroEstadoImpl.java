package co.edu.uco.fink.business.usecase.concrete;

import co.edu.uco.fink.business.assembler.entity.concrete.RegistroEstadoAnimalEntityDomainAssembler;
import co.edu.uco.fink.business.domain.RegistroEstadoAnimalDomain;
import co.edu.uco.fink.business.usecase.CrearRegistroEstado;
import co.edu.uco.fink.crosscutting.exception.Enums.Lugar;
import co.edu.uco.fink.crosscutting.exception.FinKException;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.data.CodigoMensaje;
import co.edu.uco.fink.data.dao.factory.DAOfactory;
import co.edu.uco.fink.entity.AnimalEntity;
import co.edu.uco.fink.entity.EstadoAnimalEntity;
import co.edu.uco.fink.entity.RegistroEstadoAnimalEntity;

import java.util.List;
import java.util.Objects;

public class CrearRegistroEstadoImpl implements CrearRegistroEstado {

    private final DAOfactory factory;

    public CrearRegistroEstadoImpl(DAOfactory factory){
        this.factory = factory;
    }

    public void verificarExistencia(RegistroEstadoAnimalEntity registro){
        List<EstadoAnimalEntity> resultado = factory.getEstadoAnimalDAO().consultar(registro.getEstado());

        boolean existe = false;

        for(EstadoAnimalEntity estado : resultado){
            if (Objects.equals(estado.getEstado(), registro.getEstado().getEstado())){
                existe = true;
                break;
            }
        }

        if(!existe){
            String mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000047);
            String mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000048);
            throw new FinKException(mensajeTecnico, mensajeUsuario, Lugar.BUSINESS);
        }
    }

    public int obtenerIdentificador(RegistroEstadoAnimalEntity registro){
        List<AnimalEntity> resultados = factory.getAnimalDAO().consultar(registro.getAnimal());
        int identificador = 0;

        for (AnimalEntity animal : resultados){
            if (Objects.equals(animal.getRaza().getEspecie().getNombre(), registro.getAnimal().getRaza().getEspecie().getNombre()) && Objects.equals(animal.getRaza().getNombre(), registro.getAnimal().getRaza().getNombre()) && animal.getCodigo() == registro.getAnimal().getCodigo() && Objects.equals(animal.getFinca().getNombre(), registro.getAnimal().getFinca().getNombre())){
                identificador = animal.getIdentificador();
                break;
            }
        }

        if (identificador == 0){
            String mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000050);
            String mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000051);
            throw new FinKException(mensajeTecnico, mensajeUsuario, Lugar.BUSINESS);
        }

        return identificador;
    }

    public void verificarEstado(RegistroEstadoAnimalEntity registro){
        List<RegistroEstadoAnimalEntity> resultados = factory.getRegistroEstadoAnimalDAO().consultar(registro);

        for (RegistroEstadoAnimalEntity entidad : resultados){
            if (Objects.equals(entidad.getAnimal().getRaza().getEspecie().getNombre(), registro.getAnimal().getRaza().getEspecie().getNombre()) && Objects.equals(entidad.getAnimal().getRaza().getNombre(), registro.getAnimal().getRaza().getNombre()) && entidad.getAnimal().getCodigo() == registro.getAnimal().getCodigo() && Objects.equals(entidad.getAnimal().getFinca().getNombre(), registro.getAnimal().getFinca().getNombre())){
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

        registroEntity.getAnimal().setIdentificador(obtenerIdentificador(registroEntity));

        verificarExistencia(registroEntity);
        verificarEstado(registroEntity);

        factory.getRegistroEstadoAnimalDAO().actualizarEstado(registroEntity.getAnimal(), registroEntity.getEstado());
    }
}
