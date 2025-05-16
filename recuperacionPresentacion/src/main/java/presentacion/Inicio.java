/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package presentacion;

import control.CoordinadorAplicacion;
import javax.swing.SwingUtilities;

/**
 * Clase principal que inicia la aplicación ConectaITSON. Configura el Look and
 * Feel y muestra el menú principal.
 *
 * @author Alejandra García Preciado - 252444
 */
public class Inicio {

    /**
     * Método principal que inicia la aplicación.
     *
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // Ejecutar la aplicación en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            // Obtener la instancia del coordinador de la aplicación
            CoordinadorAplicacion coordinador = CoordinadorAplicacion.getInstancia();

            // Mostrar el menú principal
            coordinador.mostrarMenuPrincipal();
        });
    }
    
}
