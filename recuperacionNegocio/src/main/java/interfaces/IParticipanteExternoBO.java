/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import DTOs.ParticipanteExternoCreacionDTO;
import DTOs.ParticipanteExternoDTO;
import exception.NegocioException;
import java.util.List;

/**
 * Interfaz que define las operaciones de negocio específicas para los
 * participantes de tipo externo.
 *
 * @author Alejandra García Preciado - 252444
 */
public interface IParticipanteExternoBO extends IParticipanteBO {

    /**
     * Registra un nuevo participante externo en el sistema.
     *
     * @param externoDTO DTO con la información del participante externo
     * @return DTO con la información del participante externo registrado
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    public ParticipanteExternoDTO registrar(ParticipanteExternoCreacionDTO externoDTO) throws NegocioException;

    /**
     * Consulta un participante externo por su ID.
     *
     * @param id ID del participante externo a consultar
     * @return DTO con la información del participante externo
     * @throws NegocioException Si hay errores de persistencia o el participante
     * externo no existe
     */
    public ParticipanteExternoDTO consultarExterno(Long id) throws NegocioException;

    /**
     * Obtiene todos los participantes externos registrados en el sistema.
     *
     * @return Lista de DTOs con la información de los participantes externos
     * @throws NegocioException Si hay errores de persistencia
     */
    public List<ParticipanteExternoDTO> consultarTodosExternos() throws NegocioException;

    /**
     * Consulta participantes externos por institución.
     *
     * @param institucion Institución a buscar
     * @return Lista de DTOs con la información de los participantes externos
     * @throws NegocioException Si hay errores de persistencia
     */
    public List<ParticipanteExternoDTO> consultarPorInstitucion(String institucion) throws NegocioException;

}
