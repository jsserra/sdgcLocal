/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.PlanejamentoConsolidado;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dreges
 */
public class ModelPlanejamentoConsolidadoWs {

    private String nomeSetor;
    private LocalDateTime data;
    private String userLogin;
    private String idLotacaoSub;
    private String cpf;

    public ModelPlanejamentoConsolidadoWs(String nome, LocalDateTime data) {
        this.nomeSetor = nome;
        this.data = data;
    }

    public ModelPlanejamentoConsolidadoWs() {
    }

    public static List<ModelPlanejamentoConsolidadoWs> toModelPlanejamentoConsolidadoWs(List<PlanejamentoConsolidado> planejamentoConsolidado) {
        List<ModelPlanejamentoConsolidadoWs> mew = new ArrayList();
        for (PlanejamentoConsolidado r : planejamentoConsolidado) {
            ModelPlanejamentoConsolidadoWs rw = new ModelPlanejamentoConsolidadoWs(
                    r.getLotacaoSub().getNome(),
                    r.getDataHora()
            );
            rw.setUserLogin(r.getUserlogin().getLogin());
            mew.add(rw);
        }
        return mew;
    }

    public String getIdLotacaoSub() {
        return idLotacaoSub;
    }

    public void setIdLotacaoSub(String idLotacaoSub) {
        this.idLotacaoSub = idLotacaoSub;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
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
