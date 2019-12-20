/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acg
 */
public class UsuarioAcesso {
    private Boolean completo;
    private List<Lotacao> lotacoes = new ArrayList();
    private List<LotacaoSub> setores = new ArrayList();

    public Boolean getCompleto() {
        return completo;
    }

    public void setCompleto(Boolean completo) {
        this.completo = completo;
    }

    public List<Lotacao> getLotacoes() {
        return lotacoes;
    }

    public void setLotacoes(List<Lotacao> lotacoes) {
        this.lotacoes = lotacoes;
    }

    public List<LotacaoSub> getSetores() {
        return setores;
    }

    public void setSetores(List<LotacaoSub> setores) {
        this.setores = setores;
    }
    
    
    
    
}
