/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import java.util.List;

/**
 *
 * @author ajuliano
 */
public class ModelOcorrenciaInclusaoWs {
    private String idTipoOco;
    private Integer dias;
    private String entrada;
    private String saida;
    private String obs;
    private String setorOco;
    private List<ModelIdFuncionalWs> idFuncs;
    private Long idOcorrencia;

    
    public String getIdTipoOco() {
        return idTipoOco;
    }

    public void setIdTipoOco(String idTipoOco) {
        this.idTipoOco = idTipoOco;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getSetorOco() {
        return setorOco;
    }

    public void setSetorOco(String setorOco) {
        this.setorOco = setorOco;
    }

    public List<ModelIdFuncionalWs> getIdFuncs() {
        return idFuncs;
    }

    public void setIdFuncs(List<ModelIdFuncionalWs> idFuncs) {
        this.idFuncs = idFuncs;
    }

    public Long getIdOcorrencia() {
        return idOcorrencia;
    }

    public void setIdOcorrencia(Long idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
    }

    @Override
    public String toString() {
        return "ModelOcorrenciaInclusaoWs{" + "idTipoOco=" + idTipoOco + ", dias=" + dias + ", entrada=" + entrada + ", saida=" + saida + ", obs=" + obs + ", setorOco=" + setorOco + ", idFuncs=" + idFuncs + ", idOcorrencia=" + idOcorrencia + '}';
    }

    
    
}
