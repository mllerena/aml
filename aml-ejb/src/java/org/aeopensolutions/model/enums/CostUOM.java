package org.aeopensolutions.model.enums;

public enum CostUOM {
	
	PORCENTAJE("Porcentaje"),
	POR_KILOGRAMO("Por Kilogramo"),
	POR_HORA("Por Hora"),
	POR_UNIDADES_PRODUCIDAS("Por Unidades Producidas"); 
	
	private String enumValue;  

	CostUOM(String enumValue) {
		this.enumValue = enumValue;
	}
 
	public String getEnumValue() {
		return enumValue;
	}

	public void setEnumValue(String enumValue) {
		this.enumValue = enumValue;
	}

	

}
