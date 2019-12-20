

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.BiometriaCadastrada;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author setinf
 */
public class ModelBiometriaCadastradaWs {
    
    private Boolean biometria;
    
    
    public static ModelBiometriaCadastradaWs toModelBiometriaCadastradaWs(BiometriaCadastrada biometriaCadastrada){
           ModelBiometriaCadastradaWs mbcw = new ModelBiometriaCadastradaWs();
           mbcw.setBiometria(biometriaCadastrada.getBiometria());
           return mbcw;
    }


    public Boolean getBiometria() {
        return biometria;
    }

    public void setBiometria(Boolean biometria) {
        this.biometria = biometria;
    }
    
    
    
    
    
}
