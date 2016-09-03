package org.aeopensolutions.model.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.aeopensolutions.model.enums.Estado;

@Converter(autoApply=true) 
public class EstadoConverter implements AttributeConverter<Estado, String> {

	@Override
	public String convertToDatabaseColumn(Estado attribute) {
		switch (attribute) {
		case ACTIVO:
			return "A";
		case INACTIVO:
			return "I";
		default:
			throw new IllegalArgumentException("convertToDatabaseColumn Unknown: " + attribute);
		}
	}

	@Override
	public Estado convertToEntityAttribute(String dbData) {
		switch (dbData) {
		case "A":
			return Estado.ACTIVO;
		case "I":
			return Estado.INACTIVO;
		default:
			throw new IllegalArgumentException("convertToEntityAttribute Unknown: " + dbData);
		}
	}

}
