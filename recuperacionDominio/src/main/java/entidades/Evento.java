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
 * Clase que representa un evento académico en el sistema. Contiene información
 * general del evento y mantiene relaciones con actividades y organizador. Un
 * evento puede tener múltiples actividades asociadas.
 *
 * @author Alejandra García Preciado - 252444
 */
@Entity
@Table(name = "eventos")
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único del evento.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Código único generado automáticamente para el evento con formato
     * EV-YYYYMM-XXX.
     */
    @Column(name = "codigo", nullable = false, unique = true)
    private String codigo;

    /**
     * Título del evento.
     */
    @Column(name = "titulo", nullable = false)
    private String titulo;

    /**
     * Descripción detallada del evento.
     */
    @Column(name = "descripcion", nullable = false, length = 1000)
    private String descripcion;

    /**
     * Estado actual del evento (PLANEADO, EN_CURSO, FINALIZADO).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoEvento estado;

    /**
     * Modalidad del evento (PRESENCIAL, EN_LINEA, HIBRIDO).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "modalidad", nullable = false)
    private ModalidadEvento modalidad;

    /**
     * Fecha y hora de inicio del evento.
     */
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;

    /**
     * Fecha y hora de finalización del evento.
     */
    @Column(name = "fecha_fin", nullable = false)
    private LocalDateTime fechaFin;

    /**
     * Observaciones adicionales sobre el evento.
     */
    @Column(name = "observaciones", length = 1000)
    private String observaciones;

    /**
     * Organizador responsable del evento.
     */
    @ManyToOne
    @JoinColumn(name = "organizador_id", nullable = false)
    private Organizador organizador;

    /**
     * Lista de actividades que componen el evento.
     */
    @OneToMany(mappedBy = "evento", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Actividad> actividades;

    /**
     * Enumeración para los posibles estados de un evento.
     */
    public enum EstadoEvento {
        /**
         * Evento en fase de planificación.
         */
        PLANEADO,
        /**
         * Evento actualmente en desarrollo.
         */
        EN_CURSO,
        /**
         * Evento ya terminado.
         */
        FINALIZADO
    }

    /**
     * Enumeración para los tipos de modalidad de un evento.
     */
    public enum ModalidadEvento {
        /**
         * Evento que se realiza físicamente.
         */
        PRESENCIAL,
        /**
         * Evento que se realiza a través de internet.
         */
        EN_LINEA,
        /**
         * Evento que combina modalidad presencial y en línea.
         */
        HIBRIDO
    }

    /**
     * Constructor por defecto requerido por JPA.
     */
    public Evento() {
        this.actividades = new ArrayList<>();
        this.estado = EstadoEvento.PLANEADO;
    }

    /**
     * Constructor con todos los atributos excepto el ID.
     *
     * @param codigo Código único del evento
     * @param titulo Título del evento
     * @param descripcion Descripción detallada
     * @param estado Estado actual del evento
     * @param modalidad Modalidad del evento
     * @param fechaInicio Fecha y hora de inicio
     * @param fechaFin Fecha y hora de finalización
     * @param observaciones Observaciones adicionales
     * @param organizador Organizador responsable
     * @param actividades Lista de actividades del evento
     */
    public Evento(String codigo, String titulo, String descripcion, EstadoEvento estado,
            ModalidadEvento modalidad, LocalDateTime fechaInicio, LocalDateTime fechaFin,
            String observaciones, Organizador organizador, List<Actividad> actividades) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.modalidad = modalidad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.observaciones = observaciones;
        this.organizador = organizador;
        this.actividades = actividades != null ? actividades : new ArrayList<>();
    }

    /**
     * Constructor con todos los atributos incluido el ID.
     *
     * @param id Identificador único del evento
     * @param codigo Código único del evento
     * @param titulo Título del evento
     * @param descripcion Descripción detallada
     * @param estado Estado actual del evento
     * @param modalidad Modalidad del evento
     * @param fechaInicio Fecha y hora de inicio
     * @param fechaFin Fecha y hora de finalización
     * @param observaciones Observaciones adicionales
     * @param organizador Organizador responsable
     * @param actividades Lista de actividades del evento
     */
    public Evento(Long id, String codigo, String titulo, String descripcion, EstadoEvento estado,
            ModalidadEvento modalidad, LocalDateTime fechaInicio, LocalDateTime fechaFin,
            String observaciones, Organizador organizador, List<Actividad> actividades) {
        this.id = id;
        this.codigo = codigo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.modalidad = modalidad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.observaciones = observaciones;
        this.organizador = organizador;
        this.actividades = actividades != null ? actividades : new ArrayList<>();
    }

    /**
     * Obtiene el identificador único del evento.
     *
     * @return El ID del evento
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del evento.
     *
     * @param id El ID a establecer
     */
    public void setId(Long id) {
        this.id = id;
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
    public EstadoEvento getEstado() {
        return estado;
    }

    /**
     * Establece el estado del evento.
     *
     * @param estado El estado a establecer
     */
    public void setEstado(EstadoEvento estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la modalidad del evento.
     *
     * @return La modalidad
     */
    public ModalidadEvento getModalidad() {
        return modalidad;
    }

    /**
     * Establece la modalidad del evento.
     *
     * @param modalidad La modalidad a establecer
     */
    public void setModalidad(ModalidadEvento modalidad) {
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
     * Obtiene el organizador responsable del evento.
     *
     * @return El organizador
     */
    public Organizador getOrganizador() {
        return organizador;
    }

    /**
     * Establece el organizador responsable del evento.
     *
     * @param organizador El organizador a establecer
     */
    public void setOrganizador(Organizador organizador) {
        this.organizador = organizador;
    }

    /**
     * Obtiene la lista de actividades del evento.
     *
     * @return Lista de actividades
     */
    public List<Actividad> getActividades() {
        return actividades;
    }

    /**
     * Establece la lista de actividades del evento.
     *
     * @param actividades Lista de actividades a establecer
     */
    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    @Override
    public String toString() {
        return "Evento{" + "id=" + id + ", codigo=" + codigo + ", titulo=" + titulo + ", descripcion=" + descripcion + ", estado=" + estado + ", modalidad=" + modalidad + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", observaciones=" + observaciones + ", organizador=" + organizador + ", actividades=" + actividades + '}';
    }

}