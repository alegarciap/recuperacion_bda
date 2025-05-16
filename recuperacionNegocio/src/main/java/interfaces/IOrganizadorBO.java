/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import DTOs.OrganizadorDTO;
import exception.NegocioException;
import java.util.List;

/**
 * Interfaz que define las operaciones de negocio para la entidad Organizador.
 *
 * @author Alejandra García Preciado - 252444
 */
public interface IOrganizadorBO {

    /**
     * Registra un nuevo organizador en el sistema.
     *
     * @param organizadorDTO DTO con la información del organizador
     * @return DTO con la información del organizador registrado
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    public OrganizadorDTO registrar(OrganizadorDTO organizadorDTO) throws NegocioException;

    /**
     * Elimina un organizador existente.
     *
     * @param id ID del organizador a eliminar
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    public void eliminar(Long id) throws NegocioException;

    /**
     * Consulta un organizador por su ID.
     *
     * @param id ID del organizador a consultar
     * @return DTO con la información del organizador
     * @throws NegocioException Si hay errores de persistencia o el organizador
     * no existe
     */
    public OrganizadorDTO consultar(Long id) throws NegocioException;

    /**
     * Obtiene todos los organizadores registrados en el sistema.
     *
     * @return Lista de DTOs con la información de los organizadores
     * @throws NegocioException Si hay errores de persistencia
     */
    public List<OrganizadorDTO> consultarTodos() throws NegocioException;

    /**
     * Consulta organizadores por su tipo.
     *
     * @param tipo Tipo a buscar (ORGANIZADOR, PONENTE, RESPONSABLE)
     * @return Lista de DTOs con la información de los organizadores
     * @throws NegocioException Si hay errores de persistencia
     */
    public List<OrganizadorDTO> consultarPorTipo(String tipo) throws NegocioException;
    
}
