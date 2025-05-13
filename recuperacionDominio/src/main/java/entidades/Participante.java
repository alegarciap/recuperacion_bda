/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * Clase que representa a un participante en el sistema de gestión de eventos
 * académicos. Esta es la clase base para los diferentes tipos de participantes:
 * estudiantes, docentes y externos.
 *
 * @author Alejandra García Preciado - 252444
 */
@Entity
@Table(name = "participantes")
@Inheritance(strategy = InheritanceType.JOINED)
public class Participante implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único del participante.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Nombre del participante.
     */
    @Column(name = "nombre", nullable = false)
    private String nombre;

    /**
     * Apellido paterno del participante.
     */
    @Column(name = "apellido_paterno", nullable = false)
    private String apellidoPaterno;

    /**
     * Apellido materno del participante.
     */
    @Column(name = "apellido_materno", nullable = false)
    private String apellidoMaterno;

    /**
     * Correo electrónico del participante, debe ser único.
     */
    @Column(name = "correo", nullable = false, unique = true)
    private String correo;

    /**
     * Contador de asistencias acumuladas por el participante.
     */
    @Column(name = "cantidad_asistencias")
    private Integer cantidadAsistencias;

    /**
     * Lista de inscripciones a actividades realizadas por este participante.
     */
    @OneToMany(mappedBy = "participante", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Inscripcion> inscripciones;

    /**
     * Constructor por defecto requerido por JPA.
     */
    public Participante() {
        this.inscripciones = new ArrayList<>();
        this.cantidadAsistencias = 0;
    }

    /**
     * Constructor con todos los atributos excepto el ID.
     *
     * @param nombre Nombre del participante
     * @param apellidoPaterno Apellido paterno del participante
     * @param apellidoMaterno Apellido materno del participante
     * @param correo Correo electrónico del participante
     * @param cantidadAsistencias Cantidad de asistencias acumuladas
     * @param inscripciones Lista de inscripciones del participante
     */
    public Participante(String nombre, String apellidoPaterno, String apellidoMaterno, String correo, Integer cantidadAsistencias, List<Inscripcion> inscripciones) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.cantidadAsistencias = cantidadAsistencias;
        this.inscripciones = inscripciones != null ? inscripciones : new ArrayList<>();
    }

    /**
     * Constructor con todos los atributos incluido el ID.
     *
     * @param id Identificador único del participante
     * @param nombre Nombre del participante
     * @param apellidoPaterno Apellido paterno del participante
     * @param apellidoMaterno Apellido materno del participante
     * @param correo Correo electrónico del participante
     * @param cantidadAsistencias Cantidad de asistencias acumuladas
     * @param inscripciones Lista de inscripciones del participante
     */
    public Participante(Long id, String nombre, String apellidoPaterno, String apellidoMaterno, String correo, Integer cantidadAsistencias, List<Inscripcion> inscripciones) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.cantidadAsistencias = cantidadAsistencias;
        this.inscripciones = inscripciones != null ? inscripciones : new ArrayList<>();
    }

    /**
     * Obtiene el identificador único del participante.
     *
     * @return El ID del participante
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del participante.
     *
     * @param id El ID a establecer
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del participante.
     *
     * @return El nombre del participante
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del participante.
     *
     * @param nombre El nombre a establecer
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido paterno del participante.
     *
     * @return El apellido paterno
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Establece el apellido paterno del participante.
     *
     * @param apellidoPaterno El apellido paterno a establecer
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Obtiene el apellido materno del participante.
     *
     * @return El apellido materno
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Establece el apellido materno del participante.
     *
     * @param apellidoMaterno El apellido materno a establecer
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Obtiene el correo electrónico del participante.
     *
     * @return El correo electrónico
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del participante.
     *
     * @param correo El correo a establecer
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene la cantidad de asistencias registradas del participante.
     *
     * @return El número de asistencias
     */
    public Integer getCantidadAsistencias() {
        return cantidadAsistencias;
    }

    /**
     * Establece la cantidad de asistencias del participante.
     *
     * @param cantidadAsistencias El número de asistencias a establecer
     */
    public void setCantidadAsistencias(Integer cantidadAsistencias) {
        this.cantidadAsistencias = cantidadAsistencias;
    }

    /**
     * Obtiene la lista de inscripciones del participante.
     *
     * @return Lista de inscripciones
     */
    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    /**
     * Establece la lista de inscripciones del participante.
     *
     * @param inscripciones Lista de inscripciones a establecer
     */
    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    @Override
    public String toString() {
        return "Participante{" + "id=" + id + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", correo=" + correo + ", cantidadAsistencias=" + cantidadAsistencias + ", inscripciones=" + inscripciones + '}';
    }

}