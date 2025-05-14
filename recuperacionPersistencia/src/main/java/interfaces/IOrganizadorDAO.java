/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Organizador;
import entidades.Organizador.TipoOrganizador;
import exception.PersistenciaException;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD para la entidad Organizador.
 * 
 * @author Alejandra García Preciado - 252444
 */
public interface IOrganizadorDAO {
    
    /**
     * Guarda un organizador en la base de datos.
     *
     * @param organizador Objeto Organizador a guardar
     * @return Organizador guardado con su ID asignado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public Organizador guardar(Organizador organizador) throws PersistenciaException;

    /**
     * Elimina un organizador de la base de datos.
     *
     * @param id ID del organizador a eliminar
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public void eliminar(Long id) throws PersistenciaException;

    /**
     * Busca un organizador por su ID.
     *
     * @param id ID del organizador a buscar
     * @return Organizador encontrado o null si no existe
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public Organizador buscarPorId(Long id) throws PersistenciaException;

    /**
     * Obtiene todos los organizadores almacenados en la base de datos.
     *
     * @return Lista de todos los organizadores
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<Organizador> consultarTodos() throws PersistenciaException;

    /**
     * Consulta organizadores por su tipo.
     *
     * @param tipoOrganizador Tipo de organizador a buscar
     * @return Lista de organizadores del tipo especificado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<Organizador> consultarPorTipo(TipoOrganizador tipoOrganizador) throws PersistenciaException;
    
}
