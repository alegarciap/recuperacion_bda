/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import DTOs.OrganizadorDTO;
import entidades.Organizador;
import entidades.Organizador.TipoOrganizador;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de transformar objetos Organizador a sus respectivos DTOs y
 * viceversa. Proporciona métodos para convertir entidades a DTOs según los
 * requerimientos de la aplicación.
 *
 * @author Alejandra García Preciado - 252444
 */
public class OrganizadorMapper {

    /**
     * Constructor por defecto.
     */
    public OrganizadorMapper() {
    }

    /**
     * Convierte una entidad Organizador a un DTO de Organizador.
     *
     * @param organizador Entidad Organizador a convertir
     * @return OrganizadorDTO con la información
     */
    public OrganizadorDTO toDTO(Organizador organizador) {
        if (organizador == null) {
            return null;
        }

        // La colección de eventos siempre estará inicializada (aunque vacía)
        Integer totalEventos = organizador.getEventos().size();

        OrganizadorDTO dto = new OrganizadorDTO();
        dto.setNombre(organizador.getNombre());
        dto.setCorreo(organizador.getCorreo());
        dto.setTipoOrganizador(organizador.getTipoOrganizador().toString());
        dto.setTotalEventos(totalEventos);

        return dto;
    }

    /**
     * Convierte una lista de entidades Organizador a una lista de DTOs.
     *
     * @param organizadores Lista de entidades Organizador
     * @return Lista de OrganizadorDTO
     */
    public List<OrganizadorDTO> toDTOList(List<Organizador> organizadores) {
        List<OrganizadorDTO> dtos = new ArrayList<>();

        if (organizadores != null) {
            for (Organizador organizador : organizadores) {
                dtos.add(toDTO(organizador));
            }
        }

        return dtos;
    }

    /**
     * Convierte un DTO a una entidad Organizador.
     *
     * @param organizadorDTO DTO de Organizador
     * @return Entidad Organizador sin persistir
     */
    public Organizador toEntity(OrganizadorDTO organizadorDTO) {
        if (organizadorDTO == null) {
            return null;
        }

        Organizador organizador = new Organizador();
        organizador.setNombre(organizadorDTO.getNombre());
        organizador.setCorreo(organizadorDTO.getCorreo());

        // Convertir tipo de String a enum
        try {
            organizador.setTipoOrganizador(TipoOrganizador.valueOf(organizadorDTO.getTipoOrganizador()));
        } catch (IllegalArgumentException | NullPointerException ex) {
            // Valor por defecto si hay un error
            organizador.setTipoOrganizador(TipoOrganizador.ORGANIZADOR);
        }

        organizador.setEventos(new ArrayList<>());

        return organizador;
    }

    /**
     * Actualiza una entidad Organizador existente con los datos de un DTO. Este
     * método no modifica las relaciones con otras entidades (Eventos).
     *
     * @param organizador Entidad Organizador a actualizar
     * @param organizadorDTO DTO con los nuevos datos
     */
    public void updateEntityFromDTO(Organizador organizador, OrganizadorDTO organizadorDTO) {
        if (organizador == null || organizadorDTO == null) {
            return;
        }

        organizador.setNombre(organizadorDTO.getNombre());
        organizador.setCorreo(organizadorDTO.getCorreo());

        // Convertir tipo de String a enum
        try {
            organizador.setTipoOrganizador(TipoOrganizador.valueOf(organizadorDTO.getTipoOrganizador()));
        } catch (IllegalArgumentException | NullPointerException ex) {
            // No actualizar si hay un error
        }
    }
    
}
