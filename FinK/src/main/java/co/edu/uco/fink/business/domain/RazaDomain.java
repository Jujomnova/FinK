package co.edu.uco.fink.business.domain;

import co.edu.uco.fink.crosscutting.helpers.ObjectHelper;
import co.edu.uco.fink.crosscutting.helpers.TextHelper;

public class RazaDomain {
    private int identificador;
    private String nombre;
    private EspecieDomain especie;

    public RazaDomain() {
        setNombre(TextHelper.EMPTY);
        setEspecie(EspecieDomain.Crear());
    }

    public RazaDomain(final int identificador, final String nombre, final EspecieDomain especie) {
        setIdentificador(identificador);
        setNombre(nombre);
        setEspecie(especie);
    }

    public static final RazaDomain Crear(final int identificador, final String nombre, final EspecieDomain especie) {
        return new RazaDomain(identificador, nombre, especie);
    }

    public static final RazaDomain Crear(){
        return new RazaDomain();
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

    private final void setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public final EspecieDomain getEspecie() {
        return especie;
    }

    private final void setEspecie(final EspecieDomain especie) {
        this.especie = ObjectHelper.getObjectHelper().getDefault(especie, EspecieDomain.Crear());
    }
}
