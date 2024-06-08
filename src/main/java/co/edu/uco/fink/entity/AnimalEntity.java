package co.edu.uco.fink.entity;

import co.edu.uco.fink.business.domain.FincaDomain;
import co.edu.uco.fink.business.domain.RazaDomain;
import co.edu.uco.fink.crosscutting.helpers.NumericHelper;
import co.edu.uco.fink.crosscutting.helpers.ObjectHelper;
import co.edu.uco.fink.dto.animales.AnimalDTO;
import co.edu.uco.fink.dto.animales.RazaDTO;
import co.edu.uco.fink.dto.fincas.FincaDTO;

public class AnimalEntity {
    private int identificador;
    private RazaEntity raza;
    private int codigo;
    private FincaEntity finca;

    public AnimalEntity(final int identificador) {
        setIdentificador(identificador);
        setRaza(RazaEntity.Build());
        setCodigo(NumericHelper.ZERO);
        setFinca(FincaEntity.build());
    }

    public AnimalEntity(final int identificador, final RazaEntity raza, final int codigo, final FincaEntity finca) {
        setIdentificador(identificador);
        setRaza(raza);
        setCodigo(codigo);
        setFinca(finca);
    }

    public static final AnimalEntity Build(final int identificador){
        return new AnimalEntity(identificador);
    }

    public static final AnimalEntity Build(final int identificador, final RazaEntity raza, final int codigo, final FincaEntity finca){
        return new AnimalEntity(identificador, raza, codigo, finca);
    }

    protected static final AnimalEntity Build(){
        return new AnimalEntity(NumericHelper.ZERO);
    }

    public final int getIdentificador() {
        return identificador;
    }

    public final void setIdentificador(final int identificador) {
        this.identificador = identificador;
    }

    public final RazaEntity getRaza() {
        return raza;
    }

    public final void setRaza(final RazaEntity raza) {
        this.raza = ObjectHelper.getObjectHelper().getDefault(raza, RazaEntity.Build());
    }

    public final int getCodigo() {
        return codigo;
    }

    public final void setCodigo(final int codigo) {
        this.codigo = codigo;
    }

    public final FincaEntity getFinca() {
        return finca;
    }

    public final void setFinca(final FincaEntity finca) {
        this.finca = ObjectHelper.getObjectHelper().getDefault(finca, FincaEntity.build());
    }
}
