/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.time.LocalDateTime;

/**
 * DTO específico para la creación de nuevos eventos. Contiene solo los campos
 * necesarios para crear un evento, incluyendo la referencia al organizador
 * mediante su ID.
 *
 * @author Alejandra García Preciado - 252444
 */
public class EventoCreacionDTO {

    /**
     * Título del evento.
     */
    private String titulo;

    /**
     * Descripción detallada del evento.
     */
    private String descripcion;

    /**
     * Modalidad del evento (PRESENCIAL, EN_LINEA, HIBRIDO).
     */
    private String modalidad;

    /**
     * Fecha y hora de inicio del evento.
     */
    private LocalDateTime fechaInicio;

    /**
     * Fecha y hora de finalización del evento.
     */
    private LocalDateTime fechaFin;

    /**
     * Observaciones adicionales sobre el evento.
     */
    private String observaciones;

    /**
     * ID del organizador responsable.
     */
    private Long organizadorId;

    /**
     * Constructor por defecto.
     */
    public EventoCreacionDTO() {
    }

    /**
     * Constructor con todos los atributos.
     *
     * @param titulo Título del evento
     * @param descripcion Descripción detallada
     * @param modalidad Modalidad del evento
     * @param fechaInicio Fecha y hora de inicio
     * @param fechaFin Fecha y hora de finalización
     * @param observaciones Observaciones adicionales
     * @param organizadorId ID del organizador responsable
     */
    public EventoCreacionDTO(String titulo, String descripcion, String modalidad,
            LocalDateTime fechaInicio, LocalDateTime fechaFin,
            String observaciones, Long organizadorId) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.modalidad = modalidad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.observaciones = observaciones;
        this.organizadorId = organizadorId;
    }
    
    /**
     * Obtiene el título del evento.
     *
     * @return El título
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título del evento.
     *
     * @param titulo El título a establecer
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene la descripción detallada del evento.
     *
     * @return La descripción
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción detallada del evento.
     *
     * @param descripcion La descripción a establecer
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la modalidad del evento.
     *
     * @return La modalidad
     */
    public String getModalidad() {
        return modalidad;
    }

    /**
     * Establece la modalidad del evento.
     *
     * @param modalidad La modalidad a establecer
     */
    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    /**
     * Obtiene la fecha y hora de inicio del evento.
     *
     * @return La fecha de inicio
     */
    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Establece la fecha y hora de inicio del evento.
     *
     * @param fechaInicio La fecha de inicio a establecer
     */
    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Obtiene la fecha y hora de finalización del evento.
     *
     * @return La fecha de fin
     */
    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    /**
     * Establece la fecha y hora de finalización del evento.
     *
     * @param fechaFin La fecha de fin a establecer
     */
    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * Obtiene las observaciones adicionales del evento.
     *
     * @return Las observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Establece las observaciones adicionales del evento.
     *
     * @param observaciones Las observaciones a establecer
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Obtiene el ID del organizador responsable del evento.
     *
     * @return El ID del organizador
     */
    public Long getOrganizadorId() {
        return organizadorId;
    }

    /**
     * Establece el ID del organizador responsable del evento.
     *
     * @param organizadorId El ID del organizador a establecer
     */
    public void setOrganizadorId(Long organizadorId) {
        this.organizadorId = organizadorId;
    }

}
