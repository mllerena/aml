package org.aeopensolutions.model.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.aeopensolutions.model.enums.CostUOM;
import org.aeopensolutions.model.enums.YesNo;

@Converter(autoApply=true)  
public class CostUOMConverter implements AttributeConverter<CostUOM, String> { 

	@Override
	public String convertToDatabaseColumn(CostUOM attribute) {
		System.out.println("convertToDatabaseColumn: " + attribute);
		switch (attribute) {
		case PORCENTAJE:
			return "P";
		case POR_KILOGRAMO:
			return "K";
		case POR_HORA:
			return "H";
		case POR_UNIDADES_PRODUCIDAS:
			return "U"; 
		default:
			throw new IllegalArgumentException("convertToDatabaseColumn Unknown: " + attribute);
		}
	}

	@Override
	public CostUOM convertToEntityAttribute(String dbData) {
		System.out.println("convertToEntityAttribute: " + dbData);
		if(dbData != null){
			switch (dbData) {
			case "P":
				return CostUOM.PORCENTAJE;
			case "K":
				return CostUOM.POR_KILOGRAMO;
			case "H":
				return CostUOM.POR_HORA;
			case "U":
				return CostUOM.POR_UNIDADES_PRODUCIDAS; 
			default:
				throw new IllegalArgumentException("convertToEntityAttribute Unknown: " + dbData);
			}	
		}else{
			return null; 	
		}
		
	}

}
