/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import DTOs.ActividadCreacionDTO;
import DTOs.ActividadDTO;
import DTOs.ActividadDetalleDTO;
import DTOs.LugarDTO;
import exception.NegocioException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Interfaz que define las operaciones de negocio para la entidad Actividad.
 *
 * @author Alejandra García Preciado - 252444
 */
public interface IActividadBO {

    /**
     * Registra una nueva actividad en el sistema.
     *
     * @param actividadDTO DTO con la información de la actividad
     * @return DTO con la información de la actividad registrada
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    public ActividadDTO registrar(ActividadCreacionDTO actividadDTO) throws NegocioException;

    /**
     * Actualiza la información de una actividad existente.
     *
     * @param id ID de la actividad a actualizar
     * @param actividadDTO DTO con la nueva información
     * @return DTO con la información actualizada
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    public ActividadDTO actualizar(Long id, ActividadDTO actividadDTO) throws NegocioException;

    /**
     * Consulta una actividad por su ID.
     *
     * @param id ID de la actividad a consultar
     * @return DTO con la información detallada de la actividad
     * @throws NegocioException Si hay errores de persistencia o la actividad no
     * existe
     */
    public ActividadDetalleDTO consultar(Long id) throws NegocioException;

    /**
     * Obtiene todas las actividades registradas en el sistema.
     *
     * @return Lista de DTOs con la información básica de las actividades
     * @throws NegocioException Si hay errores de persistencia
     */
    public List<ActividadDTO> consultarTodos() throws NegocioException;

    /**
     * Consulta actividades por el evento al que pertenecen.
     *
     * @param eventoId ID del evento
     * @return Lista de DTOs con la información de las actividades
     * @throws NegocioException Si hay errores de persistencia
     */
    public List<ActividadDTO> consultarPorEvento(Long eventoId) throws NegocioException;

    /**
     * Consulta actividades por fecha.
     *
     * @param fecha Fecha a consultar
     * @return Lista de DTOs con la información de las actividades
     * @throws NegocioException Si hay errores de persistencia
     */
    public List<ActividadDTO> consultarPorFecha(LocalDateTime fecha) throws NegocioException;

    /**
     * Consulta una actividad por su nombre y fecha.
     *
     * @param nombre Nombre de la actividad
     * @param fechaHora Fecha y hora de inicio
     * @return DTO con la información detallada de la actividad
     * @throws NegocioException Si hay errores de persistencia o la actividad no
     * existe
     */
    public ActividadDetalleDTO consultarPorFechaNombre(String nombre, LocalDateTime fechaHora) throws NegocioException;

    /**
     * Marca una actividad como finalizada.
     *
     * @param nombre Nombre de la actividad
     * @param fechaHora Fecha y hora de inicio
     * @return DTO con la información actualizada
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    public ActividadDTO finalizarActividad(String nombre, LocalDateTime fechaHora) throws NegocioException;

    /**
     * Verifica si hay conflictos de horario entre actividades en el mismo
     * lugar.
     *
     * @param actividadDTO DTO con la información de la actividad a verificar
     * @param lugarDTO Objeto LugarDTO con la información del lugar
     * @return true si hay conflictos, false si no los hay
     * @throws NegocioException Si hay errores de persistencia
     */
    public boolean verificarConflictosHorario(ActividadCreacionDTO actividadDTO, LugarDTO lugarDTO) throws NegocioException;

}
