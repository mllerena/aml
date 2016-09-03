package org.aeopensolutions.model.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.aeopensolutions.model.enums.PeriodicityType;
import org.aeopensolutions.model.enums.YesNo;

@Converter(autoApply=true)  
public class PeriodicityTypeConverter implements AttributeConverter<PeriodicityType, String> {

	@Override
	public String convertToDatabaseColumn(PeriodicityType attribute) {
		System.out.println("convertToDatabaseColumn: " + attribute);
		/*
		DA (Daily)
		M1 (Monthly)
		M2 (Bi-Monthly)
		M3 (Quarterly)
		M6 (Half-yearly)
		WE (Weekly)
		
		Bi-mensual.
	o Diario.
	o Semestral.
	o Mensual.
	o Trimestral.
	SEMANAL
		*/ 
		switch (attribute) {
		case DIARIO:
			return "DA";
		case MENSUAL:
			return "M1";
		case BI_MENSUAL:
			return "M2";
		case TRIMESTRAL:
			return "M3";
		case SEMESTRAL:
			return "M6";
		case SEMANAL:
			return "WE";
		default:
			throw new IllegalArgumentException("convertToDatabaseColumn Unknown: " + attribute);
		}
	}

	@Override
	public PeriodicityType convertToEntityAttribute(String dbData) {
		System.out.println("convertToEntityAttribute: " + dbData);
		if(dbData != null){
			switch (dbData) {
			case "DA":
				return PeriodicityType.DIARIO;
			case "M1":
				return PeriodicityType.MENSUAL;
			case "M2":
				return PeriodicityType.BI_MENSUAL;
			case "M3":
				return PeriodicityType.TRIMESTRAL;
			case "M6":
				return PeriodicityType.SEMESTRAL;
			case "WE":
				return PeriodicityType.SEMANAL;
			default:
				throw new IllegalArgumentException("convertToEntityAttribute Unknown: " + dbData);
			}	
		}
		
		return null; 
		
	}

}
