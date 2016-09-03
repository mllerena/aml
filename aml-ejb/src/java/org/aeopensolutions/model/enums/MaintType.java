package org.aeopensolutions.model.enums;

public enum MaintType {
	
	CORRECTIVO("Correctivo"),
	CORRECTIVO_URGENTE("Correctivo (Urgente)"),
	PREVENTIVO("Preventivo");  
	
	private String enumValue;  

	MaintType(String enumValue) {
		this.enumValue = enumValue;
	}
 
	public String getEnumValue() {
		return enumValue;
	}

	public void setEnumValue(String enumValue) {
		this.enumValue = enumValue;
	}

	

}
