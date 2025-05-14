/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.ParticipanteEstudiante;
import exception.PersistenciaException;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD para la entidad ParticipanteEstudiante.
 * 
 * @author Alejandra García Preciado - 252444
 */
public interface IParticipanteEstudianteDAO extends IParticipanteDAO {
    
    /**
     * Guarda un participante estudiante en la base de datos.
     *
     * @param estudiante Objeto ParticipanteEstudiante a guardar
     * @return ParticipanteEstudiante guardado con su ID asignado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public ParticipanteEstudiante guardarEstudiante(ParticipanteEstudiante estudiante) throws PersistenciaException;

    /**
     * Busca un participante estudiante por su ID.
     *
     * @param id ID del participante estudiante a buscar
     * @return ParticipanteEstudiante encontrado o null si no existe
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public ParticipanteEstudiante buscarEstudiantePorId(Long id) throws PersistenciaException;

    /**
     * Obtiene todos los participantes estudiantes almacenados en la base de
     * datos.
     *
     * @return Lista de todos los participantes estudiantes
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<ParticipanteEstudiante> consultarTodosEstudiantes() throws PersistenciaException;

    /**
     * Busca un participante estudiante por su número de control.
     *
     * @param numeroControl Número de control a buscar
     * @return ParticipanteEstudiante encontrado o null si no existe
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public ParticipanteEstudiante buscarPorNumeroControl(Integer numeroControl) throws PersistenciaException;

    /**
     * Consulta participantes estudiantes por carrera.
     *
     * @param carrera Carrera a buscar
     * @return Lista de participantes estudiantes de la carrera especificada
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<ParticipanteEstudiante> consultarPorCarrera(String carrera) throws PersistenciaException;
    
}
