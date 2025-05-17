/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase que representa a un participante de tipo estudiante en el sistema.
 * Hereda de la clase Participante y añade atributos específicos de los
 * estudiantes.
 *
 * @author Alejandra García Preciado - 252444
 */
@Entity
@Table(name = "participantes_estudiantes")
public class ParticipanteEstudiante extends Participante {

    /**
     * Número de control del estudiante, debe ser único.
     */
    @Column(name = "numero_control", nullable = false, unique = true)
    private String numeroControl; // String por la encriptación

    /**
     * Carrera que está cursando el estudiante.
     */
    @Column(name = "carrera", nullable = false)
    private String carrera;

    /**
     * Constructor por defecto requerido por JPA.
     */
    public ParticipanteEstudiante() {
        super();
    }

    /**
     * Constructor con todos los atributos excepto el ID.
     *
     * @param nombre Nombre del estudiante
     * @param apellidoPaterno Apellido paterno del estudiante
     * @param apellidoMaterno Apellido materno del estudiante
     * @param correo Correo electrónico del estudiante
     * @param cantidadAsistencias Cantidad de asistencias acumuladas
     * @param inscripciones Lista de inscripciones del estudiante
     * @param numeroControl Número de control único del estudiante
     * @param carrera Carrera que estudia
     */
    public ParticipanteEstudiante(String nombre, String apellidoPaterno, String apellidoMaterno, String correo, Integer cantidadAsistencias, List<Inscripcion> inscripciones, String numeroControl, String carrera) {
        super(nombre, apellidoPaterno, apellidoMaterno, correo, cantidadAsistencias, inscripciones);
        this.numeroControl = numeroControl;
        this.carrera = carrera;
    }

    /**
     * Constructor con todos los atributos incluido el ID.
     *
     * @param id Identificador único del estudiante
     * @param nombre Nombre del estudiante
     * @param apellidoPaterno Apellido paterno del estudiante
     * @param apellidoMaterno Apellido materno del estudiante
     * @param correo Correo electrónico del estudiante
     * @param cantidadAsistencias Cantidad de asistencias acumuladas
     * @param inscripciones Lista de inscripciones del estudiante
     * @param numeroControl Número de control único del estudiante
     * @param carrera Carrera que estudia
     */
    public ParticipanteEstudiante(Long id, String nombre, String apellidoPaterno, String apellidoMaterno, String correo, Integer cantidadAsistencias, List<Inscripcion> inscripciones, String numeroControl, String carrera) {
        super(id, nombre, apellidoPaterno, apellidoMaterno, correo, cantidadAsistencias, inscripciones);
        this.numeroControl = numeroControl;
        this.carrera = carrera;
    }

    /**
     * Obtiene el número de control del estudiante.
     *
     * @return El número de control
     */
    public String getNumeroControl() {
        return numeroControl;
    }

    /**
     * Establece el número de control del estudiante.
     *
     * @param numeroControl El número de control a establecer
     */
    public void setNumeroControl(String numeroControl) {
        this.numeroControl = numeroControl;
    }

    /**
     * Obtiene la carrera que estudia el participante.
     *
     * @return La carrera
     */
    public String getCarrera() {
        return carrera;
    }

    /**
     * Establece la carrera que estudia el participante.
     *
     * @param carrera La carrera a establecer
     */
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return "ParticipanteEstudiante{" + "numeroControl=" + numeroControl + ", carrera=" + carrera + '}';
    }

}