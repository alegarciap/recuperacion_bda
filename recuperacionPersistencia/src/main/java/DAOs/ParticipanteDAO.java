/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import conexion.Conexion;
import entidades.Participante;
import exception.PersistenciaException;
import interfaces.IParticipanteDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * Implementación de la interfaz IParticipanteDAO utilizando JPA.
 * 
 * @author Alejandra García Preciado - 252444
 */
public class ParticipanteDAO implements IParticipanteDAO {

    /**
     * Guarda un participante en la base de datos.
     *
     * @param participante Objeto Participante a guardar
     * @return Participante guardado con su ID asignado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public Participante guardar(Participante participante) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(participante);
            em.getTransaction().commit();
            return participante;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al guardar el participante: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Busca un participante por su ID.
     *
     * @param id ID del participante a buscar
     * @return Participante encontrado o null si no existe
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public Participante buscarPorId(Long id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.find(Participante.class, id);
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar el participante: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Obtiene todos los participantes almacenados en la base de datos.
     *
     * @return Lista de todos los participantes
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<Participante> consultarTodos() throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Participante> query = em.createQuery("select p from Participante p", Participante.class);
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar todos los participantes: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Consulta participantes por su nombre o apellidos.
     *
     * @param nombre Nombre o parte del nombre/apellidos a buscar
     * @return Lista de participantes que coinciden con el criterio
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<Participante> consultarPorNombre(String nombre) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Participante> query = em.createQuery(
                    "select p from Participante p where p.nombre like :nombre "
                    + "or p.apellidoPaterno like :nombre "
                    + "or p.apellidoMaterno like :nombre",
                    Participante.class);
            query.setParameter("nombre", "%" + nombre + "%");
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar participantes por nombre: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Busca un participante por su correo electrónico.
     *
     * @param correo Correo electrónico a buscar
     * @return Participante encontrado o null si no existe
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public Participante buscarPorCorreo(String correo) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Participante> query = em.createQuery(
                    "select p from Participante p where p.correo = :correo", Participante.class);
            query.setParameter("correo", correo);
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar participante por correo: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Consulta participantes por número mínimo de asistencias.
     *
     * @param minAsistencias Número mínimo de asistencias
     * @return Lista de participantes con igual o más asistencias
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<Participante> consultarPorAsistenciasMinimas(int minAsistencias) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Participante> query = em.createQuery(
                    "select p from Participante p where p.cantidadAsistencias >= :minAsistencias",
                    Participante.class);
            query.setParameter("minAsistencias", minAsistencias);
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar participantes por asistencias mínimas: " + ex.getMessage());
        } finally {
            em.close();
        }
    }
    
}
