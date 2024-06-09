package co.edu.uco.fink.entity;

import co.edu.uco.fink.crosscutting.helpers.NumericHelper;
import co.edu.uco.fink.crosscutting.helpers.ObjectHelper;
import co.edu.uco.fink.crosscutting.helpers.TextHelper;
import co.edu.uco.fink.dto.animales.EspecieDTO;
import co.edu.uco.fink.dto.animales.RazaDTO;

public class RazaEntity {
    private int identificador;
    private String nombre;
    private EspecieEntity especie;

    public RazaEntity(final int identificador) {
        setIdentificador(identificador);
        setNombre(TextHelper.EMPTY);
        setEspecie(EspecieEntity.Build());
    }

    public RazaEntity(final int identificador, final String nombre, final EspecieEntity especie) {
        setIdentificador(identificador);
        setNombre(nombre);
        setEspecie(especie);
    }

    public static final RazaEntity Build(final int identificador){
        return new RazaEntity(identificador);
    }

    public static final RazaEntity Build(final int identificador, final String nombre, final EspecieEntity especie) {
        return new RazaEntity(identificador, nombre, especie);
    }

    protected static final RazaEntity Build(){
        return new RazaEntity(NumericHelper.ZERO);
    }

    public final int getIdentificador() {
        return identificador;
    }

    public final void setIdentificador(final int identificador) {
        this.identificador = identificador;
    }

    public final String getNombre() {
        return nombre;
    }

    public final void setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public final EspecieEntity getEspecie() {
        return especie;
    }

    public final void setEspecie(final EspecieEntity especie) {
        this.especie = ObjectHelper.getObjectHelper().getDefault(especie, EspecieEntity.Build());
    }
}
