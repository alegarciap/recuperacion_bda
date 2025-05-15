/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import DTOs.ActividadCreacionDTO;
import DTOs.ActividadDTO;
import DTOs.ActividadDetalleDTO;
import DTOs.ParticipanteDTO;
import entidades.Actividad;
import entidades.Evento;
import entidades.Inscripcion;
import entidades.Lugar;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de transformar objetos Actividad a sus respectivos DTOs y
 * viceversa. Proporciona métodos para convertir entidades a diferentes tipos de
 * DTOs según los requerimientos de la aplicación.
 *
 * @author Alejandra García Preciado - 252444
 */
public class ActividadMapper {

    /**
     * Instancia del mapper de participantes para conversiones anidadas.
     */
    private final ParticipanteMapper participanteMapper;

    /**
     * Constructor que inicializa las dependencias necesarias.
     */
    public ActividadMapper() {
        this.participanteMapper = new ParticipanteMapper();
    }

    /**
     * Convierte una entidad Actividad a un DTO básico de Actividad.
     *
     * @param actividad Entidad Actividad a convertir
     * @return ActividadDTO con la información básica
     */
    public ActividadDTO toDTO(Actividad actividad) {
        if (actividad == null) {
            return null;
        }
        
        String nombreEvento = actividad.getEvento().getTitulo();
        String nombreLugar = actividad.getLugar().getNombre();

        // La colección de inscripciones siempre estará inicializada (aunque vacía)
        int numeroInscritos = actividad.getInscripciones().size();
        
        double porcentajeOcupacion = (numeroInscritos * 100.0) / actividad.getCapacidad();

        ActividadDTO dto = new ActividadDTO();
        dto.setNombre(actividad.getNombre());
        dto.setTipo(actividad.getTipo());
        dto.setFechaHoraInicio(actividad.getFechaHoraInicio());
        dto.setCapacidad(actividad.getCapacidad());
        dto.setDuracion(actividad.getDuracion());
        dto.setFinalizado(actividad.getFinalizado());
        dto.setNombreEvento(nombreEvento);
        dto.setNombreLugar(nombreLugar);
        dto.setNumeroInscritos(numeroInscritos);
        dto.setPorcentajeOcupacion(porcentajeOcupacion);

        return dto;
    }

    /**
     * Convierte una lista de entidades Actividad a una lista de DTOs.
     *
     * @param actividades Lista de entidades Actividad
     * @return Lista de ActividadDTO
     */
    public List<ActividadDTO> toDTOList(List<Actividad> actividades) {
        List<ActividadDTO> dtos = new ArrayList<>();

        if (actividades != null) {
            for (Actividad actividad : actividades) {
                dtos.add(toDTO(actividad));
            }
        }

        return dtos;
    }

    /**
     * Convierte una entidad Actividad a un DTO detallado, incluyendo
     * información de participantes inscritos.
     *
     * @param actividad Entidad Actividad a convertir
     * @return ActividadDetalleDTO con información detallada
     */
    public ActividadDetalleDTO toDetalleDTO(Actividad actividad) {
        if (actividad == null) {
            return null;
        }

        // Convertir a DTO básico primero
        ActividadDTO basicoDTO = toDTO(actividad);

        // Crear DTO detallado con la información del básico
        ActividadDetalleDTO detalleDTO = new ActividadDetalleDTO();
        detalleDTO.setNombre(basicoDTO.getNombre());
        detalleDTO.setTipo(basicoDTO.getTipo());
        detalleDTO.setFechaHoraInicio(basicoDTO.getFechaHoraInicio());
        detalleDTO.setCapacidad(basicoDTO.getCapacidad());
        detalleDTO.setDuracion(basicoDTO.getDuracion());
        detalleDTO.setFinalizado(basicoDTO.getFinalizado());
        detalleDTO.setNombreEvento(basicoDTO.getNombreEvento());
        detalleDTO.setNombreLugar(basicoDTO.getNombreLugar());
        detalleDTO.setNumeroInscritos(basicoDTO.getNumeroInscritos());
        detalleDTO.setPorcentajeOcupacion(basicoDTO.getPorcentajeOcupacion());

        // Convertir participantes inscritos
        List<ParticipanteDTO> participantesDTO = new ArrayList<>();
        for (Inscripcion inscripcion : actividad.getInscripciones()) {
            participantesDTO.add(participanteMapper.toDTO(inscripcion.getParticipante()));
        }
        detalleDTO.setParticipantes(participantesDTO);

        // Calcular hora estimada de finalización
        detalleDTO.calcularHoraEstimadaFin();

        return detalleDTO;
    }

    /**
     * Convierte un DTO de creación a una entidad Actividad. Nota: Las entidades
     * referenciadas (Evento, Lugar) deben ser proporcionadas externamente ya
     * que el DTO solo contiene sus IDs.
     *
     * @param actividadDTO DTO de creación
     * @param evento Entidad Evento a la que pertenecerá la actividad
     * @param lugar Entidad Lugar donde se realizará la actividad
     * @return Entidad Actividad sin persistir
     */
    public Actividad toEntity(ActividadCreacionDTO actividadDTO, Evento evento, Lugar lugar) {
        if (actividadDTO == null) {
            return null;
        }

        Actividad actividad = new Actividad();
        actividad.setNombre(actividadDTO.getNombre());
        actividad.setTipo(actividadDTO.getTipo());
        actividad.setFechaHoraInicio(actividadDTO.getFechaHoraInicio());
        actividad.setCapacidad(actividadDTO.getCapacidad());
        actividad.setDuracion(actividadDTO.getDuracion());
        actividad.setFinalizado(false); // Por defecto, una nueva actividad no está finalizada
        actividad.setEvento(evento);
        actividad.setLugar(lugar);
        actividad.setInscripciones(new ArrayList<>());

        return actividad;
    }

    /**
     * Actualiza una entidad Actividad existente con los datos de un DTO. Este
     * método no modifica las relaciones con otras entidades (Evento, Lugar,
     * Inscripciones).
     *
     * @param actividad Entidad Actividad a actualizar
     * @param actividadDTO DTO con los nuevos datos
     */
    public void updateEntityFromDTO(Actividad actividad, ActividadDTO actividadDTO) {
        if (actividad == null || actividadDTO == null) {
            return;
        }

        actividad.setNombre(actividadDTO.getNombre());
        actividad.setTipo(actividadDTO.getTipo());
        actividad.setFechaHoraInicio(actividadDTO.getFechaHoraInicio());
        actividad.setCapacidad(actividadDTO.getCapacidad());
        actividad.setDuracion(actividadDTO.getDuracion());
        actividad.setFinalizado(actividadDTO.getFinalizado());
    }

}
