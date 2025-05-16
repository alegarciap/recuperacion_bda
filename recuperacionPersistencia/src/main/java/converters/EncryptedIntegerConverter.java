/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import utils.EncryptionUtil;

/**
 * Conversor JPA que automatiza la encriptación/desencriptación de campos
 * Integer.
 *
 * @author Alejandra García Preciado - 252444
 */
@Converter
public class EncryptedIntegerConverter implements AttributeConverter<Integer, String> {

    @Override
    public String convertToDatabaseColumn(Integer attribute) {
        if (attribute == null) {
            return null;
        }
        return EncryptionUtil.encriptar(attribute.toString());
    }

    @Override
    public Integer convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        String desencriptado = EncryptionUtil.desencriptar(dbData);
        if (desencriptado == null) {
            return null;
        }
        try {
            return Integer.parseInt(desencriptado);
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir a Integer: " + e.getMessage());
            return null;
        }
    }
    
}
