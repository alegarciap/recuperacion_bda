/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO que proporciona información detallada de un evento, incluyendo la lista
 * completa de actividades. Utilizado para mostrar vistas detalladas de un
 * evento específico.
 *
 * @author Alejandra García Preciado - 252444
 */
public class EventoDetalleDTO extends EventoDTO {

    /**
     * Lista de actividades que componen el evento.
     */
    private List<ActividadDTO> actividades;

    /**
     * Días restantes para el inicio del evento (si aún no ha iniciado).
     */
    private Long diasRestantes;

    /**
     * Duración total del evento en días.
     */
    private Long duracionTotal;

    /**
     * Constructor por defecto.
     */
    public EventoDetalleDTO() {
        super();
        this.actividades = new ArrayList<>();
    }

    /**
     * Constructor con los atributos básicos heredados de EventoDTO.
     *
     * @param codigo Código único del evento
     * @param titulo Título del evento
     * @param descripcion Descripción detallada
     * @param estado Estado actual del evento
     * @param modalidad Modalidad del evento
     * @param fechaInicio Fecha y hora de inicio
     * @param fechaFin Fecha y hora de finalización
     * @param observaciones Observaciones adicionales
     * @param nombreOrganizador Nombre del organizador responsable
     * @param correoOrganizador Correo del organizador
     */
    public EventoDetalleDTO(String codigo, String titulo, String descripcion, String estado,
            String modalidad, LocalDateTime fechaInicio, LocalDateTime fechaFin,
            String observaciones, String nombreOrganizador, String correoOrganizador) {
        super(codigo, titulo, descripcion, estado, modalidad, fechaInicio, fechaFin,
                observaciones, nombreOrganizador, correoOrganizador, new ArrayList<>(), 0);
        this.actividades = new ArrayList<>();
        calcularMetricas();
    }

    /**
     * Constructor completo incluyendo la lista de actividades.
     *
     * @param codigo Código único del evento
     * @param titulo Título del evento
     * @param descripcion Descripción detallada
     * @param estado Estado actual del evento
     * @param modalidad Modalidad del evento
     * @param fechaInicio Fecha y hora de inicio
     * @param fechaFin Fecha y hora de finalización
     * @param observaciones Observaciones adicionales
     * @param nombreOrganizador Nombre del organizador responsable
     * @param correoOrganizador Correo del organizador
     * @param actividades Lista de actividades del evento
     */
    public EventoDetalleDTO(String codigo, String titulo, String descripcion, String estado,
            String modalidad, LocalDateTime fechaInicio, LocalDateTime fechaFin,
            String observaciones, String nombreOrganizador, String correoOrganizador,
            List<ActividadDTO> actividades) {
        super(codigo, titulo, descripcion, estado, modalidad, fechaInicio, fechaFin,
                observaciones, nombreOrganizador, correoOrganizador, new ArrayList<>(), 0);
        this.actividades = actividades;
        actualizarListaNombres();
        calcularMetricas();
    }

    /**
     * Obtiene la lista de actividades que componen el evento.
     *
     * @return Lista de actividades
     */
    public List<ActividadDTO> getActividades() {
        return actividades;
    }

    /**
     * Establece la lista de actividades que componen el evento.
     *
     * @param actividades Lista de actividades a establecer
     */
    public void setActividades(List<ActividadDTO> actividades) {
        this.actividades = actividades;
        actualizarListaNombres();
        setTotalActividades(actividades != null ? actividades.size() : 0);
    }

    /**
     * Obtiene los días restantes para el inicio del evento.
     *
     * @return Días restantes
     */
    public Long getDiasRestantes() {
        return diasRestantes;
    }

    /**
     * Establece los días restantes para el inicio del evento.
     *
     * @param diasRestantes Días restantes a establecer
     */
    public void setDiasRestantes(Long diasRestantes) {
        this.diasRestantes = diasRestantes;
    }

    /**
     * Obtiene la duración total del evento en días.
     *
     * @return Duración total
     */
    public Long getDuracionTotal() {
        return duracionTotal;
    }

    /**
     * Establece la duración total del evento en días.
     *
     * @param duracionTotal Duración total a establecer
     */
    public void setDuracionTotal(Long duracionTotal) {
        this.duracionTotal = duracionTotal;
    }

    /**
     * Agrega una actividad a la lista y actualiza las listas derivadas.
     *
     * @param actividad La actividad a agregar
     */
    public void agregarActividad(ActividadDTO actividad) {
        if (this.actividades == null) {
            this.actividades = new ArrayList<>();
        }
        this.actividades.add(actividad);
        agregarNombreActividad(actividad.getNombre());
    }

    /**
     * Actualiza la lista de nombres de actividades basada en la lista de
     * actividades.
     */
    private void actualizarListaNombres() {
        List<String> nombres = new ArrayList<>();
        if (this.actividades != null) {
            for (ActividadDTO actividad : this.actividades) {
                nombres.add(actividad.getNombre());
            }
        }
        setNombreActividades(nombres);
    }

    /**
     * Calcula métricas adicionales como días restantes y duración total.
     */
    private void calcularMetricas() {
        LocalDateTime ahora = LocalDateTime.now();

        // Calcular días restantes
        if (getFechaInicio() != null && ahora.isBefore(getFechaInicio())) {
            this.diasRestantes = java.time.Duration.between(ahora, getFechaInicio()).toDays();
        } else {
            this.diasRestantes = 0L;
        }

        // Calcular duración total
        if (getFechaInicio() != null && getFechaFin() != null) {
            this.duracionTotal = java.time.Duration.between(getFechaInicio(), getFechaFin()).toDays();
            if (this.duracionTotal == 0) {
                this.duracionTotal = 1L; // Mínimo un día si ocurre en el mismo día
            }
        }
    }
    
}
