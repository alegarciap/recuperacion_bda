/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DTOs.LugarDTO;
import entidades.Lugar;
import entidades.Lugar.TipoLugar;
import exception.NegocioException;
import exception.PersistenciaException;
import fabrica.FabricaDAO;
import interfaces.ILugarBO;
import interfaces.ILugarDAO;
import java.util.List;
import mapper.LugarMapper;

/**
 * Implementación de la interfaz ILugarBO. Contiene la lógica de negocio para la
 * gestión de lugares en el sistema.
 *
 * @author Alejandra García Preciado - 252444
 */
public class LugarBO implements ILugarBO {

    private final ILugarDAO lugarDAO;
    private final LugarMapper lugarMapper;

    /**
     * Constructor que inicializa las dependencias necesarias.
     */
    public LugarBO() {
        this.lugarDAO = FabricaDAO.getInstancia().crearLugarDAO();
        this.lugarMapper = new LugarMapper();
    }

    /**
     * Registra un nuevo lugar en el sistema.
     *
     * @param lugarDTO DTO con la información del lugar
     * @return DTO con la información del lugar registrado
     * @throws NegocioException Si hay errores de validación o persistencia
     */
    @Override
    public LugarDTO registrar(LugarDTO lugarDTO) throws NegocioException {
        try {
            // Validar que los campos requeridos no sean nulos o vacíos
            validarCamposRequeridos(lugarDTO);

            // Crear y persistir la entidad Lugar
            Lugar lugar = lugarMapper.toEntity(lugarDTO);
            lugar = lugarDAO.guardar(lugar);

            // Retornar el DTO con la información del lugar persistido
            return lugarMapper.toDTO(lugar);

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al registrar el lugar: " + ex.getMessage());
        }
    }

    /**
     * Consulta un lugar por su ID.
     *
     * @param id ID del lugar a consultar
     * @return DTO con la información del lugar
     * @throws NegocioException Si hay errores de persistencia o el lugar no
     * existe
     */
    @Override
    public LugarDTO consultar(Long id) throws NegocioException {
        try {
            Lugar lugar = lugarDAO.buscarPorId(id);
            if (lugar == null) {
                throw new NegocioException("El lugar con ID " + id + " no existe.");
            }
            return lugarMapper.toDTO(lugar);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar el lugar: " + ex.getMessage());
        }
    }

    /**
     * Obtiene todos los lugares registrados en el sistema.
     *
     * @return Lista de DTOs con la información de los lugares
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public List<LugarDTO> consultarTodos() throws NegocioException {
        try {
            List<Lugar> lugares = lugarDAO.consultarTodos();
            return lugarMapper.toDTOList(lugares);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar todos los lugares: " + ex.getMessage());
        }
    }

    /**
     * Consulta lugares por su nombre.
     *
     * @param nombre Nombre o parte del nombre a buscar
     * @return Lista de DTOs con la información de los lugares
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public List<LugarDTO> consultarPorNombre(String nombre) throws NegocioException {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new NegocioException("El nombre a buscar no puede estar vacío.");
            }
            List<Lugar> lugares = lugarDAO.consultarPorNombre(nombre);
            return lugarMapper.toDTOList(lugares);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar lugares por nombre: " + ex.getMessage());
        }
    }

    /**
     * Consulta lugares por su tipo.
     *
     * @param tipo Tipo a buscar (AULA, LABORATORIO, PLATAFORMA_VIRTUAL)
     * @return Lista de DTOs con la información de los lugares
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public List<LugarDTO> consultarPorTipo(String tipo) throws NegocioException {
        try {
            if (tipo == null || tipo.trim().isEmpty()) {
                throw new NegocioException("El tipo a buscar no puede estar vacío.");
            }

            TipoLugar tipoLugar;
            try {
                tipoLugar = TipoLugar.valueOf(tipo);
            } catch (IllegalArgumentException ex) {
                throw new NegocioException("Tipo no válido. Valores permitidos: AULA, LABORATORIO, PLATAFORMA_VIRTUAL");
            }

            List<Lugar> lugares = lugarDAO.consultarPorTipo(tipoLugar);
            return lugarMapper.toDTOList(lugares);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar lugares por tipo: " + ex.getMessage());
        }
    }

    /**
     * Consulta lugares por capacidad mínima.
     *
     * @param capacidadMinima Capacidad mínima requerida
     * @return Lista de DTOs con la información de los lugares
     * @throws NegocioException Si hay errores de persistencia
     */
    @Override
    public List<LugarDTO> consultarPorCapacidadMinima(int capacidadMinima) throws NegocioException {
        try {
            if (capacidadMinima < 0) {
                throw new NegocioException("La capacidad mínima no puede ser negativa.");
            }
            List<Lugar> lugares = lugarDAO.consultarPorCapacidadMinima(capacidadMinima);
            return lugarMapper.toDTOList(lugares);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar lugares por capacidad mínima: " + ex.getMessage());
        }
    }

    /**
     * Valida que los campos requeridos del DTO no sean nulos o vacíos.
     *
     * @param lugarDTO DTO a validar
     * @throws NegocioException Si algún campo requerido es inválido
     */
    private void validarCamposRequeridos(LugarDTO lugarDTO) throws NegocioException {
        if (lugarDTO == null) {
            throw new NegocioException("La información del lugar no puede ser nula.");
        }

        if (lugarDTO.getNombre() == null || lugarDTO.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre del lugar es obligatorio.");
        }

        if (lugarDTO.getTipoLugar() == null || lugarDTO.getTipoLugar().trim().isEmpty()) {
            throw new NegocioException("El tipo de lugar es obligatorio.");
        }

        try {
            TipoLugar.valueOf(lugarDTO.getTipoLugar());
        } catch (IllegalArgumentException ex) {
            throw new NegocioException("Tipo no válido. Valores permitidos: AULA, LABORATORIO, PLATAFORMA_VIRTUAL");
        }

        if (lugarDTO.getCapacidad() == null || lugarDTO.getCapacidad() <= 0) {
            throw new NegocioException("La capacidad debe ser un número positivo.");
        }
    }

}
