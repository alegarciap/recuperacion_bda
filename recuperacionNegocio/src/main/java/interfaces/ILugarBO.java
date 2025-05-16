/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import DTOs.LugarDTO;
import exception.NegocioException;
import java.util.List;

/**
 * Interfaz que define las operaciones de negocio para la entidad Lugar.
 *
 * @author Alejandra García Preciado - 252444
 */
public interface ILugarBO {

    /**
     * Registra un nuevo lugar en el sistema.
     *
     * @param lugarDTO DTO con la información del lugar
     * @return DTO con la información del lugar registrado
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    public LugarDTO registrar(LugarDTO lugarDTO) throws NegocioException;

    /**
     * Consulta un lugar por su ID.
     *
     * @param id ID del lugar a consultar
     * @return DTO con la información del lugar
     * @throws NegocioException Si hay errores de persistencia o el lugar no
     * existe
     */
    public LugarDTO consultar(Long id) throws NegocioException;

    /**
     * Obtiene todos los lugares registrados en el sistema.
     *
     * @return Lista de DTOs con la información de los lugares
     * @throws NegocioException Si hay errores de persistencia
     */
    public List<LugarDTO> consultarTodos() throws NegocioException;

    /**
     * Consulta lugares por su nombre.
     *
     * @param nombre Nombre o parte del nombre a buscar
     * @return Lista de DTOs con la información de los lugares
     * @throws NegocioException Si hay errores de persistencia
     */
    public List<LugarDTO> consultarPorNombre(String nombre) throws NegocioException;

    /**
     * Consulta lugares por su tipo.
     *
     * @param tipo Tipo a buscar (AULA, LABORATORIO, PLATAFORMA_VIRTUAL)
     * @return Lista de DTOs con la información de los lugares
     * @throws NegocioException Si hay errores de persistencia
     */
    public List<LugarDTO> consultarPorTipo(String tipo) throws NegocioException;

    /**
     * Consulta lugares por capacidad mínima.
     *
     * @param capacidadMinima Capacidad mínima requerida
     * @return Lista de DTOs con la información de los lugares
     * @throws NegocioException Si hay errores de persistencia
     */
    public List<LugarDTO> consultarPorCapacidadMinima(int capacidadMinima) throws NegocioException;

}
