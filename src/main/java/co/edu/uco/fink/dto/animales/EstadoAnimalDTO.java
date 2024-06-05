package co.edu.uco.fink.dto.animales;

import co.edu.uco.fink.crosscutting.helpers.TextHelper;

public class EstadoAnimalDTO {
    private int identificador;
    private String estado;

    public EstadoAnimalDTO() {
        setEstado(TextHelper.EMPTY);
    }

    public EstadoAnimalDTO(final int identificador, final String estado) {
        setIdentificador(identificador);
        setEstado(estado);
    }

    public static final EstadoAnimalDTO Build(){
        return new EstadoAnimalDTO();
    }

    public final int getIdentificador() {
        return identificador;
    }

    public final EstadoAnimalDTO setIdentificador(final int identificador) {
        this.identificador = identificador;
        return this;
    }

    public final String getEstado() {
        return estado;
    }

    public final EstadoAnimalDTO setEstado(final String estado) {
        this.estado = TextHelper.applyTrim(estado);
        return this;
    }
}
