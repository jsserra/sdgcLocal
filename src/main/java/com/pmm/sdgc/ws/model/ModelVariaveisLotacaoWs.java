/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.VariaveisLotacao;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juliano
 */
public class ModelVariaveisLotacaoWs {

    private String lotacao;
    private String variaveisDesc;
    private LocalDate periodoFolha;   
    private Boolean ativo;

    public ModelVariaveisLotacaoWs(String lotacao, String variaveisDesc, LocalDate periodoFolha, Boolean ativo) {
        this.lotacao = lotacao;
        this.variaveisDesc = variaveisDesc;
        this.periodoFolha = periodoFolha;
        this.ativo = ativo;
    }

    public static List<ModelVariaveisLotacaoWs> toModelLotacaoWs(List<VariaveisLotacao> variaveisLot) {
        List<ModelVariaveisLotacaoWs> mlw = new ArrayList();
        for (VariaveisLotacao v : variaveisLot) {
            ModelVariaveisLotacaoWs lw = new ModelVariaveisLotacaoWs(
                    v.getLotacao().getNome(),
                    v.getVariaveisDesc().getNome(),
                    v.getPeriodoFolha(),
                    v.getAtivo()                    
            );
            mlw.add(lw);
        }
        return mlw;
    }

    public String getLotacao() {
        return lotacao;
    }

    public void setLotacao(String lotacao) {
        this.lotacao = lotacao;
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
