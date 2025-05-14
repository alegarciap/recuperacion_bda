/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Actividad;
import entidades.Evento;
import entidades.Lugar;
import exception.PersistenciaException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD para la entidad Actividad.
 * 
 * @author Alejandra García Preciado - 252444
 */
public interface IActividadDAO {
    
    /**
     * Guarda una actividad en la base de datos.
     *
     * @param actividad Objeto Actividad a guardar
     * @return Actividad guardada con su ID asignado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public Actividad guardar(Actividad actividad) throws PersistenciaException;

    /**
     * Actualiza una actividad existente en la base de datos.
     *
     * @param actividad Objeto Actividad con los datos actualizados
     * @return Actividad actualizada
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public Actividad actualizar(Actividad actividad) throws PersistenciaException;

    /**
     * Busca una actividad por su ID.
     *
     * @param id ID de la actividad a buscar
     * @return Actividad encontrada o null si no existe
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public Actividad buscarPorId(Long id) throws PersistenciaException;

    /**
     * Obtiene todas las actividades almacenadas en la base de datos.
     *
     * @return Lista de todas las actividades
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<Actividad> consultarTodos() throws PersistenciaException;

    /**
     * Consulta actividades por el evento al que pertenecen.
     *
     * @param evento Evento al que pertenecen las actividades
     * @return Lista de actividades del evento
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<Actividad> consultarPorEvento(Evento evento) throws PersistenciaException;

    /**
     * Consulta actividades por el lugar donde se realizan.
     *
     * @param lugar Lugar donde se realizan las actividades
     * @return Lista de actividades en el lugar especificado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<Actividad> consultarPorLugar(Lugar lugar) throws PersistenciaException;

    /**
     * Consulta actividades por un rango de fechas.
     *
     * @param fechaInicio Fecha de inicio del rango
     * @param fechaFin Fecha de fin del rango
     * @return Lista de actividades en el rango de fechas
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<Actividad> consultarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws PersistenciaException;

    /**
     * Consulta actividades por su estado de finalización.
     *
     * @param finalizado Estado de finalización a buscar
     * @return Lista de actividades con el estado especificado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<Actividad> consultarPorEstadoFinalizacion(Boolean finalizado) throws PersistenciaException;
    
}
