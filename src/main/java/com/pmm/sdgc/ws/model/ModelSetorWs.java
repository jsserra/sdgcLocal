/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.LotacaoSub;
import com.pmm.sdgc.model.SolicitacaoSub;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author setinf
 */
public class ModelSetorWs {

    private String nome;
    private String idSetor;
    private String idFuncional;
    private Boolean ativo;
    private LocalDateTime data;

    public ModelSetorWs(String nome, String id, Boolean ativo) {
        this.nome = nome;
        this.idSetor = id;
        this.ativo = ativo;
    }

    public ModelSetorWs(String nome, String id) {
        this.nome = nome;
        this.idSetor = id;
    }

    public ModelSetorWs(String nome, String id, LocalDateTime data) {
        this.nome = nome;
        this.idSetor = id;
        this.data = data;
    }

    public ModelSetorWs() {
    }

    public static List<ModelSetorWs> toModelLotacaoSubWs(List<LotacaoSub> lotacoesSub) {
        List<ModelSetorWs> mlsw = new ArrayList();
        for (LotacaoSub ls : lotacoesSub) {
            ModelSetorWs lsw = new ModelSetorWs(
                    ls.getNome(),
                    ls.getId(),
                    false
            );
            mlsw.add(lsw);
        }
        return mlsw;
    }

    public static List<ModelSetorWs> toModelLotacaoSubInfoWs(List<SolicitacaoSub> lotacoesSub) {
        List<ModelSetorWs> mlsw = new ArrayList();
        for (SolicitacaoSub ls : lotacoesSub) {
            ModelSetorWs lsw = new ModelSetorWs(
                    ls.getLotacaoSub().getNome(),
                    ls.getLotacaoSub().getId(),
                    ls.getData()
            );
            mlsw.add(lsw);
        }
        return mlsw;
    }

    public static List<ModelSetorWs> toModelSetorAtivoWs(List<SolicitacaoSub> lotacoes) {
        List<ModelSetorWs> mlsw = new ArrayList();
        for (SolicitacaoSub ls : lotacoes) {
            ModelSetorWs lsw = new ModelSetorWs(
                    ls.getLotacaoSub().getNome(),
                    ls.getLotacaoSub().getId(),
                    ls.getLotacaoSub().getAtivo()
            );
            mlsw.add(lsw);
        }
        return mlsw;
    }
    public static List<ModelSetorWs> toModelLotacaoSubAtivoWs(List<LotacaoSub> lotacoes) {
        List<ModelSetorWs> mlsw = new ArrayList();
        for (LotacaoSub ls : lotacoes) {
            ModelSetorWs lsw = new ModelSetorWs(
                    ls.getNome(),
                    ls.getId(),
                    ls.getAtivo()
            );
            mlsw.add(lsw);
        }
        return mlsw;
    }

    public static List<ModelSetorWs> toModelSetorAtivoSemAtivoWs(List<SolicitacaoSub> lotacoes) {
        List<ModelSetorWs> mlsw = new ArrayList();
        for (SolicitacaoSub ls : lotacoes) {
            ModelSetorWs lsw = new ModelSetorWs(
                    ls.getLotacaoSub().getNome(),
                    ls.getLotacaoSub().getId()
            );
            mlsw.add(lsw);
        }
        return mlsw;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(String id) {
        this.idSetor = id;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getIdFuncional() {
        return idFuncional;
    }

    public void setIdFuncional(String idFuncional) {
        this.idFuncional = idFuncional;
    }

}
