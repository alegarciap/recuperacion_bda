/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 * DTO base para la creación de participantes. Contiene los campos comunes
 * necesarios para crear cualquier tipo de participante. Las clases específicas
 * para cada tipo extenderán de esta.
 *
 * @author Alejandra García Preciado - 252444
 */
public class ParticipanteCreacionDTO {

    /**
     * Nombre del participante.
     */
    private String nombre;

    /**
     * Apellido paterno del participante.
     */
    private String apellidoPaterno;

    /**
     * Apellido materno del participante.
     */
    private String apellidoMaterno;

    /**
     * Correo electrónico de contacto.
     */
    private String correo;

    /**
     * Constructor por defecto.
     */
    public ParticipanteCreacionDTO() {
    }

    /**
     * Constructor con todos los atributos.
     *
     * @param nombre Nombre del participante
     * @param apellidoPaterno Apellido paterno
     * @param apellidoMaterno Apellido materno
     * @param correo Correo electrónico
     */
    public ParticipanteCreacionDTO(String nombre, String apellidoPaterno, String apellidoMaterno, String correo) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
    }

    /**
     * Obtiene el nombre del participante.
     *
     * @return El nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del participante.
     *
     * @param nombre El nombre a establecer
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido paterno del participante.
     *
     * @return El apellido paterno
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Establece el apellido paterno del participante.
     *
     * @param apellidoPaterno El apellido paterno a establecer
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Obtiene el apellido materno del participante.
     *
     * @return El apellido materno
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Establece el apellido materno del participante.
     *
     * @param apellidoMaterno El apellido materno a establecer
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Obtiene el correo electrónico del participante.
     *
     * @return El correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del participante.
     *
     * @param correo El correo a establecer
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
