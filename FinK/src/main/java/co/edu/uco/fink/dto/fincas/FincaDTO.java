package co.edu.uco.fink.dto.fincas;

import co.edu.uco.fink.crosscutting.helpers.TextHelper;

public class FincaDTO {
    private int id;
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public FincaDTO(){
        setNombre(TextHelper.EMPTY);
    }

    public FincaDTO(final int id, final String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static final FincaDTO build(){
        return new FincaDTO();
    }

    public final FincaDTO setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }

    public final int getId() {
        return id;
    }

    public final FincaDTO setId(int id) {
        this.id = id;
        return this;
    }
}
