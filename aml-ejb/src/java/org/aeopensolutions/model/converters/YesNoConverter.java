package org.aeopensolutions.model.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.aeopensolutions.model.enums.YesNo;

@Converter(autoApply=true)  
public class YesNoConverter implements AttributeConverter<YesNo, String> {

	@Override
	public String convertToDatabaseColumn(YesNo attribute) {
		System.out.println("convertToDatabaseColumn: " + attribute);
		switch (attribute) {
		case SI:
			return "Y";
		case NO:
			return "N"; 
		default:
			throw new IllegalArgumentException("convertToDatabaseColumn Unknown: " + attribute);
		}
	}

	@Override
	public YesNo convertToEntityAttribute(String dbData) {
		System.out.println("convertToEntityAttribute: " + dbData);
		if(dbData != null){
			switch (dbData) {
			case "Y":
				return YesNo.SI;
			case "N":  
				return YesNo.NO; 
			default:
				throw new IllegalArgumentException("convertToEntityAttribute Unknown: " + dbData);
			}	
		}else{
			return YesNo.NO;	
		}
		
	}

}
