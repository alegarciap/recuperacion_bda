/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabrica;

import BOs.*;
import interfaces.*;

/**
 * Fábrica para crear instancias de BOs. Implementa el patrón Singleton.
 *
 * @author Alejandra García Preciado - 252444
 */
public class FabricaBO {

    private static FabricaBO instancia;

    private FabricaBO() {
    }

    /**
     * Obtiene la instancia única de la fábrica.
     *
     * @return Instancia de FabricaBO
     */
    public static FabricaBO getInstancia() {
        if (instancia == null) {
            instancia = new FabricaBO();
        }
        return instancia;
    }

    /**
     * Crea una instancia de IActividadBO.
     *
     * @return Instancia de IActividadBO
     */
    public IActividadBO crearActividadBO() {
        return new ActividadBO();
    }

    /**
     * Crea una instancia de IEventoBO.
     *
     * @return Instancia de IEventoBO
     */
    public IEventoBO crearEventoBO() {
        return new EventoBO();
    }

    /**
     * Crea una instancia de IInscripcionBO.
     *
     * @return Instancia de IInscripcionBO
     */
    public IInscripcionBO crearInscripcionBO() {
        return new InscripcionBO();
    }

    /**
     * Crea una instancia de ILugarBO.
     *
     * @return Instancia de ILugarBO
     */
    public ILugarBO crearLugarBO() {
        return new LugarBO();
    }

    /**
     * Crea una instancia de IOrganizadorBO.
     *
     * @return Instancia de IOrganizadorBO
     */
    public IOrganizadorBO crearOrganizadorBO() {
        return new OrganizadorBO();
    }

    /**
     * Crea una instancia de IParticipanteBO.
     *
     * @return Instancia de IParticipanteBO
     */
    public IParticipanteBO crearParticipanteBO() {
        return new ParticipanteBO();
    }

    /**
     * Crea una instancia de IParticipanteEstudianteBO.
     *
     * @return Instancia de IParticipanteEstudianteBO
     */
    public IParticipanteEstudianteBO crearParticipanteEstudianteBO() {
        return new ParticipanteEstudianteBO();
    }

    /**
     * Crea una instancia de IParticipanteDocenteBO.
     *
     * @return Instancia de IParticipanteDocenteBO
     */
    public IParticipanteDocenteBO crearParticipanteDocenteBO() {
        return new ParticipanteDocenteBO();
    }

    /**
     * Crea una instancia de IParticipanteExternoBO.
     *
     * @return Instancia de IParticipanteExternoBO
     */
    public IParticipanteExternoBO crearParticipanteExternoBO() {
        return new ParticipanteExternoBO();
    }
    
}
