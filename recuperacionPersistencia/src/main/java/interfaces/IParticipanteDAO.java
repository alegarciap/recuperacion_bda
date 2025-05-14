/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Participante;
import exception.PersistenciaException;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD para la entidad Participante.
 * 
 * @author Alejandra García Preciado - 252444
 */
public interface IParticipanteDAO {
    
    /**
     * Guarda un participante en la base de datos.
     *
     * @param participante Objeto Participante a guardar
     * @return Participante guardado con su ID asignado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public Participante guardar(Participante participante) throws PersistenciaException;

    /**
     * Busca un participante por su ID.
     *
     * @param id ID del participante a buscar
     * @return Participante encontrado o null si no existe
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public Participante buscarPorId(Long id) throws PersistenciaException;

    /**
     * Obtiene todos los participantes almacenados en la base de datos.
     *
     * @return Lista de todos los participantes
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<Participante> consultarTodos() throws PersistenciaException;

    /**
     * Consulta participantes por su nombre o apellidos.
     *
     * @param nombre Nombre o parte del nombre/apellidos a buscar
     * @return Lista de participantes que coinciden con el criterio
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<Participante> consultarPorNombre(String nombre) throws PersistenciaException;

    /**
     * Busca un participante por su correo electrónico.
     *
     * @param correo Correo electrónico a buscar
     * @return Participante encontrado o null si no existe
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public Participante buscarPorCorreo(String correo) throws PersistenciaException;

    /**
     * Consulta participantes por número mínimo de asistencias.
     *
     * @param minAsistencias Número mínimo de asistencias
     * @return Lista de participantes con igual o más asistencias
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<Participante> consultarPorAsistenciasMinimas(int minAsistencias) throws PersistenciaException;
    
}
