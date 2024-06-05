package co.edu.uco.fink.entity;

import co.edu.uco.fink.crosscutting.helpers.NumericHelper;
import co.edu.uco.fink.crosscutting.helpers.ObjectHelper;
import co.edu.uco.fink.crosscutting.helpers.TextHelper;

public class EmpleadoEntity {
    private int identificador;
    private int documento;
    private String clave;
    private String estado;
    private FincaEntity finca;

    private EmpleadoEntity(final int identificador){
        setIdentificador(identificador);
        setDocumento(NumericHelper.ZERO);
        setClave(TextHelper.EMPTY);
        setEstado(TextHelper.EMPTY);
        setFinca(FincaEntity.build());
    }

    private EmpleadoEntity(final int identificador, final int documento, final String clave, final String estado, final FincaEntity finca) {
        setIdentificador(identificador);
        setDocumento(documento);
        setClave(clave);
        setEstado(estado);
        setFinca(finca);
    }

    public static final EmpleadoEntity Build(final int identificador){
        return new EmpleadoEntity(identificador);
    }

    public static final EmpleadoEntity Build(final int identificador, final int documento, final String clave, final String estado, final FincaEntity finca){
        return new EmpleadoEntity(identificador, documento, clave, estado, finca);
    }

    protected static final EmpleadoEntity Build(){
        return new EmpleadoEntity(NumericHelper.ZERO);
    }

    public final int getIdentificador() {
        return identificador;
    }


    public final void setIdentificador(final int identificador) {
        this.identificador = identificador;
    }

    public final int getDocumento() {
        return documento;
    }

    public final void setDocumento(final int documento) {
        this.documento = documento;
    }

    public final String getEstado() {
        return estado;
    }

    public final void setEstado(final String estado) {
        this.estado = TextHelper.applyTrim(estado);
    }

    public final FincaEntity getFinca() {
        return finca;
    }

    public final void setFinca(FincaEntity finca) {
        this.finca = ObjectHelper.getObjectHelper().getDefault(finca, FincaEntity.build());
    }

    public final String getClave() {
        return clave;
    }

    public final void setClave(String clave) {
        this.clave = TextHelper.applyTrim(clave);
    }
}