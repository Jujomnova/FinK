package co.edu.uco.fink.business.domain;

import co.edu.uco.fink.crosscutting.helpers.ObjectHelper;

import java.time.LocalDateTime;

public class RegistroEstadoAnimalDomain {
    private int identificador;
    private AnimalDomain animal;
    private EstadoAnimalDomain estado;
    private LocalDateTime fechaActualizacion;

    public RegistroEstadoAnimalDomain() {
        setAnimal(AnimalDomain.Crear());
        setEstado(EstadoAnimalDomain.Crear());
        setFechaActualizacion(LocalDateTime.now());
    }

    public RegistroEstadoAnimalDomain(final int identificador, final AnimalDomain animal, final EstadoAnimalDomain estado, final LocalDateTime fechaActualizacion) {
        setIdentificador(identificador);
        setAnimal(animal);
        setEstado(estado);
        setFechaActualizacion(fechaActualizacion);
    }

    public static final RegistroEstadoAnimalDomain Crear(final int identificador ,final AnimalDomain animal, final EstadoAnimalDomain estado, final LocalDateTime fechaActualizacion) {
        return new RegistroEstadoAnimalDomain(identificador, animal, estado, fechaActualizacion);
    }

    public static final RegistroEstadoAnimalDomain Crear() {
        return new RegistroEstadoAnimalDomain();
    }

    public final int getIdentificador() {
        return identificador;
    }

    private final void setIdentificador(final int identificador) {
        this.identificador = identificador;
    }

    public final AnimalDomain getAnimal() {
        return animal;
    }

    private final void setAnimal(final AnimalDomain animal) {
        this.animal = ObjectHelper.getObjectHelper().getDefault(animal, AnimalDomain.Crear());
    }

    public final EstadoAnimalDomain getEstado() {
        return estado;
    }

    private final void setEstado(final EstadoAnimalDomain estado) {
        this.estado = ObjectHelper.getObjectHelper().getDefault(estado, EstadoAnimalDomain.Crear());
    }

    public final LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    private final void setFechaActualizacion(final LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}
