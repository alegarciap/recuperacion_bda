/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import DTOs.InscripcionCreacionDTO;
import DTOs.InscripcionDTO;
import entidades.Actividad;
import entidades.Inscripcion;
import entidades.Inscripcion.EstadoAsistencia;
import entidades.Participante;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de transformar objetos Inscripcion a sus respectivos DTOs y
 * viceversa. Proporciona métodos para convertir entidades a DTOs según los
 * requerimientos de la aplicación.
 *
 * @author Alejandra García Preciado - 252444
 */
public class InscripcionMapper {

    /**
     * Constructor por defecto.
     */
    public InscripcionMapper() {
    }

    /**
     * Convierte una entidad Inscripcion a un DTO de Inscripcion.
     *
     * @param inscripcion Entidad Inscripcion a convertir
     * @return InscripcionDTO con la información
     */
    public InscripcionDTO toDTO(Inscripcion inscripcion) {
        if (inscripcion == null) {
            return null;
        }

        // Obtener los datos del participante 
        Participante participante = inscripcion.getParticipante();
        String nombreParticipante = participante.getNombre() + " "
                + participante.getApellidoPaterno() + " "
                + participante.getApellidoMaterno();
        String correoParticipante = participante.getCorreo();

        // Determinar tipo de participante
        String tipoParticipante = "";
        if (participante instanceof entidades.ParticipanteEstudiante) {
            tipoParticipante = "ESTUDIANTE";
        } else if (participante instanceof entidades.ParticipanteDocente) {
            tipoParticipante = "DOCENTE";
        } else if (participante instanceof entidades.ParticipanteExterno) {
            tipoParticipante = "EXTERNO";
        }

        // Obtener los datos de la actividad y evento 
        Actividad actividad = inscripcion.getActividad();
        String nombreActividad = actividad.getNombre();
        String nombreEvento = actividad.getEvento().getTitulo();

        InscripcionDTO dto = new InscripcionDTO();
        dto.setFechaHora(inscripcion.getFechaHora());

        // El estado de asistencia puede ser null 
        if (inscripcion.getEstadoAsistencia() != null) {
            dto.setEstadoAsistencia(inscripcion.getEstadoAsistencia().toString());
        }

        dto.setNombreParticipante(nombreParticipante);
        dto.setCorreoParticipante(correoParticipante);
        dto.setTipoParticipante(tipoParticipante);
        dto.setNombreActividad(nombreActividad);
        dto.setNombreEvento(nombreEvento);

        return dto;
    }

    /**
     * Convierte una lista de entidades Inscripcion a una lista de DTOs.
     *
     * @param inscripciones Lista de entidades Inscripcion
     * @return Lista de InscripcionDTO
     */
    public List<InscripcionDTO> toDTOList(List<Inscripcion> inscripciones) {
        List<InscripcionDTO> dtos = new ArrayList<>();

        if (inscripciones != null) {
            for (Inscripcion inscripcion : inscripciones) {
                dtos.add(toDTO(inscripcion));
            }
        }

        return dtos;
    }

    /**
     * Convierte un DTO de creación a una entidad Inscripcion. Nota: Las
     * entidades Participante y Actividad deben ser proporcionadas externamente
     * ya que el DTO solo contiene sus IDs.
     *
     * @param inscripcionDTO DTO de creación
     * @param participante Entidad Participante de la inscripción
     * @param actividad Entidad Actividad de la inscripción
     * @return Entidad Inscripcion sin persistir
     */
    public Inscripcion toEntity(InscripcionCreacionDTO inscripcionDTO, Participante participante, Actividad actividad) {
        if (inscripcionDTO == null) {
            return null;
        }

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setFechaHora(LocalDateTime.now());
        inscripcion.setParticipante(participante);
        inscripcion.setActividad(actividad);
        
        return inscripcion;
    }

    /**
     * Actualiza el estado de asistencia de una inscripción.
     *
     * @param inscripcion Entidad Inscripcion a actualizar
     * @param estadoAsistencia Nuevo estado de asistencia como string
     */
    public void updateEstadoAsistencia(Inscripcion inscripcion, String estadoAsistencia) {
        if (inscripcion == null || estadoAsistencia == null) {
            return;
        }

        try {
            EstadoAsistencia estado = EstadoAsistencia.valueOf(estadoAsistencia);
            inscripcion.setEstadoAsistencia(estado);

            // Si el participante asistió, actualizar su contador
            if (estado == EstadoAsistencia.ASISTIO && inscripcion.getParticipante() != null) {
                Participante participante = inscripcion.getParticipante();
                Integer cantidadActual = participante.getCantidadAsistencias();
                if (cantidadActual == null) {
                    cantidadActual = 0;
                }
                participante.setCantidadAsistencias(cantidadActual + 1);
            }
        } catch (IllegalArgumentException ex) {
            // No hacer nada si el estado no es válido
        }
    }

}
