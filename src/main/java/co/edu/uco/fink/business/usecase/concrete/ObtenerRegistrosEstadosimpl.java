package co.edu.uco.fink.business.usecase.concrete;

import co.edu.uco.fink.business.assembler.entity.concrete.RegistroEstadoAnimalEntityDomainAssembler;
import co.edu.uco.fink.business.domain.RegistroEstadoAnimalDomain;
import co.edu.uco.fink.business.usecase.ObtenerRegistrosEstados;
import co.edu.uco.fink.data.dao.factory.DAOfactory;
import co.edu.uco.fink.entity.AnimalEntity;
import co.edu.uco.fink.entity.RegistroEstadoAnimalEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ObtenerRegistrosEstadosimpl implements ObtenerRegistrosEstados{

    private final DAOfactory factory;

    public ObtenerRegistrosEstadosimpl(DAOfactory factory) {
        this.factory = factory;
    }

    @Override
    public List<RegistroEstadoAnimalDomain> ejecutar() {
        List<AnimalEntity> animales = factory.getAnimalDAO().consultar(AnimalEntity.Build(0));
        List<RegistroEstadoAnimalEntity> filtro = factory.getRegistroEstadoAnimalDAO().consultar(RegistroEstadoAnimalEntity.Build(0));

        var listaFiltrada = new ArrayList<RegistroEstadoAnimalEntity>();

        for (AnimalEntity animal : animales) {
            for (RegistroEstadoAnimalEntity registroEstadoAnimal : filtro) {
                if (Objects.equals(animal.getRaza().getEspecie().getNombre(), registroEstadoAnimal.getAnimal().getRaza().getEspecie().getNombre()) && Objects.equals(animal.getRaza().getNombre(), registroEstadoAnimal.getAnimal().getRaza().getNombre()) && animal.getCodigo() == registroEstadoAnimal.getAnimal().getCodigo()){
                    listaFiltrada.add(registroEstadoAnimal);
                    break;
                }
            }
        }

        return RegistroEstadoAnimalEntityDomainAssembler.obtenerInstancia().ensamblarListaDominios(listaFiltrada);
    }
}
