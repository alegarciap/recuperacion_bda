/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO que proporciona información detallada de una actividad, incluyendo la
 * lista de participantes inscritos. Utilizado para mostrar vistas detalladas de
 * una actividad específica.
 *
 * @author Alejandra García Preciado - 252444
 */
public class ActividadDetalleDTO extends ActividadDTO {

    /**
     * Lista de participantes inscritos en la actividad.
     */
    private List<ParticipanteDTO> participantes;

    /**
     * Hora estimada de finalización basada en la hora de inicio y la duración.
     */
    private LocalDateTime horaEstimadaFin;

    /**
     * Espacios disponibles en la actividad.
     */
    private Integer espaciosDisponibles;

    /**
     * Constructor por defecto.
     */
    public ActividadDetalleDTO() {
        super();
        this.participantes = new ArrayList<>();
    }

    /**
     * Constructor con los atributos básicos heredados de ActividadDTO.
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
    public ActividadDetalleDTO(String nombre, String tipo, LocalDateTime fechaHoraInicio,
            Integer capacidad, Integer duracion, Boolean finalizado,
            String nombreEvento, String nombreLugar,
            Integer numeroInscritos, Double porcentajeOcupacion) {
        super(nombre, tipo, fechaHoraInicio, capacidad, duracion, finalizado,
                nombreEvento, nombreLugar, numeroInscritos, porcentajeOcupacion);
        this.participantes = new ArrayList<>();
        this.horaEstimadaFin = fechaHoraInicio.plusMinutes(duracion);
        this.espaciosDisponibles = capacidad - numeroInscritos;
    }

    /**
     * Constructor completo incluyendo la lista de participantes.
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
     * @param participantes Lista de participantes inscritos
     */
    public ActividadDetalleDTO(String nombre, String tipo, LocalDateTime fechaHoraInicio,
            Integer capacidad, Integer duracion, Boolean finalizado,
            String nombreEvento, String nombreLugar,
            Integer numeroInscritos, Double porcentajeOcupacion,
            List<ParticipanteDTO> participantes) {
        super(nombre, tipo, fechaHoraInicio, capacidad, duracion, finalizado,
                nombreEvento, nombreLugar, numeroInscritos, porcentajeOcupacion);
        this.participantes = participantes;
        this.horaEstimadaFin = fechaHoraInicio.plusMinutes(duracion);
        this.espaciosDisponibles = capacidad - numeroInscritos;
    }

    /**
     * Obtiene la lista de participantes inscritos en la actividad.
     *
     * @return Lista de participantes
     */
    public List<ParticipanteDTO> getParticipantes() {
        return participantes;
    }

    /**
     * Establece la lista de participantes inscritos en la actividad.
     *
     * @param participantes Lista de participantes a establecer
     */
    public void setParticipantes(List<ParticipanteDTO> participantes) {
        this.participantes = participantes;
        if (participantes != null) {
            setNumeroInscritos(participantes.size());
            if (getCapacidad() > 0) {
                setPorcentajeOcupacion((double) participantes.size() / getCapacidad() * 100);
                this.espaciosDisponibles = getCapacidad() - participantes.size();
            }
        }
    }

    /**
     * Obtiene la hora estimada de finalización de la actividad.
     *
     * @return La hora estimada de fin
     */
    public LocalDateTime getHoraEstimadaFin() {
        return horaEstimadaFin;
    }

    /**
     * Establece la hora estimada de finalización de la actividad.
     *
     * @param horaEstimadaFin La hora estimada de fin a establecer
     */
    public void setHoraEstimadaFin(LocalDateTime horaEstimadaFin) {
        this.horaEstimadaFin = horaEstimadaFin;
    }

    /**
     * Calcula la hora estimada de finalización basada en la hora de inicio y la
     * duración. Actualiza el atributo horaEstimadaFin.
     */
    public void calcularHoraEstimadaFin() {
        if (getFechaHoraInicio() != null && getDuracion() != null) {
            this.horaEstimadaFin = getFechaHoraInicio().plusMinutes(getDuracion());
        }
    }

    /**
     * Obtiene los espacios disponibles en la actividad.
     *
     * @return El número de espacios disponibles
     */
    public Integer getEspaciosDisponibles() {
        return espaciosDisponibles;
    }

    /**
     * Establece los espacios disponibles en la actividad.
     *
     * @param espaciosDisponibles El número de espacios disponibles a establecer
     */
    public void setEspaciosDisponibles(Integer espaciosDisponibles) {
        this.espaciosDisponibles = espaciosDisponibles;
    }

    /**
     * Agrega un participante a la lista y actualiza las estadísticas.
     *
     * @param participante El participante a agregar
     */
    public void agregarParticipante(ParticipanteDTO participante) {
        if (this.participantes == null) {
            this.participantes = new ArrayList<>();
        }
        this.participantes.add(participante);
        actualizarEstadisticas();
    }

    /**
     * Actualiza las estadísticas de la actividad (número de inscritos,
     * porcentaje de ocupación y espacios disponibles).
     */
    private void actualizarEstadisticas() {
        if (this.participantes != null) {
            setNumeroInscritos(this.participantes.size());
            if (getCapacidad() > 0) {
                setPorcentajeOcupacion((double) this.participantes.size() / getCapacidad() * 100);
                this.espaciosDisponibles = getCapacidad() - this.participantes.size();
            }
        }
    }

}
