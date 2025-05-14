/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 * DTO que representa a un participante en el sistema. Utilizado para transferir
 * información de participantes entre capas sin exponer directamente las
 * entidades.
 *
 * @author Alejandra García Preciado - 252444
 */
public class ParticipanteDTO {

    /**
     * Nombre del participante.
     */
    private String nombre;

    /**
     * Apellido paterno del participante.
     */
    private String apellidoPaterno;

    /**
     * Apellido materno del participante.
     */
    private String apellidoMaterno;

    /**
     * Correo electrónico de contacto.
     */
    private String correo;

    /**
     * Tipo de participante (ESTUDIANTE, DOCENTE, EXTERNO).
     */
    private String tipoParticipante;

    /**
     * Cantidad de asistencias acumuladas.
     */
    private Integer cantidadAsistencias;

    /**
     * Número de inscripciones realizadas.
     */
    private Integer totalInscripciones;

    /**
     * Constructor por defecto.
     */
    public ParticipanteDTO() {
    }

    /**
     * Constructor con los atributos principales.
     *
     * @param nombre Nombre del participante
     * @param apellidoPaterno Apellido paterno
     * @param apellidoMaterno Apellido materno
     * @param correo Correo electrónico
     * @param tipoParticipante Tipo de participante
     */
    public ParticipanteDTO(String nombre, String apellidoPaterno, String apellidoMaterno,
            String correo, String tipoParticipante) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.tipoParticipante = tipoParticipante;
        this.cantidadAsistencias = 0;
        this.totalInscripciones = 0;
    }

    /**
     * Constructor con todos los atributos.
     *
     * @param nombre Nombre del participante
     * @param apellidoPaterno Apellido paterno
     * @param apellidoMaterno Apellido materno
     * @param correo Correo electrónico
     * @param tipoParticipante Tipo de participante
     * @param cantidadAsistencias Cantidad de asistencias acumuladas
     * @param totalInscripciones Número de inscripciones realizadas
     */
    public ParticipanteDTO(String nombre, String apellidoPaterno, String apellidoMaterno,
            String correo, String tipoParticipante,
            Integer cantidadAsistencias, Integer totalInscripciones) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.tipoParticipante = tipoParticipante;
        this.cantidadAsistencias = cantidadAsistencias;
        this.totalInscripciones = totalInscripciones;
    }
    
    /**
     * Obtiene el nombre del participante.
     *
     * @return El nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del participante.
     *
     * @param nombre El nombre a establecer
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido paterno del participante.
     *
     * @return El apellido paterno
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Establece el apellido paterno del participante.
     *
     * @param apellidoPaterno El apellido paterno a establecer
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Obtiene el apellido materno del participante.
     *
     * @return El apellido materno
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Establece el apellido materno del participante.
     *
     * @param apellidoMaterno El apellido materno a establecer
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Obtiene el correo electrónico del participante.
     *
     * @return El correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del participante.
     *
     * @param correo El correo a establecer
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene el tipo de participante.
     *
     * @return El tipo
     */
    public String getTipoParticipante() {
        return tipoParticipante;
    }

    /**
     * Establece el tipo de participante.
     *
     * @param tipoParticipante El tipo a establecer
     */
    public void setTipoParticipante(String tipoParticipante) {
        this.tipoParticipante = tipoParticipante;
    }

    /**
     * Obtiene la cantidad de asistencias acumuladas.
     *
     * @return El número de asistencias
     */
    public Integer getCantidadAsistencias() {
        return cantidadAsistencias;
    }

    /**
     * Establece la cantidad de asistencias acumuladas.
     *
     * @param cantidadAsistencias El número de asistencias a establecer
     */
    public void setCantidadAsistencias(Integer cantidadAsistencias) {
        this.cantidadAsistencias = cantidadAsistencias;
    }

    /**
     * Obtiene el número de inscripciones realizadas.
     *
     * @return El número de inscripciones
     */
    public Integer getTotalInscripciones() {
        return totalInscripciones;
    }

    /**
     * Establece el número de inscripciones realizadas.
     *
     * @param totalInscripciones El número de inscripciones a establecer
     */
    public void setTotalInscripciones(Integer totalInscripciones) {
        this.totalInscripciones = totalInscripciones;
    }

    /**
     * Obtiene el nombre completo del participante (nombre y apellidos).
     *
     * @return El nombre completo
     */
    public String getNombreCompleto() {
        return this.nombre + " " + this.apellidoPaterno + " " + this.apellidoMaterno;
    }

}
