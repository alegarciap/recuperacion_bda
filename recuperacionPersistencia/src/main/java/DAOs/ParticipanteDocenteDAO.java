/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import conexion.Conexion;
import entidades.Participante;
import entidades.ParticipanteDocente;
import exception.PersistenciaException;
import interfaces.IParticipanteDocenteDAO;
import java.lang.reflect.Field;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import utils.EncryptionUtil;

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
            // Encriptar datos sensibles antes de guardar
            encriptarDatosSensibles(docente);
            
            em.getTransaction().begin();
            em.persist(docente);
            em.getTransaction().commit();
            
            // Desencriptar datos para devolverlos a la capa de negocio
            desencriptarDatosSensibles(docente);
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
    
    // Métodos auxiliares para encriptar/desencriptar datos
    private void encriptarDatosSensibles(ParticipanteDocente participante) {
        if (participante.getCorreo() != null) {
            try {
                Field correoField = Participante.class.getDeclaredField("correo");
                correoField.setAccessible(true);
                String correoOriginal = (String) correoField.get(participante);
                correoField.set(participante, EncryptionUtil.encriptar(correoOriginal));
            } catch (Exception ex) {
                System.err.println("Error al encriptar correo: " + ex.getMessage());
            }
        }
    }

    private void desencriptarDatosSensibles(ParticipanteDocente participante) {
        if (participante.getCorreo() != null) {
            try {
                Field correoField = Participante.class.getDeclaredField("correo");
                correoField.setAccessible(true);
                String correoEncriptado = (String) correoField.get(participante);
                correoField.set(participante, EncryptionUtil.desencriptar(correoEncriptado));
            } catch (Exception ex) {
                System.err.println("Error al desencriptar correo: " + ex.getMessage());
            }
        }
    }
    
}
