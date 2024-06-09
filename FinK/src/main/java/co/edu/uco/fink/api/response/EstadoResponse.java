package co.edu.uco.fink.api.response;

import co.edu.uco.fink.dto.animales.EstadoAnimalDTO;

import java.util.ArrayList;

public class EstadoResponse extends Response<EstadoAnimalDTO>{

    public EstadoResponse() {
        setMensajes(new ArrayList<>());
        setDatos(new ArrayList<>());
    }

}
