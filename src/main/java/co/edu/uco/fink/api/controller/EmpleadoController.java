package co.edu.uco.fink.api.controller;


import co.edu.uco.fink.api.response.EmpleadoResponse;
import co.edu.uco.fink.business.fachade.concrete.VerificarEmpleadoFachadaimpl;
import co.edu.uco.fink.crosscutting.exception.FinKException;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.data.CodigoMensaje;
import co.edu.uco.fink.dto.fincas.EmpleadoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/empleado")
public final class EmpleadoController {

    @GetMapping("/dummy")
    public EmpleadoDTO dummy(){
        return EmpleadoDTO.build();
    }

    @PostMapping("/login")
    public ResponseEntity<EmpleadoResponse> verificar(@RequestBody EmpleadoDTO empleado){
        EmpleadoResponse empleadoResponse = EmpleadoResponse.build();
        var httpStatusCode = HttpStatus.ACCEPTED;

        try {
            final VerificarEmpleadoFachadaimpl fachada = new VerificarEmpleadoFachadaimpl();
            final var resultado = new ArrayList<EmpleadoDTO>();
            resultado.add(fachada.ejecutar(empleado));
            empleadoResponse.setDatos(resultado);
            empleadoResponse.getMensajes().add(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000037));
        } catch (FinKException exception){

            httpStatusCode = HttpStatus.BAD_REQUEST;
            empleadoResponse.getMensajes().add(exception.getMensajeUsuario());
            exception.printStackTrace();
        }
        catch (Exception exception){
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000038);
            empleadoResponse.getMensajes().add(mensajeUsuario);
            exception.printStackTrace();
        }

        return new ResponseEntity<>(empleadoResponse, httpStatusCode);
    }
}