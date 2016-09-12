/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aeopensolutions.view.components;

/**
 *
 * @author MLLERENA
 */
public enum DataViewType {

    GRID("Grid"), TABLE("Table"), ROW("Row");

    private String enumValue;

    private DataViewType(String enumValue) {
        this.enumValue = enumValue;
    }

    public String getEnumValue() {
        return enumValue;
    }

    public void setEnumValue(String enumValue) {
        this.enumValue = enumValue;
    }

}
