/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 * DTO específico para la creación de participantes docentes. Extiende de
 * ParticipanteCreacionDTO e incluye campos específicos para docentes.
 *
 * @author Alejandra García Preciado - 252444
 */
public class ParticipanteDocenteCreacionDTO extends ParticipanteCreacionDTO {

    /**
     * Departamento al que pertenece.
     */
    private String departamento;

    /**
     * Constructor por defecto.
     */
    public ParticipanteDocenteCreacionDTO() {
        super();
    }

    /**
     * Constructor con todos los atributos.
     *
     * @param nombre Nombre del docente
     * @param apellidoPaterno Apellido paterno
     * @param apellidoMaterno Apellido materno
     * @param correo Correo electrónico
     * @param departamento Departamento al que pertenece
     */
    public ParticipanteDocenteCreacionDTO(String nombre, String apellidoPaterno, String apellidoMaterno,
            String correo, String departamento) {
        super(nombre, apellidoPaterno, apellidoMaterno, correo);
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
