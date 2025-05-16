/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 * DTO que representa a un organizador de eventos. Utilizado para transferir
 * información de organizadores entre capas sin exponer directamente las
 * entidades.
 *
 * @author Alejandra García Preciado - 252444
 */
public class OrganizadorDTO {
    
    /**
     * Identificador único del organizador.
     */
    private Long idOrganizador;

    /**
     * Nombre del organizador.
     */
    private String nombre;

    /**
     * Correo electrónico de contacto.
     */
    private String correo;

    /**
     * Tipo de organizador (ORGANIZADOR, PONENTE, RESPONSABLE).
     */
    private String tipoOrganizador;

    /**
     * Número de eventos organizados.
     */
    private Integer totalEventos;

    /**
     * Constructor por defecto.
     */
    public OrganizadorDTO() {
    }

    /**
     * Constructor con todos los atributos.
     *
     * @param idOrganizador Identificador único del organizador.
     * @param nombre Nombre del organizador
     * @param correo Correo electrónico
     * @param tipoOrganizador Tipo de organizador
     * @param totalEventos Número de eventos organizados
     */
    public OrganizadorDTO(Long idOrganizador, String nombre, String correo, String tipoOrganizador, Integer totalEventos) {
        this.idOrganizador = idOrganizador;
        this.nombre = nombre;
        this.correo = correo;
        this.tipoOrganizador = tipoOrganizador;
        this.totalEventos = totalEventos;
    }
    
    /**
     * Constructor con los atributos principales.
     *
     * @param nombre Nombre del organizador
     * @param correo Correo electrónico
     * @param tipoOrganizador Tipo de organizador
     */
    public OrganizadorDTO(String nombre, String correo, String tipoOrganizador) {
        this.nombre = nombre;
        this.correo = correo;
        this.tipoOrganizador = tipoOrganizador;
        this.totalEventos = 0;
    }

    /**
     * Constructor con todos los atributos menos el identificador.
     *
     * @param nombre Nombre del organizador
     * @param correo Correo electrónico
     * @param tipoOrganizador Tipo de organizador
     * @param totalEventos Número de eventos organizados
     */
    public OrganizadorDTO(String nombre, String correo, String tipoOrganizador, Integer totalEventos) {
        this.nombre = nombre;
        this.correo = correo;
        this.tipoOrganizador = tipoOrganizador;
        this.totalEventos = totalEventos;
    }

    public Long getIdOrganizador() {
        return idOrganizador;
    }

    public void setIdOrganizador(Long idOrganizador) {
        this.idOrganizador = idOrganizador;
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
     * Obtiene el correo electrónico del organizador.
     *
     * @return El correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del organizador.
     *
     * @param correo El correo a establecer
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene el tipo de organizador.
     *
     * @return El tipo
     */
    public String getTipoOrganizador() {
        return tipoOrganizador;
    }

    /**
     * Establece el tipo de organizador.
     *
     * @param tipoOrganizador El tipo a establecer
     */
    public void setTipoOrganizador(String tipoOrganizador) {
        this.tipoOrganizador = tipoOrganizador;
    }

    /**
     * Obtiene el número de eventos organizados.
     *
     * @return El número de eventos
     */
    public Integer getTotalEventos() {
        return totalEventos;
    }

    /**
     * Establece el número de eventos organizados.
     *
     * @param totalEventos El número de eventos a establecer
     */
    public void setTotalEventos(Integer totalEventos) {
        this.totalEventos = totalEventos;
    }

}
