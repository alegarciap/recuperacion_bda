/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Lugar;
import entidades.Lugar.TipoLugar;
import exception.PersistenciaException;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD para la entidad Lugar.
 * 
 * @author Alejandra García Preciado - 252444
 */
public interface ILugarDAO {
    
    /**
     * Guarda un lugar en la base de datos.
     *
     * @param lugar Objeto Lugar a guardar
     * @return Lugar guardado con su ID asignado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public Lugar guardar(Lugar lugar) throws PersistenciaException;

    /**
     * Busca un lugar por su ID.
     *
     * @param id ID del lugar a buscar
     * @return Lugar encontrado o null si no existe
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public Lugar buscarPorId(Long id) throws PersistenciaException;

    /**
     * Obtiene todos los lugares almacenados en la base de datos.
     *
     * @return Lista de todos los lugares
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<Lugar> consultarTodos() throws PersistenciaException;

    /**
     * Consulta lugares por su nombre.
     *
     * @param nombre Nombre o parte del nombre a buscar
     * @return Lista de lugares que coinciden con el nombre
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<Lugar> consultarPorNombre(String nombre) throws PersistenciaException;

    /**
     * Consulta lugares por su tipo.
     *
     * @param tipoLugar Tipo de lugar a buscar
     * @return Lista de lugares del tipo especificado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<Lugar> consultarPorTipo(TipoLugar tipoLugar) throws PersistenciaException;

    /**
     * Consulta lugares por capacidad mínima.
     *
     * @param capacidadMinima Capacidad mínima requerida
     * @return Lista de lugares con capacidad igual o mayor a la especificada
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<Lugar> consultarPorCapacidadMinima(int capacidadMinima) throws PersistenciaException;
    
}
