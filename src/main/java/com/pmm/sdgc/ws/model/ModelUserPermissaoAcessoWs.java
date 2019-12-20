/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.PlanejamentoConsolidado;
import com.pmm.sdgc.model.UserPermissaoAcesso;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dreges
 */
public class ModelUserPermissaoAcessoWs {

    private String link;
    private Boolean alterar;
    private Boolean buscar;
    private Boolean incluir;
    private Boolean listar;
    private Boolean excluir;
    private Integer direcao;

    public ModelUserPermissaoAcessoWs(String link, Boolean alterar, Boolean buscar, Boolean incluir, Boolean listar, Boolean excluir) {
        this.link = link;
        this.alterar = alterar;
        this.buscar = buscar;
        this.incluir = incluir;
        this.listar = listar;
        this.excluir = excluir;
    }

    public ModelUserPermissaoAcessoWs(String link1) {
    }

    public static List<ModelUserPermissaoAcessoWs> toModelUserPermissaoAcessoWs(List<UserPermissaoAcesso> userPermissaoAcessos) {
        List<ModelUserPermissaoAcessoWs> mew = new ArrayList();
        for (UserPermissaoAcesso upa : userPermissaoAcessos) {
            ModelUserPermissaoAcessoWs mupa = new ModelUserPermissaoAcessoWs(
                    upa.getUserMenu().getLink(),
                    upa.getAlterar(),
                    upa.getBuscar(),
                    upa.getIncluir(),
                    upa.getListar(),
                    upa.getExcluir()
            );
            mew.add(mupa);
        }
        return mew;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Boolean getAlterar() {
        return alterar;
    }

    public void setAlterar(Boolean alterar) {
        this.alterar = alterar;
    }

    public Boolean getBuscar() {
        return buscar;
    }

    public void setBuscar(Boolean buscar) {
        this.buscar = buscar;
    }

    public Boolean getIncluir() {
        return incluir;
    }

    public void setIncluir(Boolean incluir) {
        this.incluir = incluir;
    }

    public Boolean getListar() {
        return listar;
    }

    public void setListar(Boolean listar) {
        this.listar = listar;
    }

    public Integer getDirecao() {
        return direcao;
    }

    public void setDirecao(Integer direcao) {
        this.direcao = direcao;
    }

    public Boolean getExcluir() {
        return excluir;
    }

    public void setExcluir(Boolean excluir) {
        this.excluir = excluir;
    }

}
