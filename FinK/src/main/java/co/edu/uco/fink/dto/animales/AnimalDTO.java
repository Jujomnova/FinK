package co.edu.uco.fink.dto.animales;

import co.edu.uco.fink.crosscutting.helpers.NumericHelper;
import co.edu.uco.fink.crosscutting.helpers.ObjectHelper;
import co.edu.uco.fink.dto.fincas.FincaDTO;

public class AnimalDTO {
    private int identificador;
    private RazaDTO raza;
    private int codigo;
    private FincaDTO finca;

    public AnimalDTO() {
        setRaza(RazaDTO.Build());
        setCodigo(NumericHelper.ZERO);
        setFinca(FincaDTO.build());
    }

    public AnimalDTO(final int identificador, final RazaDTO raza, final int codigo, final FincaDTO finca) {
        setIdentificador(identificador);
        setRaza(raza);
        setCodigo(codigo);
        setFinca(finca);
    }

    public static final AnimalDTO Build(){
        return new AnimalDTO();
    }

    public final int getIdentificador() {
        return identificador;
    }

    public final AnimalDTO setIdentificador(final int identificador) {
        this.identificador = identificador;
        return this;
    }

    public final RazaDTO getRaza() {
        return raza;
    }

    public final AnimalDTO setRaza(final RazaDTO raza) {
        this.raza = ObjectHelper.getObjectHelper().getDefault(raza, RazaDTO.Build());
        return this;
    }

    public final int getCodigo() {
        return codigo;
    }

    public final AnimalDTO setCodigo(final int codigo) {
        this.codigo = codigo;
        return this;
    }

    public final FincaDTO getFinca() {
        return finca;
    }

    public final AnimalDTO setFinca(final FincaDTO finca) {
        this.finca = ObjectHelper.getObjectHelper().getDefault(finca, FincaDTO.build());
        return this;
    }
}
