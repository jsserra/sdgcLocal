/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.UserLog;
import java.time.LocalDateTime;

/**
 *
 * @author setinf
 */
public class ModelUserLogWs {
    private LocalDateTime dataHora;
    
    public ModelUserLogWs(LocalDateTime d){
        this.dataHora = d;
    }

    public static ModelUserLogWs toModelUserLogWs(UserLog ul){
        return new ModelUserLogWs(ul.getDataHora());
    }
    
    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
    
}
