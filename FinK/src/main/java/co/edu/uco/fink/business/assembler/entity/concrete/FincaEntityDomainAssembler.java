package co.edu.uco.fink.business.assembler.entity.concrete;

import co.edu.uco.fink.business.assembler.entity.EntityDomainAssembler;
import co.edu.uco.fink.business.domain.FincaDomain;
import co.edu.uco.fink.crosscutting.helpers.ObjectHelper;
import co.edu.uco.fink.entity.FincaEntity;

import java.util.ArrayList;
import java.util.List;

public class FincaEntityDomainAssembler implements EntityDomainAssembler<FincaDomain, FincaEntity> {

    private static final EntityDomainAssembler <FincaDomain, FincaEntity> instancia = new FincaEntityDomainAssembler();

    public static final EntityDomainAssembler <FincaDomain, FincaEntity> obtenerInstancia(){
        return instancia;
    }

    @Override
    public FincaDomain ensamblarDominio(FincaEntity entity) {
        var FincaEntityTemp = ObjectHelper.getObjectHelper().getDefault(entity, FincaEntity.Build(0));
        return FincaDomain.Crear(FincaEntityTemp.getId(), FincaEntityTemp.getNombre());
    }

    @Override
    public FincaEntity ensamblarEntidad(FincaDomain dominio) {
        var FincaDomainTemp = ObjectHelper.getObjectHelper().getDefault(dominio, FincaDomain.Crear());
        return FincaEntity.Build(FincaDomainTemp.getId(), FincaDomainTemp.getNombre());
    }

    @Override
    public List<FincaDomain> ensamblarListaDominios(List<FincaEntity> listaEntidades) {
        var listaEntidadesTmp = ObjectHelper.getObjectHelper().getDefault(listaEntidades, new ArrayList<FincaEntity>());
        var resultados = new ArrayList<FincaDomain>();

        for (FincaEntity FincaEntity : listaEntidadesTmp){
            var fincaDomainTmp = ensamblarDominio(FincaEntity);
            resultados.add(fincaDomainTmp);
        }
        return resultados;
    }
}
