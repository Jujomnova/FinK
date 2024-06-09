package co.edu.uco.fink.api.response;

import co.edu.uco.fink.dto.animales.RegistroEstadoAnimalDTO;

import java.util.ArrayList;

public class RegistroEstadoResponse extends Response<RegistroEstadoAnimalDTO>{
    
    public RegistroEstadoResponse() {
        setMensajes(new ArrayList<>());
        setDatos(new ArrayList<>());
    }
}
