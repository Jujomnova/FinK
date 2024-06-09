package co.edu.uco.fink.business.domain;

import co.edu.uco.fink.crosscutting.helpers.NumericHelper;
import co.edu.uco.fink.crosscutting.helpers.ObjectHelper;

public class AnimalDomain {
    private int identificador;
    private RazaDomain raza;
    private int codigo;
    private FincaDomain finca;

    public AnimalDomain() {
        setRaza(RazaDomain.Crear());
        setCodigo(NumericHelper.ZERO);
        setFinca(FincaDomain.Crear());
    }

    public AnimalDomain(final int identificador, final RazaDomain raza, final int codigo, final FincaDomain finca) {
        setIdentificador(identificador);
        setRaza(raza);
        setCodigo(codigo);
        setFinca(finca);
    }

    public static final AnimalDomain Crear(final int identificador, final RazaDomain raza, final int codigo, final FincaDomain finca){
        return new AnimalDomain(identificador, raza, codigo, finca);
    }

    public static final AnimalDomain Crear(){
        return new AnimalDomain();
    }

    public final int getIdentificador() {
        return identificador;
    }

    public final void setIdentificador(final int identificador) {
        this.identificador = identificador;
    }

    public final RazaDomain getRaza() {
        return raza;
    }

    public final void setRaza(final RazaDomain raza) {
        this.raza = ObjectHelper.getObjectHelper().getDefault(raza, RazaDomain.Crear());
    }

    public final int getCodigo() {
        return codigo;
    }

    public final void setCodigo(final int codigo) {
        this.codigo = codigo;
    }

    public final FincaDomain getFinca() {
        return finca;
    }

    public final void setFinca(final FincaDomain finca) {
        this.finca = ObjectHelper.getObjectHelper().getDefault(finca, FincaDomain.Crear());
    }
}
