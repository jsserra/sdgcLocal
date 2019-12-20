/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import java.util.List;

/**
 *
 * @author dreges
 */
public class ModelVariaveisInclusaoWs {
    private List<ModelIdFuncionalWs> idFuncs;
    private Integer idVariaveisDesc;
    private String idLotacaoSub;
    private Double quantidade;
    private Double valor;
    private Integer idVariavel;

    public Integer getIdVariaveisDesc() {
        return idVariaveisDesc;
    }

    public void setIdVariaveisDesc(Integer idVariaveisDesc) {
        this.idVariaveisDesc = idVariaveisDesc;
    }

    public String getIdLotacaoSub() {
        return idLotacaoSub;
    }

    public void setIdLotacaoSub(String idLotacaoSub) {
        this.idLotacaoSub = idLotacaoSub;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getIdVariavel() {
        return idVariavel;
    }

    public void setIdVariavel(Integer idVariavel) {
        this.idVariavel = idVariavel;
    }

    public List<ModelIdFuncionalWs> getIdFuncs() {
        return idFuncs;
    }

    public void setIdFuncs(List<ModelIdFuncionalWs> idFuncs) {
        this.idFuncs = idFuncs;
    }




    
    
}
