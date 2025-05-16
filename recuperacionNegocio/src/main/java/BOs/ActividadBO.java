/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DTOs.ActividadCreacionDTO;
import DTOs.ActividadDTO;
import DTOs.ActividadDetalleDTO;
import entidades.Actividad;
import entidades.Evento;
import entidades.Evento.EstadoEvento;
import entidades.Lugar;
import exception.NegocioException;
import exception.PersistenciaException;
import fabrica.FabricaDAO;
import interfaces.IActividadBO;
import interfaces.IActividadDAO;
import interfaces.IEventoDAO;
import interfaces.ILugarDAO;
import java.time.LocalDateTime;
import java.util.List;
import mapper.ActividadMapper;

/**
 * Implementación de la interfaz IActividadBO. Contiene la lógica de negocio
 * para la gestión de actividades en el sistema.
 *
 * @author Alejandra García Preciado - 252444
 */
public class ActividadBO implements IActividadBO {

    private final IActividadDAO actividadDAO;
    private final IEventoDAO eventoDAO;
    private final ILugarDAO lugarDAO;
    private final ActividadMapper actividadMapper;

    /**
     * Constructor que inicializa las dependencias necesarias.
     */
    public ActividadBO() {
        this.actividadDAO = FabricaDAO.getInstancia().crearActividadDAO();
        this.eventoDAO = FabricaDAO.getInstancia().crearEventoDAO();
        this.lugarDAO = FabricaDAO.getInstancia().crearLugarDAO();
        this.actividadMapper = new ActividadMapper();
    }

    /**
     * Registra una nueva actividad en el sistema.
     *
     * @param actividadDTO DTO con la información de la actividad a registrar
     * @return DTO con la información de la actividad registrada
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    @Override
    public ActividadDTO registrar(ActividadCreacionDTO actividadDTO) throws NegocioException {
        try {
            // Validar que los campos requeridos no sean nulos o vacíos
            validarCamposRequeridos(actividadDTO);

            // Obtener el evento al que pertenecerá la actividad
            Evento evento = eventoDAO.buscarPorId(actividadDTO.getEventoId());
            if (evento == null) {
                throw new NegocioException("El evento especificado no existe.");
            }

            // Validar que el evento esté en estado PLANEADO
            if (evento.getEstado() != EstadoEvento.PLANEADO) {
                throw new NegocioException("Solo se pueden agregar actividades a eventos en estado PLANEADO.");
            }

            // Obtener el lugar donde se realizará la actividad
            Lugar lugar = lugarDAO.buscarPorId(actividadDTO.getLugarId());
            if (lugar == null) {
                throw new NegocioException("El lugar especificado no existe.");
            }

            // Validar que el nombre de la actividad no se repita dentro del mismo evento
            validarNombreUnico(actividadDTO.getNombre(), evento);

            // Validar que no haya conflictos de horario en el mismo lugar
            boolean hayConflicto = verificarConflictosHorario(actividadDTO, actividadDTO.getLugarId());
            if (hayConflicto) {
                throw new NegocioException("Existe un conflicto de horario con otra actividad en el mismo lugar.");
            }

            // Crear y persistir la entidad Actividad
            Actividad actividad = actividadMapper.toEntity(actividadDTO, evento, lugar);
            actividad.setFinalizado(false); // Estado inicial

            actividad = actividadDAO.guardar(actividad);

            // Retornar el DTO con la información de la actividad persistida
            return actividadMapper.toDTO(actividad);

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al registrar la actividad: " + ex.getMessage());
        }
    }

    /**
     * Actualiza la información de una actividad existente.
     *
     * @param id ID de la actividad a actualizar
     * @param actividadDTO DTO con la nueva información
     * @return DTO con la información actualizada
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    @Override
    public ActividadDTO actualizar(Long id, ActividadDTO actividadDTO) throws NegocioException {
        try {
            // Obtener la actividad original
            Actividad actividad = actividadDAO.buscarPorId(id);
            if (actividad == null) {
                throw new NegocioException("La actividad con ID " + id + " no existe.");
            }

            // Validar que no se intente modificar una actividad finalizada
            if (actividad.getFinalizado()) {
                throw new NegocioException("No se puede modificar una actividad finalizada.");
            }

            // Validar que el evento esté en estado PLANEADO
            if (actividad.getEvento().getEstado() != EstadoEvento.PLANEADO) {
                throw new NegocioException("Solo se pueden modificar actividades de eventos en estado PLANEADO.");
            }

            // Validar campos actualizados
            if (actividadDTO.getNombre() == null || actividadDTO.getNombre().trim().isEmpty()) {
                throw new NegocioException("El nombre de la actividad es obligatorio.");
            }

            if (actividadDTO.getTipo() == null || actividadDTO.getTipo().trim().isEmpty()) {
                throw new NegocioException("El tipo de actividad es obligatorio.");
            }

            // Validar que el nombre actualizado no duplique otro nombre en el mismo evento
            if (!actividadDTO.getNombre().equals(actividad.getNombre())) {
                validarNombreUnico(actividadDTO.getNombre(), actividad.getEvento());
            }

            // Actualizar la entidad actividad
            actividadMapper.updateEntityFromDTO(actividad, actividadDTO);

            // Persistir cambios
            actividad = actividadDAO.actualizar(actividad);

            // Retornar el DTO actualizado
            return actividadMapper.toDTO(actividad);

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al actualizar la actividad: " + ex.getMessage());
        }
    }

    /**
     * Consulta una actividad por su ID.
     *
     * @param id ID de la actividad a consultar
     * @return DTO con la información detallada de la actividad
     * @throws NegocioException Si hay errores de persistencia o la actividad no
     * existe
     */
    @Override
    public ActividadDetalleDTO consultar(Long id) throws NegocioException {
        try {
            Actividad actividad = actividadDAO.buscarPorId(id);
            if (actividad == null) {
                throw new NegocioException("La actividad con ID " + id + " no existe.");
            }
            return actividadMapper.toDetalleDTO(actividad);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar la actividad: " + ex.getMessage());
        }
    }

    /**
     * Obtiene todas las actividades registradas en el sistema.
     *
     * @return Lista de DTOs con la información básica de las actividades
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public List<ActividadDTO> consultarTodos() throws NegocioException {
        try {
            List<Actividad> actividades = actividadDAO.consultarTodos();
            return actividadMapper.toDTOList(actividades);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar todas las actividades: " + ex.getMessage());
        }
    }

    /**
     * Consulta actividades por el evento al que pertenecen.
     *
     * @param eventoId ID del evento
     * @return Lista de DTOs con la información de las actividades
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public List<ActividadDTO> consultarPorEvento(Long eventoId) throws NegocioException {
        try {
            if (eventoId == null) {
                throw new NegocioException("El ID del evento no puede ser nulo.");
            }

            Evento evento = eventoDAO.buscarPorId(eventoId);
            if (evento == null) {
                throw new NegocioException("El evento con ID " + eventoId + " no existe.");
            }

            List<Actividad> actividades = actividadDAO.consultarPorEvento(evento);
            return actividadMapper.toDTOList(actividades);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar actividades por evento: " + ex.getMessage());
        }
    }

    /**
     * Consulta actividades por fecha.
     *
     * @param fecha Fecha a consultar
     * @return Lista de DTOs con la información de las actividades
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public List<ActividadDTO> consultarPorFecha(LocalDateTime fecha) throws NegocioException {
        try {
            if (fecha == null) {
                throw new NegocioException("La fecha no puede ser nula.");
            }

            // Definir un rango de 24 horas para la búsqueda
            LocalDateTime fechaInicio = fecha.toLocalDate().atStartOfDay();
            LocalDateTime fechaFin = fechaInicio.plusDays(1).minusSeconds(1);

            List<Actividad> actividades = actividadDAO.consultarPorRangoFechas(fechaInicio, fechaFin);
            return actividadMapper.toDTOList(actividades);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar actividades por fecha: " + ex.getMessage());
        }
    }

    /**
     * Marca una actividad como finalizada.
     *
     * @param id ID de la actividad a finalizar
     * @return DTO con la información actualizada
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    @Override
    public ActividadDTO finalizarActividad(Long id) throws NegocioException {
        try {
            Actividad actividad = actividadDAO.buscarPorId(id);
            if (actividad == null) {
                throw new NegocioException("La actividad con ID " + id + " no existe.");
            }

            // Verificar si ya está finalizada
            if (actividad.getFinalizado()) {
                throw new NegocioException("La actividad ya está finalizada.");
            }

            // Marcar como finalizada
            actividad.setFinalizado(true);
            actividad = actividadDAO.actualizar(actividad);

            return actividadMapper.toDTO(actividad);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al finalizar la actividad: " + ex.getMessage());
        }
    }

    /**
     * Verifica si hay conflictos de horario entre actividades en el mismo
     * lugar.
     *
     * @param actividadDTO DTO con la información de la actividad a verificar
     * @param lugarId ID del lugar a verificar
     * @return true si hay conflictos, false si no los hay
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public boolean verificarConflictosHorario(ActividadCreacionDTO actividadDTO, Long lugarId) throws NegocioException {
        try {
            if (actividadDTO == null || lugarId == null) {
                throw new NegocioException("La actividad y el lugar no pueden ser nulos.");
            }

            // Obtener el lugar
            Lugar lugar = lugarDAO.buscarPorId(lugarId);
            if (lugar == null) {
                throw new NegocioException("El lugar con ID " + lugarId + " no existe.");
            }

            // Obtener todas las actividades en el mismo lugar
            List<Actividad> actividades = actividadDAO.consultarPorLugar(lugar);
            if (actividades.isEmpty()) {
                return false; // No hay otras actividades en este lugar
            }

            // Calcular la hora de finalización de la nueva actividad
            LocalDateTime horaInicio = actividadDTO.getFechaHoraInicio();
            LocalDateTime horaFin = horaInicio.plusMinutes(actividadDTO.getDuracion());

            // Verificar si hay otras actividades
            for (Actividad actividad : actividades) {
                // Si la actividad está finalizada, no se considera para conflictos
                if (actividad.getFinalizado()) {
                    continue;
                }

                // Calcular hora de finalización de la actividad existente
                LocalDateTime actividadInicio = actividad.getFechaHoraInicio();
                LocalDateTime actividadFin = actividadInicio.plusMinutes(actividad.getDuracion());

                // Verificar si se traslapan
                // Caso 1: La nueva actividad comienza durante otra actividad
                // Caso 2: La nueva actividad termina durante otra actividad
                // Caso 3: La nueva actividad abarca completamente otra actividad
                if ((horaInicio.isBefore(actividadFin) && horaInicio.isAfter(actividadInicio))
                        || (horaFin.isAfter(actividadInicio) && horaFin.isBefore(actividadFin))
                        || (horaInicio.isBefore(actividadInicio) && horaFin.isAfter(actividadFin))
                        || (horaInicio.isEqual(actividadInicio))) {
                    return true; // Hay PROBLEMASSS
                }
            }

            return false; // No hay 
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al verificar conflictos de horario: " + ex.getMessage());
        }
    }

    /**
     * Valida que los campos requeridos del DTO no sean nulos o vacíos.
     *
     * @param actividadDTO DTO a validar
     * @throws NegocioException Si algún campo requerido es inválido
     */
    private void validarCamposRequeridos(ActividadCreacionDTO actividadDTO) throws NegocioException {
        if (actividadDTO == null) {
            throw new NegocioException("La información de la actividad no puede ser nula.");
        }

        if (actividadDTO.getNombre() == null || actividadDTO.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre de la actividad es obligatorio.");
        }

        if (actividadDTO.getTipo() == null || actividadDTO.getTipo().trim().isEmpty()) {
            throw new NegocioException("El tipo de actividad es obligatorio.");
        }

        if (actividadDTO.getFechaHoraInicio() == null) {
            throw new NegocioException("La fecha y hora de inicio son obligatorias.");
        }

        if (actividadDTO.getCapacidad() == null || actividadDTO.getCapacidad() <= 0) {
            throw new NegocioException("La capacidad debe ser un número positivo.");
        }

        if (actividadDTO.getDuracion() == null || actividadDTO.getDuracion() <= 0) {
            throw new NegocioException("La duración debe ser un número positivo.");
        }

        if (actividadDTO.getEventoId() == null) {
            throw new NegocioException("El evento de la actividad es obligatorio.");
        }

        if (actividadDTO.getLugarId() == null) {
            throw new NegocioException("El lugar de la actividad es obligatorio.");
        }

        // Validar que la fecha de inicio sea futura
        if (actividadDTO.getFechaHoraInicio().isBefore(LocalDateTime.now())) {
            throw new NegocioException("La fecha y hora de inicio deben ser futuras.");
        }
    }

    /**
     * Valida que el nombre de la actividad no se repita dentro del mismo
     * evento.
     *
     * @param nombre Nombre a validar
     * @param evento Evento al que pertenecerá la actividad
     * @throws NegocioException Si el nombre ya existe en el evento
     * @throws PersistenciaException Si hay errores de persistencia
     */
    private void validarNombreUnico(String nombre, Evento evento) throws NegocioException, PersistenciaException {
        List<Actividad> actividades = actividadDAO.consultarPorEvento(evento);
        for (Actividad actividad : actividades) {
            if (actividad.getNombre().equalsIgnoreCase(nombre.trim())) {
                throw new NegocioException("Ya existe una actividad con el nombre '" + nombre + "' en este evento.");
            }
        }
    }

}
