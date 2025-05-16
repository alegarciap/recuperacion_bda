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
 * String.
 *
 * @author Alejandra García Preciado - 252444
 */
@Converter
public class EncryptedStringConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return EncryptionUtil.encriptar(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return EncryptionUtil.desencriptar(dbData);
    }

}
