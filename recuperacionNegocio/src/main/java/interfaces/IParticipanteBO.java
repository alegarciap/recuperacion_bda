/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import DTOs.ParticipanteDTO;
import exception.NegocioException;
import java.util.List;

/**
 * Interfaz que define las operaciones de negocio comunes para todos los tipos
 * de participantes.
 *
 * @author Alejandra García Preciado - 252444
 */
public interface IParticipanteBO {

    /**
     * Consulta un participante por su ID.
     *
     * @param id ID del participante a consultar
     * @return DTO con la información del participante
     * @throws NegocioException Si hay errores de persistencia o el participante
     * no existe
     */
    public ParticipanteDTO consultar(Long id) throws NegocioException;

    /**
     * Obtiene todos los participantes registrados en el sistema.
     *
     * @return Lista de DTOs con la información de los participantes
     * @throws NegocioException Si hay errores de persistencia
     */
    public List<ParticipanteDTO> consultarTodos() throws NegocioException;

    /**
     * Consulta participantes por su nombre o apellidos.
     *
     * @param nombre Nombre o parte del nombre/apellidos a buscar
     * @return Lista de DTOs con la información de los participantes
     * @throws NegocioException Si hay errores de persistencia
     */
    public List<ParticipanteDTO> consultarPorNombre(String nombre) throws NegocioException;

    /**
     * Consulta un participante por su correo electrónico.
     *
     * @param correo Correo electrónico a buscar
     * @return DTO con la información del participante
     * @throws NegocioException Si hay errores de persistencia
     */
    public ParticipanteDTO consultarPorCorreo(String correo) throws NegocioException;

}
