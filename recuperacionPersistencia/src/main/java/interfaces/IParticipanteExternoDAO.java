/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.ParticipanteExterno;
import exception.PersistenciaException;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD para la entidad ParticipanteExterno.
 * 
 * @author Alejandra García Preciado - 252444
 */
public interface IParticipanteExternoDAO extends IParticipanteDAO {
    
    /**
     * Guarda un participante externo en la base de datos.
     *
     * @param externo Objeto ParticipanteExterno a guardar
     * @return ParticipanteExterno guardado con su ID asignado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public ParticipanteExterno guardarExterno(ParticipanteExterno externo) throws PersistenciaException;

    /**
     * Busca un participante externo por su ID.
     *
     * @param id ID del participante externo a buscar
     * @return ParticipanteExterno encontrado o null si no existe
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public ParticipanteExterno buscarExternoPorId(Long id) throws PersistenciaException;

    /**
     * Obtiene todos los participantes externos almacenados en la base de datos.
     *
     * @return Lista de todos los participantes externos
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<ParticipanteExterno> consultarTodosExternos() throws PersistenciaException;

    /**
     * Consulta participantes externos por institución.
     *
     * @param institucion Institución a buscar
     * @return Lista de participantes externos de la institución especificada
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<ParticipanteExterno> consultarPorInstitucion(String institucion) throws PersistenciaException;
    
}
