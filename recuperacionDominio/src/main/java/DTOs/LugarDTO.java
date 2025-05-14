/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 * DTO que representa un lugar donde se realizan actividades. Utilizado para
 * transferir información de lugares entre capas sin exponer directamente las
 * entidades.
 *
 * @author Alejandra García Preciado - 252444
 */
public class LugarDTO {

    /**
     * Nombre o identificador del lugar.
     */
    private String nombre;

    /**
     * Tipo de lugar (AULA, LABORATORIO, PLATAFORMA_VIRTUAL).
     */
    private String tipoLugar;

    /**
     * Capacidad máxima de personas.
     */
    private Integer capacidad;

    /**
     * Número de actividades programadas en este lugar.
     */
    private Integer totalActividades;

    /**
     * Constructor por defecto.
     */
    public LugarDTO() {
    }

    /**
     * Constructor con los atributos principales.
     *
     * @param nombre Nombre del lugar
     * @param tipoLugar Tipo de lugar
     * @param capacidad Capacidad máxima
     */
    public LugarDTO(String nombre, String tipoLugar, Integer capacidad) {
        this.nombre = nombre;
        this.tipoLugar = tipoLugar;
        this.capacidad = capacidad;
        this.totalActividades = 0;
    }

    /**
     * Constructor con todos los atributos.
     *
     * @param nombre Nombre del lugar
     * @param tipoLugar Tipo de lugar
     * @param capacidad Capacidad máxima
     * @param totalActividades Número de actividades programadas
     */
    public LugarDTO(String nombre, String tipoLugar, Integer capacidad, Integer totalActividades) {
        this.nombre = nombre;
        this.tipoLugar = tipoLugar;
        this.capacidad = capacidad;
        this.totalActividades = totalActividades;
    }
    
    /**
     * Obtiene el nombre del lugar.
     *
     * @return El nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del lugar.
     *
     * @param nombre El nombre a establecer
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el tipo de lugar.
     *
     * @return El tipo
     */
    public String getTipoLugar() {
        return tipoLugar;
    }

    /**
     * Establece el tipo de lugar.
     *
     * @param tipoLugar El tipo a establecer
     */
    public void setTipoLugar(String tipoLugar) {
        this.tipoLugar = tipoLugar;
    }

    /**
     * Obtiene la capacidad máxima del lugar.
     *
     * @return La capacidad
     */
    public Integer getCapacidad() {
        return capacidad;
    }

    /**
     * Establece la capacidad máxima del lugar.
     *
     * @param capacidad La capacidad a establecer
     */
    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * Obtiene el número de actividades programadas en este lugar.
     *
     * @return El número de actividades
     */
    public Integer getTotalActividades() {
        return totalActividades;
    }

    /**
     * Establece el número de actividades programadas en este lugar.
     *
     * @param totalActividades El número de actividades a establecer
     */
    public void setTotalActividades(Integer totalActividades) {
        this.totalActividades = totalActividades;
    }

}
