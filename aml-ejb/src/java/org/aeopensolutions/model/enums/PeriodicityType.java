package org.aeopensolutions.model.enums;

public enum PeriodicityType {
	
	DIARIO("Diario"),
	MENSUAL("Mensual"),
	BI_MENSUAL("Bi-mensual"),
	TRIMESTRAL("Trimestral"),
	SEMESTRAL("Semestral"),
	SEMANAL("Semanal"); 
	

	
	private String enumValue;  

	PeriodicityType(String enumValue) {
		this.enumValue = enumValue;
	}
 
	public String getEnumValue() {
		return enumValue;
	}

	public void setEnumValue(String enumValue) {
		this.enumValue = enumValue;
	}

	

}
