package co.edu.uco.fink.entity;

import co.edu.uco.fink.crosscutting.helpers.NumericHelper;
import co.edu.uco.fink.crosscutting.helpers.TextHelper;

public class EspecieEntity {
    private int identificador;
    private String nombre;

    public EspecieEntity(final int identificador) {
        setIdentificador(identificador);
        setNombre(TextHelper.EMPTY);
    }

    public EspecieEntity(final int identificador, final String nombre) {
        setIdentificador(identificador);
        setNombre(nombre);
    }

    public static final EspecieEntity Build(final int identificador) {
        return new EspecieEntity(identificador);
    }

    public static final EspecieEntity Build(final int identificador, final String nombre) {
        return new EspecieEntity(identificador, nombre);
    }

    protected static final EspecieEntity Build(){
        return new EspecieEntity(NumericHelper.ZERO);
    }

    public final int getIdentificador() {
        return identificador;
    }

    public final void setIdentificador(final int identificador) {
        this.identificador = identificador;
    }

    public final String getNombre() {
        return nombre;
    }

    public final void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }
}
