package co.edu.uco.fink.api.controller;


import co.edu.uco.fink.api.response.RegistroEstadoResponse;
import co.edu.uco.fink.business.fachade.concrete.CrearRegistroEstadoFachadaImpl;
import co.edu.uco.fink.business.fachade.concrete.ObtenerRegistrosEstadosFachadaImpl;
import co.edu.uco.fink.crosscutting.exception.FinKException;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.data.CodigoMensaje;
import co.edu.uco.fink.crosscutting.helpers.TextHelper;
import co.edu.uco.fink.dto.animales.RegistroEstadoAnimalDTO;
import co.edu.uco.fink.dto.fincas.FincaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/registroEstado")
public final class RegistroEstadoController {

    @GetMapping("/dummy")
    public RegistroEstadoAnimalDTO dummy(){
        return RegistroEstadoAnimalDTO.Build();
    }

    @PostMapping("/obtenerEstados")
    public ResponseEntity<RegistroEstadoResponse> obtener(@RequestBody FincaDTO finca){
        var httpStatusCode = HttpStatus.ACCEPTED;
        var registroEstadoResponse = new RegistroEstadoResponse();

        try {
            var fachade = new ObtenerRegistrosEstadosFachadaImpl();
            registroEstadoResponse.setDatos(fachade.ejeciutar(finca));
        } catch (final FinKException exception){
            exception.printStackTrace();
            registroEstadoResponse.getMensajes().add(exception.getMensajeUsuario());
            httpStatusCode = HttpStatus.BAD_REQUEST;
        } catch (final Exception exception){
            exception.printStackTrace();
            registroEstadoResponse.getMensajes().add(TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000046), "la pagina"));
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(registroEstadoResponse, httpStatusCode);
    }


    @PostMapping("/actualizarEstado")
    public ResponseEntity<RegistroEstadoResponse> crear(@RequestBody RegistroEstadoAnimalDTO registroEstadoAnimal){

        var httpStatusCode = HttpStatus.ACCEPTED;
        var registroEstadoResponse = new RegistroEstadoResponse();

        try {
            var fachade = new CrearRegistroEstadoFachadaImpl();
            fachade.ejecutar(registroEstadoAnimal);
            registroEstadoResponse.getMensajes().add(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000035));
        } catch (FinKException exception){

            httpStatusCode = HttpStatus.BAD_REQUEST;
            registroEstadoResponse.getMensajes().add(exception.getMensajeUsuario());
            exception.printStackTrace();
        }
        catch (Exception exception){
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000036);
            registroEstadoResponse.getMensajes().add(mensajeUsuario);
            exception.printStackTrace();
        }

        return new ResponseEntity<>(registroEstadoResponse, httpStatusCode);

    }
}
