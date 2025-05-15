/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import DTOs.ParticipanteDocenteCreacionDTO;
import DTOs.ParticipanteDocenteDTO;
import entidades.ParticipanteDocente;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de transformar objetos ParticipanteDocente a sus respectivos
 * DTOs y viceversa. Proporciona métodos para convertir entidades a DTOs según
 * los requerimientos de la aplicación.
 *
 * @author Alejandra García Preciado - 252444
 */
public class ParticipanteDocenteMapper {

    /**
     * Constructor por defecto.
     */
    public ParticipanteDocenteMapper() {
    }

    /**
     * Convierte una entidad ParticipanteDocente a un DTO específico.
     *
     * @param docente Entidad ParticipanteDocente a convertir
     * @return ParticipanteDocenteDTO con la información
     */
    public ParticipanteDocenteDTO toDTO(ParticipanteDocente docente) {
        if (docente == null) {
            return null;
        }

        // La colección de inscripciones siempre estará inicializada (aunque vacía)
        Integer totalInscripciones = docente.getInscripciones().size();

        ParticipanteDocenteDTO dto = new ParticipanteDocenteDTO();
        dto.setNombre(docente.getNombre());
        dto.setApellidoPaterno(docente.getApellidoPaterno());
        dto.setApellidoMaterno(docente.getApellidoMaterno());
        dto.setCorreo(docente.getCorreo());
        dto.setCantidadAsistencias(docente.getCantidadAsistencias());
        dto.setTotalInscripciones(totalInscripciones);
        dto.setDepartamento(docente.getDepartamento());

        return dto;
    }

    /**
     * Convierte una lista de entidades ParticipanteDocente a una lista de DTOs.
     *
     * @param docentes Lista de entidades ParticipanteDocente
     * @return Lista de ParticipanteDocenteDTO
     */
    public List<ParticipanteDocenteDTO> toDTOList(List<ParticipanteDocente> docentes) {
        List<ParticipanteDocenteDTO> dtos = new ArrayList<>();

        if (docentes != null) {
            for (ParticipanteDocente docente : docentes) {
                dtos.add(toDTO(docente));
            }
        }

        return dtos;
    }

    /**
     * Convierte un DTO de creación a una entidad ParticipanteDocente.
     *
     * @param docenteDTO DTO de creación
     * @return Entidad ParticipanteDocente sin persistir
     */
    public ParticipanteDocente toEntity(ParticipanteDocenteCreacionDTO docenteDTO) {
        if (docenteDTO == null) {
            return null;
        }

        ParticipanteDocente docente = new ParticipanteDocente();
        docente.setNombre(docenteDTO.getNombre());
        docente.setApellidoPaterno(docenteDTO.getApellidoPaterno());
        docente.setApellidoMaterno(docenteDTO.getApellidoMaterno());
        docente.setCorreo(docenteDTO.getCorreo());
        docente.setDepartamento(docenteDTO.getDepartamento());
        docente.setCantidadAsistencias(0); // Inicialmente sin asistencias
        docente.setInscripciones(new ArrayList<>());

        return docente;
    }

    /**
     * Actualiza una entidad ParticipanteDocente existente con los datos de un
     * DTO. Este método no modifica las relaciones con otras entidades
     * (Inscripciones).
     *
     * @param docente Entidad ParticipanteDocente a actualizar
     * @param docenteDTO DTO con los nuevos datos
     */
    public void updateEntityFromDTO(ParticipanteDocente docente, ParticipanteDocenteDTO docenteDTO) {
        if (docente == null || docenteDTO == null) {
            return;
        }

        docente.setNombre(docenteDTO.getNombre());
        docente.setApellidoPaterno(docenteDTO.getApellidoPaterno());
        docente.setApellidoMaterno(docenteDTO.getApellidoMaterno());
        docente.setCorreo(docenteDTO.getCorreo());
        docente.setDepartamento(docenteDTO.getDepartamento());
        // No se actualiza cantidadAsistencias ya que este valor se calcula
    }

}
