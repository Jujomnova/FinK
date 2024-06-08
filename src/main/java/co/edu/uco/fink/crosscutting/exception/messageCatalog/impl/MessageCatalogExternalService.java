package co.edu.uco.fink.crosscutting.exception.messageCatalog.impl;

import co.edu.uco.fink.crosscutting.exception.messageCatalog.MessageCatalog;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.custom.CrosscuttingFinKException;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.data.CodigoMensaje;
import co.edu.uco.fink.crosscutting.exception.messageCatalog.data.Mensaje;
import co.edu.uco.fink.crosscutting.helpers.ObjectHelper;
import co.edu.uco.fink.crosscutting.helpers.TextHelper;

import java.util.HashMap;
import java.util.Map;

public class MessageCatalogExternalService implements MessageCatalog {

    private final Map<String, Mensaje> mensajes = new HashMap<>();

    @Override
    public final void inicializar() {
        mensajes.clear();
        mensajes.put(CodigoMensaje.M000025.getIdentificador(),
                new Mensaje(CodigoMensaje.M000025, "Se ha intentado asignar un empleado que no está activo  \"${1}\""));
        mensajes.put(CodigoMensaje.M000026.getIdentificador(),
                new Mensaje(CodigoMensaje.M000026, "Se ha presentado un error tratando de realizar un insert de la tarea \"${1}\" en la tabla de \"${2}\" de la base de datos PostgreSQL..."));
        mensajes.put(CodigoMensaje.M000027.getIdentificador(),
                new Mensaje(CodigoMensaje.M000027, "El animal está deshabilitado"));
        mensajes.put(CodigoMensaje.M000028.getIdentificador(),
                new Mensaje(CodigoMensaje.M000028, "Se ha intentado asignar un estado a un animal deshabilitado"));
        mensajes.put(CodigoMensaje.M000029.getIdentificador(),
                new Mensaje(CodigoMensaje.M000029, "El animal ya tiene el estado que se intenta asignar"));
        mensajes.put(CodigoMensaje.M000030.getIdentificador(),
                new Mensaje(CodigoMensaje.M000030, "Se ha intentado asignar un estado a un animal que ya tiene el estado"));
        mensajes.put(CodigoMensaje.M000039.getIdentificador(),
                new Mensaje(CodigoMensaje.M000039, "El numero de documento o constraseña son incorrectos"));
        mensajes.put(CodigoMensaje.M000040.getIdentificador(),
                new Mensaje(CodigoMensaje.M000040, "Se han ingresado credenciales incorrectas en el login"));
        mensajes.put(CodigoMensaje.M000041.getIdentificador(),
                new Mensaje(CodigoMensaje.M000041, "documento invalido"));
        mensajes.put(CodigoMensaje.M000042.getIdentificador(),
                new Mensaje(CodigoMensaje.M000042, "Se ha ingresado un numero de documento invalido"));
        mensajes.put(CodigoMensaje.M000043.getIdentificador(),
                new Mensaje(CodigoMensaje.M000043, "El usuario ingresado está actualmente inactivo, por favor comuniquese con el administrador de su finca para reactivar su usuario"));
        mensajes.put(CodigoMensaje.M000044.getIdentificador(),
                new Mensaje(CodigoMensaje.M000044, "Se ha intentado iniciar sesión con un usuario desactivado"));
    }

    @Override
    public String obtenerContenidoMensaje(final CodigoMensaje codigo, final String... parametros) {
        return obtenerMensaje(codigo, parametros).getContenido();
    }

    @Override
    public final Mensaje obtenerMensaje(CodigoMensaje codigo, final String... parametros) {


        if (ObjectHelper.getObjectHelper().isNUll(codigo)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00001);
            throw new CrosscuttingFinKException(mensajeTecnico, mensajeUsuario);
        }

        if (codigo.isBase()) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00005,
                    codigo.getIdentificador());
            throw new CrosscuttingFinKException(mensajeTecnico, mensajeUsuario);
        }

        if (!mensajes.containsKey(codigo.getIdentificador())) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = TextHelper.replaceParams(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00006), codigo.getIdentificador());

            throw new CrosscuttingFinKException(mensajeTecnico, mensajeUsuario);
        }

        return mensajes.get(codigo.getIdentificador());
    }
}
