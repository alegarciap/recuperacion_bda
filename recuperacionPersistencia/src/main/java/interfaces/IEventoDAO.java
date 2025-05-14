/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Evento;
import entidades.Evento.EstadoEvento;
import entidades.Evento.ModalidadEvento;
import entidades.Organizador;
import exception.PersistenciaException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD para la entidad Evento.
 * 
 * @author Alejandra García Preciado - 252444
 */
public interface IEventoDAO {
    
    /**
     * Guarda un evento en la base de datos.
     *
     * @param evento Objeto Evento a guardar
     * @return Evento guardado con su ID asignado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public Evento guardar(Evento evento) throws PersistenciaException;

    /**
     * Actualiza un evento existente en la base de datos.
     *
     * @param evento Objeto Evento con los datos actualizados
     * @return Evento actualizado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public Evento actualizar(Evento evento) throws PersistenciaException;

    /**
     * Busca un evento por su ID.
     *
     * @param id ID del evento a buscar
     * @return Evento encontrado o null si no existe
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public Evento buscarPorId(Long id) throws PersistenciaException;

    /**
     * Busca un evento por su código único.
     *
     * @param codigo Código único del evento
     * @return Evento encontrado o null si no existe
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public Evento buscarPorCodigo(String codigo) throws PersistenciaException;

    /**
     * Obtiene todos los eventos almacenados en la base de datos.
     *
     * @return Lista de todos los eventos
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<Evento> consultarTodos() throws PersistenciaException;

    /**
     * Consulta eventos por su título.
     *
     * @param titulo Título o parte del título a buscar
     * @return Lista de eventos que coinciden con el título
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<Evento> consultarPorTitulo(String titulo) throws PersistenciaException;

    /**
     * Consulta eventos por su estado.
     *
     * @param estado Estado a buscar
     * @return Lista de eventos con el estado especificado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<Evento> consultarPorEstado(EstadoEvento estado) throws PersistenciaException;

    /**
     * Consulta eventos por su modalidad.
     *
     * @param modalidad Modalidad a buscar
     * @return Lista de eventos con la modalidad especificada
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<Evento> consultarPorModalidad(ModalidadEvento modalidad) throws PersistenciaException;

    /**
     * Consulta eventos por un rango de fechas.
     *
     * @param fechaInicio Fecha de inicio del rango
     * @param fechaFin Fecha de fin del rango
     * @return Lista de eventos en el rango de fechas
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<Evento> consultarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws PersistenciaException;

    /**
     * Consulta eventos por organizador.
     *
     * @param organizador Organizador de los eventos
     * @return Lista de eventos del organizador especificado
     * @throws PersistenciaException Si ocurre un error durante la operación
     */
    public List<Evento> consultarPorOrganizador(Organizador organizador) throws PersistenciaException;
    
}
