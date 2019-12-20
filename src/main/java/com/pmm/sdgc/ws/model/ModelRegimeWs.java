/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.EscalaTipo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author setinf
 */
public class ModelRegimeWs {

    private String nome;
    private String idRegime;
    private Boolean ativo;

    public ModelRegimeWs(String nome, String id, Boolean ativo) {
        this.nome = nome;
        this.idRegime = id;
        this.ativo = ativo;
    }

    public ModelRegimeWs(String nome, String id) {
        this.nome = nome;
        this.idRegime = id;
    }

    public ModelRegimeWs() {
    }
    
    

    public static List<ModelRegimeWs> toModelRegimeWs(List<EscalaTipo> regimes) {
        List<ModelRegimeWs> mrw = new ArrayList();
        for (EscalaTipo r : regimes) {
            ModelRegimeWs rw = new ModelRegimeWs(
                    r.getNomeEscalaTipo(),
                    r.getId(),
                    false
            );
            mrw.add(rw);
        }
        return mrw;
    }

    public static List<ModelRegimeWs> toModelRegimeInfoWs(List<EscalaTipo> regimes) {
        List<ModelRegimeWs> mrw = new ArrayList();
        for (EscalaTipo r : regimes) {
            ModelRegimeWs rw = new ModelRegimeWs(
                    r.getNomeEscalaTipo(),
                    r.getId()
            );
            mrw.add(rw);
        }
        return mrw;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getIdRegime() {
        return idRegime;
    }

    public void setIdRegime(String idRegime) {
        this.idRegime = idRegime;
    }

    @Override
    public String toString() {
        return "ModelRegimeWs{" + "nome=" + nome + ", idRegime=" + idRegime + ", ativo=" + ativo + '}';
    }

    
}
