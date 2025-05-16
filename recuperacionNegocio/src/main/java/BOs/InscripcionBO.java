/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DTOs.InscripcionCreacionDTO;
import DTOs.InscripcionDTO;
import entidades.Actividad;
import entidades.Inscripcion;
import entidades.Inscripcion.EstadoAsistencia;
import entidades.Participante;
import exception.NegocioException;
import exception.PersistenciaException;
import fabrica.FabricaDAO;
import interfaces.IActividadDAO;
import interfaces.IInscripcionBO;
import interfaces.IInscripcionDAO;
import interfaces.IParticipanteDAO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;
import mapper.InscripcionMapper;

/**
 * Implementación de la interfaz IInscripcionBO. Contiene la lógica de negocio
 * para la gestión de inscripciones de participantes a actividades.
 *
 * @author Alejandra García Preciado - 252444
 */
public class InscripcionBO implements IInscripcionBO {

    private final IInscripcionDAO inscripcionDAO;
    private final IParticipanteDAO participanteDAO;
    private final IActividadDAO actividadDAO;
    private final InscripcionMapper inscripcionMapper;

    /**
     * Constructor que inicializa las dependencias necesarias.
     */
    public InscripcionBO() {
        this.inscripcionDAO = FabricaDAO.getInstancia().crearInscripcionDAO();
        this.participanteDAO = FabricaDAO.getInstancia().crearParticipanteDAO();
        this.actividadDAO = FabricaDAO.getInstancia().crearActividadDAO();
        this.inscripcionMapper = new InscripcionMapper();
    }

    /**
     * Registra una nueva inscripción de un participante a una actividad.
     *
     * @param inscripcionDTO DTO con la información de la inscripción
     * @return DTO con la información de la inscripción registrada
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    @Override
    public InscripcionDTO registrar(InscripcionCreacionDTO inscripcionDTO) throws NegocioException {
        try {
            // Validar que los campos requeridos no sean nulos
            if (inscripcionDTO == null) {
                throw new NegocioException("La información de la inscripción no puede ser nula.");
            }

            if (inscripcionDTO.getParticipanteId() == null) {
                throw new NegocioException("El participante es obligatorio.");
            }

            if (inscripcionDTO.getActividadId() == null) {
                throw new NegocioException("La actividad es obligatoria.");
            }

            // Verificar que el participante exista
            Participante participante = participanteDAO.buscarPorId(inscripcionDTO.getParticipanteId());
            if (participante == null) {
                throw new NegocioException("El participante con ID " + inscripcionDTO.getParticipanteId() + " no existe.");
            }

            // Verificar que la actividad exista
            Actividad actividad = actividadDAO.buscarPorId(inscripcionDTO.getActividadId());
            if (actividad == null) {
                throw new NegocioException("La actividad con ID " + inscripcionDTO.getActividadId() + " no existe.");
            }

            // Verificar que la actividad no esté finalizada
            if (actividad.getFinalizado()) {
                throw new NegocioException("No se pueden realizar inscripciones a una actividad finalizada.");
            }

            // Verificar que el participante no esté ya inscrito a esta actividad
            boolean inscritoExistente = verificarInscripcionExistente(
                    inscripcionDTO.getParticipanteId(), inscripcionDTO.getActividadId());
            if (inscritoExistente) {
                throw new NegocioException("El participante ya está inscrito a esta actividad.");
            }

            // Verificar que haya cupo disponible en la actividad
            boolean hayEspacio = verificarCupoDisponible(inscripcionDTO.getActividadId());
            if (!hayEspacio) {
                throw new NegocioException("La actividad ha alcanzado su capacidad máxima.");
            }

            // Crear y persistir la entidad Inscripcion
            Inscripcion inscripcion = inscripcionMapper.toEntity(inscripcionDTO, participante, actividad);
            inscripcion.setFechaHora(LocalDateTime.now());

            inscripcion = inscripcionDAO.guardar(inscripcion);

            // Retornar el DTO con la información de la inscripción persistida
            return inscripcionMapper.toDTO(inscripcion);

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al registrar la inscripción: " + ex.getMessage());
        }
    }

    /**
     * Cancela una inscripción existente.
     *
     * @param id ID de la inscripción a cancelar
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    @Override
    public void cancelar(Long id) throws NegocioException {
        try {
            // Verificar que la inscripción exista
            Inscripcion inscripcion = inscripcionDAO.buscarPorId(id);
            if (inscripcion == null) {
                throw new NegocioException("La inscripción con ID " + id + " no existe.");
            }

            // Verificar que la actividad no esté finalizada
            if (inscripcion.getActividad().getFinalizado()) {
                throw new NegocioException("No se puede cancelar una inscripción de una actividad finalizada.");
            }

            // Eliminar la inscripción
            inscripcionDAO.eliminar(id);

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al cancelar la inscripción: " + ex.getMessage());
        }
    }

    /**
     * Obtiene todas las inscripciones de una actividad identificada por nombre
     * y fecha.
     *
     * @param nombreActividad Nombre de la actividad
     * @param fechaHoraStr Fecha y hora de inicio en formato string
     * @return Lista de DTOs con la información de las inscripciones
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public List<InscripcionDTO> consultarPorActividad(String nombreActividad, String fechaHoraStr) throws NegocioException {
        try {
            // Convertir la fecha/hora de string a LocalDateTime
            LocalDateTime fechaHora;
            try {
                // Intentar diferentes formatos de fecha/hora
                DateTimeFormatter formatter;
                if (fechaHoraStr.contains("T")) {
                    formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                } else if (fechaHoraStr.contains("/")) {
                    formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                } else {
                    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                }
                fechaHora = LocalDateTime.parse(fechaHoraStr, formatter);
            } catch (DateTimeParseException ex) {
                throw new NegocioException("Formato de fecha/hora inválido: " + fechaHoraStr);
            }

            // Buscar actividades que coincidan con nombre y fecha
            List<Actividad> actividades = actividadDAO.consultarTodos().stream()
                    .filter(a -> a.getNombre().equals(nombreActividad)
                    && a.getFechaHoraInicio().isEqual(fechaHora))
                    .collect(Collectors.toList());

            if (actividades.isEmpty()) {
                throw new NegocioException("No se encontró la actividad especificada.");
            }

            // Tomar la primera actividad que coincida
            Actividad actividad = actividades.get(0);

            // Consultar inscripciones
            List<Inscripcion> inscripciones = inscripcionDAO.consultarPorActividad(actividad);
            return inscripcionMapper.toDTOList(inscripciones);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar inscripciones por actividad: " + ex.getMessage());
        }
    }

    /**
     * Registra la asistencia de un participante a una actividad.
     *
     * @param inscripcionDTO Objeto InscripcionDTO con la información de la
     * inscripción
     * @param asistio true si el participante asistió, false si no asistió
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    @Override
    public void registrarAsistencia(InscripcionDTO inscripcionDTO, boolean asistio) throws NegocioException {
        try {
            // Buscar la inscripción por los datos proporcionados
            // Buscar la actividad por nombre
            List<Actividad> actividades = actividadDAO.consultarTodos().stream()
                    .filter(a -> a.getNombre().equals(inscripcionDTO.getNombreActividad()))
                    .collect(Collectors.toList());

            if (actividades.isEmpty()) {
                throw new NegocioException("No se encontró la actividad especificada.");
            }

            // Buscar la inscripción adecuada
            Inscripcion inscripcionEncontrada = null;

            for (Actividad actividad : actividades) {
                List<Inscripcion> inscripciones = inscripcionDAO.consultarPorActividad(actividad);
                for (Inscripcion insc : inscripciones) {
                    // Verificar si coincide con los datos del DTO
                    String nombreCompleto = insc.getParticipante().getNombre() + " "
                            + insc.getParticipante().getApellidoPaterno() + " "
                            + insc.getParticipante().getApellidoMaterno();

                    if (nombreCompleto.equals(inscripcionDTO.getNombreParticipante())
                            && insc.getParticipante().getCorreo().equals(inscripcionDTO.getCorreoParticipante())) {
                        inscripcionEncontrada = insc;
                        break;
                    }
                }

                if (inscripcionEncontrada != null) {
                    break;
                }
            }

            if (inscripcionEncontrada == null) {
                throw new NegocioException("No se encontró la inscripción especificada.");
            }

            // Actualizar estado y aplicar cambios en contador de asistencias
            EstadoAsistencia nuevoEstado;
            if (asistio) {
                nuevoEstado = EstadoAsistencia.ASISTIO;
            } else {
                nuevoEstado = EstadoAsistencia.NO_ASISTIO;
            }
            inscripcionMapper.updateEstadoAsistencia(inscripcionEncontrada, nuevoEstado.toString());

            // Guardar cambios
            inscripcionDAO.actualizar(inscripcionEncontrada);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al registrar la asistencia: " + ex.getMessage());
        }
    }

    /**
     * Obtiene todas las inscripciones de un participante.
     *
     * @param participanteId ID del participante
     * @return Lista de DTOs con la información de las inscripciones
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public List<InscripcionDTO> consultarPorParticipante(Long participanteId) throws NegocioException {
        try {
            if (participanteId == null) {
                throw new NegocioException("El ID del participante no puede ser nulo.");
            }

            // Verificar que el participante exista
            Participante participante = participanteDAO.buscarPorId(participanteId);
            if (participante == null) {
                throw new NegocioException("El participante con ID " + participanteId + " no existe.");
            }

            // Consultar inscripciones
            List<Inscripcion> inscripciones = inscripcionDAO.consultarPorParticipante(participante);
            return inscripcionMapper.toDTOList(inscripciones);

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar inscripciones por participante: " + ex.getMessage());
        }
    }

    /**
     * Verifica si un participante ya está inscrito a una actividad.
     *
     * @param participanteId ID del participante
     * @param actividadId ID de la actividad
     * @return true si ya está inscrito, false si no lo está
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public boolean verificarInscripcionExistente(Long participanteId, Long actividadId) throws NegocioException {
        try {
            // Verificar parámetros
            if (participanteId == null || actividadId == null) {
                throw new NegocioException("Los IDs de participante y actividad no pueden ser nulos.");
            }

            // Buscar el participante y la actividad
            Participante participante = participanteDAO.buscarPorId(participanteId);
            if (participante == null) {
                throw new NegocioException("El participante con ID " + participanteId + " no existe.");
            }

            Actividad actividad = actividadDAO.buscarPorId(actividadId);
            if (actividad == null) {
                throw new NegocioException("La actividad con ID " + actividadId + " no existe.");
            }

            // Verificar si ya existe una inscripción
            Inscripcion inscripcion = inscripcionDAO.buscarPorParticipanteYActividad(participante, actividad);
            return inscripcion != null;

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al verificar inscripción existente: " + ex.getMessage());
        }
    }

    /**
     * Verifica si una actividad tiene cupo disponible.
     *
     * @param actividadId ID de la actividad
     * @return true si hay cupo disponible, false si está llena
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public boolean verificarCupoDisponible(Long actividadId) throws NegocioException {
        try {
            // Verificar parámetro
            if (actividadId == null) {
                throw new NegocioException("El ID de la actividad no puede ser nulo.");
            }

            // Buscar la actividad
            Actividad actividad = actividadDAO.buscarPorId(actividadId);
            if (actividad == null) {
                throw new NegocioException("La actividad con ID " + actividadId + " no existe.");
            }

            // Obtener la capacidad máxima
            int capacidadMaxima = actividad.getCapacidad();

            // Obtener el número de inscripciones actuales
            List<Inscripcion> inscripciones = inscripcionDAO.consultarPorActividad(actividad);
            int inscripcionesActuales = inscripciones.size();

            // Verificar si hay cupo disponible
            return inscripcionesActuales < capacidadMaxima;

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al verificar cupo disponible: " + ex.getMessage());
        }
    }

}
