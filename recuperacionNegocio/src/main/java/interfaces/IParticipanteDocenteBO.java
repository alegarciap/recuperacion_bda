/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import DTOs.ParticipanteDocenteCreacionDTO;
import DTOs.ParticipanteDocenteDTO;
import exception.NegocioException;
import java.util.List;

/**
 * Interfaz que define las operaciones de negocio específicas para los
 * participantes de tipo docente.
 *
 * @author Alejandra García Preciado - 252444
 */
public interface IParticipanteDocenteBO extends IParticipanteBO {

    /**
     * Registra un nuevo participante docente en el sistema.
     *
     * @param docenteDTO DTO con la información del docente
     * @return DTO con la información del docente registrado
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    public ParticipanteDocenteDTO registrar(ParticipanteDocenteCreacionDTO docenteDTO) throws NegocioException;

    /**
     * Consulta un participante docente por su ID.
     *
     * @param id ID del docente a consultar
     * @return DTO con la información del docente
     * @throws NegocioException Si hay errores de persistencia o el docente no
     * existe
     */
    public ParticipanteDocenteDTO consultarDocente(Long id) throws NegocioException;

    /**
     * Obtiene todos los participantes docentes registrados en el sistema.
     *
     * @return Lista de DTOs con la información de los docentes
     * @throws NegocioException Si hay errores de persistencia
     */
    public List<ParticipanteDocenteDTO> consultarTodosDocentes() throws NegocioException;

    /**
     * Consulta participantes docentes por departamento.
     *
     * @param departamento Departamento a buscar
     * @return Lista de DTOs con la información de los docentes
     * @throws NegocioException Si hay errores de persistencia
     */
    public List<ParticipanteDocenteDTO> consultarPorDepartamento(String departamento) throws NegocioException;

}
