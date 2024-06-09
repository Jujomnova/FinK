package co.edu.uco.fink.entity;

import co.edu.uco.fink.crosscutting.helpers.NumericHelper;
import co.edu.uco.fink.crosscutting.helpers.ObjectHelper;

import java.time.LocalDateTime;

public class RegistroEstadoAnimalEntity {
    private int identificador;
    private AnimalEntity animal;
    private EstadoAnimalEntity estado;
    private LocalDateTime fechaActualizacion;

    public RegistroEstadoAnimalEntity(final int identificador) {
        setIdentificador(identificador);
        setAnimal(AnimalEntity.Build());
        setEstado(EstadoAnimalEntity.Build());
        setFechaActualizacion(LocalDateTime.now());
    }

    public RegistroEstadoAnimalEntity(final int identificador, final AnimalEntity animal, final EstadoAnimalEntity estado, final LocalDateTime fechaActualizacion) {
        setIdentificador(identificador);
        setAnimal(animal);
        setEstado(estado);
        setFechaActualizacion(fechaActualizacion);
    }

    public static final RegistroEstadoAnimalEntity Build(final int identificador) {
        return new RegistroEstadoAnimalEntity(identificador);
    }

    public static final RegistroEstadoAnimalEntity Build(final int identificador ,final AnimalEntity animal, final EstadoAnimalEntity estado, final LocalDateTime fechaActualizacion) {
        return new RegistroEstadoAnimalEntity(identificador, animal, estado, fechaActualizacion);
    }

    protected static final RegistroEstadoAnimalEntity Build() {
        return new RegistroEstadoAnimalEntity(NumericHelper.ZERO);
    }

    public final int getIdentificador() {
        return identificador;
    }

    public final void setIdentificador(final int identificador) {
        this.identificador = identificador;
    }

    public final AnimalEntity getAnimal() {
        return animal;
    }

    public final void setAnimal(final AnimalEntity animal) {
        this.animal = ObjectHelper.getObjectHelper().getDefault(animal, AnimalEntity.Build());
    }

    public final EstadoAnimalEntity getEstado() {
        return estado;
    }

    public final void setEstado(final EstadoAnimalEntity estado) {
        this.estado = ObjectHelper.getObjectHelper().getDefault(estado, EstadoAnimalEntity.Build());
    }

    public final LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public final void setFechaActualizacion(final LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}
