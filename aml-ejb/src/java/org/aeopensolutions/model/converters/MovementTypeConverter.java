package org.aeopensolutions.model.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.aeopensolutions.model.enums.MovementType;

@Converter(autoApply=true)  
public class MovementTypeConverter implements AttributeConverter<MovementType, String> { 

	@Override
	public String convertToDatabaseColumn(MovementType attribute) {
		System.out.println("convertToDatabaseColumn: " + attribute);
		switch (attribute) {
		case ENTRADA_INVENTARIO:
			return "I+";
		case SALIDA_INVENTARIO:
			return "I-";
		case MOVIMIENTO_PARA:
			return "M+";
		case MOVIMIENTO_DE:
			return "M-";
		case PRODUCCION_POSITIVA:
			return "P+";
		case PRODUCCION_NEGATIVA:
			return "P-"; 
		default:
			throw new IllegalArgumentException("convertToDatabaseColumn Unknown: " + attribute);
		}
	}

	@Override
	public MovementType convertToEntityAttribute(String dbData) {
		System.out.println("convertToEntityAttribute: " + dbData);
		if(dbData != null){
			switch (dbData) {
			case "I+":
				return MovementType.ENTRADA_INVENTARIO;
			case "I-": 
				return MovementType.SALIDA_INVENTARIO;
			case "M+":
				return MovementType.MOVIMIENTO_PARA;
			case "M-": 
				return MovementType.MOVIMIENTO_DE;
			case "P+":
				return MovementType.PRODUCCION_POSITIVA;
			case "P-": 
				return MovementType.PRODUCCION_NEGATIVA; 
			default:
				throw new IllegalArgumentException("convertToEntityAttribute Unknown: " + dbData);
			}	
		}else{
			return null; 	
		}
		
	}

}
