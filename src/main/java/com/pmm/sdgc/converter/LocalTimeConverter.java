/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.converter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author acg
 */
@Converter(autoApply = true)
public class LocalTimeConverter implements AttributeConverter<LocalTime, String> {

    @Override
    public String convertToDatabaseColumn(LocalTime localTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        try {
            String strTime = localTime.format(formatter);
            return strTime;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public LocalTime convertToEntityAttribute(String strTime) {
        if (strTime == null) {
            return null;
        }
        if (strTime.isEmpty()) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = null;
        try {
            localTime = LocalTime.parse(strTime.trim(), formatter);
            return localTime;
        } catch (Exception e) {
            return null;
        }

    }

}
