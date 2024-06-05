package co.edu.uco.fink.api.response;

import co.edu.uco.fink.dto.fincas.EmpleadoDTO;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoResponse extends Response<EmpleadoDTO> {

    public static final EmpleadoResponse build(List<String> mensajes, List<EmpleadoDTO> datos){
        EmpleadoResponse instance = new EmpleadoResponse();
        instance.setMensajes(mensajes);
        instance.setDatos(datos);

        return instance;
    }

    public static final EmpleadoResponse build(List<EmpleadoDTO> datos){
        EmpleadoResponse instance = new EmpleadoResponse();
        instance.setMensajes(new ArrayList<>());
        instance.setDatos(datos);

        return instance;
    }

    public static final EmpleadoResponse build() {
        EmpleadoResponse instance = new EmpleadoResponse();
        instance.setMensajes(new ArrayList<>());
        instance.setDatos(new ArrayList<>());

        return instance;
    }

}
