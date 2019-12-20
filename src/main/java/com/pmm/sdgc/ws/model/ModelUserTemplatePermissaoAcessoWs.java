    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import java.util.List;
import java.util.Objects;


/**
 *
 * @author setinf
 */
public class ModelUserTemplatePermissaoAcessoWs {

    private Integer idUserMenu;   
    private Integer ordenar;    
    private Integer direcao;
    private String icon;   
    private String menuN1; 
    private String menuN2; 
    private String menuN3; 
    private String menuN4; 
    private String link;   
    private String pasta;
    private String arquivo;
    private Boolean userMenuativo;
    private Integer idUserTemplate;
    private Boolean incluir;
    private Boolean alterar;
    private Boolean excluir;
    private Boolean listar;
    private Boolean buscar;
    private List<ModelUserMenu> idsUserMenu;

    public Integer getIdUserMenu() {
        return idUserMenu;
    }

    public void setIdUserMenu(Integer idUserMenu) {
        this.idUserMenu = idUserMenu;
    }

    public Integer getOrdenar() {
        return ordenar;
    }

    public void setOrdenar(Integer ordenar) {
        this.ordenar = ordenar;
    }

    public Integer getDirecao() {
        return direcao;
    }

    public void setDirecao(Integer direcao) {
        this.direcao = direcao;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMenuN1() {
        return menuN1;
    }

    public void setMenuN1(String menuN1) {
        this.menuN1 = menuN1;
    }

    public String getMenuN2() {
        return menuN2;
    }

    public void setMenuN2(String menuN2) {
        this.menuN2 = menuN2;
    }

    public String getMenuN3() {
        return menuN3;
    }

    public void setMenuN3(String menuN3) {
        this.menuN3 = menuN3;
    }

    public String getMenuN4() {
        return menuN4;
    }

    public void setMenuN4(String menuN4) {
        this.menuN4 = menuN4;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public Boolean getUserMenuativo() {
        return userMenuativo;
    }

    public void setUserMenuativo(Boolean userMenuativo) {
        this.userMenuativo = userMenuativo;
    }

    public Integer getIdUserTemplate() {
        return idUserTemplate;
    }

    public void setIdUserTemplate(Integer idUserTemplate) {
        this.idUserTemplate = idUserTemplate;
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

    public List<ModelUserMenu> getIdsUserMenu() {
        return idsUserMenu;
    }

    public void setIdsUserMenu(List<ModelUserMenu> idsUserMenu) {
        this.idsUserMenu = idsUserMenu;
    }

    @Override
    public String toString() {
        return "\n\n\n----------->ModelUserTemplatePermissaoAcessoWs{" + "\nidUserMenu=" + idUserMenu + ", \nordenar=" + ordenar + ", \ndirecao=" + direcao + ", \nicon=" + icon + ", \nmenuN1=" + menuN1 + ", \nmenuN2=" + menuN2 + ", \nmenuN3=" + menuN3 + ", \nmenuN4=" + menuN4 + ", \nlink=" + link + ", \npasta=" + pasta + ", \narquivo=" + arquivo + ", \nuserMenuativo=" + userMenuativo + ", \nidUserTemplate=" + idUserTemplate + ", \nincluir=" + incluir + ", \nalterar=" + alterar + ", \nexcluir=" + excluir + ", \nlistar=" + listar + ", \nbuscar=" + buscar + ", \nidsUserMenu=" + idsUserMenu + "\n}";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.idUserMenu);
        hash = 73 * hash + Objects.hashCode(this.ordenar);
        hash = 73 * hash + Objects.hashCode(this.direcao);
        hash = 73 * hash + Objects.hashCode(this.icon);
        hash = 73 * hash + Objects.hashCode(this.menuN1);
        hash = 73 * hash + Objects.hashCode(this.menuN2);
        hash = 73 * hash + Objects.hashCode(this.menuN3);
        hash = 73 * hash + Objects.hashCode(this.menuN4);
        hash = 73 * hash + Objects.hashCode(this.link);
        hash = 73 * hash + Objects.hashCode(this.pasta);
        hash = 73 * hash + Objects.hashCode(this.arquivo);
        hash = 73 * hash + Objects.hashCode(this.userMenuativo);
        hash = 73 * hash + Objects.hashCode(this.idUserTemplate);
        hash = 73 * hash + Objects.hashCode(this.incluir);
        hash = 73 * hash + Objects.hashCode(this.alterar);
        hash = 73 * hash + Objects.hashCode(this.excluir);
        hash = 73 * hash + Objects.hashCode(this.listar);
        hash = 73 * hash + Objects.hashCode(this.buscar);
        hash = 73 * hash + Objects.hashCode(this.idsUserMenu);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ModelUserTemplatePermissaoAcessoWs other = (ModelUserTemplatePermissaoAcessoWs) obj;
        if (!Objects.equals(this.icon, other.icon)) {
            return false;
        }
        if (!Objects.equals(this.menuN1, other.menuN1)) {
            return false;
        }
        if (!Objects.equals(this.menuN2, other.menuN2)) {
            return false;
        }
        if (!Objects.equals(this.menuN3, other.menuN3)) {
            return false;
        }
        if (!Objects.equals(this.menuN4, other.menuN4)) {
            return false;
        }
        if (!Objects.equals(this.link, other.link)) {
            return false;
        }
        if (!Objects.equals(this.pasta, other.pasta)) {
            return false;
        }
        if (!Objects.equals(this.arquivo, other.arquivo)) {
            return false;
        }
        if (!Objects.equals(this.idUserMenu, other.idUserMenu)) {
            return false;
        }
        if (!Objects.equals(this.ordenar, other.ordenar)) {
            return false;
        }
        if (!Objects.equals(this.direcao, other.direcao)) {
            return false;
        }
        if (!Objects.equals(this.userMenuativo, other.userMenuativo)) {
            return false;
        }
        if (!Objects.equals(this.idUserTemplate, other.idUserTemplate)) {
            return false;
        }
        if (!Objects.equals(this.incluir, other.incluir)) {
            return false;
        }
        if (!Objects.equals(this.alterar, other.alterar)) {
            return false;
        }
        if (!Objects.equals(this.excluir, other.excluir)) {
            return false;
        }
        if (!Objects.equals(this.listar, other.listar)) {
            return false;
        }
        if (!Objects.equals(this.buscar, other.buscar)) {
            return false;
        }
        if (!Objects.equals(this.idsUserMenu, other.idsUserMenu)) {
            return false;
        }
        return true;
    }

    
   
}
