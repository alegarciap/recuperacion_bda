/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * Clase que representa la inscripción de un participante a una actividad
 * específica. Almacena la información de la inscripción y el estado de
 * asistencia. Esta clase implementa la relación muchos a muchos entre
 * Participante y Actividad.
 *
 * @author Alejandra García Preciado - 252444
 */
@Entity
@Table(name = "inscripciones", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"actividad_id", "participante_id"})
})
public class Inscripcion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único de la inscripción.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Fecha y hora en que se realizó la inscripción.
     */
    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    /**
     * Estado de asistencia del participante (ASISTIO, NO_ASISTIO).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_asistencia")
    private EstadoAsistencia estadoAsistencia;

    /**
     * Participante que se inscribió a la actividad.
     */
    @ManyToOne
    @JoinColumn(name = "participante_id", nullable = false)
    private Participante participante;

    /**
     * Actividad a la que se inscribió el participante.
     */
    @ManyToOne
    @JoinColumn(name = "actividad_id", nullable = false)
    private Actividad actividad;

    /**
     * Enumeración para los posibles estados de asistencia de un participante.
     */
    public enum EstadoAsistencia {
        /**
         * El participante asistió a la actividad.
         */
        ASISTIO,
        /**
         * El participante no asistió a la actividad.
         */
        NO_ASISTIO
    }

    /**
     * Constructor por defecto requerido por JPA.
     */
    public Inscripcion() {
        this.fechaHora = LocalDateTime.now();
    }

    /**
     * Constructor con todos los atributos excepto el ID.
     *
     * @param fechaHora Fecha y hora de la inscripción
     * @param estadoAsistencia Estado de asistencia
     * @param participante Participante inscrito
     * @param actividad Actividad a la que se inscribió
     */
    public Inscripcion(LocalDateTime fechaHora, EstadoAsistencia estadoAsistencia,
            Participante participante, Actividad actividad) {
        this.fechaHora = fechaHora != null ? fechaHora : LocalDateTime.now();
        this.estadoAsistencia = estadoAsistencia;
        this.participante = participante;
        this.actividad = actividad;
    }

    /**
     * Constructor con todos los atributos incluido el ID.
     *
     * @param id Identificador único de la inscripción
     * @param fechaHora Fecha y hora de la inscripción
     * @param estadoAsistencia Estado de asistencia
     * @param participante Participante inscrito
     * @param actividad Actividad a la que se inscribió
     */
    public Inscripcion(Long id, LocalDateTime fechaHora, EstadoAsistencia estadoAsistencia,
            Participante participante, Actividad actividad) {
        this.id = id;
        this.fechaHora = fechaHora != null ? fechaHora : LocalDateTime.now();
        this.estadoAsistencia = estadoAsistencia;
        this.participante = participante;
        this.actividad = actividad;
    }

    /**
     * Obtiene el identificador único de la inscripción.
     *
     * @return El ID de la inscripción
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único de la inscripción.
     *
     * @param id El ID a establecer
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha y hora en que se realizó la inscripción.
     *
     * @return La fecha y hora
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Establece la fecha y hora en que se realizó la inscripción.
     *
     * @param fechaHora La fecha y hora a establecer
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Obtiene el estado de asistencia del participante.
     *
     * @return El estado de asistencia
     */
    public EstadoAsistencia getEstadoAsistencia() {
        return estadoAsistencia;
    }

    /**
     * Establece el estado de asistencia del participante.
     *
     * @param estadoAsistencia El estado de asistencia a establecer
     */
    public void setEstadoAsistencia(EstadoAsistencia estadoAsistencia) {
        this.estadoAsistencia = estadoAsistencia;
    }

    /**
     * Obtiene el participante inscrito.
     *
     * @return El participante
     */
    public Participante getParticipante() {
        return participante;
    }

    /**
     * Establece el participante inscrito.
     *
     * @param participante El participante a establecer
     */
    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    /**
     * Obtiene la actividad a la que se inscribió el participante.
     *
     * @return La actividad
     */
    public Actividad getActividad() {
        return actividad;
    }

    /**
     * Establece la actividad a la que se inscribió el participante.
     *
     * @param actividad La actividad a establecer
     */
    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    @Override
    public String toString() {
        return "Inscripcion{" + "id=" + id + ", fechaHora=" + fechaHora + ", estadoAsistencia=" + estadoAsistencia + ", participante=" + participante + ", actividad=" + actividad + '}';
    }

}