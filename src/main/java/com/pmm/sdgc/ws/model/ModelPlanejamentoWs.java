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
public class ModelPlanejamentoWs {
    private Long idPlanejamentoAuxiliar;
    private Long idPlanejamento;
    private Long idTpPlan;
    private String idFunc;
    private Integer diaSemana;
    private String hInicial;
    private String hFinal;
    private String setorPlan;
    private Boolean feriado;
    private Boolean pontoFacult;
    private String mesAno;
    private String planejamentoTipo;
    private String nomeSetor;

    public Long getIdTpPlan() {
        return idTpPlan;
    }

    public void setIdTpPlan(Long idTpPlan) {
        this.idTpPlan = idTpPlan;
    }

    public String getIdFunc() {
        return idFunc;
    }

    public void setIdFunc(String idFunc) {
        this.idFunc = idFunc;
    }

    public Integer getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(Integer diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String gethInicial() {
        return hInicial;
    }

    public void sethInicial(String hInicial) {
        this.hInicial = hInicial;
    }

    public String gethFinal() {
        return hFinal;
    }

    public void sethFinal(String hFinal) {
        this.hFinal = hFinal;
    }

    public String getSetorPlan() {
        return setorPlan;
    }

    public void setSetorPlan(String setorPlan) {
        this.setorPlan = setorPlan;
    }

    public Boolean getFeriado() {
        return feriado;
    }

    public void setFeriado(Boolean feriado) {
        this.feriado = feriado;
    }

    public Boolean getPontoFacult() {
        return pontoFacult;
    }

    public void setPontoFacult(Boolean pontoFacult) {
        this.pontoFacult = pontoFacult;
    }

    public String getMesAno() {
        return mesAno;
    }

    public void setMesAno(String mesAno) {
        this.mesAno = mesAno;
    }

    public String getPlanejamentoTipo() {
        return planejamentoTipo;
    }

    public void setPlanejamentoTipo(String planejamentoTipo) {
        this.planejamentoTipo = planejamentoTipo;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }

    public Long getIdPlanejamentoAuxiliar() {
        return idPlanejamentoAuxiliar;
    }

    public void setIdPlanejamentoAuxiliar(Long idPlanejamentoAuxiliar) {
        this.idPlanejamentoAuxiliar = idPlanejamentoAuxiliar;
    }

    public Long getIdPlanejamento() {
        return idPlanejamento;
    }

    public void setIdPlanejamento(Long idPlanejamento) {
        this.idPlanejamento = idPlanejamento;
    }

    @Override
    public String toString() {
        return "ModelPlanejamentoWs{" + "idPlanejamentoAuxiliar=" + idPlanejamentoAuxiliar + ", idPlanejamento=" + idPlanejamento + ", idTpPlan=" + idTpPlan + ", idFunc=" + idFunc + ", diaSemana=" + diaSemana + ", hInicial=" + hInicial + ", hFinal=" + hFinal + ", setorPlan=" + setorPlan + ", feriado=" + feriado + ", pontoFacult=" + pontoFacult + ", mesAno=" + mesAno + ", planejamentoTipo=" + planejamentoTipo + ", nomeSetor=" + nomeSetor + '}';
    }
    
    
}
