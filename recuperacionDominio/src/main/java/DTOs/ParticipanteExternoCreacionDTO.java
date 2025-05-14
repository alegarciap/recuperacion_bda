/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 * DTO específico para la creación de participantes externos. Extiende de
 * ParticipanteCreacionDTO e incluye campos específicos para externos.
 *
 * @author Alejandra García Preciado - 252444
 */
public class ParticipanteExternoCreacionDTO extends ParticipanteCreacionDTO {

    /**
     * Institución a la que pertenece.
     */
    private String institucion;

    /**
     * Constructor por defecto.
     */
    public ParticipanteExternoCreacionDTO() {
        super();
    }

    /**
     * Constructor con todos los atributos.
     *
     * @param nombre Nombre del participante externo
     * @param apellidoPaterno Apellido paterno
     * @param apellidoMaterno Apellido materno
     * @param correo Correo electrónico
     * @param institucion Institución a la que pertenece
     */
    public ParticipanteExternoCreacionDTO(String nombre, String apellidoPaterno, String apellidoMaterno,
            String correo, String institucion) {
        super(nombre, apellidoPaterno, apellidoMaterno, correo);
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
