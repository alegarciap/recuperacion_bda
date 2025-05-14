/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 * DTO específico para la creación de nuevas inscripciones. Contiene solo los
 * campos necesarios para crear una inscripción, incluyendo las referencias a
 * participante y actividad mediante sus IDs.
 *
 * @author Alejandra García Preciado - 252444
 */
public class InscripcionCreacionDTO {

    /**
     * ID del participante a inscribir.
     */
    private Long participanteId;

    /**
     * ID de la actividad para la inscripción.
     */
    private Long actividadId;

    /**
     * Constructor por defecto.
     */
    public InscripcionCreacionDTO() {
    }

    /**
     * Constructor con todos los atributos.
     *
     * @param participanteId ID del participante
     * @param actividadId ID de la actividad
     */
    public InscripcionCreacionDTO(Long participanteId, Long actividadId) {
        this.participanteId = participanteId;
        this.actividadId = actividadId;
    }

    /**
     * Obtiene el ID del participante a inscribir.
     *
     * @return El ID del participante
     */
    public Long getParticipanteId() {
        return participanteId;
    }

    /**
     * Establece el ID del participante a inscribir.
     *
     * @param participanteId El ID del participante a establecer
     */
    public void setParticipanteId(Long participanteId) {
        this.participanteId = participanteId;
    }

    /**
     * Obtiene el ID de la actividad para la inscripción.
     *
     * @return El ID de la actividad
     */
    public Long getActividadId() {
        return actividadId;
    }

    /**
     * Establece el ID de la actividad para la inscripción.
     *
     * @param actividadId El ID de la actividad a establecer
     */
    public void setActividadId(Long actividadId) {
        this.actividadId = actividadId;
    }

}
