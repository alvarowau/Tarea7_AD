package org.alvarowau;

/**
 * Clase que representa una matrícula de un alumno en un módulo educativo.
 * Contiene información sobre el alumno, el módulo, el curso académico y la nota obtenida.
 *
 * @author Álvaro
 * @version 1.0
 */
public class Matricula {
    private String dni;
    private String nombreModulo;
    private String curso;
    private double nota;

    /**
     * Constructor que crea una nueva matrícula con todos los datos necesarios.
     *
     * @param dni Documento Nacional de Identidad del alumno
     * @param nombreModulo Nombre del módulo en el que se matricula el alumno
     * @param curso Curso académico en el que se realiza la matrícula
     * @param nota Calificación obtenida por el alumno en el módulo
     */
    public Matricula(String dni, String nombreModulo, String curso, double nota) {
        this.dni = dni;
        this.nombreModulo = nombreModulo;
        this.curso = curso;
        this.nota = nota;
    }

    /**
     * Obtiene el DNI del alumno matriculado.
     *
     * @return DNI del alumno
     */
    public String getDni() { return dni; }

    /**
     * Establece el DNI del alumno matriculado.
     *
     * @param dni Nuevo DNI del alumno
     */
    public void setDni(String dni) { this.dni = dni; }

    /**
     * Obtiene el nombre del módulo en el que está matriculado el alumno.
     *
     * @return Nombre del módulo
     */
    public String getNombreModulo() { return nombreModulo; }

    /**
     * Establece el nombre del módulo en el que está matriculado el alumno.
     *
     * @param nombreModulo Nuevo nombre del módulo
     */
    public void setNombreModulo(String nombreModulo) { this.nombreModulo = nombreModulo; }

    /**
     * Obtiene el curso académico de la matrícula.
     *
     * @return Curso académico
     */
    public String getCurso() { return curso; }

    /**
     * Establece el curso académico de la matrícula.
     *
     * @param curso Nuevo curso académico
     */
    public void setCurso(String curso) { this.curso = curso; }

    /**
     * Obtiene la nota del alumno en el módulo matriculado.
     *
     * @return Nota obtenida
     */
    public double getNota() { return nota; }

    /**
     * Establece la nota del alumno en el módulo matriculado.
     *
     * @param nota Nueva nota del alumno
     */
    public void setNota(double nota) { this.nota = nota; }
}