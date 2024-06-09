package co.edu.uco.fink.entity;

import co.edu.uco.fink.crosscutting.helpers.NumericHelper;
import co.edu.uco.fink.crosscutting.helpers.TextHelper;
import co.edu.uco.fink.dto.fincas.FincaDTO;

public class FincaEntity {
    private int id;
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public FincaEntity(final int id){
        setId(id);
        setNombre(TextHelper.EMPTY);
    }

    public FincaEntity(final int id, final String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static final FincaEntity Build(final int id){
        return new FincaEntity(id);
    }

    public static final FincaEntity Build(final int id, final String nombre){
        return new FincaEntity(id, nombre);
    }

    protected static final FincaEntity build(){
        return new FincaEntity(NumericHelper.ZERO);
    }


    public final void setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public final int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
    }
}
