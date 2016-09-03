package org.aeopensolutions.model.enums;

public enum ShiftType {
	
	TURNO_MANIANA("Turno de mañana"),
	TURNO_TARDE("Turno de tarde"); 
	
	private String enumValue;  

	ShiftType(String enumValue) {
		this.enumValue = enumValue;
	}
 
	public String getEnumValue() {
		return enumValue;
	}

	public void setEnumValue(String enumValue) {
		this.enumValue = enumValue;
	}

	

}
