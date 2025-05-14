/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 * DTO específico para la creación de participantes estudiantes. Extiende de
 * ParticipanteCreacionDTO e incluye campos específicos para estudiantes.
 *
 * @author Alejandra García Preciado - 252444
 */
public class ParticipanteEstudianteCreacionDTO extends ParticipanteCreacionDTO {

    /**
     * Número de control del estudiante.
     */
    private Integer numeroControl;

    /**
     * Carrera que estudia.
     */
    private String carrera;

    /**
     * Constructor por defecto.
     */
    public ParticipanteEstudianteCreacionDTO() {
        super();
    }

    /**
     * Constructor con todos los atributos.
     *
     * @param nombre Nombre del estudiante
     * @param apellidoPaterno Apellido paterno
     * @param apellidoMaterno Apellido materno
     * @param correo Correo electrónico
     * @param numeroControl Número de control
     * @param carrera Carrera que estudia
     */
    public ParticipanteEstudianteCreacionDTO(String nombre, String apellidoPaterno, String apellidoMaterno,
            String correo, Integer numeroControl, String carrera) {
        super(nombre, apellidoPaterno, apellidoMaterno, correo);
        this.numeroControl = numeroControl;
        this.carrera = carrera;
    }

    /**
     * Obtiene el número de control del estudiante.
     *
     * @return El número de control
     */
    public Integer getNumeroControl() {
        return numeroControl;
    }

    /**
     * Establece el número de control del estudiante.
     *
     * @param numeroControl El número de control a establecer
     */
    public void setNumeroControl(Integer numeroControl) {
        this.numeroControl = numeroControl;
    }

    /**
     * Obtiene la carrera que estudia.
     *
     * @return La carrera
     */
    public String getCarrera() {
        return carrera;
    }

    /**
     * Establece la carrera que estudia.
     *
     * @param carrera La carrera a establecer
     */
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

}
