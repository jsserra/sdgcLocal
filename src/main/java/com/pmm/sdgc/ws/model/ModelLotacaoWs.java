/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.Lotacao;
import com.pmm.sdgc.model.Solicitacao;
import com.pmm.sdgc.model.SolicitacaoSub;
import java.time.LocalDateTime;
//import com.pmm.sdgc.model.Solicitacao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author setinf
 */
public class ModelLotacaoWs {

    private String nome;
    private String idLotacao;
    private Boolean ativo;
    private LocalDateTime data;
    private String idFuncional;
    private String userLogin;

    public ModelLotacaoWs(String nome, String id, Boolean ativo) {
        this.nome = nome;
        this.idLotacao = id;
        this.ativo = ativo;
    }

    public ModelLotacaoWs(String nome, String id, LocalDateTime data) {
        this.nome = nome;
        this.idLotacao = id;
        this.data = data;
    }

    public ModelLotacaoWs() {
    }

    public String getIdFuncional() {
        return idFuncional;
    }

    public void setIdFuncional(String idFuncional) {
        this.idFuncional = idFuncional;
    }

    public static List<ModelLotacaoWs> toModelLotacaoWs(List<Lotacao> lotacoes) {
        List<ModelLotacaoWs> mlw = new ArrayList();
        for (Lotacao l : lotacoes) {
            ModelLotacaoWs lw = new ModelLotacaoWs(
                    l.getNome(),
                    l.getId(),
                    false
            );
            mlw.add(lw);
        }
        return mlw;
    }

    public static List<ModelLotacaoWs> toModelLotacaoInfoWs(List<Solicitacao> lotacoes) {
        List<ModelLotacaoWs> mlw = new ArrayList();
        for (Solicitacao l : lotacoes) {
            ModelLotacaoWs lw = new ModelLotacaoWs(
                    l.getLotacao().getNome(),
                    l.getLotacao().getId(),
                    l.getData()
            );
            mlw.add(lw);
        }
        return mlw;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdLotacao() {
        return idLotacao;
    }

    public void setIdLotacao(String id) {
        this.idLotacao = id;
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

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

}
