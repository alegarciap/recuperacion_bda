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
 * Clase que representa a un participante de tipo docente en el sistema. Hereda
 * de la clase Participante y añade atributos específicos de los docentes.
 *
 * @author Alejandra García Preciado - 252444
 */
@Entity
@Table(name = "participantes_docentes")
public class ParticipanteDocente extends Participante {

    /**
     * Departamento al que pertenece el docente.
     */
    @Column(name = "departamento", nullable = false)
    private String departamento;

    /**
     * Constructor por defecto requerido por JPA.
     */
    public ParticipanteDocente() {
        super();
    }

    /**
     * Constructor con todos los atributos excepto el ID.
     *
     * @param nombre Nombre del docente
     * @param apellidoPaterno Apellido paterno del docente
     * @param apellidoMaterno Apellido materno del docente
     * @param correo Correo electrónico del docente
     * @param cantidadAsistencias Cantidad de asistencias acumuladas
     * @param inscripciones Lista de inscripciones del docente
     * @param departamento Departamento al que pertenece
     */
    public ParticipanteDocente(String nombre, String apellidoPaterno, String apellidoMaterno, String correo, Integer cantidadAsistencias, List<Inscripcion> inscripciones, String departamento) {
        super(nombre, apellidoPaterno, apellidoMaterno, correo, cantidadAsistencias, inscripciones);
        this.departamento = departamento;
    }

    /**
     * Constructor con todos los atributos incluido el ID.
     *
     * @param id Identificador único del docente
     * @param nombre Nombre del docente
     * @param apellidoPaterno Apellido paterno del docente
     * @param apellidoMaterno Apellido materno del docente
     * @param correo Correo electrónico del docente
     * @param cantidadAsistencias Cantidad de asistencias acumuladas
     * @param inscripciones Lista de inscripciones del docente
     * @param departamento Departamento al que pertenece
     */
    public ParticipanteDocente(Long id, String nombre, String apellidoPaterno, String apellidoMaterno, String correo, Integer cantidadAsistencias, List<Inscripcion> inscripciones, String departamento) {
        super(id, nombre, apellidoPaterno, apellidoMaterno, correo, cantidadAsistencias, inscripciones);
        this.departamento = departamento;
    }

    /**
     * Obtiene el departamento al que pertenece el docente.
     *
     * @return El departamento
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Establece el departamento al que pertenece el docente.
     *
     * @param departamento El departamento a establecer
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "ParticipanteDocente{" + "departamento=" + departamento + '}';
    }

}