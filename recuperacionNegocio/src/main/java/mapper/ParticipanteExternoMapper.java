/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import DTOs.ParticipanteExternoCreacionDTO;
import DTOs.ParticipanteExternoDTO;
import entidades.ParticipanteExterno;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de transformar objetos ParticipanteExterno a sus respectivos
 * DTOs y viceversa. Proporciona métodos para convertir entidades a DTOs según
 * los requerimientos de la aplicación.
 *
 * @author Alejandra García Preciado - 252444
 */
public class ParticipanteExternoMapper {

    /**
     * Constructor por defecto.
     */
    public ParticipanteExternoMapper() {
    }

    /**
     * Convierte una entidad ParticipanteExterno a un DTO específico.
     *
     * @param externo Entidad ParticipanteExterno a convertir
     * @return ParticipanteExternoDTO con la información
     */
    public ParticipanteExternoDTO toDTO(ParticipanteExterno externo) {
        if (externo == null) {
            return null;
        }

        // La colección de inscripciones siempre estará inicializada (aunque vacía)
        Integer totalInscripciones = externo.getInscripciones().size();

        ParticipanteExternoDTO dto = new ParticipanteExternoDTO();
        dto.setNombre(externo.getNombre());
        dto.setApellidoPaterno(externo.getApellidoPaterno());
        dto.setApellidoMaterno(externo.getApellidoMaterno());
        dto.setCorreo(externo.getCorreo());
        dto.setCantidadAsistencias(externo.getCantidadAsistencias());
        dto.setTotalInscripciones(totalInscripciones);
        dto.setInstitucion(externo.getInstitucion());

        return dto;
    }

    /**
     * Convierte una lista de entidades ParticipanteExterno a una lista de DTOs.
     *
     * @param externos Lista de entidades ParticipanteExterno
     * @return Lista de ParticipanteExternoDTO
     */
    public List<ParticipanteExternoDTO> toDTOList(List<ParticipanteExterno> externos) {
        List<ParticipanteExternoDTO> dtos = new ArrayList<>();

        if (externos != null) {
            for (ParticipanteExterno externo : externos) {
                dtos.add(toDTO(externo));
            }
        }

        return dtos;
    }

    /**
     * Convierte un DTO de creación a una entidad ParticipanteExterno.
     *
     * @param externoDTO DTO de creación
     * @return Entidad ParticipanteExterno sin persistir
     */
    public ParticipanteExterno toEntity(ParticipanteExternoCreacionDTO externoDTO) {
        if (externoDTO == null) {
            return null;
        }

        ParticipanteExterno externo = new ParticipanteExterno();
        externo.setNombre(externoDTO.getNombre());
        externo.setApellidoPaterno(externoDTO.getApellidoPaterno());
        externo.setApellidoMaterno(externoDTO.getApellidoMaterno());
        externo.setCorreo(externoDTO.getCorreo());
        externo.setInstitucion(externoDTO.getInstitucion());
        externo.setCantidadAsistencias(0); // Inicialmente sin asistencias
        externo.setInscripciones(new ArrayList<>());

        return externo;
    }

    /**
     * Actualiza una entidad ParticipanteExterno existente con los datos de un
     * DTO. Este método no modifica las relaciones con otras entidades
     * (Inscripciones).
     *
     * @param externo Entidad ParticipanteExterno a actualizar
     * @param externoDTO DTO con los nuevos datos
     */
    public void updateEntityFromDTO(ParticipanteExterno externo, ParticipanteExternoDTO externoDTO) {
        if (externo == null || externoDTO == null) {
            return;
        }

        externo.setNombre(externoDTO.getNombre());
        externo.setApellidoPaterno(externoDTO.getApellidoPaterno());
        externo.setApellidoMaterno(externoDTO.getApellidoMaterno());
        externo.setCorreo(externoDTO.getCorreo());
        externo.setInstitucion(externoDTO.getInstitucion());
        // No se actualiza cantidadAsistencias ya que este valor se calcula
    }

}
