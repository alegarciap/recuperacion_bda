/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.time.LocalDateTime;

/**
 * DTO que representa una actividad dentro de un evento académico. Utilizado
 * para transferir información de actividades entre capas sin exponer
 * directamente las entidades.
 *
 * @author Alejandra García Preciado - 252444
 */
public class ActividadDTO {

    /**
     * Nombre de la actividad.
     */
    private String nombre;

    /**
     * Tipo de actividad (conferencia, taller, panel, etc.).
     */
    private String tipo;

    /**
     * Fecha y hora de inicio de la actividad.
     */
    private LocalDateTime fechaHoraInicio;

    /**
     * Capacidad máxima de participantes.
     */
    private Integer capacidad;

    /**
     * Duración estimada en minutos.
     */
    private Integer duracion;

    /**
     * Indica si la actividad ha sido finalizada.
     */
    private Boolean finalizado;

    /**
     * Nombre del evento al que pertenece la actividad.
     */
    private String nombreEvento;

    /**
     * Nombre del lugar donde se realizará la actividad.
     */
    private String nombreLugar;

    /**
     * Número de inscritos actuales.
     */
    private Integer numeroInscritos;

    /**
     * Porcentaje de ocupación.
     */
    private Double porcentajeOcupacion;

    /**
     * Constructor por defecto.
     */
    public ActividadDTO() {
    }

    /**
     * Constructor con los atributos principales.
     *
     * @param nombre Nombre de la actividad
     * @param tipo Tipo de actividad
     * @param fechaHoraInicio Fecha y hora de inicio
     * @param capacidad Capacidad máxima de participantes
     * @param duracion Duración estimada en minutos
     * @param finalizado Indica si la actividad está finalizada
     * @param nombreEvento Nombre del evento al que pertenece
     * @param nombreLugar Nombre del lugar donde se realizará
     */
    public ActividadDTO(String nombre, String tipo, LocalDateTime fechaHoraInicio,
            Integer capacidad, Integer duracion, Boolean finalizado,
            String nombreEvento, String nombreLugar) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.fechaHoraInicio = fechaHoraInicio;
        this.capacidad = capacidad;
        this.duracion = duracion;
        this.finalizado = finalizado;
        this.nombreEvento = nombreEvento;
        this.nombreLugar = nombreLugar;
        this.numeroInscritos = 0;
        this.porcentajeOcupacion = 0.0;
    }

    /**
     * Constructor completo incluyendo estadísticas.
     *
     * @param nombre Nombre de la actividad
     * @param tipo Tipo de actividad
     * @param fechaHoraInicio Fecha y hora de inicio
     * @param capacidad Capacidad máxima de participantes
     * @param duracion Duración estimada en minutos
     * @param finalizado Indica si la actividad está finalizada
     * @param nombreEvento Nombre del evento al que pertenece
     * @param nombreLugar Nombre del lugar donde se realizará
     * @param numeroInscritos Número de participantes inscritos
     * @param porcentajeOcupacion Porcentaje de ocupación actual
     */
    public ActividadDTO(String nombre, String tipo, LocalDateTime fechaHoraInicio,
            Integer capacidad, Integer duracion, Boolean finalizado,
            String nombreEvento, String nombreLugar,
            Integer numeroInscritos, Double porcentajeOcupacion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.fechaHoraInicio = fechaHoraInicio;
        this.capacidad = capacidad;
        this.duracion = duracion;
        this.finalizado = finalizado;
        this.nombreEvento = nombreEvento;
        this.nombreLugar = nombreLugar;
        this.numeroInscritos = numeroInscritos;
        this.porcentajeOcupacion = porcentajeOcupacion;
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

    /**
     * Obtiene el nombre del lugar donde se realizará la actividad.
     *
     * @return El nombre del lugar
     */
    public String getNombreLugar() {
        return nombreLugar;
    }

    /**
     * Establece el nombre del lugar donde se realizará la actividad.
     *
     * @param nombreLugar El nombre del lugar a establecer
     */
    public void setNombreLugar(String nombreLugar) {
        this.nombreLugar = nombreLugar;
    }

    /**
     * Obtiene el número de participantes inscritos.
     *
     * @return El número de inscritos
     */
    public Integer getNumeroInscritos() {
        return numeroInscritos;
    }

    /**
     * Establece el número de participantes inscritos.
     *
     * @param numeroInscritos El número de inscritos a establecer
     */
    public void setNumeroInscritos(Integer numeroInscritos) {
        this.numeroInscritos = numeroInscritos;
    }

    /**
     * Obtiene el porcentaje de ocupación de la actividad.
     *
     * @return El porcentaje de ocupación
     */
    public Double getPorcentajeOcupacion() {
        return porcentajeOcupacion;
    }

    /**
     * Establece el porcentaje de ocupación de la actividad.
     *
     * @param porcentajeOcupacion El porcentaje de ocupación a establecer
     */
    public void setPorcentajeOcupacion(Double porcentajeOcupacion) {
        this.porcentajeOcupacion = porcentajeOcupacion;
    }

}
