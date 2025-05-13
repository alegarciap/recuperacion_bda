/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Clase que maneja la conexión con la base de datos. Maneja el principio
 * SingleTon, es decir, sólo existe una instancia de esta clase en todo el
 * sistema.
 *
 * @author Alejandra García Preciado - 252444
 */
public class Conexion {

    /**
     * Clase SingleTon EntityManagerFactory para crear objetos EntityManager
     * cuando se requiera, para el manejo de las operaciones CRUD con la base de
     * datos.
     */
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConexionPU"); // solo un factory en toda la aplicación

    /**
     * Crea una nueva conexión con la base de datos.
     *
     * @return Objeto EntityManager.
     */
    public static EntityManager crearConexion() {
        return emf.createEntityManager(); // se reutiliza el factory y se obtiene un nuevo EntityManager
    }

    /**
     * Cierra la conexión con la base de datos.
     */
    public static void cerrar() {
        if (emf.isOpen()) {
            emf.close();
        }
    }

}
