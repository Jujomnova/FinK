package co.edu.uco.fink.dto.animales;

import co.edu.uco.fink.crosscutting.helpers.TextHelper;

public class EspecieDTO {
    private int identificador;
    private String nombre;

    public EspecieDTO() {
        setNombre(TextHelper.EMPTY);
    }

    public EspecieDTO(final int identificador, final String nombre) {
        setIdentificador(identificador);
        setNombre(nombre);
    }

    public static final EspecieDTO Build(){
        return new EspecieDTO();
    }

    public final int getIdentificador() {
        return identificador;
    }

    public final EspecieDTO setIdentificador(final int identificador) {
        this.identificador = identificador;
        return this;
    }

    public final String getNombre() {
        return nombre;
    }

    public final EspecieDTO setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }
}
