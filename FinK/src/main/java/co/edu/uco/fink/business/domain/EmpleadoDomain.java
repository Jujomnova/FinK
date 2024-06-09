package co.edu.uco.fink.business.domain;

import co.edu.uco.fink.crosscutting.helpers.NumericHelper;
import co.edu.uco.fink.crosscutting.helpers.ObjectHelper;
import co.edu.uco.fink.crosscutting.helpers.TextHelper;

public class EmpleadoDomain {

    private int identificador;
    private int documento;
    private String clave;
    private String estado;
    private FincaDomain finca;

    public EmpleadoDomain(final int identificador,final int documento, final String clave, final String estado, final FincaDomain finca) {
        setIdentificador(identificador);
        setDocumento(documento);
        setClave(clave);
        setEstado(estado);
        setFinca(finca);
    }

    private EmpleadoDomain (){
        setDocumento(NumericHelper.ZERO);
    }

    public final int getIdentificador() {
        return identificador;
    }

    private final void setIdentificador(final int identificador) {
        this.identificador = identificador;
    }

    public final int getDocumento() {
        return documento;
    }

    private final void setDocumento(final int documento) {
        this.documento = documento;
    }

    public static final EmpleadoDomain crear(final int identificador,final int documento, final String clave, final String estado, final FincaDomain finca){
        return new EmpleadoDomain(identificador,documento, clave, estado, finca);
    }

    public static final EmpleadoDomain crear(){
        return new EmpleadoDomain();
    }

    private void setEstado(final String estado) {
        this.estado = TextHelper.applyTrim(estado);
    }

    public final String getEstado() {
        return estado;
    }

    private void setFinca(final FincaDomain finca) {
        this.finca = ObjectHelper.getObjectHelper().getDefault(finca, FincaDomain.Crear());
    }

    public final FincaDomain getFinca() {
        return finca;
    }

    public String getClave() {
        return clave;
    }

    private void setClave(String clave) {
        this.clave = TextHelper.applyTrim(clave);
    }
}