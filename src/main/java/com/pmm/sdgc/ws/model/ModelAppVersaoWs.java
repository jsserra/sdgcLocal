/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

/**
 *
 * @author acg
 */
public class ModelAppVersaoWs {
    private Integer id;
    private String nome;
    private String versao;

    public ModelAppVersaoWs(Integer id, String nome, String versao) {
        this.id = id;
        this.nome = nome;
        this.versao = versao;
    }

    public ModelAppVersaoWs() {
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }
    
    
    
    
}
