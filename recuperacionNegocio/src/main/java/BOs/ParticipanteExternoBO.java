/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DTOs.ParticipanteExternoCreacionDTO;
import DTOs.ParticipanteExternoDTO;
import entidades.ParticipanteExterno;
import exception.NegocioException;
import exception.PersistenciaException;
import fabrica.FabricaDAO;
import interfaces.IParticipanteExternoBO;
import interfaces.IParticipanteExternoDAO;
import java.util.List;
import mapper.ParticipanteExternoMapper;

/**
 * Implementación de la interfaz IParticipanteExternoBO. Contiene la lógica de
 * negocio para la gestión de participantes externos en el sistema.
 *
 * @author Alejandra García Preciado - 252444
 */
public class ParticipanteExternoBO extends ParticipanteBO implements IParticipanteExternoBO {

    private final IParticipanteExternoDAO externoDAO;
    private final ParticipanteExternoMapper externoMapper;

    /**
     * Constructor que inicializa las dependencias necesarias.
     */
    public ParticipanteExternoBO() {
        super();
        this.externoDAO = FabricaDAO.getInstancia().crearParticipanteExternoDAO();
        this.externoMapper = new ParticipanteExternoMapper();
    }

    /**
     * Registra un nuevo participante externo en el sistema.
     *
     * @param externoDTO DTO con la información del participante externo
     * @return DTO con la información del participante externo registrado
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    @Override
    public ParticipanteExternoDTO registrar(ParticipanteExternoCreacionDTO externoDTO) throws NegocioException {
        try {
            // Validar que los campos requeridos no sean nulos o vacíos
            validarCamposRequeridos(externoDTO);

            // Verificar que el correo no esté duplicado
            if (participanteDAO.buscarPorCorreo(externoDTO.getCorreo()) != null) {
                throw new NegocioException("El correo electrónico ya está registrado en el sistema.");
            }

            // Crear y persistir la entidad ParticipanteExterno
            ParticipanteExterno externo = externoMapper.toEntity(externoDTO);
            externo = externoDAO.guardarExterno(externo);

            // Retornar el DTO con la información del participante externo persistido
            return externoMapper.toDTO(externo);

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al registrar el participante externo: " + ex.getMessage());
        }
    }

    /**
     * Consulta un participante externo por su ID.
     *
     * @param id ID del participante externo a consultar
     * @return DTO con la información del participante externo
     * @throws NegocioException Si hay errores de persistencia o el participante
     * externo no existe
     */
    @Override
    public ParticipanteExternoDTO consultarExterno(Long id) throws NegocioException {
        try {
            ParticipanteExterno externo = externoDAO.buscarExternoPorId(id);
            if (externo == null) {
                throw new NegocioException("El participante externo con ID " + id + " no existe.");
            }
            return externoMapper.toDTO(externo);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar el participante externo: " + ex.getMessage());
        }
    }

    /**
     * Obtiene todos los participantes externos registrados en el sistema.
     *
     * @return Lista de DTOs con la información de los participantes externos
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public List<ParticipanteExternoDTO> consultarTodosExternos() throws NegocioException {
        try {
            List<ParticipanteExterno> externos = externoDAO.consultarTodosExternos();
            return externoMapper.toDTOList(externos);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar todos los participantes externos: " + ex.getMessage());
        }
    }

    /**
     * Consulta participantes externos por institución.
     *
     * @param institucion Institución a buscar
     * @return Lista de DTOs con la información de los participantes externos
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public List<ParticipanteExternoDTO> consultarPorInstitucion(String institucion) throws NegocioException {
        try {
            if (institucion == null || institucion.trim().isEmpty()) {
                throw new NegocioException("La institución a buscar no puede estar vacía.");
            }
            List<ParticipanteExterno> externos = externoDAO.consultarPorInstitucion(institucion);
            return externoMapper.toDTOList(externos);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar participantes externos por institución: " + ex.getMessage());
        }
    }

    /**
     * Valida que los campos requeridos del DTO no sean nulos o vacíos.
     *
     * @param externoDTO DTO a validar
     * @throws NegocioException Si algún campo requerido es inválido
     */
    private void validarCamposRequeridos(ParticipanteExternoCreacionDTO externoDTO) throws NegocioException {
        if (externoDTO == null) {
            throw new NegocioException("La información del participante externo no puede ser nula.");
        }

        if (externoDTO.getNombre() == null || externoDTO.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre del participante externo es obligatorio.");
        }

        if (externoDTO.getApellidoPaterno() == null || externoDTO.getApellidoPaterno().trim().isEmpty()) {
            throw new NegocioException("El apellido paterno del participante externo es obligatorio.");
        }

        if (externoDTO.getApellidoMaterno() == null || externoDTO.getApellidoMaterno().trim().isEmpty()) {
            throw new NegocioException("El apellido materno del participante externo es obligatorio.");
        }

        if (externoDTO.getCorreo() == null || externoDTO.getCorreo().trim().isEmpty()) {
            throw new NegocioException("El correo del participante externo es obligatorio.");
        }

        // Validar formato de correo electrónico
        if (!validarFormatoCorreo(externoDTO.getCorreo())) {
            throw new NegocioException("El formato del correo electrónico no es válido.");
        }
    }

}
