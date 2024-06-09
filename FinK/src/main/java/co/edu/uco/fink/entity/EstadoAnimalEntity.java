package co.edu.uco.fink.entity;

import co.edu.uco.fink.crosscutting.helpers.NumericHelper;
import co.edu.uco.fink.crosscutting.helpers.TextHelper;
import co.edu.uco.fink.dto.animales.EstadoAnimalDTO;

public class EstadoAnimalEntity {
    private int identificador;
    private String estado;

    public EstadoAnimalEntity(final int identificador) {
        setIdentificador(identificador);
        setEstado(TextHelper.EMPTY);
    }

    public EstadoAnimalEntity(final int identificador, final String estado) {
        setIdentificador(identificador);
        setEstado(estado);
    }

    public static final EstadoAnimalEntity Build(final int identificador){
        return new EstadoAnimalEntity(identificador);
    }

    public static final EstadoAnimalEntity Build(final int identificador, final String estado) {
        return new EstadoAnimalEntity(identificador, estado);
    }

    protected static final EstadoAnimalEntity Build(){
        return new EstadoAnimalEntity(NumericHelper.ZERO);
    }

    public final int getIdentificador() {
        return identificador;
    }

    public final void setIdentificador(final int identificador) {
        this.identificador = identificador;
    }

    public final String getEstado() {
        return estado;
    }

    public final void setEstado(final String estado) {
        this.estado = TextHelper.applyTrim(estado);
    }
}
