/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import conexion.Conexion;
import entidades.ParticipanteDocente;
import exception.PersistenciaException;
import interfaces.IParticipanteDocenteDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Implementación de la interfaz IParticipanteDocenteDAO utilizando JPA.
 * 
 * @author Alejandra García Preciado - 252444
 */
public class ParticipanteDocenteDAO extends ParticipanteDAO implements IParticipanteDocenteDAO {

    /**
     * Guarda un participante docente en la base de datos.
     *
     * @param docente Objeto ParticipanteDocente a guardar
     * @return ParticipanteDocente guardado con su ID asignado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public ParticipanteDocente guardarDocente(ParticipanteDocente docente) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(docente);
            em.getTransaction().commit();
            return docente;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al guardar el docente: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Busca un participante docente por su ID.
     *
     * @param id ID del participante docente a buscar
     * @return ParticipanteDocente encontrado o null si no existe
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public ParticipanteDocente buscarDocentePorId(Long id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.find(ParticipanteDocente.class, id);
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar el docente: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Obtiene todos los participantes docentes almacenados en la base de datos.
     *
     * @return Lista de todos los participantes docentes
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<ParticipanteDocente> consultarTodosDocentes() throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<ParticipanteDocente> query = em.createQuery(
                    "select pd from ParticipanteDocente pd", ParticipanteDocente.class);
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar todos los docentes: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Consulta participantes docentes por departamento.
     *
     * @param departamento Departamento a buscar
     * @return Lista de participantes docentes del departamento especificado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<ParticipanteDocente> consultarPorDepartamento(String departamento) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<ParticipanteDocente> query = em.createQuery(
                    "select pd from ParticipanteDocente pd where pd.departamento like :departamento",
                    ParticipanteDocente.class);
            query.setParameter("departamento", "%" + departamento + "%");
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar docentes por departamento: " + ex.getMessage());
        } finally {
            em.close();
        }
    }
    
}
