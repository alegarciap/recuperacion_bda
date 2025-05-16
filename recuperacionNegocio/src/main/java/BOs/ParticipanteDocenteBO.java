/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DTOs.ParticipanteDocenteCreacionDTO;
import DTOs.ParticipanteDocenteDTO;
import entidades.ParticipanteDocente;
import exception.NegocioException;
import exception.PersistenciaException;
import fabrica.FabricaDAO;
import interfaces.IParticipanteDocenteBO;
import interfaces.IParticipanteDocenteDAO;
import java.util.List;
import mapper.ParticipanteDocenteMapper;

/**
 * Implementación de la interfaz IParticipanteDocenteBO. Contiene la lógica de
 * negocio para la gestión de participantes docentes en el sistema.
 *
 * @author Alejandra García Preciado - 252444
 */
public class ParticipanteDocenteBO extends ParticipanteBO implements IParticipanteDocenteBO {

    private final IParticipanteDocenteDAO docenteDAO;
    private final ParticipanteDocenteMapper docenteMapper;

    /**
     * Constructor que inicializa las dependencias necesarias.
     */
    public ParticipanteDocenteBO() {
        super();
        this.docenteDAO = FabricaDAO.getInstancia().crearParticipanteDocenteDAO();
        this.docenteMapper = new ParticipanteDocenteMapper();
    }

    /**
     * Registra un nuevo participante docente en el sistema.
     *
     * @param docenteDTO DTO con la información del docente
     * @return DTO con la información del docente registrado
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    @Override
    public ParticipanteDocenteDTO registrar(ParticipanteDocenteCreacionDTO docenteDTO) throws NegocioException {
        try {
            // Validar que los campos requeridos no sean nulos o vacíos
            validarCamposRequeridos(docenteDTO);

            // Verificar que el correo no esté duplicado
            if (participanteDAO.buscarPorCorreo(docenteDTO.getCorreo()) != null) {
                throw new NegocioException("El correo electrónico ya está registrado en el sistema.");
            }

            // Crear y persistir la entidad ParticipanteDocente
            ParticipanteDocente docente = docenteMapper.toEntity(docenteDTO);
            docente = docenteDAO.guardarDocente(docente);

            // Retornar el DTO con la información del docente persistido
            return docenteMapper.toDTO(docente);

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al registrar el docente: " + ex.getMessage());
        }
    }

    /**
     * Consulta un participante docente por su ID.
     *
     * @param id ID del docente a consultar
     * @return DTO con la información del docente
     * @throws NegocioException Si hay errores de persistencia o el docente no
     * existe
     */
    @Override
    public ParticipanteDocenteDTO consultarDocente(Long id) throws NegocioException {
        try {
            ParticipanteDocente docente = docenteDAO.buscarDocentePorId(id);
            if (docente == null) {
                throw new NegocioException("El docente con ID " + id + " no existe.");
            }
            return docenteMapper.toDTO(docente);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar el docente: " + ex.getMessage());
        }
    }

    /**
     * Obtiene todos los participantes docentes registrados en el sistema.
     *
     * @return Lista de DTOs con la información de los docentes
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public List<ParticipanteDocenteDTO> consultarTodosDocentes() throws NegocioException {
        try {
            List<ParticipanteDocente> docentes = docenteDAO.consultarTodosDocentes();
            return docenteMapper.toDTOList(docentes);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar todos los docentes: " + ex.getMessage());
        }
    }

    /**
     * Consulta participantes docentes por departamento.
     *
     * @param departamento Departamento a buscar
     * @return Lista de DTOs con la información de los docentes
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public List<ParticipanteDocenteDTO> consultarPorDepartamento(String departamento) throws NegocioException {
        try {
            if (departamento == null || departamento.trim().isEmpty()) {
                throw new NegocioException("El departamento a buscar no puede estar vacío.");
            }
            List<ParticipanteDocente> docentes = docenteDAO.consultarPorDepartamento(departamento);
            return docenteMapper.toDTOList(docentes);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar docentes por departamento: " + ex.getMessage());
        }
    }

    /**
     * Valida que los campos requeridos del DTO no sean nulos o vacíos.
     *
     * @param docenteDTO DTO a validar
     * @throws NegocioException Si algún campo requerido es inválido
     */
    private void validarCamposRequeridos(ParticipanteDocenteCreacionDTO docenteDTO) throws NegocioException {
        if (docenteDTO == null) {
            throw new NegocioException("La información del docente no puede ser nula.");
        }

        if (docenteDTO.getNombre() == null || docenteDTO.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre del docente es obligatorio.");
        }

        if (docenteDTO.getApellidoPaterno() == null || docenteDTO.getApellidoPaterno().trim().isEmpty()) {
            throw new NegocioException("El apellido paterno del docente es obligatorio.");
        }

        if (docenteDTO.getApellidoMaterno() == null || docenteDTO.getApellidoMaterno().trim().isEmpty()) {
            throw new NegocioException("El apellido materno del docente es obligatorio.");
        }

        if (docenteDTO.getCorreo() == null || docenteDTO.getCorreo().trim().isEmpty()) {
            throw new NegocioException("El correo del docente es obligatorio.");
        }

        // Validar formato de correo electrónico
        if (!validarFormatoCorreo(docenteDTO.getCorreo())) {
            throw new NegocioException("El formato del correo electrónico no es válido.");
        }

        if (docenteDTO.getDepartamento() == null || docenteDTO.getDepartamento().trim().isEmpty()) {
            throw new NegocioException("El departamento del docente es obligatorio.");
        }
    }

}
