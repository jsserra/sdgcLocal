/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.OcorrenciaDesc;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ajuliano
 */
public class ModelOcorrenciaDescWs {

    private String idOcorrencia;
    private String nome;
    private String descricao;
    private String tipo;
    private Integer diasMax;
    private Integer diasMin;
    private Integer difnirDias;
     private Integer definirPeriodo;
    private LocalDateTime dataHr;
    private Integer userLog;
        
    public ModelOcorrenciaDescWs(){
    }

    public ModelOcorrenciaDescWs(String idOcorrencia, String nome, String descricao, String tipo, Integer diasMax, Integer diasMin, Integer difnirDias, LocalDateTime dataHr, Integer userLog,Integer definirPeriodo) {
        this.idOcorrencia = idOcorrencia;
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.diasMax = diasMax;
        this.diasMin = diasMin;
        this.difnirDias = difnirDias;
        this.dataHr = dataHr;
        this.userLog = userLog;
        this.definirPeriodo = definirPeriodo;
    }

    public static List<ModelOcorrenciaDescWs> toModelOcorrenciaDescWs(List<OcorrenciaDesc> ocorrenciasDesc) {
        List<ModelOcorrenciaDescWs> modw = new ArrayList();

        for (OcorrenciaDesc od : ocorrenciasDesc) {
            ModelOcorrenciaDescWs mod = new ModelOcorrenciaDescWs(
                    od.getIdOcorrencia(),
                    od.getNome(),
                    od.getDescricao(),
                    od.getTipo(),
                    od.getDiasMax(),
                    od.getDiasMim(),
                    od.getDefinirDias(),
                    od.getDataHr(),
                    od.getUserLogin().getId(),     
                    od.getDefinirPeriodo()
            );
            modw.add(mod);
        }
        return modw;
    }

    public String getIdOcorrencia() {
        return idOcorrencia;
    }

    public void setIdOcorrencia(String idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
    }
       
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getDiasMax() {
        return diasMax;
    }

    public void setDiasMax(Integer diasMax) {
        this.diasMax = diasMax;
    }

    public Integer getDiasMin() {
        return diasMin;
    }

    public void setDiasMin(Integer diasMin) {
        this.diasMin = diasMin;
    }

    public Integer getDifnirDias() {
        return difnirDias;
    }

    public void setDifnirDias(Integer difnirDias) {
        this.difnirDias = difnirDias;
    }

    public LocalDateTime getDataHr() {
        return dataHr;
    }

    public void setDataHr(LocalDateTime dataHr) {
        this.dataHr = dataHr;
    }

    public Integer getUserLog() {
        return userLog;
    }

    public void setUserLog(Integer userLog) {
        this.userLog = userLog;
    }

    public Integer getDefinirPeriodo() {
        return definirPeriodo;
    }

    public void setDefinirPeriodo(Integer definirPeriodo) {
        this.definirPeriodo = definirPeriodo;
    }
}
