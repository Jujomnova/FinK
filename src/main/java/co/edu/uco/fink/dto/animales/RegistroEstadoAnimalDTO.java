package co.edu.uco.fink.dto.animales;

import co.edu.uco.fink.crosscutting.helpers.ObjectHelper;

import java.time.LocalDateTime;

public class RegistroEstadoAnimalDTO {
    private int identificador;
    private AnimalDTO animal;
    private EstadoAnimalDTO estado;
    private LocalDateTime fechaActualizacion;

    public RegistroEstadoAnimalDTO() {
        setAnimal(AnimalDTO.Build());
        setEstado(EstadoAnimalDTO.Build());
        setFechaActualizacion(LocalDateTime.now());
    }

    public RegistroEstadoAnimalDTO(final int identificador, final AnimalDTO animal, final EstadoAnimalDTO estado, final LocalDateTime fechaActualizacion) {
        setIdentificador(identificador);
        setAnimal(animal);
        setEstado(estado);
        setFechaActualizacion(fechaActualizacion);
    }

    public static final RegistroEstadoAnimalDTO Build() {
        return new RegistroEstadoAnimalDTO();
    }

    public final int getIdentificador() {
        return identificador;
    }

    public final RegistroEstadoAnimalDTO setIdentificador(final int identificador) {
        this.identificador = identificador;
        return this;
    }

    public final AnimalDTO getAnimal() {
        return animal;
    }

    public final RegistroEstadoAnimalDTO setAnimal(final AnimalDTO animal) {
        this.animal = ObjectHelper.getObjectHelper().getDefault(animal, AnimalDTO.Build());
        return this;
    }

    public final EstadoAnimalDTO getEstado() {
        return estado;
    }

    public final RegistroEstadoAnimalDTO setEstado(final EstadoAnimalDTO estado) {
        this.estado = ObjectHelper.getObjectHelper().getDefault(estado, EstadoAnimalDTO.Build());
        return this;
    }

    public final LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public final RegistroEstadoAnimalDTO setFechaActualizacion(final LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
        return this;
    }
}
