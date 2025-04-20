package org.alvarowau;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona las operaciones CRUD de matrículas de alumnos,
 * incluyendo la conexión con la base de datos y notificación de eventos.
 * <p>
 * Proporciona funcionalidad para cargar, añadir y gestionar matrículas,
 * además de implementar un sistema de notificación para cambios de modo.
 *
 * @author Álvaro
 * @version 1.0
 * @see Matricula
 * @see ModoCambioListener
 * @see ModoCambioEvent
 */
public class GestorMatriculas {
    private List<Matricula> listaMatriculas;
    private Matricula actual;
    private final List<ModoCambioListener> listeners;

    /**
     * Constructor que inicializa las listas de matrículas y listeners.
     */
    public GestorMatriculas() {
        listaMatriculas = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    /**
     * Añade un listener para ser notificado de cambios de modo.
     *
     * @param listener Objeto que implementa ModoCambioListener a registrar
     */
    public void addModoCambioListener(ModoCambioListener listener) {
        listeners.add(listener);
    }

    /**
     * Elimina un listener registrado previamente.
     *
     * @param listener Objeto listener a eliminar
     */
    public void removeModoCambioListener(ModoCambioListener listener) {
        listeners.remove(listener);
    }

    /**
     * Notifica a todos los listeners registrados sobre un cambio de modo.
     *
     * @param modo Nuevo modo que se ha establecido ("individual" o "todos")
     */
    private void fireModoCambioEvent(String modo) {
        ModoCambioEvent event = new ModoCambioEvent(this, modo);
        for (ModoCambioListener listener : listeners) {
            listener.modoCambiado(event);
        }
    }

    /**
     * Selecciona una matrícula de la lista por su índice.
     *
     * @param i Índice de la matrícula a seleccionar
     */
    public void seleccionarFila(int i) {
        if (i >= 0 && i < listaMatriculas.size()) {
            actual = listaMatriculas.get(i);
        }
    }

    /**
     * Carga todas las matrículas asociadas a un DNI desde la base de datos.
     *
     * @param dni DNI del alumno cuyas matrículas se quieren cargar
     */
    public void recargarDNI(String dni) {
        listaMatriculas.clear();
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM matriculas WHERE DNI = ?")) {
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listaMatriculas.add(new Matricula(
                        rs.getString("DNI"),
                        rs.getString("NombreModulo"),
                        rs.getString("Curso"),
                        rs.getDouble("Nota")
                ));
            }
            fireModoCambioEvent("individual");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Añade la matrícula actual a la base de datos y a la lista local.
     */
    public void addMatricula() {
        if (actual != null) {
            try (Connection conn = conectar();
                 PreparedStatement stmt = conn.prepareStatement(
                         "INSERT INTO matriculas (DNI, NombreModulo, Curso, Nota) VALUES (?, ?, ?, ?)")) {
                stmt.setString(1, actual.getDni());
                stmt.setString(2, actual.getNombreModulo());
                stmt.setString(3, actual.getCurso());
                stmt.setDouble(4, actual.getNota());
                stmt.executeUpdate();
                listaMatriculas.add(actual);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Carga todas las matrículas existentes en la base de datos.
     */
    public void cargarTodas() {
        listaMatriculas.clear();
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM matriculas")) {
            while (rs.next()) {
                listaMatriculas.add(new Matricula(
                        rs.getString("DNI"),
                        rs.getString("NombreModulo"),
                        rs.getString("Curso"),
                        rs.getDouble("Nota")
                ));
            }
            fireModoCambioEvent("todos");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene la lista completa de matrículas cargadas.
     *
     * @return Lista de objetos Matricula
     */
    public List<Matricula> getListaMatriculas() {
        return listaMatriculas;
    }

    /**
     * Obtiene la matrícula actualmente seleccionada.
     *
     * @return Matricula seleccionada o null si no hay ninguna
     */
    public Matricula getActual() {
        return actual;
    }

    /**
     * Establece la matrícula actual.
     *
     * @param actual Matrícula a establecer como actual
     */
    public void setActual(Matricula actual) {
        this.actual = actual;
    }

    /**
     * Establece conexión con la base de datos.
     *
     * @return Conexión a la base de datos
     * @throws SQLException Si ocurre algún error al conectar
     */
    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/alumnos?useUnicode=true&characterEncoding=UTF-8",
                "dam7", "ad07"
        );
    }
}