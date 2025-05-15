/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import DTOs.ParticipanteCreacionDTO;
import DTOs.ParticipanteDTO;
import entidades.Participante;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de transformar objetos Participante a sus respectivos DTOs y
 * viceversa. Proporciona métodos para convertir entidades a DTOs según los
 * requerimientos de la aplicación.
 *
 * @author Alejandra García Preciado - 252444
 */
public class ParticipanteMapper {

    /**
     * Constructor por defecto.
     */
    public ParticipanteMapper() {
    }

    /**
     * Convierte una entidad Participante a un DTO de Participante.
     *
     * @param participante Entidad Participante a convertir
     * @return ParticipanteDTO con la información
     */
    public ParticipanteDTO toDTO(Participante participante) {
        if (participante == null) {
            return null;
        }

        String tipoParticipante = "GENERAL";
        if (participante instanceof entidades.ParticipanteEstudiante) {
            tipoParticipante = "ESTUDIANTE";
        } else if (participante instanceof entidades.ParticipanteDocente) {
            tipoParticipante = "DOCENTE";
        } else if (participante instanceof entidades.ParticipanteExterno) {
            tipoParticipante = "EXTERNO";
        }

        // La colección de inscripciones siempre estará inicializada (aunque vacía)
        Integer totalInscripciones = participante.getInscripciones().size();

        ParticipanteDTO dto = new ParticipanteDTO();
        dto.setNombre(participante.getNombre());
        dto.setApellidoPaterno(participante.getApellidoPaterno());
        dto.setApellidoMaterno(participante.getApellidoMaterno());
        dto.setCorreo(participante.getCorreo());
        dto.setTipoParticipante(tipoParticipante);
        dto.setCantidadAsistencias(participante.getCantidadAsistencias());
        dto.setTotalInscripciones(totalInscripciones);

        return dto;
    }

    /**
     * Convierte una lista de entidades Participante a una lista de DTOs.
     *
     * @param participantes Lista de entidades Participante
     * @return Lista de ParticipanteDTO
     */
    public List<ParticipanteDTO> toDTOList(List<Participante> participantes) {
        List<ParticipanteDTO> dtos = new ArrayList<>();

        if (participantes != null) {
            for (Participante participante : participantes) {
                dtos.add(toDTO(participante));
            }
        }

        return dtos;
    }

    /**
     * Convierte un DTO de creación a una entidad Participante base. Este método
     * crea una entidad Participante general sin información específica para los
     * diferentes tipos de participantes.
     *
     * @param participanteDTO DTO de creación
     * @return Entidad Participante sin persistir
     */
    public Participante toEntity(ParticipanteCreacionDTO participanteDTO) {
        if (participanteDTO == null) {
            return null;
        }

        Participante participante = new Participante();
        participante.setNombre(participanteDTO.getNombre());
        participante.setApellidoPaterno(participanteDTO.getApellidoPaterno());
        participante.setApellidoMaterno(participanteDTO.getApellidoMaterno());
        participante.setCorreo(participanteDTO.getCorreo());
        participante.setCantidadAsistencias(0); // Inicialmente sin asistencias
        participante.setInscripciones(new ArrayList<>());

        return participante;
    }

    /**
     * Actualiza una entidad Participante existente con los datos de un DTO.
     * Este método no modifica las relaciones con otras entidades
     * (Inscripciones).
     *
     * @param participante Entidad Participante a actualizar
     * @param participanteDTO DTO con los nuevos datos
     */
    public void updateEntityFromDTO(Participante participante, ParticipanteDTO participanteDTO) {
        if (participante == null || participanteDTO == null) {
            return;
        }

        participante.setNombre(participanteDTO.getNombre());
        participante.setApellidoPaterno(participanteDTO.getApellidoPaterno());
        participante.setApellidoMaterno(participanteDTO.getApellidoMaterno());
        participante.setCorreo(participanteDTO.getCorreo());
        // No se actualiza cantidadAsistencias ya que este valor se calcula
    }

}
