package org.aeopensolutions.model.enums;

public enum YesNo {

	SI("Si"), NO("No");

	private String enumValue;

	private YesNo(String enumValue) {
		this.enumValue = enumValue;
	}

	public String getEnumValue() {
		return enumValue;
	}

	public void setEnumValue(String enumValue) {
		this.enumValue = enumValue;
	}

	
	
}
