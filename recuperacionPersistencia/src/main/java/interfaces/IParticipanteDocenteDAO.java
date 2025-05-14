/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.ParticipanteDocente;
import exception.PersistenciaException;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD para la entidad ParticipanteDocente.
 * 
 * @author Alejandra García Preciado - 252444
 */
public interface IParticipanteDocenteDAO extends IParticipanteDAO {
    
    /**
     * Guarda un participante docente en la base de datos.
     *
     * @param docente Objeto ParticipanteDocente a guardar
     * @return ParticipanteDocente guardado con su ID asignado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public ParticipanteDocente guardarDocente(ParticipanteDocente docente) throws PersistenciaException;

    /**
     * Busca un participante docente por su ID.
     *
     * @param id ID del participante docente a buscar
     * @return ParticipanteDocente encontrado o null si no existe
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public ParticipanteDocente buscarDocentePorId(Long id) throws PersistenciaException;

    /**
     * Obtiene todos los participantes docentes almacenados en la base de datos.
     *
     * @return Lista de todos los participantes docentes
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<ParticipanteDocente> consultarTodosDocentes() throws PersistenciaException;

    /**
     * Consulta participantes docentes por departamento.
     *
     * @param departamento Departamento a buscar
     * @return Lista de participantes docentes del departamento especificado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<ParticipanteDocente> consultarPorDepartamento(String departamento) throws PersistenciaException;
    
}
