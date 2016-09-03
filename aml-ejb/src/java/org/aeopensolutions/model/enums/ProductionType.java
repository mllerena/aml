package org.aeopensolutions.model.enums;

public enum ProductionType {
	
	PRODUCT_PRODUCTION_POSITIVE ("Producción de producto (P+)"),
	PRODUCT_USE_NEGATIVE ("Uso del producto (P-)"); 
	
	private String enumValue;  

	ProductionType(String enumValue) {
		this.enumValue = enumValue;
	}
 
	public String getEnumValue() {
		return enumValue;
	}

	public void setEnumValue(String enumValue) {
		this.enumValue = enumValue;
	}

	

}
