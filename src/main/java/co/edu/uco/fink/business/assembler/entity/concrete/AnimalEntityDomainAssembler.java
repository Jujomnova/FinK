package co.edu.uco.fink.business.assembler.entity.concrete;

import co.edu.uco.fink.business.assembler.entity.EntityDomainAssembler;
import co.edu.uco.fink.business.domain.AnimalDomain;
import co.edu.uco.fink.business.domain.FincaDomain;
import co.edu.uco.fink.business.domain.RazaDomain;
import co.edu.uco.fink.crosscutting.helpers.ObjectHelper;
import co.edu.uco.fink.entity.AnimalEntity;
import co.edu.uco.fink.entity.FincaEntity;
import co.edu.uco.fink.entity.RazaEntity;

import java.util.ArrayList;
import java.util.List;

public class AnimalEntityDomainAssembler implements EntityDomainAssembler<AnimalDomain, AnimalEntity> {

    private static final EntityDomainAssembler<AnimalDomain, AnimalEntity> instancia = new AnimalEntityDomainAssembler();
    private static final EntityDomainAssembler<RazaDomain, RazaEntity> razaAssembler = new RazaEntityDomainAssembler();
    private static final EntityDomainAssembler<FincaDomain, FincaEntity> fincaAssembler = new FincaEntityDomainAssembler();


    public static final EntityDomainAssembler<AnimalDomain, AnimalEntity> obtenerInstancia() {
        return instancia;
    }

    @Override
    public AnimalDomain ensamblarDominio(AnimalEntity Entity) {
        var AnimalEntityTemp = ObjectHelper.getObjectHelper().getDefault(Entity, AnimalEntity.Build(0));
        RazaDomain razaDomain = razaAssembler.ensamblarDominio(AnimalEntityTemp.getRaza());
        FincaDomain fincaDomain = fincaAssembler.ensamblarDominio(AnimalEntityTemp.getFinca());
        return AnimalDomain.Crear(AnimalEntityTemp.getIdentificador(), razaDomain, AnimalEntityTemp.getCodigo(), fincaDomain);
    }

    @Override
    public AnimalEntity ensamblarEntidad(AnimalDomain dominio) {
        var AnimalDomainTemp = ObjectHelper.getObjectHelper().getDefault(dominio, AnimalDomain.Crear());
        RazaEntity razaEntity= razaAssembler.ensamblarEntidad(AnimalDomainTemp.getRaza());
        FincaEntity fincaEntity = fincaAssembler.ensamblarEntidad(AnimalDomainTemp.getFinca());
        return new AnimalEntity(AnimalDomainTemp.getIdentificador(), razaEntity, AnimalDomainTemp.getCodigo(), fincaEntity);
    }

    @Override
    public List<AnimalDomain> ensamblarListaDominios(List<AnimalEntity> listaEntitys) {
        var listaEntitysTmp = ObjectHelper.getObjectHelper().getDefault(listaEntitys, new ArrayList<AnimalEntity>());
        var resultados = new ArrayList<AnimalDomain>();

        for (AnimalEntity AnimalEntity : listaEntitysTmp) {
            var AnimalDomainTmp = ensamblarDominio(AnimalEntity);
            resultados.add(AnimalDomainTmp);
        }
        return resultados;
    }
}
