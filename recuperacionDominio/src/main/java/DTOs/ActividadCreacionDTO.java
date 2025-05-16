/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.time.LocalDateTime;

/**
 * DTO específico para la creación de nuevas actividades. Contiene solo los
 * campos necesarios para crear una actividad, incluyendo las referencias a
 * otras entidades mediante sus IDs.
 *
 * @author Alejandra García Preciado - 252444
 */
public class ActividadCreacionDTO {

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
     * Nombre del evento al que pertenece la actividad.
     */
    private String nombreEvento;

    /**
     * Nombre del lugar donde se realizará la actividad.
     */
    private String nombreLugar;
    
    /**
     * Nombre del organizador de la actividad.
     */
    private String nombreOrganizador;

    /**
     * Constructor por defecto.
     */
    public ActividadCreacionDTO() {
    }

    /**
     * Constructor con todos los atributos.
     *
     * @param nombre Nombre de la actividad
     * @param tipo Tipo de actividad
     * @param fechaHoraInicio Fecha y hora de inicio
     * @param capacidad Capacidad máxima de participantes
     * @param duracion Duración estimada en minutos
     * @param nombreEvento Nombre del evento al que pertenece
     * @param nombreLugar Nombre del lugar donde se realizará
     * @param nombreOrganizador Nombre del organizador de la actividad
     */
    public ActividadCreacionDTO(String nombre, String tipo, LocalDateTime fechaHoraInicio, Integer capacidad, Integer duracion, String nombreEvento, String nombreLugar, String nombreOrganizador) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.fechaHoraInicio = fechaHoraInicio;
        this.capacidad = capacidad;
        this.duracion = duracion;
        this.nombreEvento = nombreEvento;
        this.nombreLugar = nombreLugar;
        this.nombreOrganizador = nombreOrganizador;
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
     * Obtiene el nombre del organizador que organiza la actividad.
     *
     * @return El nombre del organizador
     */
    public String getNombreOrganizador() {
        return nombreOrganizador;
    }
    
    /**
     * Establece el nombre del organizador que organiza la actividad.
     *
     * @param nombreOrganizador El nombre del organizador a establecer
     */
    public void setNombreOrganizador(String nombreOrganizador) {
        this.nombreOrganizador = nombreOrganizador;
    }
    
}
