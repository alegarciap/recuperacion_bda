/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import DTOs.ParticipanteEstudianteCreacionDTO;
import DTOs.ParticipanteEstudianteDTO;
import exception.NegocioException;
import java.util.List;

/**
 * Interfaz que define las operaciones de negocio específicas para los
 * participantes de tipo estudiante.
 *
 * @author Alejandra García Preciado - 252444
 */
public interface IParticipanteEstudianteBO extends IParticipanteBO {

    /**
     * Registra un nuevo participante estudiante en el sistema.
     *
     * @param estudianteDTO DTO con la información del estudiante
     * @return DTO con la información del estudiante registrado
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    public ParticipanteEstudianteDTO registrar(ParticipanteEstudianteCreacionDTO estudianteDTO) throws NegocioException;

    /**
     * Consulta un participante estudiante por su ID.
     *
     * @param id ID del estudiante a consultar
     * @return DTO con la información del estudiante
     * @throws NegocioException Si hay errores de persistencia o el estudiante
     * no existe
     */
    public ParticipanteEstudianteDTO consultarEstudiante(Long id) throws NegocioException;

    /**
     * Obtiene todos los participantes estudiantes registrados en el sistema.
     *
     * @return Lista de DTOs con la información de los estudiantes
     * @throws NegocioException Si hay errores de persistencia
     */
    public List<ParticipanteEstudianteDTO> consultarTodosEstudiantes() throws NegocioException;

    /**
     * Consulta un participante estudiante por su número de control.
     *
     * @param numeroControl Número de control a buscar
     * @return DTO con la información del estudiante
     * @throws NegocioException Si hay errores de persistencia
     */
    public ParticipanteEstudianteDTO consultarPorNumeroControl(String numeroControl) throws NegocioException;

    /**
     * Consulta participantes estudiantes por carrera.
     *
     * @param carrera Carrera a buscar
     * @return Lista de DTOs con la información de los estudiantes
     * @throws NegocioException Si hay errores de persistencia
     */
    public List<ParticipanteEstudianteDTO> consultarPorCarrera(String carrera) throws NegocioException;

    /**
     * Verifica si un número de control está disponible (no duplicado).
     *
     * @param numeroControl Número de control a validar
     * @return true si está disponible, false si ya existe
     * @throws NegocioException Si hay errores de persistencia
     */
    public boolean verificarNumeroControlDisponible(String numeroControl) throws NegocioException;

}
