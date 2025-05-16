/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DTOs.ParticipanteDTO;
import entidades.Participante;
import exception.NegocioException;
import exception.PersistenciaException;
import fabrica.FabricaDAO;
import interfaces.IParticipanteBO;
import interfaces.IParticipanteDAO;
import java.util.List;
import mapper.ParticipanteMapper;

/**
 * Implementación de la interfaz IParticipanteBO. Contiene la lógica de negocio
 * para la gestión general de participantes en el sistema.
 *
 * @author Alejandra García Preciado - 252444
 */
public class ParticipanteBO implements IParticipanteBO {

    protected final IParticipanteDAO participanteDAO;
    protected final ParticipanteMapper participanteMapper;

    /**
     * Constructor que inicializa las dependencias necesarias.
     */
    public ParticipanteBO() {
        this.participanteDAO = FabricaDAO.getInstancia().crearParticipanteDAO();
        this.participanteMapper = new ParticipanteMapper();
    }

    /**
     * Consulta un participante por su ID.
     *
     * @param id ID del participante a consultar
     * @return DTO con la información del participante
     * @throws NegocioException Si hay errores de persistencia o el participante
     * no existe
     */
    @Override
    public ParticipanteDTO consultar(Long id) throws NegocioException {
        try {
            Participante participante = participanteDAO.buscarPorId(id);
            if (participante == null) {
                throw new NegocioException("El participante con ID " + id + " no existe.");
            }
            return participanteMapper.toDTO(participante);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar el participante: " + ex.getMessage());
        }
    }

    /**
     * Obtiene todos los participantes registrados en el sistema.
     *
     * @return Lista de DTOs con la información de los participantes
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public List<ParticipanteDTO> consultarTodos() throws NegocioException {
        try {
            List<Participante> participantes = participanteDAO.consultarTodos();
            return participanteMapper.toDTOList(participantes);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar todos los participantes: " + ex.getMessage());
        }
    }

    /**
     * Consulta participantes por su nombre o apellidos.
     *
     * @param nombre Nombre o parte del nombre/apellidos a buscar
     * @return Lista de DTOs con la información de los participantes
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public List<ParticipanteDTO> consultarPorNombre(String nombre) throws NegocioException {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new NegocioException("El nombre a buscar no puede estar vacío.");
            }
            List<Participante> participantes = participanteDAO.consultarPorNombre(nombre);
            return participanteMapper.toDTOList(participantes);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar participantes por nombre: " + ex.getMessage());
        }
    }

    /**
     * Consulta un participante por su correo electrónico.
     *
     * @param correo Correo electrónico a buscar
     * @return DTO con la información del participante
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public ParticipanteDTO consultarPorCorreo(String correo) throws NegocioException {
        try {
            if (correo == null || correo.trim().isEmpty()) {
                throw new NegocioException("El correo a buscar no puede estar vacío.");
            }
            Participante participante = participanteDAO.buscarPorCorreo(correo);
            if (participante == null) {
                return null; // No existe un participante con ese correo
            }
            return participanteMapper.toDTO(participante);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar participante por correo: " + ex.getMessage());
        }
    }

    /**
     * Valida que el formato del correo electrónico sea válido.
     *
     * @param correo Correo a validar
     * @return true si el formato es válido, false en caso contrario
     */
    protected boolean validarFormatoCorreo(String correo) {
        // Validación de formato de correo electrónico
        String regex = "^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$";
        return correo.matches(regex);
    }

}
