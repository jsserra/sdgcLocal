/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author acg
 */
@Converter(autoApply = true)
public class LocalDateConverter2 implements AttributeConverter<LocalDate, String> {

    @Override
    public String convertToDatabaseColumn(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            String strData = localDate.format(formatter);
            return strData;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public LocalDate convertToEntityAttribute(String strData) {
        if (strData == null) {
            return null;
        }
        if (strData.isEmpty()) {
            return null;
        }
        strData = "01/"+strData;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(strData.trim(), formatter);
            return localDate;
        } catch (Exception e) {
            return null;
        }

    }

}

