/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DTOs.ParticipanteEstudianteCreacionDTO;
import DTOs.ParticipanteEstudianteDTO;
import entidades.ParticipanteEstudiante;
import exception.NegocioException;
import exception.PersistenciaException;
import fabrica.FabricaDAO;
import interfaces.IParticipanteEstudianteBO;
import interfaces.IParticipanteEstudianteDAO;
import java.util.List;
import mapper.ParticipanteEstudianteMapper;

/**
 * Implementación de la interfaz IParticipanteEstudianteBO. Contiene la lógica
 * de negocio para la gestión de participantes estudiantes en el sistema.
 *
 * @author Alejandra García Preciado - 252444
 */
public class ParticipanteEstudianteBO extends ParticipanteBO implements IParticipanteEstudianteBO {

    private final IParticipanteEstudianteDAO estudianteDAO;
    private final ParticipanteEstudianteMapper estudianteMapper;

    /**
     * Constructor que inicializa las dependencias necesarias.
     */
    public ParticipanteEstudianteBO() {
        super();
        this.estudianteDAO = FabricaDAO.getInstancia().crearParticipanteEstudianteDAO();
        this.estudianteMapper = new ParticipanteEstudianteMapper();
    }

    /**
     * Registra un nuevo participante estudiante en el sistema.
     *
     * @param estudianteDTO DTO con la información del estudiante
     * @return DTO con la información del estudiante registrado
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    @Override
    public ParticipanteEstudianteDTO registrar(ParticipanteEstudianteCreacionDTO estudianteDTO) throws NegocioException {
        try {
            // Validar que los campos requeridos no sean nulos o vacíos
            validarCamposRequeridos(estudianteDTO);

            // Verificar que el número de control no esté duplicado
            if (!verificarNumeroControlDisponible(estudianteDTO.getNumeroControl())) {
                throw new NegocioException("El número de control ya está registrado en el sistema.");
            }

            // Verificar que el correo no esté duplicado
            ParticipanteEstudiante existente = estudianteDAO.buscarPorNumeroControl(estudianteDTO.getNumeroControl());
            if (existente != null) {
                throw new NegocioException("Ya existe un estudiante con el número de control " + estudianteDTO.getNumeroControl());
            }

            // Crear y persistir la entidad ParticipanteEstudiante
            ParticipanteEstudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
            estudiante = estudianteDAO.guardarEstudiante(estudiante);

            // Retornar el DTO con la información del estudiante persistido
            return estudianteMapper.toDTO(estudiante);

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al registrar el estudiante: " + ex.getMessage());
        }
    }

    /**
     * Consulta un participante estudiante por su ID.
     *
     * @param id ID del estudiante a consultar
     * @return DTO con la información del estudiante
     * @throws NegocioException Si hay errores de persistencia o el estudiante
     * no existe
     */
    @Override
    public ParticipanteEstudianteDTO consultarEstudiante(Long id) throws NegocioException {
        try {
            ParticipanteEstudiante estudiante = estudianteDAO.buscarEstudiantePorId(id);
            if (estudiante == null) {
                throw new NegocioException("El estudiante con ID " + id + " no existe.");
            }
            return estudianteMapper.toDTO(estudiante);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar el estudiante: " + ex.getMessage());
        }
    }

    /**
     * Obtiene todos los participantes estudiantes registrados en el sistema.
     *
     * @return Lista de DTOs con la información de los estudiantes
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public List<ParticipanteEstudianteDTO> consultarTodosEstudiantes() throws NegocioException {
        try {
            List<ParticipanteEstudiante> estudiantes = estudianteDAO.consultarTodosEstudiantes();
            return estudianteMapper.toDTOList(estudiantes);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar todos los estudiantes: " + ex.getMessage());
        }
    }

    /**
     * Consulta un participante estudiante por su número de control.
     *
     * @param numeroControl Número de control a buscar
     * @return DTO con la información del estudiante
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public ParticipanteEstudianteDTO consultarPorNumeroControl(String numeroControl) throws NegocioException {
        try {
            if (numeroControl == null) {
                throw new NegocioException("El número de control no puede ser nulo.");
            }
            ParticipanteEstudiante estudiante = estudianteDAO.buscarPorNumeroControl(numeroControl);
            if (estudiante == null) {
                return null; // No existe un estudiante con ese número de control
            }
            return estudianteMapper.toDTO(estudiante);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar estudiante por número de control: " + ex.getMessage());
        }
    }

    /**
     * Consulta participantes estudiantes por carrera.
     *
     * @param carrera Carrera a buscar
     * @return Lista de DTOs con la información de los estudiantes
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public List<ParticipanteEstudianteDTO> consultarPorCarrera(String carrera) throws NegocioException {
        try {
            if (carrera == null || carrera.trim().isEmpty()) {
                throw new NegocioException("La carrera a buscar no puede estar vacía.");
            }
            List<ParticipanteEstudiante> estudiantes = estudianteDAO.consultarPorCarrera(carrera);
            return estudianteMapper.toDTOList(estudiantes);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar estudiantes por carrera: " + ex.getMessage());
        }
    }

    /**
     * Verifica si un número de control está disponible (no duplicado).
     *
     * @param numeroControl Número de control a validar
     * @return true si está disponible, false si ya existe
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public boolean verificarNumeroControlDisponible(String numeroControl) throws NegocioException {
        try {
            if (numeroControl == null) {
                throw new NegocioException("El número de control no puede ser nulo.");
            }
            ParticipanteEstudiante estudiante = estudianteDAO.buscarPorNumeroControl(numeroControl);
            return estudiante == null; // Si es null, está disponible
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al verificar disponibilidad del número de control: " + ex.getMessage());
        }
    }

    /**
     * Valida que los campos requeridos del DTO no sean nulos o vacíos.
     *
     * @param estudianteDTO DTO a validar
     * @throws NegocioException Si algún campo requerido es inválido
     */
    private void validarCamposRequeridos(ParticipanteEstudianteCreacionDTO estudianteDTO) throws NegocioException {
        if (estudianteDTO == null) {
            throw new NegocioException("La información del estudiante no puede ser nula.");
        }

        if (estudianteDTO.getNombre() == null || estudianteDTO.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre del estudiante es obligatorio.");
        }

        if (estudianteDTO.getApellidoPaterno() == null || estudianteDTO.getApellidoPaterno().trim().isEmpty()) {
            throw new NegocioException("El apellido paterno del estudiante es obligatorio.");
        }

        if (estudianteDTO.getApellidoMaterno() == null || estudianteDTO.getApellidoMaterno().trim().isEmpty()) {
            throw new NegocioException("El apellido materno del estudiante es obligatorio.");
        }

        if (estudianteDTO.getCorreo() == null || estudianteDTO.getCorreo().trim().isEmpty()) {
            throw new NegocioException("El correo del estudiante es obligatorio.");
        }

        // Validar formato de correo electrónico
        if (!validarFormatoCorreo(estudianteDTO.getCorreo())) {
            throw new NegocioException("El formato del correo electrónico no es válido.");
        }

        if (estudianteDTO.getNumeroControl() == null) {
            throw new NegocioException("El número de control del estudiante es obligatorio.");
        }

        if (estudianteDTO.getCarrera() == null || estudianteDTO.getCarrera().trim().isEmpty()) {
            throw new NegocioException("La carrera del estudiante es obligatoria.");
        }
    }

}
