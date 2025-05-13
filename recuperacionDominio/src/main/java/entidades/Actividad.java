/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * Clase que representa una actividad específica dentro de un evento académico.
 * Contiene información detallada de la actividad y sus relaciones con eventos,
 * lugares e inscripciones. Cada actividad pertenece a un evento y se realiza en
 * un lugar específico.
 *
 * @author Alejandra García Preciado - 252444
 */
@Entity
@Table(name = "actividades", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nombre", "evento_id"})
})
public class Actividad implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único de la actividad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Nombre de la actividad.
     */
    @Column(name = "nombre", nullable = false)
    private String nombre;

    /**
     * Tipo de actividad (conferencia, taller, panel, etc.).
     */
    @Column(name = "tipo", nullable = false)
    private String tipo;

    /**
     * Fecha y hora de inicio de la actividad.
     */
    @Column(name = "fecha_hora_inicio", nullable = false)
    private LocalDateTime fechaHoraInicio;

    /**
     * Capacidad máxima de participantes.
     */
    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;

    /**
     * Duración estimada en minutos.
     */
    @Column(name = "duracion", nullable = false)
    private Integer duracion;

    /**
     * Indica si la actividad ha sido finalizada.
     */
    @Column(name = "finalizado")
    private Boolean finalizado;

    /**
     * Evento al que pertenece esta actividad.
     */
    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    /**
     * Lugar donde se realizará la actividad.
     */
    @ManyToOne
    @JoinColumn(name = "lugar_id", nullable = false)
    private Lugar lugar;

    /**
     * Lista de inscripciones de participantes a esta actividad.
     */
    @OneToMany(mappedBy = "actividad", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Inscripcion> inscripciones;

    /**
     * Constructor por defecto requerido por JPA.
     */
    public Actividad() {
        this.inscripciones = new ArrayList<>();
        this.finalizado = false;
    }

    /**
     * Constructor con todos los atributos excepto el ID.
     *
     * @param nombre Nombre de la actividad
     * @param tipo Tipo de actividad
     * @param fechaHoraInicio Fecha y hora de inicio
     * @param capacidad Capacidad máxima de participantes
     * @param duracion Duración estimada en minutos
     * @param finalizado Indica si la actividad está finalizada
     * @param evento Evento al que pertenece
     * @param lugar Lugar donde se realizará
     * @param inscripciones Lista de inscripciones
     */
    public Actividad(String nombre, String tipo, LocalDateTime fechaHoraInicio,
            Integer capacidad, Integer duracion, Boolean finalizado,
            Evento evento, Lugar lugar, List<Inscripcion> inscripciones) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.fechaHoraInicio = fechaHoraInicio;
        this.capacidad = capacidad;
        this.duracion = duracion;
        this.finalizado = finalizado != null ? finalizado : false;
        this.evento = evento;
        this.lugar = lugar;
        this.inscripciones = inscripciones != null ? inscripciones : new ArrayList<>();
    }

    /**
     * Constructor con todos los atributos incluido el ID.
     *
     * @param id Identificador único de la actividad
     * @param nombre Nombre de la actividad
     * @param tipo Tipo de actividad
     * @param fechaHoraInicio Fecha y hora de inicio
     * @param capacidad Capacidad máxima de participantes
     * @param duracion Duración estimada en minutos
     * @param finalizado Indica si la actividad está finalizada
     * @param evento Evento al que pertenece
     * @param lugar Lugar donde se realizará
     * @param inscripciones Lista de inscripciones
     */
    public Actividad(Long id, String nombre, String tipo, LocalDateTime fechaHoraInicio,
            Integer capacidad, Integer duracion, Boolean finalizado,
            Evento evento, Lugar lugar, List<Inscripcion> inscripciones) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.fechaHoraInicio = fechaHoraInicio;
        this.capacidad = capacidad;
        this.duracion = duracion;
        this.finalizado = finalizado != null ? finalizado : false;
        this.evento = evento;
        this.lugar = lugar;
        this.inscripciones = inscripciones != null ? inscripciones : new ArrayList<>();
    }

    /**
     * Obtiene el identificador único de la actividad.
     *
     * @return El ID de la actividad
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único de la actividad.
     *
     * @param id El ID a establecer
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la actividad.
     *
     * @return El nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la actividad.
     *
     * @param nombre El nombre a establecer
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el tipo de actividad.
     *
     * @return El tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de actividad.
     *
     * @param tipo El tipo a establecer
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene la fecha y hora de inicio de la actividad.
     *
     * @return La fecha y hora de inicio
     */
    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    /**
     * Establece la fecha y hora de inicio de la actividad.
     *
     * @param fechaHoraInicio La fecha y hora a establecer
     */
    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    /**
     * Obtiene la capacidad máxima de participantes.
     *
     * @return La capacidad
     */
    public Integer getCapacidad() {
        return capacidad;
    }

    /**
     * Establece la capacidad máxima de participantes.
     *
     * @param capacidad La capacidad a establecer
     */
    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * Obtiene la duración estimada en minutos.
     *
     * @return La duración
     */
    public Integer getDuracion() {
        return duracion;
    }

    /**
     * Establece la duración estimada en minutos.
     *
     * @param duracion La duración a establecer
     */
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    /**
     * Verifica si la actividad está finalizada.
     *
     * @return true si la actividad está finalizada, false en caso contrario
     */
    public Boolean getFinalizado() {
        return finalizado;
    }

    /**
     * Establece si la actividad está finalizada.
     *
     * @param finalizado El estado de finalización a establecer
     */
    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    /**
     * Obtiene el evento al que pertenece la actividad.
     *
     * @return El evento
     */
    public Evento getEvento() {
        return evento;
    }

    /**
     * Establece el evento al que pertenece la actividad.
     *
     * @param evento El evento a establecer
     */
    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    /**
     * Obtiene el lugar donde se realizará la actividad.
     *
     * @return El lugar
     */
    public Lugar getLugar() {
        return lugar;
    }

    /**
     * Establece el lugar donde se realizará la actividad.
     *
     * @param lugar El lugar a establecer
     */
    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    /**
     * Obtiene la lista de inscripciones a la actividad.
     *
     * @return Lista de inscripciones
     */
    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    /**
     * Establece la lista de inscripciones a la actividad.
     *
     * @param inscripciones Lista de inscripciones a establecer
     */
    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    @Override
    public String toString() {
        return "Actividad{" + "id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", fechaHoraInicio=" + fechaHoraInicio + ", capacidad=" + capacidad + ", duracion=" + duracion + ", finalizado=" + finalizado + ", evento=" + evento + ", lugar=" + lugar + ", inscripciones=" + inscripciones + '}';
    }

}