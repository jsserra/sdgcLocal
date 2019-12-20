/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.VariaveisLotacaoSub;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juliano
 */
public class ModelVariaveisLotacaoSubWs {

    private String lotacaoSub;
    private String variaveisDesc;
    private LocalDate periodoFolha;   
    private Boolean ativo;

    public ModelVariaveisLotacaoSubWs(String lotacaoSub, String variaveisDesc, LocalDate periodoFolha, Boolean ativo) {
        this.lotacaoSub = lotacaoSub;
        this.variaveisDesc = variaveisDesc;
        this.periodoFolha = periodoFolha;
        this.ativo = ativo;
    }

    public static List<ModelVariaveisLotacaoSubWs> toModelLotacaoSubWs(List<VariaveisLotacaoSub> variaveisLotSub) {
        List<ModelVariaveisLotacaoSubWs> mlw = new ArrayList();
        for (VariaveisLotacaoSub v : variaveisLotSub) {
            ModelVariaveisLotacaoSubWs lw = new ModelVariaveisLotacaoSubWs(
                    v.getLotacaoSub().getNome(),
                    v.getVariaveisDesc().getNome(),
                    v.getPeriodoFolha(),
                    v.getAtivo()                    
            );
            mlw.add(lw);
        }
        return mlw;
    }

    public String getLotacaoSub() {
        return lotacaoSub;
    }

    public void setLotacaoSub(String lotacaoSub) {
        this.lotacaoSub = lotacaoSub;
    }

    public String getVariaveisDesc() {
        return variaveisDesc;
    }

    public void setVariaveisDesc(String variaveisDesc) {
        this.variaveisDesc = variaveisDesc;
    }

    public LocalDate getPeriodoFolha() {
        return periodoFolha;
    }

    public void setPeriodoFolha(LocalDate periodoFolha) {
        this.periodoFolha = periodoFolha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

  

    

}
