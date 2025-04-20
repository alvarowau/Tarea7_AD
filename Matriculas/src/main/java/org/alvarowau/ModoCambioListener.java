package org.alvarowau;

import java.util.EventListener;

/**
 * Interfaz que define el contrato para los listeners que deben responder
 * a eventos de cambio de modo en la aplicación.
 * <p>
 * Las clases que implementen esta interfaz podrán ser notificadas cuando
 * ocurra un cambio de modo en el sistema.
 *
 * @author Álvaro 
 * @version 1.0
 * @see java.util.EventListener
 * @see ModoCambioEvent
 */
public interface ModoCambioListener extends EventListener {
    /**
     * Método invocado cuando se produce un cambio de modo en la aplicación.
     *
     * @param e El objeto evento que contiene información sobre el cambio de modo
     * @see ModoCambioEvent
     */
    void modoCambiado(ModoCambioEvent e);
}