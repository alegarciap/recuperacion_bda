/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import conexion.Conexion;
import entidades.Evento;
import entidades.Evento.EstadoEvento;
import entidades.Evento.ModalidadEvento;
import entidades.Organizador;
import exception.PersistenciaException;
import interfaces.IEventoDAO;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * Implementación de la interfaz IEventoDAO utilizando JPA.
 * 
 * @author Alejandra García Preciado - 252444
 */
public class EventoDAO implements IEventoDAO {

    /**
     * Guarda un evento en la base de datos.
     *
     * @param evento Objeto Evento a guardar
     * @return Evento guardado con su ID asignado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public Evento guardar(Evento evento) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(evento);
            em.getTransaction().commit();
            return evento;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al guardar el evento: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Actualiza un evento existente en la base de datos.
     *
     * @param evento Objeto Evento con los datos actualizados
     * @return Evento actualizado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public Evento actualizar(Evento evento) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            Evento eventoActualizado = em.merge(evento);
            em.getTransaction().commit();
            return eventoActualizado;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al actualizar el evento: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Busca un evento por su ID.
     *
     * @param id ID del evento a buscar
     * @return Evento encontrado o null si no existe
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public Evento buscarPorId(Long id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.find(Evento.class, id);
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar el evento: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Busca un evento por su código único.
     *
     * @param codigo Código único del evento
     * @return Evento encontrado o null si no existe
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public Evento buscarPorCodigo(String codigo) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Evento> query = em.createQuery(
                    "select e from Evento e where e.codigo = :codigo", Evento.class);
            query.setParameter("codigo", codigo);
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar evento por código: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Obtiene todos los eventos almacenados en la base de datos.
     *
     * @return Lista de todos los eventos
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<Evento> consultarTodos() throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Evento> query = em.createQuery("select e from Evento e", Evento.class);
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar todos los eventos: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Consulta eventos por su título.
     *
     * @param titulo Título o parte del título a buscar
     * @return Lista de eventos que coinciden con el título
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<Evento> consultarPorTitulo(String titulo) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Evento> query = em.createQuery(
                    "select e from Evento e where e.titulo like :titulo", Evento.class);
            query.setParameter("titulo", "%" + titulo + "%");
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar eventos por título: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Consulta eventos por su estado.
     *
     * @param estado Estado a buscar
     * @return Lista de eventos con el estado especificado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<Evento> consultarPorEstado(EstadoEvento estado) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Evento> query = em.createQuery(
                    "select e from Evento e where e.estado = :estado", Evento.class);
            query.setParameter("estado", estado);
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar eventos por estado: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Consulta eventos por su modalidad.
     *
     * @param modalidad Modalidad a buscar
     * @return Lista de eventos con la modalidad especificada
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<Evento> consultarPorModalidad(ModalidadEvento modalidad) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Evento> query = em.createQuery(
                    "select e from Evento e where e.modalidad = :modalidad", Evento.class);
            query.setParameter("modalidad", modalidad);
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar eventos por modalidad: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Consulta eventos por un rango de fechas.
     *
     * @param fechaInicio Fecha de inicio del rango
     * @param fechaFin Fecha de fin del rango
     * @return Lista de eventos en el rango de fechas
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<Evento> consultarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Evento> query = em.createQuery(
                    "select e from Evento e where (e.fechaInicio between :fechaInicio and :fechaFin) "
                    + "or (e.fechaFin between :fechaInicio and :fechaFin) "
                    + "or (e.fechaInicio <= :fechaInicio and e.fechaFin >= :fechaFin)",
                    Evento.class);
            query.setParameter("fechaInicio", fechaInicio);
            query.setParameter("fechaFin", fechaFin);
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar eventos por rango de fechas: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Consulta eventos por organizador.
     *
     * @param organizador Organizador de los eventos
     * @return Lista de eventos del organizador especificado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<Evento> consultarPorOrganizador(Organizador organizador) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Evento> query = em.createQuery(
                    "select e from Evento e where e.organizador = :organizador", Evento.class);
            query.setParameter("organizador", organizador);
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar eventos por organizador: " + ex.getMessage());
        } finally {
            em.close();
        }
    }
    
}
