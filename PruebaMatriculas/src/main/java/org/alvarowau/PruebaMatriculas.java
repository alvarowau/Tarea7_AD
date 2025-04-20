package org.alvarowau;

/**
 * Clase de prueba que demuestra el funcionamiento del GestorMatriculas.
 * <p>
 * Esta clase muestra ejemplos de uso de las principales funcionalidades del sistema:
 * <ul>
 *   <li>Carga de todas las matrículas</li>
 *   <li>Filtrado por DNI de alumno</li>
 *   <li>Inserción de nuevas matrículas</li>
 *   <li>Notificación de eventos de cambio de modo</li>
 * </ul>
 *
 * @author Álvaro
 * @version 1.0
 * @see GestorMatriculas
 * @see Matricula
 * @see ModoCambioListener
 */
public class PruebaMatriculas {

    /**
     * Método principal que ejecuta las pruebas de funcionalidad.
     *
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // Creación del gestor de matrículas
        GestorMatriculas gestor = new GestorMatriculas();

        // Registro de un listener para cambios de modo
        gestor.addModoCambioListener(e -> System.out.println("Modo cambiado a: " + e.getModo()));

        // Carga y muestra de todas las matrículas
        gestor.cargarTodas();
        System.out.println("Listado completo de matrículas:");
        for (Matricula m : gestor.getListaMatriculas()) {
            System.out.println(m.getDni() + " - " + m.getNombreModulo() + " - " + m.getCurso() + " - " + m.getNota());
        }

        // Carga y muestra de matrículas de un alumno específico
        gestor.recargarDNI("12345678A");
        System.out.println("\nMatrículas del alumno 12345678A:");
        for (Matricula m : gestor.getListaMatriculas()) {
            System.out.println(m.getDni() + " - " + m.getNombreModulo() + " - " + m.getCurso() + " - " + m.getNota());
        }

        // Creación e inserción de una nueva matrícula
        Matricula nueva = new Matricula("96385274F", "Servicios en Red", "24-25", 9.1);
        gestor.setActual(nueva);
        gestor.addMatricula();
        System.out.println("\nNueva matrícula añadida.");

        // Verificación de la inserción cargando todo de nuevo
        gestor.cargarTodas();
        System.out.println("\nListado actualizado de matrículas:");
        for (Matricula m : gestor.getListaMatriculas()) {
            System.out.println(m.getDni() + " - " + m.getNombreModulo() + " - " + m.getCurso() + " - " + m.getNota());
        }
    }
}