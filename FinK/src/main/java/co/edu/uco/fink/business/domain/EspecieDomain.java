package co.edu.uco.fink.business.domain;

import co.edu.uco.fink.crosscutting.helpers.TextHelper;

public class EspecieDomain {
    private int identificador;
    private String nombre;

    public EspecieDomain() {
        setNombre(TextHelper.EMPTY);
    }

    public EspecieDomain(final int identificador, final String nombre) {
        setIdentificador(identificador);
        setNombre(nombre);
    }

    public static final EspecieDomain Crear(final int identificador, final String nombre) {
        return new EspecieDomain(identificador, nombre);
    }

    public static final EspecieDomain Crear(){
        return new EspecieDomain();
    }

    public final int getIdentificador() {
        return identificador;
    }

    private final void setIdentificador(final int identificador) {
        this.identificador = identificador;
    }

    public final String getNombre() {
        return nombre;
    }

    private final void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }
}
