package co.edu.uco.fink.dto.animales;

import co.edu.uco.fink.crosscutting.helpers.ObjectHelper;
import co.edu.uco.fink.crosscutting.helpers.TextHelper;

public class RazaDTO {
    private int identificador;
    private String nombre;
    private EspecieDTO especie;

    public RazaDTO() {
        setNombre(TextHelper.EMPTY);
        setEspecie(EspecieDTO.Build());
    }

    public RazaDTO(final int identificador, final String nombre, final EspecieDTO especie) {
        setIdentificador(identificador);
        setNombre(nombre);
        setEspecie(especie);
    }

    public static final RazaDTO Build(){
        return new RazaDTO();
    }

    public final int getIdentificador() {
        return identificador;
    }

    public final RazaDTO setIdentificador(final int identificador) {
        this.identificador = identificador;
        return this;
    }

    public final String getNombre() {
        return nombre;
    }

    public final RazaDTO setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }

    public final EspecieDTO getEspecie() {
        return especie;
    }

    public final RazaDTO setEspecie(final EspecieDTO especie) {
        this.especie = ObjectHelper.getObjectHelper().getDefault(especie, EspecieDTO.Build());
        return this;
    }
}
