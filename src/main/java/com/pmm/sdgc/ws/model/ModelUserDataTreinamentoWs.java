/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;
import com.pmm.sdgc.converter.LocalDateConverter1;
import java.time.LocalDate;
import javax.persistence.Convert;
/**
 *
 * @author acg
 */
public class ModelUserDataTreinamentoWs {
    
    private Integer idUserLogin;
    private LocalDate data;

    public Integer getIdUserLogin() {
        return idUserLogin;
    }

    public void setIdUserLogin(Integer idUserLogin) {
        this.idUserLogin = idUserLogin;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ModelUserDataTreinamentoWs{" + "idUserLogin=" + idUserLogin + ", data=" + data + '}';
    }
}
