/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import conexion.Conexion;
import entidades.Lugar;
import exception.PersistenciaException;
import interfaces.ILugarDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Implementación de la interfaz ILugarDAO utilizando JPA.
 * 
 * @author Alejandra García Preciado - 252444
 */
public class LugarDAO implements ILugarDAO {

    /**
     * Guarda un lugar en la base de datos.
     *
     * @param lugar Objeto Lugar a guardar
     * @return Lugar guardado con su ID asignado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public Lugar guardar(Lugar lugar) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(lugar);
            em.getTransaction().commit();
            return lugar;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al guardar el lugar: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Busca un lugar por su ID.
     *
     * @param id ID del lugar a buscar
     * @return Lugar encontrado o null si no existe
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public Lugar buscarPorId(Long id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.find(Lugar.class, id);
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar el lugar: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Obtiene todos los lugares almacenados en la base de datos.
     *
     * @return Lista de todos los lugares
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<Lugar> consultarTodos() throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Lugar> query = em.createQuery("select l from Lugar l", Lugar.class);
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar todos los lugares: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Consulta lugares por su nombre.
     *
     * @param nombre Nombre o parte del nombre a buscar
     * @return Lista de lugares que coinciden con el nombre
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<Lugar> consultarPorNombre(String nombre) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Lugar> query = em.createQuery(
                    "select l from Lugar l where l.nombre like :nombre", Lugar.class);
            query.setParameter("nombre", "%" + nombre + "%");
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar lugares por nombre: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Consulta lugares por su tipo.
     *
     * @param tipoLugar Tipo de lugar a buscar
     * @return Lista de lugares del tipo especificado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<Lugar> consultarPorTipo(Lugar.TipoLugar tipoLugar) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Lugar> query = em.createQuery(
                    "select l from Lugar l where l.tipoLugar = :tipoLugar", Lugar.class);
            query.setParameter("tipoLugar", tipoLugar);
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar lugares por tipo: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Consulta lugares por capacidad mínima.
     *
     * @param capacidadMinima Capacidad mínima requerida
     * @return Lista de lugares con capacidad igual o mayor a la especificada
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    @Override
    public List<Lugar> consultarPorCapacidadMinima(int capacidadMinima) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Lugar> query = em.createQuery(
                    "select l from Lugar l where l.capacidad >= :capacidadMinima", Lugar.class);
            query.setParameter("capacidadMinima", capacidadMinima);
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al consultar lugares por capacidad mínima: " + ex.getMessage());
        } finally {
            em.close();
        }
    }
    
}
