/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DTOs.EventoCreacionDTO;
import DTOs.EventoDTO;
import DTOs.EventoDetalleDTO;
import entidades.Evento;
import entidades.Evento.EstadoEvento;
import static entidades.Evento.EstadoEvento.EN_CURSO;
import static entidades.Evento.EstadoEvento.FINALIZADO;
import static entidades.Evento.EstadoEvento.PLANEADO;
import entidades.Evento.ModalidadEvento;
import entidades.Organizador;
import exception.NegocioException;
import exception.PersistenciaException;
import fabrica.FabricaDAO;
import interfaces.IEventoBO;
import interfaces.IEventoDAO;
import interfaces.IOrganizadorDAO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import mapper.EventoMapper;

/**
 * Implementación de la interfaz IEventoBO. Contiene la lógica de negocio para
 * la gestión de eventos en el sistema.
 *
 * @author Alejandra García Preciado - 252444
 */
public class EventoBO implements IEventoBO {

    private final IEventoDAO eventoDAO;
    private final IOrganizadorDAO organizadorDAO;
    private final EventoMapper eventoMapper;

    /**
     * Constructor que inicializa las dependencias necesarias.
     */
    public EventoBO() {
        this.eventoDAO = FabricaDAO.getInstancia().crearEventoDAO();
        this.organizadorDAO = FabricaDAO.getInstancia().crearOrganizadorDAO();
        this.eventoMapper = new EventoMapper();
    }

    /**
     * Registra un nuevo evento en el sistema.
     *
     * @param eventoDTO DTO con la información del evento a registrar
     * @return DTO con la información del evento registrado
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    @Override
    public EventoDTO registrar(EventoCreacionDTO eventoDTO) throws NegocioException {
        try {
            // Validar que los campos requeridos no sean nulos o vacíos
            validarCamposRequeridos(eventoDTO);

            // Validar que el título no esté duplicado para el periodo actual
            if (!validarTituloDisponible(eventoDTO.getTitulo())) {
                throw new NegocioException("Ya existe un evento con el mismo título en el periodo actual.");
            }

            // Validar fechas
            validarFechas(eventoDTO.getFechaInicio(), eventoDTO.getFechaFin());

            // Obtener el organizador
            Organizador organizador = organizadorDAO.buscarPorId(eventoDTO.getOrganizadorId());
            if (organizador == null) {
                throw new NegocioException("El organizador especificado no existe.");
            }

            // Generar el código único del evento
            String codigo = generarCodigo();

            // Crear y persistir la entidad Evento
            Evento evento = eventoMapper.toEntity(eventoDTO, organizador);
            evento.setCodigo(codigo);
            evento.setEstado(EstadoEvento.PLANEADO); // Estado inicial

            evento = eventoDAO.guardar(evento);

            // Retornar el DTO con la información del evento persistido
            return eventoMapper.toDTO(evento);

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al registrar el evento: " + ex.getMessage());
        }
    }

    /**
     * Actualiza la información de un evento existente.
     *
     * @param id ID del evento a actualizar
     * @param eventoDTO DTO con la nueva información
     * @return DTO con la información actualizada
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    @Override
    public EventoDTO actualizar(Long id, EventoDTO eventoDTO) throws NegocioException {
        try {
            // Obtener el evento original
            Evento evento = eventoDAO.buscarPorId(id);
            if (evento == null) {
                throw new NegocioException("El evento con ID " + id + " no existe.");
            }

            // Validar que no se intente modificar un evento finalizado
            if (evento.getEstado() == EstadoEvento.FINALIZADO) {
                throw new NegocioException("No se puede modificar un evento finalizado.");
            }

            // Validar campos actualizados
            if (eventoDTO.getTitulo() == null || eventoDTO.getTitulo().trim().isEmpty()) {
                throw new NegocioException("El título del evento es obligatorio.");
            }

            if (eventoDTO.getDescripcion() == null || eventoDTO.getDescripcion().trim().isEmpty()) {
                throw new NegocioException("La descripción del evento es obligatoria.");
            }

            // Validar fechas
            validarFechas(eventoDTO.getFechaInicio(), eventoDTO.getFechaFin());

            // Actualizar la entidad evento
            eventoMapper.updateEntityFromDTO(evento, eventoDTO);

            // Persistir cambios
            evento = eventoDAO.actualizar(evento);

            // Retornar el DTO actualizado
            return eventoMapper.toDTO(evento);

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al actualizar el evento: " + ex.getMessage());
        }
    }

    /**
     * Consulta un evento por su ID.
     *
     * @param id ID del evento a consultar
     * @return DTO con la información detallada del evento
     * @throws NegocioException Si hay errores de persistencia o el evento no
     * existe
     */
    @Override
    public EventoDetalleDTO consultar(Long id) throws NegocioException {
        try {
            Evento evento = eventoDAO.buscarPorId(id);
            if (evento == null) {
                throw new NegocioException("El evento con ID " + id + " no existe.");
            }
            return eventoMapper.toDetalleDTO(evento);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar el evento: " + ex.getMessage());
        }
    }

    /**
     * Consulta un evento por su código único.
     *
     * @param codigo Código único del evento
     * @return DTO con la información detallada del evento
     * @throws NegocioException Si hay errores de persistencia o el evento no
     * existe
     */
    @Override
    public EventoDetalleDTO consultarPorCodigo(String codigo) throws NegocioException {
        try {
            Evento evento = eventoDAO.buscarPorCodigo(codigo);
            if (evento == null) {
                throw new NegocioException("El evento con código " + codigo + " no existe.");
            }
            return eventoMapper.toDetalleDTO(evento);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar el evento por código: " + ex.getMessage());
        }
    }

    /**
     * Obtiene todos los eventos registrados en el sistema.
     *
     * @return Lista de DTOs con la información básica de los eventos
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public List<EventoDTO> consultarTodos() throws NegocioException {
        try {
            List<Evento> eventos = eventoDAO.consultarTodos();
            return eventoMapper.toDTOList(eventos);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar todos los eventos: " + ex.getMessage());
        }
    }

    /**
     * Consulta eventos por su título.
     *
     * @param titulo Título o parte del título a buscar
     * @return Lista de DTOs con la información de los eventos
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public List<EventoDTO> consultarPorTitulo(String titulo) throws NegocioException {
        try {
            if (titulo == null || titulo.trim().isEmpty()) {
                throw new NegocioException("El título a buscar no puede estar vacío.");
            }
            List<Evento> eventos = eventoDAO.consultarPorTitulo(titulo);
            return eventoMapper.toDTOList(eventos);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar eventos por título: " + ex.getMessage());
        }
    }

    /**
     * Consulta eventos por su estado.
     *
     * @param estado Estado a buscar (PLANEADO, EN_CURSO, FINALIZADO)
     * @return Lista de DTOs con la información de los eventos
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public List<EventoDTO> consultarPorEstado(String estado) throws NegocioException {
        try {
            if (estado == null || estado.trim().isEmpty()) {
                throw new NegocioException("El estado a buscar no puede estar vacío.");
            }

            EstadoEvento estadoEvento;
            try {
                estadoEvento = EstadoEvento.valueOf(estado);
            } catch (IllegalArgumentException ex) {
                throw new NegocioException("Estado no válido. Valores permitidos: PLANEADO, EN_CURSO, FINALIZADO");
            }

            List<Evento> eventos = eventoDAO.consultarPorEstado(estadoEvento);
            return eventoMapper.toDTOList(eventos);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar eventos por estado: " + ex.getMessage());
        }
    }

    /**
     * Consulta eventos por su modalidad.
     *
     * @param modalidad Modalidad a buscar (PRESENCIAL, EN_LINEA, HIBRIDO)
     * @return Lista de DTOs con la información de los eventos
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public List<EventoDTO> consultarPorModalidad(String modalidad) throws NegocioException {
        try {
            if (modalidad == null || modalidad.trim().isEmpty()) {
                throw new NegocioException("La modalidad a buscar no puede estar vacía.");
            }

            ModalidadEvento modalidadEvento;
            try {
                modalidadEvento = ModalidadEvento.valueOf(modalidad);
            } catch (IllegalArgumentException ex) {
                throw new NegocioException("Modalidad no válida. Valores permitidos: PRESENCIAL, EN_LINEA, HIBRIDO");
            }

            List<Evento> eventos = eventoDAO.consultarPorModalidad(modalidadEvento);
            return eventoMapper.toDTOList(eventos);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar eventos por modalidad: " + ex.getMessage());
        }
    }

    /**
     * Consulta eventos por un rango de fechas.
     *
     * @param fechaInicio Fecha de inicio del rango
     * @param fechaFin Fecha de fin del rango
     * @return Lista de DTOs con la información de los eventos
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public List<EventoDTO> consultarPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) throws NegocioException {
        try {
            // Validar fechas
            if (fechaInicio == null || fechaFin == null) {
                throw new NegocioException("Ambas fechas deben ser proporcionadas.");
            }

            if (fechaInicio.isAfter(fechaFin)) {
                throw new NegocioException("La fecha de inicio no puede ser posterior a la fecha de fin.");
            }

            // Convertir LocalDate a LocalDateTime para la búsqueda
            LocalDateTime inicio = fechaInicio.atStartOfDay();
            LocalDateTime fin = fechaFin.atTime(23, 59, 59);

            List<Evento> eventos = eventoDAO.consultarPorRangoFechas(inicio, fin);
            return eventoMapper.toDTOList(eventos);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar eventos por rango de fechas: " + ex.getMessage());
        }
    }

    /**
     * Cambia el estado de un evento.
     *
     * @param id ID del evento
     * @param nuevoEstado Nuevo estado a establecer
     * @return DTO con la información actualizada
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    @Override
    public EventoDTO cambiarEstado(Long id, String nuevoEstado) throws NegocioException {
        try {
            // Validar que el estado sea válido
            if (nuevoEstado == null || nuevoEstado.trim().isEmpty()) {
                throw new NegocioException("El nuevo estado no puede estar vacío.");
            }

            EstadoEvento estado;
            try {
                estado = EstadoEvento.valueOf(nuevoEstado);
            } catch (IllegalArgumentException ex) {
                throw new NegocioException("Estado no válido. Valores permitidos: PLANEADO, EN_CURSO, FINALIZADO");
            }

            // Obtener el evento
            Evento evento = eventoDAO.buscarPorId(id);
            if (evento == null) {
                throw new NegocioException("El evento con ID " + id + " no existe.");
            }

            // Validar la transición de estado
            validarTransicionEstado(evento.getEstado(), estado);

            // Actualizar el estado
            evento.setEstado(estado);
            evento = eventoDAO.actualizar(evento);

            return eventoMapper.toDTO(evento);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al cambiar el estado del evento: " + ex.getMessage());
        }
    }

    /**
     * Genera un código único para un nuevo evento.
     *
     * @return Código único en formato EV-YYYYMM-XXX
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public String generarCodigo() throws NegocioException {
        try {
            // Obtener el año y mes actual
            YearMonth yearMonth = YearMonth.now();
            String fechaParte = yearMonth.format(DateTimeFormatter.ofPattern("yyyyMM"));

            // Contar eventos para este periodo y generar el número secuencial
            int contador = 1; // Valor por defecto
            List<Evento> eventos = eventoDAO.consultarTodos();
            if (eventos != null && !eventos.isEmpty()) {
                List<Evento> eventosDelPeriodo = new ArrayList<>();
                String prefijo = "EV-" + fechaParte + "-";

                // Filtrar eventos del periodo actual y encontrar el número más alto
                for (Evento evento : eventos) {
                    if (evento.getCodigo() != null && evento.getCodigo().startsWith(prefijo)) {
                        eventosDelPeriodo.add(evento);

                        try {
                            String secuencialStr = evento.getCodigo().substring(prefijo.length());
                            int secuencial = Integer.parseInt(secuencialStr);
                            if (secuencial >= contador) {
                                contador = secuencial + 1;
                            }
                        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                            // Ignorar códigos mal formados
                        }
                    }
                }
            }

            // Formatear el código
            return String.format("EV-%s-%03d", fechaParte, contador);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al generar código para el evento: " + ex.getMessage());
        }
    }

    /**
     * Valida si un título está disponible (no duplicado) para el periodo
     * actual.
     *
     * @param titulo Título a validar
     * @return true si está disponible, false si ya existe
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public boolean validarTituloDisponible(String titulo) throws NegocioException {
        try {
            if (titulo == null || titulo.trim().isEmpty()) {
                throw new NegocioException("El título no puede estar vacío.");
            }

            // Obtener el año y mes actual
            YearMonth yearMonth = YearMonth.now();
            LocalDateTime inicioMes = yearMonth.atDay(1).atStartOfDay();
            LocalDateTime finMes = yearMonth.atEndOfMonth().atTime(23, 59, 59);

            // Obtener eventos del periodo actual
            List<Evento> eventos = eventoDAO.consultarPorRangoFechas(inicioMes, finMes);

            // Verificar si ya existe un evento con el mismo título
            for (Evento evento : eventos) {
                if (evento.getTitulo().equalsIgnoreCase(titulo.trim())) {
                    return false; // Ya existe un evento con este título
                }
            }

            return true; // El título está disponible
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al validar disponibilidad del título: " + ex.getMessage());
        }
    }

    /**
     * Valida que los campos requeridos del DTO no sean nulos o vacíos.
     *
     * @param eventoDTO DTO a validar
     * @throws NegocioException Si algún campo requerido es inválido
     */
    private void validarCamposRequeridos(EventoCreacionDTO eventoDTO) throws NegocioException {
        if (eventoDTO == null) {
            throw new NegocioException("La información del evento no puede ser nula.");
        }

        if (eventoDTO.getTitulo() == null || eventoDTO.getTitulo().trim().isEmpty()) {
            throw new NegocioException("El título del evento es obligatorio.");
        }

        if (eventoDTO.getDescripcion() == null || eventoDTO.getDescripcion().trim().isEmpty()) {
            throw new NegocioException("La descripción del evento es obligatoria.");
        }

        if (eventoDTO.getModalidad() == null || eventoDTO.getModalidad().trim().isEmpty()) {
            throw new NegocioException("La modalidad del evento es obligatoria.");
        }

        try {
            ModalidadEvento.valueOf(eventoDTO.getModalidad());
        } catch (IllegalArgumentException ex) {
            throw new NegocioException("Modalidad no válida. Valores permitidos: PRESENCIAL, EN_LINEA, HIBRIDO");
        }

        if (eventoDTO.getFechaInicio() == null) {
            throw new NegocioException("La fecha de inicio del evento es obligatoria.");
        }

        if (eventoDTO.getFechaFin() == null) {
            throw new NegocioException("La fecha de fin del evento es obligatoria.");
        }

        if (eventoDTO.getOrganizadorId() == null) {
            throw new NegocioException("El organizador del evento es obligatorio.");
        }
    }

    /**
     * Valida que las fechas de inicio y fin sean válidas.
     *
     * @param fechaInicio Fecha de inicio a validar
     * @param fechaFin Fecha de fin a validar
     * @throws NegocioException Si las fechas son inválidas
     */
    private void validarFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws NegocioException {
        if (fechaInicio == null || fechaFin == null) {
            throw new NegocioException("Las fechas de inicio y fin son obligatorias.");
        }

        LocalDateTime ahora = LocalDateTime.now();

        if (fechaInicio.isBefore(ahora)) {
            throw new NegocioException("La fecha de inicio no puede ser anterior a la fecha actual.");
        }

        if (fechaFin.isBefore(fechaInicio)) {
            throw new NegocioException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
    }

    /**
     * Valida que la transición de estado sea válida.
     *
     * @param estadoActual Estado actual del evento
     * @param nuevoEstado Nuevo estado a establecer
     * @throws NegocioException Si la transición no es válida
     */
    private void validarTransicionEstado(EstadoEvento estadoActual, EstadoEvento nuevoEstado) throws NegocioException {
        if (estadoActual == nuevoEstado) {
            return; // No hay cambio de estado
        }

        switch (estadoActual) {
            case PLANEADO:
                // Desde PLANEADO se puede pasar a EN_CURSO o FINALIZADO
                break;
            case EN_CURSO:
                // Desde EN_CURSO solo se puede pasar a FINALIZADO
                if (nuevoEstado == EstadoEvento.PLANEADO) {
                    throw new NegocioException("No se puede cambiar un evento en curso a estado planeado.");
                }
                break;
            case FINALIZADO:
                // Un evento FINALIZADO no puede cambiar de estado
                throw new NegocioException("No se puede cambiar el estado de un evento finalizado.");
            default:
                throw new NegocioException("Estado actual no reconocido.");
        }
    }
    
}
