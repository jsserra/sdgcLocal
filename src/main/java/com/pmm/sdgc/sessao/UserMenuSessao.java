/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.sessao;

/**
 *
 * @author acg
 */
public class UserMenuSessao {
    private String pasta;
    private String arquivo;
    private Boolean ativo;
    private String nomeOperacao;

    public UserMenuSessao(String pasta, String arquivo, Boolean ativo, String nomeOperacao) {
        this.pasta = pasta;
        this.arquivo = arquivo;
        this.ativo = ativo;
        this.nomeOperacao = nomeOperacao;
    }

    public UserMenuSessao() {
    }


    public String getPasta() {
        return pasta;
    }

    public void setPasta(String pasta) {
        this.pasta = pasta;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getNomeOperacao() {
        return nomeOperacao;
    }

    public void setNomeOperacao(String nomeOperacao) {
        this.nomeOperacao = nomeOperacao;
    }
    

}
