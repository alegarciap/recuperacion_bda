/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import conexion.Conexion;
import entidades.Organizador;
import entidades.Organizador.TipoOrganizador;
import exception.PersistenciaException;
import interfaces.IOrganizadorDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Implementación de la interfaz IOrganizadorDAO utilizando JPA.
 * 
 * @author Alejandra García Preciado - 252444
 */
public class OrganizadorDAO implements IOrganizadorDAO {

    /**
     * Guarda un organizador en la base de datos.
     * 
     * @param organizador Objeto Organizador a guardar
     * @return Organizador guardado con su ID asignado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public Organizador guardar(Organizador organizador) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(organizador);
            em.getTransaction().commit();
            return organizador;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al guardar el organizador: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Elimina un organizador de la base de datos.
     * 
     * @param id ID del organizador a eliminar
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public void eliminar(Long id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            Organizador organizador = em.find(Organizador.class, id);
            if (organizador != null) {
                em.remove(organizador);
                em.getTransaction().commit();
            } else {
                em.getTransaction().rollback();
                throw new PersistenciaException("No se encontró el organizador con ID: " + id);
            }
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al eliminar el organizador: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Busca un organizador por su ID.
     * 
     * @param id ID del organizador a buscar
     * @return Organizador encontrado o null si no existe
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public Organizador buscarPorId(Long id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.find(Organizador.class, id);
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar el organizador: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Obtiene todos los organizadores almacenados en la base de datos.
     * 
     * @return Lista de todos los organizadores
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<Organizador> consultarTodos() throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Organizador> query = em.createQuery("select o from Organizador o", Organizador.class);
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar todos los organizadores: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Consulta organizadores por su tipo.
     * 
     * @param tipoOrganizador Tipo de organizador a buscar
     * @return Lista de organizadores del tipo especificado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<Organizador> consultarPorTipo(TipoOrganizador tipoOrganizador) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Organizador> query = em.createQuery(
                    "select o from Organizador o where o.tipoOrganizador = :tipoOrganizador", Organizador.class);
            query.setParameter("tipoOrganizador", tipoOrganizador);
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar organizadores por tipo: " + ex.getMessage());
        } finally {
            em.close();
        }
    }
    
}
