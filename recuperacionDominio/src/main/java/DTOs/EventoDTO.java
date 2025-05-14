/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO que representa un evento académico en el sistema. Utilizado para
 * transferir información de eventos entre capas sin exponer directamente las
 * entidades.
 *
 * @author Alejandra García Preciado - 252444
 */
public class EventoDTO {

    /**
     * Código único del evento.
     */
    private String codigo;

    /**
     * Título del evento.
     */
    private String titulo;

    /**
     * Descripción detallada del evento.
     */
    private String descripcion;

    /**
     * Estado actual del evento (PLANEADO, EN_CURSO, FINALIZADO).
     */
    private String estado;

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
     * Nombre del organizador responsable.
     */
    private String nombreOrganizador;

    /**
     * Correo del organizador responsable.
     */
    private String correoOrganizador;

    /**
     * Lista de nombres de actividades que componen el evento.
     */
    private List<String> nombreActividades;

    /**
     * Número total de actividades.
     */
    private Integer totalActividades;

    /**
     * Constructor por defecto.
     */
    public EventoDTO() {
        this.nombreActividades = new ArrayList<>();
    }

    /**
     * Constructor con los atributos principales.
     *
     * @param codigo Código único del evento
     * @param titulo Título del evento
     * @param descripcion Descripción detallada
     * @param estado Estado actual del evento
     * @param modalidad Modalidad del evento
     * @param fechaInicio Fecha y hora de inicio
     * @param fechaFin Fecha y hora de finalización
     * @param nombreOrganizador Nombre del organizador responsable
     */
    public EventoDTO(String codigo, String titulo, String descripcion, String estado,
            String modalidad, LocalDateTime fechaInicio, LocalDateTime fechaFin,
            String nombreOrganizador) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.modalidad = modalidad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nombreOrganizador = nombreOrganizador;
        this.nombreActividades = new ArrayList<>();
        this.totalActividades = 0;
    }

    /**
     * Constructor completo con todos los atributos.
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
     * @param nombreActividades Lista de nombres de actividades
     * @param totalActividades Número total de actividades
     */
    public EventoDTO(String codigo, String titulo, String descripcion, String estado,
            String modalidad, LocalDateTime fechaInicio, LocalDateTime fechaFin,
            String observaciones, String nombreOrganizador, String correoOrganizador,
            List<String> nombreActividades, Integer totalActividades) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.modalidad = modalidad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.observaciones = observaciones;
        this.nombreOrganizador = nombreOrganizador;
        this.correoOrganizador = correoOrganizador;
        this.nombreActividades = nombreActividades != null ? nombreActividades : new ArrayList<>();
        this.totalActividades = totalActividades != null ? totalActividades : 0;
    }
    
    /**
     * Obtiene el código único del evento.
     *
     * @return El código del evento
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Establece el código único del evento.
     *
     * @param codigo El código a establecer
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
     * Obtiene el estado actual del evento.
     *
     * @return El estado del evento
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado del evento.
     *
     * @param estado El estado a establecer
     */
    public void setEstado(String estado) {
        this.estado = estado;
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
     * Obtiene el nombre del organizador responsable del evento.
     *
     * @return El nombre del organizador
     */
    public String getNombreOrganizador() {
        return nombreOrganizador;
    }

    /**
     * Establece el nombre del organizador responsable del evento.
     *
     * @param nombreOrganizador El nombre del organizador a establecer
     */
    public void setNombreOrganizador(String nombreOrganizador) {
        this.nombreOrganizador = nombreOrganizador;
    }

    /**
     * Obtiene el correo del organizador responsable.
     *
     * @return El correo del organizador
     */
    public String getCorreoOrganizador() {
        return correoOrganizador;
    }

    /**
     * Establece el correo del organizador responsable.
     *
     * @param correoOrganizador El correo del organizador a establecer
     */
    public void setCorreoOrganizador(String correoOrganizador) {
        this.correoOrganizador = correoOrganizador;
    }

    /**
     * Obtiene la lista de nombres de actividades que componen el evento.
     *
     * @return Lista de nombres de actividades
     */
    public List<String> getNombreActividades() {
        return nombreActividades;
    }

    /**
     * Establece la lista de nombres de actividades que componen el evento.
     *
     * @param nombreActividades Lista de nombres de actividades a establecer
     */
    public void setNombreActividades(List<String> nombreActividades) {
        this.nombreActividades = nombreActividades;
        if (nombreActividades != null) {
            this.totalActividades = nombreActividades.size();
        }
    }

    /**
     * Obtiene el número total de actividades del evento.
     *
     * @return El número total de actividades
     */
    public Integer getTotalActividades() {
        return totalActividades;
    }

    /**
     * Establece el número total de actividades del evento.
     *
     * @param totalActividades El número total de actividades a establecer
     */
    public void setTotalActividades(Integer totalActividades) {
        this.totalActividades = totalActividades;
    }

    /**
     * Agrega el nombre de una actividad a la lista y actualiza el contador
     * total.
     *
     * @param nombreActividad El nombre de la actividad a agregar
     */
    public void agregarNombreActividad(String nombreActividad) {
        if (this.nombreActividades == null) {
            this.nombreActividades = new ArrayList<>();
        }
        this.nombreActividades.add(nombreActividad);
        this.totalActividades = this.nombreActividades.size();
    }

}
