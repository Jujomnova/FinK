package co.edu.uco.fink.business.domain;

import co.edu.uco.fink.crosscutting.helpers.NumericHelper;
import co.edu.uco.fink.crosscutting.helpers.TextHelper;

public class EstadoAnimalDomain {
    private int identificador;
    private String estado;

    public EstadoAnimalDomain() {
        setEstado(TextHelper.EMPTY);
    }

    public EstadoAnimalDomain(final int identificador, final String estado) {
        setIdentificador(identificador);
        setEstado(estado);
    }

    public static final EstadoAnimalDomain Crear(final int identificador, final String estado) {
        return new EstadoAnimalDomain(identificador, estado);
    }

    public static final EstadoAnimalDomain Crear(){
        return new EstadoAnimalDomain();
    }

    public final int getIdentificador() {
        return identificador;
    }

    private final void setIdentificador(final int identificador) {
        this.identificador = identificador;
    }

    public final String getEstado() {
        return estado;
    }

    private final void setEstado(final String estado) {
        this.estado = TextHelper.applyTrim(estado);
    }
}
