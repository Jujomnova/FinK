package co.edu.uco.fink.business.assembler.entity.concrete;

import co.edu.uco.fink.business.assembler.entity.EntityDomainAssembler;
import co.edu.uco.fink.business.domain.EspecieDomain;
import co.edu.uco.fink.business.domain.RazaDomain;
import co.edu.uco.fink.crosscutting.helpers.ObjectHelper;
import co.edu.uco.fink.entity.EspecieEntity;
import co.edu.uco.fink.entity.RazaEntity;

import java.util.ArrayList;
import java.util.List;

public class RazaEntityDomainAssembler implements EntityDomainAssembler<RazaDomain, RazaEntity> {

    private static final EntityDomainAssembler<RazaDomain, RazaEntity> instancia = new RazaEntityDomainAssembler();
    private static final EntityDomainAssembler<EspecieDomain, EspecieEntity> especieAssembler = new EspecieEntityDomainAssembler();

    public static final EntityDomainAssembler<RazaDomain, RazaEntity> obtenerInstancia() {
        return instancia;
    }


    @Override
    public RazaDomain ensamblarDominio(RazaEntity Entity) {
        var RazaEntityTemp = ObjectHelper.getObjectHelper().getDefault(Entity, RazaEntity.Build(0));
        EspecieDomain especiedomain = especieAssembler.ensamblarDominio(RazaEntityTemp.getEspecie());
        return RazaDomain.Crear(RazaEntityTemp.getIdentificador(), RazaEntityTemp.getNombre(), especiedomain);
    }

    @Override
    public RazaEntity ensamblarEntidad(RazaDomain dominio) {
        var razaDomainTemp = ObjectHelper.getObjectHelper().getDefault(dominio, RazaDomain.Crear());
        EspecieEntity especieEntity = especieAssembler.ensamblarEntidad(razaDomainTemp.getEspecie());
        return new RazaEntity(razaDomainTemp.getIdentificador(), razaDomainTemp.getNombre(), especieEntity);
    }

    @Override
    public List<RazaDomain> ensamblarListaDominios(List<RazaEntity> listaEntitys) {
        var listaEntitysTmp = ObjectHelper.getObjectHelper().getDefault(listaEntitys, new ArrayList<RazaEntity>());
        var resultados = new ArrayList<RazaDomain>();

        for (RazaEntity RazaEntity : listaEntitysTmp) {
            var RazaDomainTmp = ensamblarDominio(RazaEntity);
            resultados.add(RazaDomainTmp);
        }
        return resultados;
    }
}
