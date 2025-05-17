/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import DTOs.ParticipanteDTO;
import presentacion.MenuPrincipalForm;
import presentacion.moduloActividades.*;
import presentacion.moduloEventos.*;
import presentacion.moduloOrganizadores.*;
import presentacion.moduloParticipantes.*;

/**
 * Coordinador central de la capa de presentación del sistema. Implementa el
 * patrón Singleton para garantizar una única instancia en toda la aplicación y
 * el patrón Mediator para gestionar la comunicación entre los diferentes
 * formularios.
 *
 * @author Alejandra García 252444
 */
public class CoordinadorAplicacion {
    
    /**
     * Instancia única del coordinador de aplicación (patrón Singleton).
     * Garantiza que solo exista una instancia en toda la aplicación.
     */
    private static CoordinadorAplicacion coordinador;
    
    /**
     * Formulario principal que muestra el menú de opciones del sistema.
     */
    private MenuPrincipalForm menuPrincipalForm;

    /**
     * Formulario que gestiona el módulo de actividades, permitiendo acceder a
     * las operaciones relacionadas con actividades.
     */
    private ModuloActividadesForm moduloActividadesForm;

    /**
     * Formulario para la creación de una nueva actividad en el sistema.
     */
    private NuevaActividadForm nuevaActividadForm;

    /**
     * Formulario que muestra y gestiona los participantes asociados a una
     * actividad.
     */
    private ParticipantesActividadForm participantesActividadForm;

    /**
     * Formulario que muestra los detalles de una actividad específica.
     */
    private VerActividadForm verActividadForm;

    /**
     * Formulario que gestiona el módulo de eventos, permitiendo acceder a las
     * operaciones relacionadas con eventos.
     */
    private ModuloEventosForm moduloEventosForm;

    /**
     * Formulario que muestra y gestiona las actividades asociadas a un evento.
     */
    private ActividadesEventoForm actividadesEventoForm;

    /**
     * Formulario que permite realizar búsquedas de eventos por su estado.
     */
    private BusquedaEstadoForm busquedaEstadoForm;

    /**
     * Formulario que permite realizar búsquedas de eventos por rango de fechas.
     */
    private BusquedaFechasForm busquedaFechasForm;

    /**
     * Formulario que permite realizar búsquedas de eventos por su modalidad.
     */
    private BusquedaModalidadForm busquedaModalidadForm;

    /**
     * Formulario para editar la información de un evento existente.
     */
    private EditarEventoForm editarEventoForm;

    /**
     * Formulario para la creación de un nuevo evento en el sistema.
     */
    private NuevoEventoForm nuevoEventoForm;

    /**
     * Formulario que muestra los detalles de un evento específico.
     */
    private VerEventoForm verEventoForm;

    /**
     * Formulario que gestiona el módulo de organizadores, permitiendo acceder a
     * las operaciones relacionadas con organizadores.
     */
    private ModuloOrganizadoresForm moduloOrganizadoresForm;

    /**
     * Formulario para la creación de un nuevo organizador en el sistema.
     */
    private NuevoOrganizadorForm nuevoOrganizadorForm;

    /**
     * Formulario que gestiona el módulo de participantes, permitiendo acceder a
     * las operaciones relacionadas con participantes.
     */
    private ModuloParticipantesForm moduloParticipantesForm;

    /**
     * Formulario que permite buscar participantes mediante diversos criterios.
     */
    private BuscadorParticipantesForm buscadorParticipantesForm;

    /**
     * Formulario que muestra el historial de participación de un participante
     * en diferentes eventos y actividades.
     */
    private HistorialParticipacionForm historialParticipacionForm;

    /**
     * Formulario para inscribir un participante existente en una actividad o
     * evento.
     */
    private InscribirParticipanteForm inscribirParticipanteForm;

    /**
     * Formulario que muestra la lista de todos los participantes inscritos en
     * una actividad o evento específico.
     */
    private ListaParticipantesInscritosForm listaParticipantesInscritosForm;

    /**
     * Formulario para la creación de un nuevo participante en el sistema.
     */
    private NuevoParticipanteForm nuevoParticipanteForm;

    /**
     * Formulario que muestra los detalles de un participante específico.
     */
    private VerParticipanteForm verParticipanteForm;
    
    private ParticipanteDTO participanteSeleccionado;
    
    /**
     * Constructor privado (patrón Singleton). Previene la creación de múltiples
     * instancias desde fuera de la clase.
     */
    private CoordinadorAplicacion() {
    }
    
    /**
     * Obtiene la instancia única del coordinador de aplicación (patrón
     * Singleton). Si no existe una instancia, la crea, de lo contrario,
     * devuelve la existente.
     *
     * @return La instancia única del CoordinadorAplicacion
     */
    public static CoordinadorAplicacion getInstancia() {
        if (coordinador == null) {
            coordinador = new CoordinadorAplicacion();
        }
        return coordinador;
    }
    
    /**
     * Muestra el formulario del menú principal. Si el formulario no ha sido
     * creado previamente, lo instancia.
     */
    public void mostrarMenuPrincipal() {
        if (menuPrincipalForm == null) {
            menuPrincipalForm = new MenuPrincipalForm();
        }
        menuPrincipalForm.setVisible(true);
    }

    /**
     * Muestra el formulario del módulo de actividades. Si el formulario no ha
     * sido creado previamente, lo instancia.
     */
    public void mostrarModuloActividades() {
        if (moduloActividadesForm == null) {
            moduloActividadesForm = new ModuloActividadesForm();
        }
        moduloActividadesForm.setVisible(true);
    }

    /**
     * Muestra el formulario para crear una nueva actividad. Si el formulario no
     * ha sido creado previamente, lo instancia.
     */
    public void mostrarNuevaActividad() {
        if (nuevaActividadForm == null) {
            nuevaActividadForm = new NuevaActividadForm();
        }
        nuevaActividadForm.setVisible(true);
    }

    /**
     * Muestra el formulario de participantes de una actividad. Si el formulario
     * no ha sido creado previamente, lo instancia.
     */
    public void mostrarParticipantesActividad() {
        if (participantesActividadForm == null) {
            participantesActividadForm = new ParticipantesActividadForm();
        }
        participantesActividadForm.setVisible(true);
    }

    /**
     * Muestra el formulario para ver detalles de una actividad. Si el
     * formulario no ha sido creado previamente, lo instancia.
     */
    public void mostrarVerActividad() {
        if (verActividadForm == null) {
            verActividadForm = new VerActividadForm();
        }
        verActividadForm.setVisible(true);
    }

    /**
     * Muestra el formulario del módulo de eventos. Si el formulario no ha sido
     * creado previamente, lo instancia.
     */
    public void mostrarModuloEventos() {
        if (moduloEventosForm == null) {
            moduloEventosForm = new ModuloEventosForm();
        }
        moduloEventosForm.setVisible(true);
    }

    /**
     * Muestra el formulario de actividades de un evento. Si el formulario no ha
     * sido creado previamente, lo instancia.
     */
    public void mostrarActividadesEvento() {
        if (actividadesEventoForm == null) {
            actividadesEventoForm = new ActividadesEventoForm();
        }
        actividadesEventoForm.setVisible(true);
    }

    /**
     * Muestra el formulario de búsqueda por estado. Si el formulario no ha sido
     * creado previamente, lo instancia.
     */
    public void mostrarBusquedaEstado() {
        if (busquedaEstadoForm == null) {
            busquedaEstadoForm = new BusquedaEstadoForm();
        }
        busquedaEstadoForm.setVisible(true);
    }

    /**
     * Muestra el formulario de búsqueda por fechas. Si el formulario no ha sido
     * creado previamente, lo instancia.
     */
    public void mostrarBusquedaFechas() {
        if (busquedaFechasForm == null) {
            busquedaFechasForm = new BusquedaFechasForm();
        }
        busquedaFechasForm.setVisible(true);
    }

    /**
     * Muestra el formulario de búsqueda por modalidad. Si el formulario no ha
     * sido creado previamente, lo instancia.
     */
    public void mostrarBusquedaModalidad() {
        if (busquedaModalidadForm == null) {
            busquedaModalidadForm = new BusquedaModalidadForm();
        }
        busquedaModalidadForm.setVisible(true);
    }

    /**
     * Muestra el formulario para editar un evento. Si el formulario no ha sido
     * creado previamente, lo instancia.
     */
    public void mostrarEditarEvento() {
        if (editarEventoForm == null) {
            editarEventoForm = new EditarEventoForm();
        }
        editarEventoForm.setVisible(true);
    }

    /**
     * Muestra el formulario para crear un nuevo evento. Si el formulario no ha
     * sido creado previamente, lo instancia.
     */
    public void mostrarNuevoEvento() {
        if (nuevoEventoForm == null) {
            nuevoEventoForm = new NuevoEventoForm();
        }
        nuevoEventoForm.setVisible(true);
    }

    /**
     * Muestra el formulario para ver detalles de un evento. Si el formulario no
     * ha sido creado previamente, lo instancia.
     */
    public void mostrarVerEvento() {
        if (verEventoForm == null) {
            verEventoForm = new VerEventoForm();
        }
        verEventoForm.setVisible(true);
    }

    /**
     * Muestra el formulario del módulo de organizadores. Si el formulario no ha
     * sido creado previamente, lo instancia.
     */
    public void mostrarModuloOrganizadores() {
        if (moduloOrganizadoresForm == null) {
            moduloOrganizadoresForm = new ModuloOrganizadoresForm();
        }
        moduloOrganizadoresForm.setVisible(true);
    }

    /**
     * Muestra el formulario para crear un nuevo organizador. Si el formulario
     * no ha sido creado previamente, lo instancia.
     */
    public void mostrarNuevoOrganizador() {
        if (nuevoOrganizadorForm == null) {
            nuevoOrganizadorForm = new NuevoOrganizadorForm();
        }
        nuevoOrganizadorForm.setVisible(true);
    }

    /**
     * Muestra el formulario del módulo de participantes. Si el formulario no ha
     * sido creado previamente, lo instancia.
     */
    public void mostrarModuloParticipantes() {
        if (moduloParticipantesForm == null) {
            moduloParticipantesForm = new ModuloParticipantesForm();
        }
        moduloParticipantesForm.setVisible(true);
    }

    /**
     * Muestra el formulario del buscador de participantes. Si el formulario no
     * ha sido creado previamente, lo instancia.
     */
    public void mostrarBuscadorParticipantes() {
        if (buscadorParticipantesForm == null) {
            buscadorParticipantesForm = new BuscadorParticipantesForm();
        }
        buscadorParticipantesForm.setVisible(true);
    }

    /**
     * Muestra el formulario de historial de participación. Si el formulario no
     * ha sido creado previamente, lo instancia.
     */
    public void mostrarHistorialParticipacion() {
        if (historialParticipacionForm == null) {
            historialParticipacionForm = new HistorialParticipacionForm();
        }
        historialParticipacionForm.setVisible(true);
    }

    /**
     * Muestra el formulario para inscribir un participante. Si el formulario no
     * ha sido creado previamente, lo instancia.
     */
    public void mostrarInscribirParticipante() {
        if (inscribirParticipanteForm == null) {
            inscribirParticipanteForm = new InscribirParticipanteForm();
        }
        inscribirParticipanteForm.setVisible(true);
    }

    /**
     * Muestra el formulario con la lista de participantes inscritos. Si el
     * formulario no ha sido creado previamente, lo instancia.
     */
    public void mostrarListaParticipantesInscritos() {
        if (listaParticipantesInscritosForm == null) {
            listaParticipantesInscritosForm = new ListaParticipantesInscritosForm();
        }
        listaParticipantesInscritosForm.setVisible(true);
    }

    /**
     * Muestra el formulario para crear un nuevo participante. Si el formulario
     * no ha sido creado previamente, lo instancia.
     */
    public void mostrarNuevoParticipante() {
        if (nuevoParticipanteForm == null) {
            nuevoParticipanteForm = new NuevoParticipanteForm();
        }
        nuevoParticipanteForm.setVisible(true);
    }

    /**
     * Muestra el formulario para ver detalles de un participante. Si el
     * formulario no ha sido creado previamente, lo instancia.
     */
    public void mostrarVerParticipante() {
        if (verParticipanteForm == null) {
            verParticipanteForm = new VerParticipanteForm();
        }
        if (participanteSeleccionado != null) {
            verParticipanteForm.setParticipante(participanteSeleccionado);
        }
        verParticipanteForm.setVisible(true);
    }
    
    /**
     * Sets the current selected participant for sharing between forms.
     *
     * @param participante The participant to set
     */
    public void setParticipanteSeleccionado(ParticipanteDTO participante) {
        this.participanteSeleccionado = participante;
    }

    /**
     * Gets the current selected participant.
     *
     * @return The currently selected participant
     */
    public ParticipanteDTO getParticipanteSeleccionado() {
        return this.participanteSeleccionado;
    }

    /**
     * Reinicia el estado del coordinador, liberando las referencias a los
     * formularios. Útil para limpiar recursos y preparar el sistema para un
     * nuevo ciclo.
     */
    public void reset() {
        menuPrincipalForm = null;

        // Reset módulo actividades
        moduloActividadesForm = null;
        nuevaActividadForm = null;
        participantesActividadForm = null;
        verActividadForm = null;

        // Reset módulo eventos
        moduloEventosForm = null;
        actividadesEventoForm = null;
        busquedaEstadoForm = null;
        busquedaFechasForm = null;
        busquedaModalidadForm = null;
        editarEventoForm = null;
        nuevoEventoForm = null;
        verEventoForm = null;

        // Reset módulo organizadores
        moduloOrganizadoresForm = null;
        nuevoOrganizadorForm = null;

        // Reset módulo participantes
        moduloParticipantesForm = null;
        buscadorParticipantesForm = null;
        historialParticipacionForm = null;
        inscribirParticipanteForm = null;
        listaParticipantesInscritosForm = null;
        nuevoParticipanteForm = null;
        verParticipanteForm = null;
    }
    
}
