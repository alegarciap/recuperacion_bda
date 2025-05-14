/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import conexion.Conexion;
import entidades.Actividad;
import entidades.Inscripcion;
import entidades.Inscripcion.EstadoAsistencia;
import entidades.Participante;
import exception.PersistenciaException;
import interfaces.IInscripcionDAO;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * Implementación de la interfaz IInscripcionDAO utilizando JPA.
 * 
 * @author Alejandra García Preciado - 252444
 */
public class InscripcionDAO implements IInscripcionDAO {

    /**
     * Guarda una inscripción en la base de datos.
     *
     * @param inscripcion Objeto Inscripcion a guardar
     * @return Inscripcion guardada con su ID asignado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public Inscripcion guardar(Inscripcion inscripcion) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(inscripcion);
            em.getTransaction().commit();
            return inscripcion;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al guardar la inscripción: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Actualiza una inscripción existente en la base de datos.
     *
     * @param inscripcion Objeto Inscripcion con los datos actualizados
     * @return Inscripcion actualizada
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public Inscripcion actualizar(Inscripcion inscripcion) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            Inscripcion inscripcionActualizada = em.merge(inscripcion);
            em.getTransaction().commit();
            return inscripcionActualizada;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al actualizar la inscripción: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Elimina una inscripción de la base de datos.
     *
     * @param id ID de la inscripción a eliminar
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public void eliminar(Long id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            Inscripcion inscripcion = em.find(Inscripcion.class, id);
            if (inscripcion != null) {
                em.remove(inscripcion);
                em.getTransaction().commit();
            } else {
                em.getTransaction().rollback();
                throw new PersistenciaException("No se encontró la inscripción con ID: " + id);
            }
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al eliminar la inscripción: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Busca una inscripción por su ID.
     *
     * @param id ID de la inscripción a buscar
     * @return Inscripcion encontrada o null si no existe
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public Inscripcion buscarPorId(Long id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.find(Inscripcion.class, id);
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar la inscripción: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Obtiene todas las inscripciones almacenadas en la base de datos.
     *
     * @return Lista de todas las inscripciones
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<Inscripcion> consultarTodos() throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Inscripcion> query = em.createQuery("select i from Inscripcion i", Inscripcion.class);
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar todas las inscripciones: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Consulta inscripciones por actividad.
     *
     * @param actividad Actividad de las inscripciones
     * @return Lista de inscripciones de la actividad
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<Inscripcion> consultarPorActividad(Actividad actividad) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Inscripcion> query = em.createQuery(
                    "select i from Inscripcion i where i.actividad = :actividad", Inscripcion.class);
            query.setParameter("actividad", actividad);
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar inscripciones por actividad: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Consulta inscripciones por participante.
     *
     * @param participante Participante de las inscripciones
     * @return Lista de inscripciones del participante
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<Inscripcion> consultarPorParticipante(Participante participante) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Inscripcion> query = em.createQuery(
                    "select i from Inscripcion i where i.participante = :participante", Inscripcion.class);
            query.setParameter("participante", participante);
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar inscripciones por participante: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Consulta inscripciones por estado de asistencia.
     *
     * @param estadoAsistencia Estado de asistencia a buscar
     * @return Lista de inscripciones con el estado especificado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<Inscripcion> consultarPorEstadoAsistencia(EstadoAsistencia estadoAsistencia) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Inscripcion> query = em.createQuery(
                    "select i from Inscripcion i where i.estadoAsistencia = :estadoAsistencia", Inscripcion.class);
            query.setParameter("estadoAsistencia", estadoAsistencia);
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar inscripciones por estado de asistencia: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Consulta inscripciones por un rango de fechas.
     *
     * @param fechaInicio Fecha de inicio del rango
     * @param fechaFin Fecha de fin del rango
     * @return Lista de inscripciones en el rango de fechas
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<Inscripcion> consultarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Inscripcion> query = em.createQuery(
                    "select i from Inscripcion i where i.fechaHora between :fechaInicio and :fechaFin",
                    Inscripcion.class);
            query.setParameter("fechaInicio", fechaInicio);
            query.setParameter("fechaFin", fechaFin);
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar inscripciones por rango de fechas: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Busca una inscripción por participante y actividad.
     *
     * @param participante Participante de la inscripción
     * @param actividad Actividad de la inscripción
     * @return Inscripcion encontrada o null si no existe
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public Inscripcion buscarPorParticipanteYActividad(Participante participante, Actividad actividad) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Inscripcion> query = em.createQuery(
                    "select i from Inscripcion i where i.participante = :participante and i.actividad = :actividad",
                    Inscripcion.class);
            query.setParameter("participante", participante);
            query.setParameter("actividad", actividad);
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar inscripción por participante y actividad: " + ex.getMessage());
        } finally {
            em.close();
        }
    }
    
}
