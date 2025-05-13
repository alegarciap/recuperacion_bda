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
 * Clase que representa un lugar o sede donde se realizan las actividades. Puede
 * ser un aula física, un laboratorio o una plataforma virtual. Contiene
 * información sobre la capacidad y el tipo de lugar.
 *
 * @author Alejandra García Preciado - 252444
 */
@Entity
@Table(name = "lugares")
public class Lugar implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único del lugar.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Nombre o identificador del lugar.
     */
    @Column(name = "nombre", nullable = false)
    private String nombre;

    /**
     * Tipo de lugar (aula, laboratorio, plataforma virtual).
     */
    @Column(name = "tipo", nullable = false)
    private TipoLugar tipoLugar;

    /**
     * Capacidad máxima de personas que pueden estar en el lugar.
     */
    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;

    /**
     * Lista de actividades que se realizan en este lugar.
     */
    @OneToMany(mappedBy = "lugar")
    private List<Actividad> actividades;

    /**
     * Enumeración para los posibles tipos de lugares.
     */
    public enum TipoLugar {
        /**
         * Un aula dentro de ITSON.
         */
        AULA,
        /**
         * Un laboratorio dentro de ITSON.
         */
        LABORATORIO,
        /**
         * Modalidad virtual.
         */
        PLATAFORMA_VIRTUAL
    }

    /**
     * Constructor por defecto requerido por JPA.
     */
    public Lugar() {
        this.actividades = new ArrayList<>();
    }

    /**
     * Constructor con todos los atributos excepto el ID.
     *
     * @param nombre Nombre o identificador del lugar
     * @param tipoLugar Tipo de lugar
     * @param capacidad Capacidad máxima
     * @param actividades Lista de actividades
     */
    public Lugar(String nombre, TipoLugar tipoLugar, Integer capacidad, List<Actividad> actividades) {
        this.nombre = nombre;
        this.tipoLugar = tipoLugar;
        this.capacidad = capacidad;
        this.actividades = actividades != null ? actividades : new ArrayList<>();
    }

    /**
     * Constructor con todos los atributos incluido el ID.
     *
     * @param id Identificador único del lugar
     * @param nombre Nombre o identificador del lugar
     * @param tipoLugar Tipo de lugar
     * @param capacidad Capacidad máxima
     * @param actividades Lista de actividades
     */
    public Lugar(Long id, String nombre, TipoLugar tipoLugar, Integer capacidad, List<Actividad> actividades) {
        this.id = id;
        this.nombre = nombre;
        this.tipoLugar = tipoLugar;
        this.capacidad = capacidad;
        this.actividades = actividades != null ? actividades : new ArrayList<>();
    }

    /**
     * Obtiene el identificador único del lugar.
     *
     * @return El ID del lugar
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del lugar.
     *
     * @param id El ID a establecer
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre o identificador del lugar.
     *
     * @return El nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre o identificador del lugar.
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
    public TipoLugar getTipoLugar() {
        return tipoLugar;
    }

    /**
     * Establece el tipo de lugar.
     *
     * @param tipoLugar El tipo a establecer
     */
    public void setTipoLugar(TipoLugar tipoLugar) {
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
     * Obtiene la lista de actividades programadas en este lugar.
     *
     * @return Lista de actividades
     */
    public List<Actividad> getActividades() {
        return actividades;
    }

    /**
     * Establece la lista de actividades programadas en este lugar.
     *
     * @param actividades Lista de actividades a establecer
     */
    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    @Override
    public String toString() {
        return "Lugar{" + "id=" + id + ", nombre=" + nombre + ", tipoLugar=" + tipoLugar + ", capacidad=" + capacidad + ", actividades=" + actividades + '}';
    }

}