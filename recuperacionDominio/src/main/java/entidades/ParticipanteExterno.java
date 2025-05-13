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
 * Clase que representa a un participante externo en el sistema. Hereda de la
 * clase Participante y añade atributos específicos para externos, como la
 * institución a la que pertenecen.
 *
 * @author Alejandra García Preciado - 252444
 */
@Entity
@Table(name = "participantes_externos")
public class ParticipanteExterno extends Participante {

    /**
     * Institución a la que pertenece el participante externo.
     */
    @Column(name = "institucion")
    private String institucion;

    /**
     * Constructor por defecto requerido por JPA.
     */
    public ParticipanteExterno() {
        super();
    }

    /**
     * Constructor con todos los atributos excepto el ID.
     *
     * @param nombre Nombre del participante externo
     * @param apellidoPaterno Apellido paterno del participante externo
     * @param apellidoMaterno Apellido materno del participante externo
     * @param correo Correo electrónico del participante externo
     * @param cantidadAsistencias Cantidad de asistencias acumuladas
     * @param inscripciones Lista de inscripciones del participante externo
     * @param institucion Institución a la que pertenece
     */
    public ParticipanteExterno(String nombre, String apellidoPaterno, String apellidoMaterno, String correo, Integer cantidadAsistencias, List<Inscripcion> inscripciones, String institucion) {
        super(nombre, apellidoPaterno, apellidoMaterno, correo, cantidadAsistencias, inscripciones);
        this.institucion = institucion;
    }

    /**
     * Constructor con todos los atributos incluido el ID.
     *
     * @param id Identificador único del participante externo
     * @param nombre Nombre del participante externo
     * @param apellidoPaterno Apellido paterno del participante externo
     * @param apellidoMaterno Apellido materno del participante externo
     * @param correo Correo electrónico del participante externo
     * @param cantidadAsistencias Cantidad de asistencias acumuladas
     * @param inscripciones Lista de inscripciones del participante externo
     * @param institucion Institución a la que pertenece
     */
    public ParticipanteExterno(Long id, String nombre, String apellidoPaterno, String apellidoMaterno, String correo, Integer cantidadAsistencias, List<Inscripcion> inscripciones, String institucion) {
        super(id, nombre, apellidoPaterno, apellidoMaterno, correo, cantidadAsistencias, inscripciones);
        this.institucion = institucion;
    }

    /**
     * Obtiene la institución a la que pertenece el participante externo.
     *
     * @return La institución
     */
    public String getInstitucion() {
        return institucion;
    }

    /**
     * Establece la institución a la que pertenece el participante externo.
     *
     * @param institucion La institución a establecer
     */
    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    @Override
    public String toString() {
        return "ParticipanteExterno{" + "institucion=" + institucion + '}';
    }

}