/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.time.LocalDateTime;

/**
 * DTO que representa una inscripción de un participante a una actividad.
 * Utilizado para transferir información de inscripciones entre capas sin
 * exponer directamente las entidades.
 *
 * @author Alejandra García Preciado - 252444
 */
public class InscripcionDTO {

    /**
     * Fecha y hora en que se realizó la inscripción.
     */
    private LocalDateTime fechaHora;

    /**
     * Estado de asistencia del participante.
     */
    private String estadoAsistencia;

    /**
     * Nombre completo del participante.
     */
    private String nombreParticipante;

    /**
     * Correo electrónico del participante.
     */
    private String correoParticipante;

    /**
     * Tipo de participante (estudiante, docente, externo).
     */
    private String tipoParticipante;

    /**
     * Nombre de la actividad.
     */
    private String nombreActividad;

    /**
     * Nombre del evento al que pertenece la actividad.
     */
    private String nombreEvento;

    /**
     * Constructor por defecto.
     */
    public InscripcionDTO() {
    }

    /**
     * Constructor con los atributos principales.
     *
     * @param fechaHora Fecha y hora de la inscripción
     * @param estadoAsistencia Estado de asistencia
     * @param nombreParticipante Nombre completo del participante
     * @param nombreActividad Nombre de la actividad
     */
    public InscripcionDTO(LocalDateTime fechaHora, String estadoAsistencia,
            String nombreParticipante, String nombreActividad) {
        this.fechaHora = fechaHora;
        this.estadoAsistencia = estadoAsistencia;
        this.nombreParticipante = nombreParticipante;
        this.nombreActividad = nombreActividad;
    }

    /**
     * Constructor completo con todos los atributos.
     *
     * @param fechaHora Fecha y hora de la inscripción
     * @param estadoAsistencia Estado de asistencia
     * @param nombreParticipante Nombre completo del participante
     * @param correoParticipante Correo electrónico del participante
     * @param tipoParticipante Tipo de participante
     * @param nombreActividad Nombre de la actividad
     * @param nombreEvento Nombre del evento
     */
    public InscripcionDTO(LocalDateTime fechaHora, String estadoAsistencia,
            String nombreParticipante, String correoParticipante,
            String tipoParticipante, String nombreActividad, String nombreEvento) {
        this.fechaHora = fechaHora;
        this.estadoAsistencia = estadoAsistencia;
        this.nombreParticipante = nombreParticipante;
        this.correoParticipante = correoParticipante;
        this.tipoParticipante = tipoParticipante;
        this.nombreActividad = nombreActividad;
        this.nombreEvento = nombreEvento;
    }

    // Getters y Setters
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
    public String getEstadoAsistencia() {
        return estadoAsistencia;
    }

    /**
     * Establece el estado de asistencia del participante.
     *
     * @param estadoAsistencia El estado de asistencia a establecer
     */
    public void setEstadoAsistencia(String estadoAsistencia) {
        this.estadoAsistencia = estadoAsistencia;
    }

    /**
     * Obtiene el nombre completo del participante.
     *
     * @return El nombre del participante
     */
    public String getNombreParticipante() {
        return nombreParticipante;
    }

    /**
     * Establece el nombre completo del participante.
     *
     * @param nombreParticipante El nombre del participante a establecer
     */
    public void setNombreParticipante(String nombreParticipante) {
        this.nombreParticipante = nombreParticipante;
    }

    /**
     * Obtiene el correo electrónico del participante.
     *
     * @return El correo del participante
     */
    public String getCorreoParticipante() {
        return correoParticipante;
    }

    /**
     * Establece el correo electrónico del participante.
     *
     * @param correoParticipante El correo del participante a establecer
     */
    public void setCorreoParticipante(String correoParticipante) {
        this.correoParticipante = correoParticipante;
    }

    /**
     * Obtiene el tipo de participante.
     *
     * @return El tipo de participante
     */
    public String getTipoParticipante() {
        return tipoParticipante;
    }

    /**
     * Establece el tipo de participante.
     *
     * @param tipoParticipante El tipo de participante a establecer
     */
    public void setTipoParticipante(String tipoParticipante) {
        this.tipoParticipante = tipoParticipante;
    }

    /**
     * Obtiene el nombre de la actividad.
     *
     * @return El nombre de la actividad
     */
    public String getNombreActividad() {
        return nombreActividad;
    }

    /**
     * Establece el nombre de la actividad.
     *
     * @param nombreActividad El nombre de la actividad a establecer
     */
    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    /**
     * Obtiene el nombre del evento al que pertenece la actividad.
     *
     * @return El nombre del evento
     */
    public String getNombreEvento() {
        return nombreEvento;
    }

    /**
     * Establece el nombre del evento al que pertenece la actividad.
     *
     * @param nombreEvento El nombre del evento a establecer
     */
    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

}
