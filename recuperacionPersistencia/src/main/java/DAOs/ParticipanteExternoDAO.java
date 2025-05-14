/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import conexion.Conexion;
import entidades.ParticipanteExterno;
import exception.PersistenciaException;
import interfaces.IParticipanteExternoDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Implementación de la interfaz IParticipanteExternoDAO utilizando JPA.
 * 
 * @author Alejandra García Preciado - 252444
 */
public class ParticipanteExternoDAO extends ParticipanteDAO implements IParticipanteExternoDAO {

    /**
     * Guarda un participante externo en la base de datos.
     *
     * @param externo Objeto ParticipanteExterno a guardar
     * @return ParticipanteExterno guardado con su ID asignado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public ParticipanteExterno guardarExterno(ParticipanteExterno externo) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(externo);
            em.getTransaction().commit();
            return externo;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al guardar el participante externo: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Busca un participante externo por su ID.
     *
     * @param id ID del participante externo a buscar
     * @return ParticipanteExterno encontrado o null si no existe
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public ParticipanteExterno buscarExternoPorId(Long id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.find(ParticipanteExterno.class, id);
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar el participante externo: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Obtiene todos los participantes externos almacenados en la base de datos.
     *
     * @return Lista de todos los participantes externos
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<ParticipanteExterno> consultarTodosExternos() throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<ParticipanteExterno> query = em.createQuery(
                    "select pe from ParticipanteExterno pe", ParticipanteExterno.class);
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar todos los participantes externos: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Consulta participantes externos por institución.
     *
     * @param institucion Institución a buscar
     * @return Lista de participantes externos de la institución especificada
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<ParticipanteExterno> consultarPorInstitucion(String institucion) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<ParticipanteExterno> query = em.createQuery(
                    "select pe from ParticipanteExterno pe where pe.institucion like :institucion",
                    ParticipanteExterno.class);
            query.setParameter("institucion", "%" + institucion + "%");
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar participantes externos por institución: " + ex.getMessage());
        } finally {
            em.close();
        }
    }
    
}
