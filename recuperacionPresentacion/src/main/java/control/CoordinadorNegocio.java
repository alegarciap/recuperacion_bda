/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import DTOs.ActividadCreacionDTO;
import DTOs.ActividadDTO;
import DTOs.ActividadDetalleDTO;
import DTOs.EventoDTO;
import DTOs.InscripcionCreacionDTO;
import DTOs.InscripcionDTO;
import DTOs.LugarDTO;
import DTOs.OrganizadorDTO;
import exception.NegocioException;
import fabrica.FabricaBO;
import interfaces.*;
import java.util.List;

/**
 * Coordinador para la comunicación con la capa de negocio. Implementa el patrón
 * Singleton para garantizar una única instancia en toda la aplicación.
 *
 * @author Alejandra García 252444
 */
public class CoordinadorNegocio {

    /**
     * Instancia única del coordinador de negocio (patrón Singleton).
     */
    private static CoordinadorNegocio instancia;

    // Instancias de los Business Objects
    private final IOrganizadorBO organizadorBO;
    private final IEventoBO eventoBO;
    private final IActividadBO actividadBO;
    private final IParticipanteBO participanteBO;
    private final IParticipanteEstudianteBO estudianteBO;
    private final IParticipanteDocenteBO docenteBO;
    private final IParticipanteExternoBO externoBO;
    private final ILugarBO lugarBO;
    private final IInscripcionBO inscripcionBO;

    /**
     * Constructor privado que inicializa los Business Objects.
     */
    private CoordinadorNegocio() {
        FabricaBO fabrica = FabricaBO.getInstancia();
        this.organizadorBO = fabrica.crearOrganizadorBO();
        this.eventoBO = fabrica.crearEventoBO();
        this.actividadBO = fabrica.crearActividadBO();
        this.participanteBO = fabrica.crearParticipanteBO();
        this.estudianteBO = fabrica.crearParticipanteEstudianteBO();
        this.docenteBO = fabrica.crearParticipanteDocenteBO();
        this.externoBO = fabrica.crearParticipanteExternoBO();
        this.lugarBO = fabrica.crearLugarBO();
        this.inscripcionBO = fabrica.crearInscripcionBO();
    }

    /**
     * Obtiene la instancia única del coordinador de negocio.
     *
     * @return La instancia única
     */
    public static CoordinadorNegocio getInstancia() {
        if (instancia == null) {
            instancia = new CoordinadorNegocio();
        }
        return instancia;
    }
    
    public List<ActividadDTO> consultarTodasActividades() throws NegocioException {
        IActividadBO actividadBO = FabricaBO.getInstancia().crearActividadBO();
        return actividadBO.consultarTodos();
    }

    public ActividadDetalleDTO consultarActividadDetalle(int indice) throws NegocioException {
        List<ActividadDTO> actividades = consultarTodasActividades();
        if (indice >= 0 && indice < actividades.size()) {
            IActividadBO actividadBO = FabricaBO.getInstancia().crearActividadBO();
            
            ActividadDTO actividad = actividades.get(indice);
            return actividadBO.consultarPorFechaNombre(actividad.getNombre(), actividad.getFechaHoraInicio());
        }
        return null;
    }

    public void finalizarActividad(ActividadDTO actividad) throws NegocioException {
        IActividadBO actividadBO = FabricaBO.getInstancia().crearActividadBO();
        actividadBO.finalizarActividad(actividad.getNombre(), actividad.getFechaHoraInicio());
    }

    public ActividadDTO registrarActividad(ActividadCreacionDTO actividad) throws NegocioException {
        IActividadBO actividadBO = FabricaBO.getInstancia().crearActividadBO();
        return actividadBO.registrar(actividad);
    }

    public boolean verificarConflictosHorario(ActividadCreacionDTO actividad, String nombreLugar) throws NegocioException {
        IActividadBO actividadBO = FabricaBO.getInstancia().crearActividadBO();
        ILugarBO lugarBO = FabricaBO.getInstancia().crearLugarBO();

        // Buscar lugar por nombre
        List<LugarDTO> lugares = lugarBO.consultarPorNombre(nombreLugar);
        if (lugares.isEmpty()) {
            throw new NegocioException("No se encontró el lugar especificado");
        }

        // Verificar conflictos con el primer lugar que coincida (asumiendo nombres únicos)
        return actividadBO.verificarConflictosHorario(actividad, lugares.get(0));
    }

    // EVENTOS
    public List<EventoDTO> consultarTodosEventos() throws NegocioException {
        IEventoBO eventoBO = FabricaBO.getInstancia().crearEventoBO();
        return eventoBO.consultarTodos();
    }

    // LUGARES
    public List<LugarDTO> consultarTodosLugares() throws NegocioException {
        ILugarBO lugarBO = FabricaBO.getInstancia().crearLugarBO();
        return lugarBO.consultarTodos();
    }

    public List<LugarDTO> buscarLugaresPorNombre(String nombre) throws NegocioException {
        ILugarBO lugarBO = FabricaBO.getInstancia().crearLugarBO();
        return lugarBO.consultarPorNombre(nombre);
    }

    // ORGANIZADORES
    /**
     * Registra un nuevo organizador.
     *
     * @param organizadorDTO Información del nuevo organizador
     * @return Organizador registrado
     * @throws NegocioException Si ocurre un error durante el registro
     */
    public OrganizadorDTO registrarOrganizador(OrganizadorDTO organizadorDTO) throws NegocioException {
        return organizadorBO.registrar(organizadorDTO);
    }

    /**
     * Elimina un organizador por su ID.
     *
     * @param id ID del organizador a eliminar
     * @throws NegocioException Si ocurre un error durante la eliminación
     */
    public void eliminarOrganizador(Long id) throws NegocioException {
        organizadorBO.eliminar(id);
    }

    /**
     * Consulta la información de un organizador por su ID.
     *
     * @param id ID del organizador
     * @return Información del organizador
     * @throws NegocioException Si ocurre un error durante la consulta
     */
    public OrganizadorDTO consultarOrganizador(Long id) throws NegocioException {
        return organizadorBO.consultar(id);
    }

    /**
     * Consulta todos los organizadores.
     *
     * @return Lista de todos los organizadores
     * @throws NegocioException Si ocurre un error durante la consulta
     */
    public List<OrganizadorDTO> consultarTodosOrganizadores() throws NegocioException {
        return organizadorBO.consultarTodos();
    }

    /**
     * Consulta organizadores por tipo.
     *
     * @param tipo Tipo de organizador a consultar
     * @return Lista de organizadores del tipo especificado
     * @throws NegocioException Si ocurre un error durante la consulta
     */
    public List<OrganizadorDTO> consultarOrganizadoresPorTipo(String tipo) throws NegocioException {
        return organizadorBO.consultarPorTipo(tipo);
    }

    // INSCRIPCIONES
    public List<InscripcionDTO> consultarInscripcionesPorActividad(String nombreActividad, String fechaHora) throws NegocioException {
        IInscripcionBO inscripcionBO = FabricaBO.getInstancia().crearInscripcionBO();
        return inscripcionBO.consultarPorActividad(nombreActividad, fechaHora);
    }

    public void registrarAsistencia(InscripcionDTO inscripcion, boolean asistio) throws NegocioException {
        IInscripcionBO inscripcionBO = FabricaBO.getInstancia().crearInscripcionBO();
        inscripcionBO.registrarAsistencia(inscripcion, asistio);
    }

    public InscripcionDTO registrarInscripcion(InscripcionCreacionDTO inscripcion) throws NegocioException {
        IInscripcionBO inscripcionBO = FabricaBO.getInstancia().crearInscripcionBO();
        return inscripcionBO.registrar(inscripcion);
    }
    
}
