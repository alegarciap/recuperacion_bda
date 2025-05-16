/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DTOs.OrganizadorDTO;
import entidades.Organizador;
import entidades.Organizador.TipoOrganizador;
import exception.NegocioException;
import exception.PersistenciaException;
import fabrica.FabricaDAO;
import interfaces.IOrganizadorBO;
import interfaces.IOrganizadorDAO;
import java.util.List;
import mapper.OrganizadorMapper;

/**
 * Implementación de la interfaz IOrganizadorBO. Contiene la lógica de negocio
 * para la gestión de organizadores en el sistema.
 *
 * @author Alejandra García Preciado - 252444
 */
public class OrganizadorBO implements IOrganizadorBO {

    private final IOrganizadorDAO organizadorDAO;
    private final OrganizadorMapper organizadorMapper;

    /**
     * Constructor que inicializa las dependencias necesarias.
     */
    public OrganizadorBO() {
        this.organizadorDAO = FabricaDAO.getInstancia().crearOrganizadorDAO();
        this.organizadorMapper = new OrganizadorMapper();
    }

    /**
     * Registra un nuevo organizador en el sistema.
     *
     * @param organizadorDTO DTO con la información del organizador
     * @return DTO con la información del organizador registrado
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    @Override
    public OrganizadorDTO registrar(OrganizadorDTO organizadorDTO) throws NegocioException {
        try {
            // Validar que los campos requeridos no sean nulos o vacíos
            validarCamposRequeridos(organizadorDTO);

            // Crear y persistir la entidad Organizador
            Organizador organizador = organizadorMapper.toEntity(organizadorDTO);
            organizador = organizadorDAO.guardar(organizador);

            // Retornar el DTO con la información del organizador persistido
            return organizadorMapper.toDTO(organizador);

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al registrar el organizador: " + ex.getMessage());
        }
    }

    /**
     * Elimina un organizador existente.
     *
     * @param id ID del organizador a eliminar
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    @Override
    public void eliminar(Long id) throws NegocioException {
        try {
            // Verificar que el organizador exista
            Organizador organizador = organizadorDAO.buscarPorId(id);
            if (organizador == null) {
                throw new NegocioException("El organizador con ID " + id + " no existe.");
            }

            // Verificar que el organizador no tenga eventos asociados
            if (organizador.getEventos() != null && !organizador.getEventos().isEmpty()) {
                throw new NegocioException("No se puede eliminar el organizador porque tiene eventos asociados.");
            }

            // Eliminar el organizador
            organizadorDAO.eliminar(id);

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al eliminar el organizador: " + ex.getMessage());
        }
    }

    /**
     * Consulta un organizador por su ID.
     *
     * @param id ID del organizador a consultar
     * @return DTO con la información del organizador
     * @throws NegocioException Si hay errores de persistencia o el organizador
     * no existe
     */
    @Override
    public OrganizadorDTO consultar(Long id) throws NegocioException {
        try {
            Organizador organizador = organizadorDAO.buscarPorId(id);
            if (organizador == null) {
                throw new NegocioException("El organizador con ID " + id + " no existe.");
            }
            return organizadorMapper.toDTO(organizador);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar el organizador: " + ex.getMessage());
        }
    }

    /**
     * Obtiene todos los organizadores registrados en el sistema.
     *
     * @return Lista de DTOs con la información de los organizadores
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public List<OrganizadorDTO> consultarTodos() throws NegocioException {
        try {
            List<Organizador> organizadores = organizadorDAO.consultarTodos();
            return organizadorMapper.toDTOList(organizadores);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar todos los organizadores: " + ex.getMessage());
        }
    }

    /**
     * Consulta organizadores por su tipo.
     *
     * @param tipo Tipo a buscar (ORGANIZADOR, PONENTE, RESPONSABLE)
     * @return Lista de DTOs con la información de los organizadores
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public List<OrganizadorDTO> consultarPorTipo(String tipo) throws NegocioException {
        try {
            if (tipo == null || tipo.trim().isEmpty()) {
                throw new NegocioException("El tipo a buscar no puede estar vacío.");
            }

            TipoOrganizador tipoOrganizador;
            try {
                tipoOrganizador = TipoOrganizador.valueOf(tipo);
            } catch (IllegalArgumentException ex) {
                throw new NegocioException("Tipo no válido. Valores permitidos: ORGANIZADOR, PONENTE, RESPONSABLE");
            }

            List<Organizador> organizadores = organizadorDAO.consultarPorTipo(tipoOrganizador);
            return organizadorMapper.toDTOList(organizadores);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar organizadores por tipo: " + ex.getMessage());
        }
    }

    /**
     * Valida que los campos requeridos del DTO no sean nulos o vacíos.
     *
     * @param organizadorDTO DTO a validar
     * @throws NegocioException Si algún campo requerido es inválido
     */
    private void validarCamposRequeridos(OrganizadorDTO organizadorDTO) throws NegocioException {
        if (organizadorDTO == null) {
            throw new NegocioException("La información del organizador no puede ser nula.");
        }

        if (organizadorDTO.getNombre() == null || organizadorDTO.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre del organizador es obligatorio.");
        }

        if (organizadorDTO.getCorreo() == null || organizadorDTO.getCorreo().trim().isEmpty()) {
            throw new NegocioException("El correo del organizador es obligatorio.");
        }

        // Validar formato de correo electrónico
        if (!validarFormatoCorreo(organizadorDTO.getCorreo())) {
            throw new NegocioException("El formato del correo electrónico no es válido.");
        }

        if (organizadorDTO.getTipoOrganizador() == null || organizadorDTO.getTipoOrganizador().trim().isEmpty()) {
            throw new NegocioException("El tipo de organizador es obligatorio.");
        }

        try {
            TipoOrganizador.valueOf(organizadorDTO.getTipoOrganizador());
        } catch (IllegalArgumentException ex) {
            throw new NegocioException("Tipo no válido. Valores permitidos: ORGANIZADOR, PONENTE, RESPONSABLE");
        }
    }

    /**
     * Valida que el formato del correo electrónico sea válido.
     *
     * @param correo Correo a validar
     * @return true si el formato es válido, false en caso contrario
     */
    private boolean validarFormatoCorreo(String correo) {
        // validación de formato de correo electrónico
        String regex = "^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$";
        return correo.matches(regex);
    }

}
