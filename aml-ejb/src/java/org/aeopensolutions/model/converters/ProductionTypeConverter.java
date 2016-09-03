package org.aeopensolutions.model.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.aeopensolutions.model.enums.CostUOM;
import org.aeopensolutions.model.enums.ProductionType;
import org.aeopensolutions.model.enums.YesNo;

@Converter(autoApply=true)  
public class ProductionTypeConverter implements AttributeConverter<ProductionType, String> { 

	@Override
	public String convertToDatabaseColumn(ProductionType attribute) {
		System.out.println("convertToDatabaseColumn: " + attribute);
		switch (attribute) {
		case PRODUCT_PRODUCTION_POSITIVE:
			return "+";
		case PRODUCT_USE_NEGATIVE:
			return "-"; 
		default:
			throw new IllegalArgumentException("convertToDatabaseColumn Unknown: " + attribute);
		}
	}

	@Override
	public ProductionType convertToEntityAttribute(String dbData) {
		System.out.println("convertToEntityAttribute: " + dbData);
		if(dbData != null){
			switch (dbData) {
			case "+":
				return ProductionType.PRODUCT_PRODUCTION_POSITIVE;
			case "-": 
				return ProductionType.PRODUCT_USE_NEGATIVE;
			default:
				throw new IllegalArgumentException("convertToEntityAttribute Unknown: " + dbData);
			}	
		}else{
			return null; 	
		}
		
	}

}
