package org.aeopensolutions.model.enums;

public enum MovementType {

	ENTRADA_INVENTARIO("Entrada de Inventario (I+)"), 
	SALIDA_INVENTARIO("Salida de Inventario (I-)"),
	MOVIMIENTO_PARA("Movimiento para (M+)"), 
	MOVIMIENTO_DE("Movimiento de (M-)"),
	PRODUCCION_POSITIVA("Producción (P+)"), 
	PRODUCCION_NEGATIVA("Producción (P-)");

	private String enumValue;

	MovementType(String enumValue) {
		this.enumValue = enumValue;
	}

	public String getEnumValue() {
		return enumValue;
	}

	public void setEnumValue(String enumValue) {
		this.enumValue = enumValue;
	}

}
