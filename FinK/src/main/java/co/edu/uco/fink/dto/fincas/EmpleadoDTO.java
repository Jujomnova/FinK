package co.edu.uco.fink.dto.fincas;

import co.edu.uco.fink.crosscutting.helpers.NumericHelper;
import co.edu.uco.fink.crosscutting.helpers.ObjectHelper;
import co.edu.uco.fink.crosscutting.helpers.TextHelper;

public class EmpleadoDTO {

    private int identificador;

    private int documento;

    private String clave;

    private String estado;

    private FincaDTO finca;

    public EmpleadoDTO(){
        setDocumento(NumericHelper.ZERO);
        setClave(TextHelper.EMPTY);
        setEstado(TextHelper.EMPTY);
        setFinca(FincaDTO.build());
    }

    public EmpleadoDTO(final int identificador, final int documento, final String clave, final String estado, final FincaDTO finca) {
        setIdentificador(identificador);
        setDocumento(documento);
        setEstado(estado);
        setFinca(finca);
        setClave(clave);
    }

    public static final EmpleadoDTO build(){
        return new EmpleadoDTO();
    }

    public final int getIdentificador() {
        return identificador;
    }

    public final EmpleadoDTO setIdentificador(final int identificador) {
        this.identificador = identificador;
        return this;
    }

    public final int getDocumento() {
        return documento;
    }

    public final EmpleadoDTO setDocumento(final int documento) {
        this.documento = documento;
        return this;
    }

    public final String getEstado() {
        return estado;
    }

    public final EmpleadoDTO setEstado(final String estado) {
        this.estado = TextHelper.applyTrim(estado);
        return this;
    }

    public final FincaDTO getFinca() {
        return finca;
    }

    public final EmpleadoDTO setFinca(final FincaDTO finca) {
        this.finca = ObjectHelper.getObjectHelper().getDefault(finca, FincaDTO.build());
        return this;
    }

    public final String getClave() {
        return clave;
    }

    public final EmpleadoDTO setClave(String clave) {
        this.clave = TextHelper.applyTrim(clave);
        return this;
    }
}