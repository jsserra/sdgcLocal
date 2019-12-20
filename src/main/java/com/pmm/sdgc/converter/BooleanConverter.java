/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;




/**
 *
 * @author acg
 */
@Converter(autoApply = false)
public class BooleanConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean valor) {
        if (valor==null) return null;
        if (valor) {
            return "S";
        } else {
            return "N";
        }
    }

    @Override
    public Boolean convertToEntityAttribute(String valor) {
        if (valor==null) return null;
        if (valor.isEmpty()) return null;
        if (valor.equals("S")) return true;
        return false;
    }

}
