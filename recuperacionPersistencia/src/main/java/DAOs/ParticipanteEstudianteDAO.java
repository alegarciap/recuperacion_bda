/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import conexion.Conexion;
import entidades.ParticipanteEstudiante;
import exception.PersistenciaException;
import interfaces.IParticipanteEstudianteDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import utils.EncryptionUtil;

/**
 * Implementación de la interfaz IParticipanteEstudianteDAO utilizando JPA.
 * 
 * @author Alejandra García Preciado - 252444
 */
public class ParticipanteEstudianteDAO extends ParticipanteDAO implements IParticipanteEstudianteDAO {

    /**
     * Guarda un participante estudiante en la base de datos.
     *
     * @param estudiante Objeto ParticipanteEstudiante a guardar
     * @return ParticipanteEstudiante guardado con su ID asignado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public ParticipanteEstudiante guardarEstudiante(ParticipanteEstudiante estudiante) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(estudiante);
            em.getTransaction().commit();
            return estudiante;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al guardar el estudiante: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Busca un participante estudiante por su ID.
     *
     * @param id ID del participante estudiante a buscar
     * @return ParticipanteEstudiante encontrado o null si no existe
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public ParticipanteEstudiante buscarEstudiantePorId(Long id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.find(ParticipanteEstudiante.class, id);
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar el estudiante: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Obtiene todos los participantes estudiantes almacenados en la base de
     * datos.
     *
     * @return Lista de todos los participantes estudiantes
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<ParticipanteEstudiante> consultarTodosEstudiantes() throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<ParticipanteEstudiante> query = em.createQuery(
                    "select pe from ParticipanteEstudiante pe", ParticipanteEstudiante.class);
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar todos los estudiantes: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Busca un participante estudiante por su número de control. Como el número
     * de control se almacena encriptado, esta búsqueda requiere cargar todos
     * los estudiantes y filtrar en memoria.
     *
     * @param numeroControl Número de control a buscar
     * @return ParticipanteEstudiante encontrado o null si no existe
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public ParticipanteEstudiante buscarPorNumeroControl(Integer numeroControl) throws PersistenciaException {
        if (numeroControl == null) {
            return null;
        }

        // Encriptar el número de control para la búsqueda
        String numeroControlEncriptado = EncryptionUtil.encriptar(numeroControl.toString());

        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<ParticipanteEstudiante> query = em.createQuery(
                    "select pe from ParticipanteEstudiante pe where pe.numeroControl = :numeroControl",
                    ParticipanteEstudiante.class);
            query.setParameter("numeroControl", numeroControlEncriptado);

            try {
                ParticipanteEstudiante estudiante = query.getSingleResult();
                // Los datos se desencriptarán en el método auxiliar del ParticipanteDAO
                return estudiante;
            } catch (NoResultException ex) {
                return null;
            }
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar estudiante por número de control: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Consulta participantes estudiantes por carrera.
     *
     * @param carrera Carrera a buscar
     * @return Lista de participantes estudiantes de la carrera especificada
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<ParticipanteEstudiante> consultarPorCarrera(String carrera) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<ParticipanteEstudiante> query = em.createQuery(
                    "select pe from ParticipanteEstudiante pe where pe.carrera like :carrera",
                    ParticipanteEstudiante.class);
            query.setParameter("carrera", "%" + carrera + "%");
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar estudiantes por carrera: " + ex.getMessage());
        } finally {
            em.close();
        }
    }
    
}
