/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.sessao;

/**
 *
 * @author dreges
 */
public class CadastroAcessoSessao {

    private Integer id;
    private String link;
    private Boolean incluir;
    private Boolean alterar;
    private Boolean excluir;
    private Boolean listar;
    private Boolean buscar;
//contrutor

 

    public CadastroAcessoSessao() {
        this.incluir = false;
        this.alterar = false;
        this.excluir = false;
        this.listar = false;
        this.buscar = false;
    }
//get set

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Boolean getIncluir() {
        return incluir;
    }

    public void setIncluir(Boolean incluir) {
        this.incluir = incluir;
    }

    public Boolean getAlterar() {
        return alterar;
    }

    public void setAlterar(Boolean alterar) {
        this.alterar = alterar;
    }

    public Boolean getExcluir() {
        return excluir;
    }

    public void setExcluir(Boolean excluir) {
        this.excluir = excluir;
    }

    public Boolean getListar() {
        return listar;
    }

    public void setListar(Boolean listar) {
        this.listar = listar;
    }

    public Boolean getBuscar() {
        return buscar;
    }

    public void setBuscar(Boolean buscar) {
        this.buscar = buscar;
    }
}
