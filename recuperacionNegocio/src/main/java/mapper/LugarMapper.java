/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import DTOs.LugarDTO;
import entidades.Lugar;
import entidades.Lugar.TipoLugar;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de transformar objetos Lugar a sus respectivos DTOs y
 * viceversa. Proporciona métodos para convertir entidades a DTOs según los
 * requerimientos de la aplicación.
 *
 * @author Alejandra García Preciado - 252444
 */
public class LugarMapper {

    /**
     * Constructor por defecto.
     */
    public LugarMapper() {
    }

    /**
     * Convierte una entidad Lugar a un DTO de Lugar.
     *
     * @param lugar Entidad Lugar a convertir
     * @return LugarDTO con la información
     */
    public LugarDTO toDTO(Lugar lugar) {
        if (lugar == null) {
            return null;
        }

        // La colección de actividades siempre estará inicializada (aunque vacía)
        Integer totalActividades = lugar.getActividades().size();

        LugarDTO dto = new LugarDTO();
        dto.setNombre(lugar.getNombre());
        dto.setTipoLugar(lugar.getTipoLugar().toString());
        dto.setCapacidad(lugar.getCapacidad());
        dto.setTotalActividades(totalActividades);

        return dto;
    }

    /**
     * Convierte una lista de entidades Lugar a una lista de DTOs.
     *
     * @param lugares Lista de entidades Lugar
     * @return Lista de LugarDTO
     */
    public List<LugarDTO> toDTOList(List<Lugar> lugares) {
        List<LugarDTO> dtos = new ArrayList<>();

        if (lugares != null) {
            for (Lugar lugar : lugares) {
                dtos.add(toDTO(lugar));
            }
        }

        return dtos;
    }

    /**
     * Convierte un DTO a una entidad Lugar.
     *
     * @param lugarDTO DTO de Lugar
     * @return Entidad Lugar sin persistir
     */
    public Lugar toEntity(LugarDTO lugarDTO) {
        if (lugarDTO == null) {
            return null;
        }

        Lugar lugar = new Lugar();
        lugar.setNombre(lugarDTO.getNombre());
        lugar.setCapacidad(lugarDTO.getCapacidad());

        // Convertir tipo de String a enum
        try {
            lugar.setTipoLugar(TipoLugar.valueOf(lugarDTO.getTipoLugar()));
        } catch (IllegalArgumentException | NullPointerException ex) {
            // Valor por defecto si hay un error
            lugar.setTipoLugar(TipoLugar.AULA);
        }

        return lugar;
    }

    /**
     * Actualiza una entidad Lugar existente con los datos de un DTO.
     *
     * @param lugar Entidad Lugar a actualizar
     * @param lugarDTO DTO con los nuevos datos
     */
    public void updateEntityFromDTO(Lugar lugar, LugarDTO lugarDTO) {
        if (lugar == null || lugarDTO == null) {
            return;
        }

        lugar.setNombre(lugarDTO.getNombre());
        lugar.setCapacidad(lugarDTO.getCapacidad());

        // Convertir tipo de String a enum
        try {
            lugar.setTipoLugar(TipoLugar.valueOf(lugarDTO.getTipoLugar()));
        } catch (IllegalArgumentException | NullPointerException ex) {
            // No actualizar si hay un error
        }
    }
    
}
