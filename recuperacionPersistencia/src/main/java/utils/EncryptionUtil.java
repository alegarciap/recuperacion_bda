/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Clase utilitaria para la encriptación y desencriptación de datos sensibles.
 * Utiliza el algoritmo AES (Advanced Encryption Standard) con una clave de 128
 * bits.
 *
 * @author Alejandra García Preciado - 252444
 */
public class EncryptionUtil {

    // Clave de encriptación codificada en Base64
    private static final String ENCODED_KEY = "MDEyMzQ1Njc4OTAxMjM0NQ==";

    /**
     * Encripta un texto utilizando el algoritmo AES.
     *
     * @param texto Texto a encriptar
     * @return Texto encriptado en formato Base64, o el texto original si ocurre
     * un error
     */
    public static String encriptar(String texto) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        }

        try {
            SecretKey key = obtenerClave();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] textoCifrado = cipher.doFinal(texto.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(textoCifrado);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | IllegalBlockSizeException | BadPaddingException e) {
            System.err.println("Error al encriptar: " + e.getMessage());
            return texto; // Devolver el texto original en caso de error
        }
    }

    /**
     * Desencripta un texto previamente encriptado con el algoritmo AES.
     *
     * @param textoEncriptado Texto encriptado en formato Base64
     * @return Texto original desencriptado, o el texto encriptado si ocurre un
     * error
     */
    public static String desencriptar(String textoEncriptado) {
        if (textoEncriptado == null || textoEncriptado.isEmpty()) {
            return textoEncriptado;
        }

        try {
            SecretKey key = obtenerClave();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] textoDesencriptado = cipher.doFinal(Base64.getDecoder().decode(textoEncriptado));
            return new String(textoDesencriptado, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | IllegalBlockSizeException | BadPaddingException | IllegalArgumentException e) {
            System.err.println("Error al desencriptar: " + e.getMessage());
            return textoEncriptado; // Devolver el texto encriptado en caso de error
        }
    }

    /**
     * Genera una nueva clave de encriptación AES de 128 bits. Este método se
     * usa solo para generar una nueva clave si es necesario.
     *
     * @return Clave codificada en Base64
     */
    public static String generarClave() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            SecretKey key = keyGen.generateKey();
            return Base64.getEncoder().encodeToString(key.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error al generar clave: " + e.getMessage());
            return null;
        }
    }

    /**
     * Obtiene la clave de encriptación a partir de la clave codificada.
     *
     * @return Objeto SecretKey para operaciones de encriptación/desencriptación
     */
    private static SecretKey obtenerClave() {
        byte[] decodedKey = Base64.getDecoder().decode(ENCODED_KEY);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

}
