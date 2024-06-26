package co.edu.uco.fink.api.controller;

import co.edu.uco.fink.api.response.EstadoResponse;
import co.edu.uco.fink.business.fachade.concrete.ObtenerEstadoFachadaImpl;
import co.edu.uco.fink.crosscutting.exception.FinKException;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.data.CodigoMensaje;
import co.edu.uco.fink.crosscutting.helpers.TextHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/estado")
public final class EstadoController {

    @GetMapping("/obtener")
    public ResponseEntity<EstadoResponse> obtener(){
        var httpStatusCode = HttpStatus.ACCEPTED;
        var estadoResponse = new EstadoResponse();

        try {
            var fachade = new ObtenerEstadoFachadaImpl();
            estadoResponse.setDatos(fachade.ejecutar());
        } catch (final FinKException exception){
            exception.printStackTrace();
            estadoResponse.getMensajes().add(exception.getMensajeUsuario());
            httpStatusCode = HttpStatus.BAD_REQUEST;
        } catch (final Exception exception){
            exception.printStackTrace();
            estadoResponse.getMensajes().add(TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M000046), "la pagina"));
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(estadoResponse, httpStatusCode);
    }
}
