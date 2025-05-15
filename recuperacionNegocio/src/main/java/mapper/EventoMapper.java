/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import DTOs.ActividadDTO;
import DTOs.EventoCreacionDTO;
import DTOs.EventoDTO;
import DTOs.EventoDetalleDTO;
import entidades.Actividad;
import entidades.Evento;
import entidades.Evento.EstadoEvento;
import entidades.Evento.ModalidadEvento;
import entidades.Organizador;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de transformar objetos Evento a sus respectivos DTOs y
 * viceversa. Proporciona métodos para convertir entidades a diferentes tipos de
 * DTOs según los requerimientos de la aplicación.
 *
 * @author Alejandra García Preciado - 252444
 */
public class EventoMapper {

    /**
     * Instancia del mapper de actividades para conversiones anidadas.
     */
    private final ActividadMapper actividadMapper;

    /**
     * Constructor que inicializa las dependencias necesarias.
     */
    public EventoMapper() {
        this.actividadMapper = new ActividadMapper();
    }

    /**
     * Convierte una entidad Evento a un DTO básico de Evento.
     *
     * @param evento Entidad Evento a convertir
     * @return EventoDTO con la información básica
     */
    public EventoDTO toDTO(Evento evento) {
        if (evento == null) {
            return null;
        }
        
        String nombreOrganizador = evento.getOrganizador().getNombre();
        String correoOrganizador = evento.getOrganizador().getCorreo();

        // Extraer nombres de actividades
        List<String> nombreActividades = new ArrayList<>();
        for (Actividad actividad : evento.getActividades()) {
            nombreActividades.add(actividad.getNombre());
        }
        Integer totalActividades = evento.getActividades().size();

        EventoDTO dto = new EventoDTO();
        dto.setCodigo(evento.getCodigo());
        dto.setTitulo(evento.getTitulo());
        dto.setDescripcion(evento.getDescripcion());
        dto.setEstado(evento.getEstado().toString());
        dto.setModalidad(evento.getModalidad().toString());
        dto.setFechaInicio(evento.getFechaInicio());
        dto.setFechaFin(evento.getFechaFin());
        dto.setObservaciones(evento.getObservaciones());
        dto.setNombreOrganizador(nombreOrganizador);
        dto.setCorreoOrganizador(correoOrganizador);
        dto.setNombreActividades(nombreActividades);
        dto.setTotalActividades(totalActividades);

        return dto;
    }

    /**
     * Convierte una lista de entidades Evento a una lista de DTOs.
     *
     * @param eventos Lista de entidades Evento
     * @return Lista de EventoDTO
     */
    public List<EventoDTO> toDTOList(List<Evento> eventos) {
        List<EventoDTO> dtos = new ArrayList<>();

        if (eventos != null) {
            for (Evento evento : eventos) {
                dtos.add(toDTO(evento));
            }
        }

        return dtos;
    }

    /**
     * Convierte una entidad Evento a un DTO detallado, incluyendo información
     * completa de sus actividades.
     *
     * @param evento Entidad Evento a convertir
     * @return EventoDetalleDTO con información detallada
     */
    public EventoDetalleDTO toDetalleDTO(Evento evento) {
        if (evento == null) {
            return null;
        }
        
        String nombreOrganizador = evento.getOrganizador().getNombre();
        String correoOrganizador = evento.getOrganizador().getCorreo();

        EventoDetalleDTO detalleDTO = new EventoDetalleDTO();
        detalleDTO.setCodigo(evento.getCodigo());
        detalleDTO.setTitulo(evento.getTitulo());
        detalleDTO.setDescripcion(evento.getDescripcion());
        detalleDTO.setEstado(evento.getEstado().toString());
        detalleDTO.setModalidad(evento.getModalidad().toString());
        detalleDTO.setFechaInicio(evento.getFechaInicio());
        detalleDTO.setFechaFin(evento.getFechaFin());
        detalleDTO.setObservaciones(evento.getObservaciones());
        detalleDTO.setNombreOrganizador(nombreOrganizador);
        detalleDTO.setCorreoOrganizador(correoOrganizador);

        // Convertir actividades
        List<ActividadDTO> actividadesDTO = new ArrayList<>();
        for (Actividad actividad : evento.getActividades()) {
            actividadesDTO.add(actividadMapper.toDTO(actividad));
        }
        detalleDTO.setActividades(actividadesDTO);

        return detalleDTO;
    }

    /**
     * Convierte un DTO de creación a una entidad Evento. Nota: La entidad
     * Organizador debe ser proporcionada externamente ya que el DTO solo
     * contiene su ID.
     *
     * @param eventoDTO DTO de creación
     * @param organizador Entidad Organizador responsable del evento
     * @return Entidad Evento sin persistir
     */
    public Evento toEntity(EventoCreacionDTO eventoDTO, Organizador organizador) {
        if (eventoDTO == null) {
            return null;
        }

        Evento evento = new Evento();
        evento.setTitulo(eventoDTO.getTitulo());
        evento.setDescripcion(eventoDTO.getDescripcion());

        // Convertir modalidad de String a enum
        try {
            evento.setModalidad(ModalidadEvento.valueOf(eventoDTO.getModalidad()));
        } catch (IllegalArgumentException | NullPointerException ex) {
            // Valor por defecto si hay un error
            evento.setModalidad(ModalidadEvento.PRESENCIAL);
        }

        evento.setFechaInicio(eventoDTO.getFechaInicio());
        evento.setFechaFin(eventoDTO.getFechaFin());
        evento.setObservaciones(eventoDTO.getObservaciones());
        evento.setOrganizador(organizador);

        // Estado inicial siempre es PLANEADO
        evento.setEstado(EstadoEvento.PLANEADO);
        
        evento.setActividades(new ArrayList<>());

        return evento;
    }

    /**
     * Actualiza una entidad Evento existente con los datos de un DTO. Este
     * método no modifica las relaciones con otras entidades (Organizador,
     * Actividades).
     *
     * @param evento Entidad Evento a actualizar
     * @param eventoDTO DTO con los nuevos datos
     */
    public void updateEntityFromDTO(Evento evento, EventoDTO eventoDTO) {
        if (evento == null || eventoDTO == null) {
            return;
        }

        evento.setTitulo(eventoDTO.getTitulo());
        evento.setDescripcion(eventoDTO.getDescripcion());

        // Convertir estado y modalidad de String a enum
        try {
            evento.setEstado(EstadoEvento.valueOf(eventoDTO.getEstado()));
        } catch (IllegalArgumentException | NullPointerException ex) {
            // No actualizar si hay un error
        }

        try {
            evento.setModalidad(ModalidadEvento.valueOf(eventoDTO.getModalidad()));
        } catch (IllegalArgumentException | NullPointerException ex) {
            // No actualizar si hay un error
        }

        evento.setFechaInicio(eventoDTO.getFechaInicio());
        evento.setFechaFin(eventoDTO.getFechaFin());
        evento.setObservaciones(eventoDTO.getObservaciones());
    }
    
}
