/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Actividad;
import entidades.Inscripcion;
import entidades.Inscripcion.EstadoAsistencia;
import entidades.Participante;
import exception.PersistenciaException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD para la entidad Inscripcion.
 * 
 * @author Alejandra García Preciado - 252444
 */
public interface IInscripcionDAO {
    
    /**
     * Guarda una inscripción en la base de datos.
     *
     * @param inscripcion Objeto Inscripcion a guardar
     * @return Inscripcion guardada con su ID asignado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public Inscripcion guardar(Inscripcion inscripcion) throws PersistenciaException;

    /**
     * Actualiza una inscripción existente en la base de datos.
     *
     * @param inscripcion Objeto Inscripcion con los datos actualizados
     * @return Inscripcion actualizada
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public Inscripcion actualizar(Inscripcion inscripcion) throws PersistenciaException;

    /**
     * Elimina una inscripción de la base de datos.
     *
     * @param id ID de la inscripción a eliminar
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public void eliminar(Long id) throws PersistenciaException;

    /**
     * Busca una inscripción por su ID.
     *
     * @param id ID de la inscripción a buscar
     * @return Inscripcion encontrada o null si no existe
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public Inscripcion buscarPorId(Long id) throws PersistenciaException;

    /**
     * Obtiene todas las inscripciones almacenadas en la base de datos.
     *
     * @return Lista de todas las inscripciones
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<Inscripcion> consultarTodos() throws PersistenciaException;

    /**
     * Consulta inscripciones por actividad.
     *
     * @param actividad Actividad de las inscripciones
     * @return Lista de inscripciones de la actividad
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<Inscripcion> consultarPorActividad(Actividad actividad) throws PersistenciaException;

    /**
     * Consulta inscripciones por participante.
     *
     * @param participante Participante de las inscripciones
     * @return Lista de inscripciones del participante
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<Inscripcion> consultarPorParticipante(Participante participante) throws PersistenciaException;

    /**
     * Consulta inscripciones por estado de asistencia.
     *
     * @param estadoAsistencia Estado de asistencia a buscar
     * @return Lista de inscripciones con el estado especificado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<Inscripcion> consultarPorEstadoAsistencia(EstadoAsistencia estadoAsistencia) throws PersistenciaException;

    /**
     * Consulta inscripciones por un rango de fechas.
     *
     * @param fechaInicio Fecha de inicio del rango
     * @param fechaFin Fecha de fin del rango
     * @return Lista de inscripciones en el rango de fechas
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<Inscripcion> consultarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws PersistenciaException;

    /**
     * Busca una inscripción por participante y actividad.
     *
     * @param participante Participante de la inscripción
     * @param actividad Actividad de la inscripción
     * @return Inscripcion encontrada o null si no existe
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public Inscripcion buscarPorParticipanteYActividad(Participante participante, Actividad actividad) throws PersistenciaException;
    
}
