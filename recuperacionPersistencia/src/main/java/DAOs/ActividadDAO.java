/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import conexion.Conexion;
import entidades.Actividad;
import entidades.Evento;
import entidades.Lugar;
import exception.PersistenciaException;
import interfaces.IActividadDAO;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Implementación de la interfaz IActividadDAO utilizando JPA.
 * 
 * @author Alejandra García Preciado - 252444
 */
public class ActividadDAO implements IActividadDAO {

    /**
     * Guarda una actividad en la base de datos.
     *
     * @param actividad Objeto Actividad a guardar
     * @return Actividad guardada con su ID asignado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public Actividad guardar(Actividad actividad) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(actividad);
            em.getTransaction().commit();
            return actividad;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al guardar la actividad: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Actualiza una actividad existente en la base de datos.
     *
     * @param actividad Objeto Actividad con los datos actualizados
     * @return Actividad actualizada
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public Actividad actualizar(Actividad actividad) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            Actividad actividadActualizada = em.merge(actividad);
            em.getTransaction().commit();
            return actividadActualizada;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al actualizar la actividad: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Busca una actividad por su ID.
     *
     * @param id ID de la actividad a buscar
     * @return Actividad encontrada o null si no existe
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public Actividad buscarPorId(Long id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.find(Actividad.class, id);
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar la actividad: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Obtiene todas las actividades almacenadas en la base de datos.
     *
     * @return Lista de todas las actividades
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<Actividad> consultarTodos() throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Actividad> query = em.createQuery("select a from Actividad a", Actividad.class);
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar todas las actividades: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Consulta actividades por el evento al que pertenecen.
     *
     * @param evento Evento al que pertenecen las actividades
     * @return Lista de actividades del evento
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<Actividad> consultarPorEvento(Evento evento) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Actividad> query = em.createQuery(
                    "select a from Actividad a where a.evento = :evento", Actividad.class);
            query.setParameter("evento", evento);
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar actividades por evento: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Consulta actividades por el lugar donde se realizan.
     *
     * @param lugar Lugar donde se realizan las actividades
     * @return Lista de actividades en el lugar especificado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<Actividad> consultarPorLugar(Lugar lugar) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Actividad> query = em.createQuery(
                    "select a from Actividad a where a.lugar = :lugar", Actividad.class);
            query.setParameter("lugar", lugar);
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar actividades por lugar: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Consulta actividades por un rango de fechas.
     *
     * @param fechaInicio Fecha de inicio del rango
     * @param fechaFin Fecha de fin del rango
     * @return Lista de actividades en el rango de fechas
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<Actividad> consultarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Actividad> query = em.createQuery(
                    "select a from Actividad a where a.fechaHoraInicio between:fechaInicio and :fechaFin",
                    Actividad.class);
            query.setParameter("fechaInicio", fechaInicio);
            query.setParameter("fechaFin", fechaFin);
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar actividades por rango de fechas: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Consulta actividades por su estado de finalización.
     *
     * @param finalizado Estado de finalización a buscar
     * @return Lista de actividades con el estado especificado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<Actividad> consultarPorEstadoFinalizacion(Boolean finalizado) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Actividad> query = em.createQuery(
                    "select a from Actividad a where a.finalizado = :finalizado",
                    Actividad.class);
            query.setParameter("finalizado", finalizado);
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar actividades por estado de finalización: " + ex.getMessage());
        } finally {
            em.close();
        }
    }
    
}
