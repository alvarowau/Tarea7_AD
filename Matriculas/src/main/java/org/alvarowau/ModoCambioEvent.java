package org.alvarowau;

import java.util.EventObject;

/**
 * Clase que representa un evento de cambio de modo en la aplicación.
 * Extiende de EventObject para proporcionar funcionalidad básica de eventos.
 *
 * @author Álvaro
 * @version 1.0
 * @see java.util.EventObject
 */
public class ModoCambioEvent extends EventObject {
    private final String modo;

    /**
     * Constructor que crea un nuevo evento de cambio de modo.
     *
     * @param source El objeto origen que dispara el evento
     * @param modo El nuevo modo que se ha establecido
     */
    public ModoCambioEvent(Object source, String modo) {
        super(source);
        this.modo = modo;
    }

    /**
     * Obtiene el modo asociado al evento.
     *
     * @return El modo que ha sido establecido
     */
    public String getModo() {
        return modo;
    }
}