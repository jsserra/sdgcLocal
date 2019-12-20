/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

/**
 *
 * @author jrdutra
 */
public class ModelPodeAlterarRegime {
    private Boolean podeAlterarRegime;

    public ModelPodeAlterarRegime(Boolean podeAlterarRegime) {
        this.podeAlterarRegime = podeAlterarRegime;
    }

    public ModelPodeAlterarRegime() {
    }
    
    
    
    public Boolean getPodeAlterarRegime() {
        return podeAlterarRegime;
    }

    public void setPodeAlterarRegime(Boolean podeAlterarRegime) {
        this.podeAlterarRegime = podeAlterarRegime;
    }
    
    
    
}
