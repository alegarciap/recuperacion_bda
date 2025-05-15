/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import DTOs.ParticipanteEstudianteCreacionDTO;
import DTOs.ParticipanteEstudianteDTO;
import entidades.ParticipanteEstudiante;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de transformar objetos ParticipanteEstudiante a sus
 * respectivos DTOs y viceversa. Proporciona métodos para convertir entidades a
 * DTOs según los requerimientos de la aplicación.
 *
 * @author Alejandra García Preciado - 252444
 */
public class ParticipanteEstudianteMapper {

    /**
     * Constructor por defecto.
     */
    public ParticipanteEstudianteMapper() {
    }

    /**
     * Convierte una entidad ParticipanteEstudiante a un DTO específico.
     *
     * @param estudiante Entidad ParticipanteEstudiante a convertir
     * @return ParticipanteEstudianteDTO con la información
     */
    public ParticipanteEstudianteDTO toDTO(ParticipanteEstudiante estudiante) {
        if (estudiante == null) {
            return null;
        }

        // La colección de inscripciones siempre estará inicializada (aunque vacía)
        Integer totalInscripciones = estudiante.getInscripciones().size();

        ParticipanteEstudianteDTO dto = new ParticipanteEstudianteDTO();
        dto.setNombre(estudiante.getNombre());
        dto.setApellidoPaterno(estudiante.getApellidoPaterno());
        dto.setApellidoMaterno(estudiante.getApellidoMaterno());
        dto.setCorreo(estudiante.getCorreo());
        dto.setCantidadAsistencias(estudiante.getCantidadAsistencias());
        dto.setTotalInscripciones(totalInscripciones);
        dto.setNumeroControl(estudiante.getNumeroControl());
        dto.setCarrera(estudiante.getCarrera());

        return dto;
    }

    /**
     * Convierte una lista de entidades ParticipanteEstudiante a una lista de
     * DTOs.
     *
     * @param estudiantes Lista de entidades ParticipanteEstudiante
     * @return Lista de ParticipanteEstudianteDTO
     */
    public List<ParticipanteEstudianteDTO> toDTOList(List<ParticipanteEstudiante> estudiantes) {
        List<ParticipanteEstudianteDTO> dtos = new ArrayList<>();

        if (estudiantes != null) {
            for (ParticipanteEstudiante estudiante : estudiantes) {
                dtos.add(toDTO(estudiante));
            }
        }

        return dtos;
    }

    /**
     * Convierte un DTO de creación a una entidad ParticipanteEstudiante.
     *
     * @param estudianteDTO DTO de creación
     * @return Entidad ParticipanteEstudiante sin persistir
     */
    public ParticipanteEstudiante toEntity(ParticipanteEstudianteCreacionDTO estudianteDTO) {
        if (estudianteDTO == null) {
            return null;
        }

        ParticipanteEstudiante estudiante = new ParticipanteEstudiante();
        estudiante.setNombre(estudianteDTO.getNombre());
        estudiante.setApellidoPaterno(estudianteDTO.getApellidoPaterno());
        estudiante.setApellidoMaterno(estudianteDTO.getApellidoMaterno());
        estudiante.setCorreo(estudianteDTO.getCorreo());
        estudiante.setNumeroControl(estudianteDTO.getNumeroControl());
        estudiante.setCarrera(estudianteDTO.getCarrera());
        estudiante.setCantidadAsistencias(0); // Inicialmente sin asistencias
        estudiante.setInscripciones(new ArrayList<>());

        return estudiante;
    }

    /**
     * Actualiza una entidad ParticipanteEstudiante existente con los datos de
     * un DTO. Este método no modifica las relaciones con otras entidades
     * (Inscripciones).
     *
     * @param estudiante Entidad ParticipanteEstudiante a actualizar
     * @param estudianteDTO DTO con los nuevos datos
     */
    public void updateEntityFromDTO(ParticipanteEstudiante estudiante, ParticipanteEstudianteDTO estudianteDTO) {
        if (estudiante == null || estudianteDTO == null) {
            return;
        }

        estudiante.setNombre(estudianteDTO.getNombre());
        estudiante.setApellidoPaterno(estudianteDTO.getApellidoPaterno());
        estudiante.setApellidoMaterno(estudianteDTO.getApellidoMaterno());
        estudiante.setCorreo(estudianteDTO.getCorreo());
        estudiante.setNumeroControl(estudianteDTO.getNumeroControl());
        estudiante.setCarrera(estudianteDTO.getCarrera());
        // No se actualiza cantidadAsistencias ya que este valor se calcula
    }

}
