/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import DTOs.EventoCreacionDTO;
import DTOs.EventoDTO;
import DTOs.EventoDetalleDTO;
import exception.NegocioException;
import java.time.LocalDate;
import java.util.List;

/**
 * Interfaz que define las operaciones de negocio para la entidad Evento.
 *
 * @author Alejandra García Preciado - 252444
 */
public interface IEventoBO {

    /**
     * Registra un nuevo evento en el sistema.
     *
     * @param eventoDTO DTO con la información del evento
     * @return DTO con la información del evento registrado
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    public EventoDTO registrar(EventoCreacionDTO eventoDTO) throws NegocioException;

    /**
     * Actualiza la información de un evento existente.
     *
     * @param id ID del evento a actualizar
     * @param eventoDTO DTO con la nueva información
     * @return DTO con la información actualizada
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    public EventoDTO actualizar(Long id, EventoDTO eventoDTO) throws NegocioException;

    /**
     * Consulta un evento por su ID.
     *
     * @param id ID del evento a consultar
     * @return DTO con la información detallada del evento
     * @throws NegocioException Si hay errores de persistencia o el evento no
     * existe
     */
    public EventoDetalleDTO consultar(Long id) throws NegocioException;

    /**
     * Consulta un evento por su código único.
     *
     * @param codigo Código único del evento
     * @return DTO con la información detallada del evento
     * @throws NegocioException Si hay errores de persistencia o el evento no
     * existe
     */
    public EventoDetalleDTO consultarPorCodigo(String codigo) throws NegocioException;

    /**
     * Obtiene todos los eventos registrados en el sistema.
     *
     * @return Lista de DTOs con la información básica de los eventos
     * @throws NegocioException Si hay errores de persistencia
     */
    public List<EventoDTO> consultarTodos() throws NegocioException;

    /**
     * Consulta eventos por su título.
     *
     * @param titulo Título o parte del título a buscar
     * @return Lista de DTOs con la información de los eventos
     * @throws NegocioException Si hay errores de persistencia
     */
    public List<EventoDTO> consultarPorTitulo(String titulo) throws NegocioException;

    /**
     * Consulta eventos por su estado.
     *
     * @param estado Estado a buscar (PLANEADO, EN_CURSO, FINALIZADO)
     * @return Lista de DTOs con la información de los eventos
     * @throws NegocioException Si hay errores de persistencia
     */
    public List<EventoDTO> consultarPorEstado(String estado) throws NegocioException;

    /**
     * Consulta eventos por su modalidad.
     *
     * @param modalidad Modalidad a buscar (PRESENCIAL, EN_LINEA, HIBRIDO)
     * @return Lista de DTOs con la información de los eventos
     * @throws NegocioException Si hay errores de persistencia
     */
    public List<EventoDTO> consultarPorModalidad(String modalidad) throws NegocioException;

    /**
     * Consulta eventos por un rango de fechas.
     *
     * @param fechaInicio Fecha de inicio del rango
     * @param fechaFin Fecha de fin del rango
     * @return Lista de DTOs con la información de los eventos
     * @throws NegocioException Si hay errores de persistencia
     */
    public List<EventoDTO> consultarPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) throws NegocioException;

    /**
     * Cambia el estado de un evento.
     *
     * @param id ID del evento
     * @param nuevoEstado Nuevo estado a establecer
     * @return DTO con la información actualizada
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    public EventoDTO cambiarEstado(Long id, String nuevoEstado) throws NegocioException;

    /**
     * Genera un código único para un nuevo evento.
     *
     * @return Código único en formato EV-YYYYMM-XXX
     * @throws NegocioException Si hay errores de persistencia
     */
    public String generarCodigo() throws NegocioException;

    /**
     * Valida si un título está disponible (no duplicado) para el periodo
     * actual.
     *
     * @param titulo Título a validar
     * @return true si está disponible, false si ya existe
     * @throws NegocioException Si hay errores de persistencia
     */
    public boolean validarTituloDisponible(String titulo) throws NegocioException;

}
