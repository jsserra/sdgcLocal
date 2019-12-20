    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

/**
 *
 * @author setinf
 */
public class ModelUserTemplateExcluirWs {
    private Integer idUserLogin;
    private String idlotacao;
    private String idlotacaosub;

    public Integer getIdUserLogin() {
        return idUserLogin;
    }

    public void setIdUserLogin(Integer idUserLogin) {
        this.idUserLogin = idUserLogin;
    }

    public String getIdlotacao() {
        return idlotacao;
    }

    public void setIdlotacao(String idlotacao) {
        this.idlotacao = idlotacao;
    }

    public String getIdlotacaosub() {
        return idlotacaosub;
    }

    public void setIdlotacaosub(String idlotacaosub) {
        this.idlotacaosub = idlotacaosub;
    }

    @Override
    public String toString() {
        return "ModelUserTemplateExcluirWs{" + "idUserLogin=" + idUserLogin + ", idlotacao=" + idlotacao + ", idlotacaosub=" + idlotacaosub + '}';
    }
   
}
