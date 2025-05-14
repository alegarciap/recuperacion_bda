/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 * DTO que representa a un participante de tipo docente. Extiende de
 * ParticipanteDTO e incluye información específica de docentes.
 *
 * @author Alejandra García Preciado - 252444
 */
public class ParticipanteDocenteDTO extends ParticipanteDTO {

    /**
     * Departamento al que pertenece.
     */
    private String departamento;

    /**
     * Constructor por defecto.
     */
    public ParticipanteDocenteDTO() {
        super();
        setTipoParticipante("DOCENTE");
    }

    /**
     * Constructor con los atributos básicos de participante y los específicos
     * de docente.
     *
     * @param nombre Nombre del docente
     * @param apellidoPaterno Apellido paterno
     * @param apellidoMaterno Apellido materno
     * @param correo Correo electrónico
     * @param departamento Departamento al que pertenece
     */
    public ParticipanteDocenteDTO(String nombre, String apellidoPaterno, String apellidoMaterno,
            String correo, String departamento) {
        super(nombre, apellidoPaterno, apellidoMaterno, correo, "DOCENTE");
        this.departamento = departamento;
    }

    /**
     * Constructor con todos los atributos.
     *
     * @param nombre Nombre del docente
     * @param apellidoPaterno Apellido paterno
     * @param apellidoMaterno Apellido materno
     * @param correo Correo electrónico
     * @param cantidadAsistencias Cantidad de asistencias acumuladas
     * @param totalInscripciones Número de inscripciones realizadas
     * @param departamento Departamento al que pertenece
     */
    public ParticipanteDocenteDTO(String nombre, String apellidoPaterno, String apellidoMaterno,
            String correo, Integer cantidadAsistencias, Integer totalInscripciones,
            String departamento) {
        super(nombre, apellidoPaterno, apellidoMaterno, correo, "DOCENTE",
                cantidadAsistencias, totalInscripciones);
        this.departamento = departamento;
    }

    /**
     * Obtiene el departamento al que pertenece el docente.
     *
     * @return El departamento
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Establece el departamento al que pertenece el docente.
     *
     * @param departamento El departamento a establecer
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

}
