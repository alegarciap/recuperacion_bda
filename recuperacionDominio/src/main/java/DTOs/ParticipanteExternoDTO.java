/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 * DTO que representa a un participante de tipo externo. Extiende de
 * ParticipanteDTO e incluye información específica de externos.
 *
 * @author Alejandra García Preciado - 252444
 */
public class ParticipanteExternoDTO extends ParticipanteDTO {

    /**
     * Institución a la que pertenece.
     */
    private String institucion;

    /**
     * Constructor por defecto.
     */
    public ParticipanteExternoDTO() {
        super();
        setTipoParticipante("EXTERNO");
    }

    /**
     * Constructor con los atributos básicos de participante y los específicos
     * de externo.
     *
     * @param nombre Nombre del participante externo
     * @param apellidoPaterno Apellido paterno
     * @param apellidoMaterno Apellido materno
     * @param correo Correo electrónico
     * @param institucion Institución a la que pertenece
     */
    public ParticipanteExternoDTO(String nombre, String apellidoPaterno, String apellidoMaterno,
            String correo, String institucion) {
        super(nombre, apellidoPaterno, apellidoMaterno, correo, "EXTERNO");
        this.institucion = institucion;
    }

    /**
     * Constructor con todos los atributos.
     *
     * @param nombre Nombre del participante externo
     * @param apellidoPaterno Apellido paterno
     * @param apellidoMaterno Apellido materno
     * @param correo Correo electrónico
     * @param cantidadAsistencias Cantidad de asistencias acumuladas
     * @param totalInscripciones Número de inscripciones realizadas
     * @param institucion Institución a la que pertenece
     */
    public ParticipanteExternoDTO(String nombre, String apellidoPaterno, String apellidoMaterno,
            String correo, Integer cantidadAsistencias, Integer totalInscripciones,
            String institucion) {
        super(nombre, apellidoPaterno, apellidoMaterno, correo, "EXTERNO",
                cantidadAsistencias, totalInscripciones);
        this.institucion = institucion;
    }

    /**
     * Obtiene la institución a la que pertenece el participante externo.
     *
     * @return La institución
     */
    public String getInstitucion() {
        return institucion;
    }

    /**
     * Establece la institución a la que pertenece el participante externo.
     *
     * @param institucion La institución a establecer
     */
    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

}
