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
public class ModelPermissaoAcessoWs {
    private Boolean incluir;
    private Boolean alterar;
    private Boolean excluir;
    private Boolean buscar;
    private Boolean listar;

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

    public Boolean getBuscar() {
        return buscar;
    }

    public void setBuscar(Boolean buscar) {
        this.buscar = buscar;
    }

    public Boolean getListar() {
        return listar;
    }

    public void setListar(Boolean listar) {
        this.listar = listar;
    }
    
    
    
}
