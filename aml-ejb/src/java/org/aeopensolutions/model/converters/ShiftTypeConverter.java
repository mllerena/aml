package org.aeopensolutions.model.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.aeopensolutions.model.enums.PeriodicityType;
import org.aeopensolutions.model.enums.ShiftType;
import org.aeopensolutions.model.enums.YesNo;

@Converter(autoApply=true)  
public class ShiftTypeConverter implements AttributeConverter<ShiftType, String> {

	@Override
	public String convertToDatabaseColumn(ShiftType attribute) {
		System.out.println("convertToDatabaseColumn: " + attribute);
		switch (attribute) {
		case TURNO_MANIANA:
			return "M";
		case TURNO_TARDE:
			return "T";
		default:
			throw new IllegalArgumentException("convertToDatabaseColumn Unknown: " + attribute);
		}
	}

	@Override
	public ShiftType convertToEntityAttribute(String dbData) {
		System.out.println("convertToEntityAttribute: " + dbData);
		if(dbData != null){
			switch (dbData) {
			case "M":
				return ShiftType.TURNO_MANIANA;
			case "T": 
				return ShiftType.TURNO_TARDE;
			default:
				throw new IllegalArgumentException("convertToEntityAttribute Unknown: " + dbData);
			}	
		}
		
		return null; 
		
	}

}
