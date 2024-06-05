package co.edu.uco.fink.business.assembler.entity.concrete;

import co.edu.uco.fink.business.assembler.entity.EntityDomainAssembler;
import co.edu.uco.fink.business.domain.EspecieDomain;
import co.edu.uco.fink.crosscutting.helpers.ObjectHelper;
import co.edu.uco.fink.entity.EspecieEntity;

import java.util.ArrayList;
import java.util.List;

public class EspecieEntityDomainAssembler implements EntityDomainAssembler<EspecieDomain, EspecieEntity> {

    private static final EntityDomainAssembler<EspecieDomain, EspecieEntity> instancia = new EspecieEntityDomainAssembler();

    public static final EntityDomainAssembler<EspecieDomain, EspecieEntity> obtenerInstancia() {
        return instancia;
    }

    @Override
    public EspecieDomain ensamblarDominio(EspecieEntity Entity) {
        var EspecieEntityTemp = ObjectHelper.getObjectHelper().getDefault(Entity, EspecieEntity.Build(0));
        return EspecieDomain.Crear(EspecieEntityTemp.getIdentificador(), EspecieEntityTemp.getNombre());
    }

    @Override
    public EspecieEntity ensamblarEntidad(EspecieDomain dominio) {
        var EspecieDomainTemp = ObjectHelper.getObjectHelper().getDefault(dominio, EspecieDomain.Crear());
        return new EspecieEntity(EspecieDomainTemp.getIdentificador(), EspecieDomainTemp.getNombre());
    }

    @Override
    public List<EspecieDomain> ensamblarListaDominios(List<EspecieEntity> listaEntitys) {
        var listaEntitysTmp = ObjectHelper.getObjectHelper().getDefault(listaEntitys, new ArrayList<EspecieEntity>());
        var resultados = new ArrayList<EspecieDomain>();

        for (EspecieEntity especieEntity : listaEntitysTmp) {
            var especieDomainTmp = ensamblarDominio(especieEntity);
            resultados.add(especieDomainTmp);
        }
        return resultados;
    }
}
