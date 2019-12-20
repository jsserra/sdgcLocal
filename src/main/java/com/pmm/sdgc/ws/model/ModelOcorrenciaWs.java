/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.Ocorrencia;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ajuliano
 */
public class ModelOcorrenciaWs {

    private Integer id;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private LocalDateTime dataLancamento;
    private String obs;
    private String Login;
    private String ocorrenciaNome;
    private String cpf;
    private Integer numeroDias;
    private String nomeLotacao;
    

    public ModelOcorrenciaWs(Integer id, LocalDateTime entrada, LocalDateTime saida, LocalDateTime dataLancamento, String obs, String Login, String ocorrenciaNome, Integer numeroDias, String cpf, String nomeLotacao) {
        this.id = id;
        this.entrada = entrada;
        this.saida = saida;
        this.dataLancamento = dataLancamento;
        this.obs = obs;
        this.Login = Login;
        this.ocorrenciaNome = ocorrenciaNome;
        this.numeroDias = numeroDias;
        this.cpf= cpf;
        this.nomeLotacao = nomeLotacao;
    }

    public static List<ModelOcorrenciaWs> toModelOcorrenciaWs(List<Ocorrencia> ocorrencias) {

        List<ModelOcorrenciaWs> mow = new ArrayList<>();
        for (Ocorrencia o : ocorrencias) {
            ModelOcorrenciaWs mo = new ModelOcorrenciaWs(
                    o.getId(),
                    o.getEntrada(),
                    o.getSaida(),
                    o.getDataHora(),
                    o.getObs(),
                    o.getUserLogin().getLogin(),
                    o.getOcorrenciaDesc().getNome(),
                    o.getNumeroDias(),
                    o.getUserLogin().getCpf(),
                    o.getLotacaoSub().getNome()
            );
            mow.add(mo);
        }
        return mow;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }

    public LocalDateTime getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDateTime dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

    public String getOcorrenciaNome() {
        return ocorrenciaNome;
    }

    public void setOcorrenciaNome(String ocorrenciaNome) {
        this.ocorrenciaNome = ocorrenciaNome;
    }

    public Integer getNumeroDias() {
        return numeroDias;
    }

    public void setNumeroDias(Integer numeroDias) {
        this.numeroDias = numeroDias;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeLotacao() {
        return nomeLotacao;
    }

    public void setNomeLotacao(String nomeLotacao) {
        this.nomeLotacao = nomeLotacao;
    }

    @Override
    public String toString() {
        return "ModelOcorrenciaWs{" + "id=" + id + ", entrada=" + entrada + ", saida=" + saida + ", dataLancamento=" + dataLancamento + ", obs=" + obs + ", Login=" + Login + ", ocorrenciaNome=" + ocorrenciaNome + ", cpf=" + cpf + ", numeroDias=" + numeroDias + ", nomeLotacao=" + nomeLotacao + '}';
    }
    
    

}
