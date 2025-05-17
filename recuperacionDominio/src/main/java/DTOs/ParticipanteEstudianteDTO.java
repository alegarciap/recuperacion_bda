/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 * DTO que representa a un participante de tipo estudiante. Extiende de
 * ParticipanteDTO e incluye información específica de estudiantes.
 *
 * @author Alejandra García Preciado - 252444
 */
public class ParticipanteEstudianteDTO extends ParticipanteDTO {

    /**
     * Número de control del estudiante.
     */
    private String numeroControl;

    /**
     * Carrera que estudia.
     */
    private String carrera;

    /**
     * Constructor por defecto.
     */
    public ParticipanteEstudianteDTO() {
        super();
        setTipoParticipante("ESTUDIANTE");
    }

    /**
     * Constructor con los atributos básicos de participante y los específicos
     * de estudiante.
     *
     * @param nombre Nombre del estudiante
     * @param apellidoPaterno Apellido paterno
     * @param apellidoMaterno Apellido materno
     * @param correo Correo electrónico
     * @param numeroControl Número de control
     * @param carrera Carrera que estudia
     */
    public ParticipanteEstudianteDTO(String nombre, String apellidoPaterno, String apellidoMaterno,
            String correo, String numeroControl, String carrera) {
        super(nombre, apellidoPaterno, apellidoMaterno, correo, "ESTUDIANTE");
        this.numeroControl = numeroControl;
        this.carrera = carrera;
    }

    /**
     * Constructor con todos los atributos.
     *
     * @param nombre Nombre del estudiante
     * @param apellidoPaterno Apellido paterno
     * @param apellidoMaterno Apellido materno
     * @param correo Correo electrónico
     * @param cantidadAsistencias Cantidad de asistencias acumuladas
     * @param totalInscripciones Número de inscripciones realizadas
     * @param numeroControl Número de control
     * @param carrera Carrera que estudia
     */
    public ParticipanteEstudianteDTO(String nombre, String apellidoPaterno, String apellidoMaterno,
            String correo, Integer cantidadAsistencias, Integer totalInscripciones,
            String numeroControl, String carrera) {
        super(nombre, apellidoPaterno, apellidoMaterno, correo, "ESTUDIANTE",
                cantidadAsistencias, totalInscripciones);
        this.numeroControl = numeroControl;
        this.carrera = carrera;
    }

    /**
     * Obtiene el número de control del estudiante.
     *
     * @return El número de control
     */
    public String getNumeroControl() {
        return numeroControl;
    }

    /**
     * Establece el número de control del estudiante.
     *
     * @param numeroControl El número de control a establecer
     */
    public void setNumeroControl(String numeroControl) {
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
