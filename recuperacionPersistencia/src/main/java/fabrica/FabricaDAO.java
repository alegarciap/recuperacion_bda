/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabrica;

import DAOs.*;
import interfaces.*;

/**
 * Fábrica para crear instancias de DAOs. Implementa el patrón Singleton.
 * 
 * @author Alejandra García Preciado - 252444
 */
public class FabricaDAO {
    
    private static FabricaDAO instancia;

    private FabricaDAO() {
    }

    /**
     * Obtiene la instancia única de la fábrica.
     *
     * @return Instancia de FabricaDAO
     */
    public static FabricaDAO getInstancia() {
        if (instancia == null) {
            instancia = new FabricaDAO();
        }
        return instancia;
    }

    /**
     * Crea una instancia de IActividadDAO.
     *
     * @return Instancia de IActividadDAO
     */
    public IActividadDAO crearActividadDAO() {
        return new ActividadDAO();
    }

    /**
     * Crea una instancia de IEventoDAO.
     *
     * @return Instancia de IEventoDAO
     */
    public IEventoDAO crearEventoDAO() {
        return new EventoDAO();
    }

    /**
     * Crea una instancia de IInscripcionDAO.
     *
     * @return Instancia de IInscripcionDAO
     */
    public IInscripcionDAO crearInscripcionDAO() {
        return new InscripcionDAO();
    }

    /**
     * Crea una instancia de ILugarDAO.
     *
     * @return Instancia de ILugarDAO
     */
    public ILugarDAO crearLugarDAO() {
        return new LugarDAO();
    }

    /**
     * Crea una instancia de IOrganizadorDAO.
     *
     * @return Instancia de IOrganizadorDAO
     */
    public IOrganizadorDAO crearOrganizadorDAO() {
        return new OrganizadorDAO();
    }

    /**
     * Crea una instancia de IParticipanteDAO.
     *
     * @return Instancia de IParticipanteDAO
     */
    public IParticipanteDAO crearParticipanteDAO() {
        return new ParticipanteDAO();
    }

    /**
     * Crea una instancia de IParticipanteEstudianteDAO.
     *
     * @return Instancia de IParticipanteEstudianteDAO
     */
    public IParticipanteEstudianteDAO crearParticipanteEstudianteDAO() {
        return new ParticipanteEstudianteDAO();
    }

    /**
     * Crea una instancia de IParticipanteDocenteDAO.
     *
     * @return Instancia de IParticipanteDocenteDAO
     */
    public IParticipanteDocenteDAO crearParticipanteDocenteDAO() {
        return new ParticipanteDocenteDAO();
    }

    /**
     * Crea una instancia de IParticipanteExternoDAO.
     *
     * @return Instancia de IParticipanteExternoDAO
     */
    public IParticipanteExternoDAO crearParticipanteExternoDAO() {
        return new ParticipanteExternoDAO();
    }
    
}
