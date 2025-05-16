/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package utils;

/**
 * Clase para probar la funcionalidad de encriptación y desencriptación. 
 *
 * @author Alejandra García Preciado - 252444
 */
public class EncryptionTest {

    public static void main(String[] args) {
        // Prueba con un correo electrónico
        String correoOriginal = "estudiante@itson.edu.mx";
        System.out.println("Correo original: " + correoOriginal);

        String correoEncriptado = EncryptionUtil.encriptar(correoOriginal);
        System.out.println("Correo encriptado: " + correoEncriptado);

        String correoDesencriptado = EncryptionUtil.desencriptar(correoEncriptado);
        System.out.println("Correo desencriptado: " + correoDesencriptado);
        System.out.println("¿Correcto? " + correoOriginal.equals(correoDesencriptado));

        System.out.println("-----------------------------");

        // Prueba con un número de control
        Integer numeroControlOriginal = 252444;
        System.out.println("Número de control original: " + numeroControlOriginal);

        String numeroEncriptado = EncryptionUtil.encriptar(numeroControlOriginal.toString());
        System.out.println("Número encriptado: " + numeroEncriptado);

        String numeroDesencriptado = EncryptionUtil.desencriptar(numeroEncriptado);
        System.out.println("Número desencriptado: " + numeroDesencriptado);
        System.out.println("¿Correcto? " + numeroControlOriginal.toString().equals(numeroDesencriptado));

        System.out.println("-----------------------------");

        // Generar una nueva clave (opcional)
        System.out.println("Nueva clave generada: " + EncryptionUtil.generarClave());
        System.out.println("Puedes usar esta clave para reemplazar la actual en EncryptionUtil si lo deseas.");

        // Simulación de encriptación/desencriptación múltiple para probar estabilidad
        System.out.println("-----------------------------");
        System.out.println("Prueba de estabilidad (encriptación múltiple):");

        String texto = "Este es un texto de prueba";
        System.out.println("Texto original: " + texto);

        String encriptado1 = EncryptionUtil.encriptar(texto);
        String encriptado2 = EncryptionUtil.encriptar(texto);
        System.out.println("¿Encriptaciones iguales? " + encriptado1.equals(encriptado2));

        String desencriptado1 = EncryptionUtil.desencriptar(encriptado1);
        String desencriptado2 = EncryptionUtil.desencriptar(encriptado2);
        System.out.println("Desencriptado 1: " + desencriptado1);
        System.out.println("Desencriptado 2: " + desencriptado2);
        System.out.println("¿Ambos desencriptados son iguales al original? "
                + (texto.equals(desencriptado1) && texto.equals(desencriptado2)));

        // Prueba de manejo de valores nulos y vacíos
        System.out.println("-----------------------------");
        System.out.println("Prueba con valores nulos y vacíos:");

        String nulo = null;
        String vacio = "";

        String encriptadoNulo = EncryptionUtil.encriptar(nulo);
        String encriptadoVacio = EncryptionUtil.encriptar(vacio);

        System.out.println("Encriptado de null: " + encriptadoNulo);
        System.out.println("Encriptado de cadena vacía: " + encriptadoVacio);

        String desencriptadoNulo = EncryptionUtil.desencriptar(encriptadoNulo);
        String desencriptadoVacio = EncryptionUtil.desencriptar(encriptadoVacio);

        System.out.println("Desencriptado de null: " + desencriptadoNulo);
        System.out.println("Desencriptado de cadena vacía: " + desencriptadoVacio);

        // Prueba con caracteres especiales
        System.out.println("-----------------------------");
        System.out.println("Prueba con caracteres especiales:");

        String textoConEspeciales = "Áéíóú Ññ !@#$%^&*()_+{}[]|\\:;\"'<>,.?/";
        System.out.println("Texto con especiales: " + textoConEspeciales);

        String encriptadoEspecial = EncryptionUtil.encriptar(textoConEspeciales);
        System.out.println("Encriptado: " + encriptadoEspecial);

        String desencriptadoEspecial = EncryptionUtil.desencriptar(encriptadoEspecial);
        System.out.println("Desencriptado: " + desencriptadoEspecial);
        System.out.println("¿Correcto? " + textoConEspeciales.equals(desencriptadoEspecial));
    }
    
}
