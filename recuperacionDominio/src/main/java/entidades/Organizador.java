/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * Clase que representa a un organizador de eventos en el sistema. Contiene la
 * información del organizador y mantiene relaciones con los eventos que
 * coordina.
 *
 * @author Alejandra García Preciado - 252444
 */
@Entity
@Table(name = "organizadores")
public class Organizador implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único del organizador.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Nombre del organizador.
     */
    @Column(name = "nombre", nullable = false)
    private String nombre;

    /**
     * Correo electrónico de contacto del organizador.
     */
    @Column(name = "correo", nullable = false, unique = true)
    private String correo;

    /**
     * Tipo de organizador (organizador, ponente, responsable).
     */
    @Column(name = "tipo", nullable = false)
    private TipoOrganizador tipoOrganizador;

    /**
     * Lista de eventos organizados por este organizador.
     */
    @OneToMany(mappedBy = "organizador", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Evento> eventos;

    /**
     * Enumeración para los posibles tipos de organizadores.
     */
    public enum TipoOrganizador {
        /**
         * Organizador de eventos.
         */
        ORGANIZADOR,
        /**
         * Ponente en actividades.
         */
        PONENTE,
        /**
         * Responsable de actividades.
         */
        RESPONSABLE
    }

    /**
     * Constructor por defecto requerido por JPA.
     */
    public Organizador() {
        this.eventos = new ArrayList<>();
    }

    /**
     * Constructor con todos los atributos excepto el ID.
     *
     * @param nombre Nombre del organizador
     * @param correo Correo electrónico de contacto
     * @param tipoOrganizador Tipo de organizador
     * @param eventos Lista de eventos organizados
     */
    public Organizador(String nombre, String correo, TipoOrganizador tipoOrganizador, List<Evento> eventos) {
        this.nombre = nombre;
        this.correo = correo;
        this.tipoOrganizador = tipoOrganizador;
        this.eventos = eventos != null ? eventos : new ArrayList<>();
    }

    /**
     * Constructor con todos los atributos incluido el ID.
     *
     * @param id Identificador único del organizador
     * @param nombre Nombre del organizador
     * @param correo Correo electrónico de contacto
     * @param tipoOrganizador Tipo de organizador
     * @param eventos Lista de eventos organizados
     */
    public Organizador(Long id, String nombre, String correo, TipoOrganizador tipoOrganizador, List<Evento> eventos) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.tipoOrganizador = tipoOrganizador;
        this.eventos = eventos != null ? eventos : new ArrayList<>();
    }

    /**
     * Obtiene el identificador único del organizador.
     *
     * @return El ID del organizador
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del organizador.
     *
     * @param id El ID a establecer
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del organizador.
     *
     * @return El nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del organizador.
     *
     * @param nombre El nombre a establecer
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el correo electrónico de contacto del organizador.
     *
     * @return El correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico de contacto del organizador.
     *
     * @param correo El correo a establecer
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene el tipo de organizador.
     *
     * @return El tipo de organizador
     */
    public TipoOrganizador getTipoOrganizador() {
        return tipoOrganizador;
    }

    /**
     * Establece el tipo de organizador.
     *
     * @param tipoOrganizador El tipo a establecer
     */
    public void setTipoOrganizador(TipoOrganizador tipoOrganizador) {
        this.tipoOrganizador = tipoOrganizador;
    }

    /**
     * Obtiene la lista de eventos organizados.
     *
     * @return Lista de eventos
     */
    public List<Evento> getEventos() {
        return eventos;
    }

    /**
     * Establece la lista de eventos organizados.
     *
     * @param eventos Lista de eventos a establecer
     */
    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    @Override
    public String toString() {
        return "Organizador{" + "id=" + id + ", nombre=" + nombre + ", correo=" + correo + ", tipoOrganizador=" + tipoOrganizador + ", eventos=" + eventos + '}';
    }

}