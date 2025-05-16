/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import DTOs.InscripcionCreacionDTO;
import DTOs.InscripcionDTO;
import exception.NegocioException;
import java.util.List;

/**
 * Interfaz que define las operaciones de negocio para la entidad Inscripcion.
 *
 * @author Alejandra García Preciado - 252444
 */
public interface IInscripcionBO {

    /**
     * Registra una nueva inscripción de un participante a una actividad.
     *
     * @param inscripcionDTO DTO con la información de la inscripción
     * @return DTO con la información de la inscripción registrada
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    public InscripcionDTO registrar(InscripcionCreacionDTO inscripcionDTO) throws NegocioException;

    /**
     * Cancela una inscripción existente.
     *
     * @param id ID de la inscripción a cancelar
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    public void cancelar(Long id) throws NegocioException;

    /**
     * Obtiene todas las inscripciones de una actividad identificada por nombre
     * y fecha.
     *
     * @param nombreActividad Nombre de la actividad
     * @param fechaHoraStr Fecha y hora de inicio en formato string
     * @return Lista de DTOs con la información de las inscripciones
     * @throws NegocioException Si hay errores de persistencia
     */
    public List<InscripcionDTO> consultarPorActividad(String nombreActividad, String fechaHoraStr) throws NegocioException;

    /**
     * Registra la asistencia de un participante a una actividad.
     *
     * @param inscripcionDTO Objeto InscripcionDTO con la información de la
     * inscripción
     * @param asistio true si el participante asistió, false si no asistió
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    public void registrarAsistencia(InscripcionDTO inscripcionDTO, boolean asistio) throws NegocioException;

    /**
     * Obtiene todas las inscripciones de un participante.
     *
     * @param participanteId ID del participante
     * @return Lista de DTOs con la información de las inscripciones
     * @throws NegocioException Si hay errores de persistencia
     */
    public List<InscripcionDTO> consultarPorParticipante(Long participanteId) throws NegocioException;

    /**
     * Verifica si un participante ya está inscrito a una actividad.
     *
     * @param participanteId ID del participante
     * @param actividadId ID de la actividad
     * @return true si ya está inscrito, false si no lo está
     * @throws NegocioException Si hay errores de persistencia
     */
    public boolean verificarInscripcionExistente(Long participanteId, Long actividadId) throws NegocioException;

    /**
     * Verifica si una actividad tiene cupo disponible.
     *
     * @param actividadId ID de la actividad
     * @return true si hay cupo disponible, false si está llena
     * @throws NegocioException Si hay errores de persistencia
     */
    public boolean verificarCupoDisponible(Long actividadId) throws NegocioException;

}
