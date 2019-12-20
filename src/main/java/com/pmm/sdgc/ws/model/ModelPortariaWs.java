/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.PortariaRelacao;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author setinf
 */
public class ModelPortariaWs {

    private String nomePortaria;
    private LocalDate dataPublicacao;
    //private Boolean ativo;
    private String tpa;
    private String transmissao;
    private String copiaDigital;

    public ModelPortariaWs(String nomePortaria, LocalDate dataPublicacao, String tpa, String transmissao, String copiaDigital) {
        this.nomePortaria = nomePortaria;
        this.dataPublicacao = dataPublicacao;
        this.tpa = tpa;
        this.transmissao = transmissao;
        this.copiaDigital = copiaDigital;
    }

    public static List<ModelPortariaWs> toModelPortariaWs(List<PortariaRelacao> portarias) {
        List<ModelPortariaWs> mpw = new ArrayList();
        for (PortariaRelacao pr : portarias) {
            ModelPortariaWs prw = new ModelPortariaWs(
                    pr.getPortaria().getNome(),
                    pr.getPortaria().getDataPublicacao(),
                    pr.getPortariaTpa().getNome(),
                    pr.getPortariaTransmissao().getNome(),
                    pr.getPortaria().getArquivoNome()
            );
            mpw.add(prw);
        }
        return mpw;

    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getTpa() {
        return tpa;
    }

    public void setTpa(String tpa) {
        this.tpa = tpa;
    }

    public String getTransmissao() {
        return transmissao;
    }

    public void setTransmissao(String transmissao) {
        this.transmissao = transmissao;
    }

    public String getCopiaDigital() {
        return copiaDigital;
    }

    public void setCopiaDigital(String copiaDigital) {
        this.copiaDigital = copiaDigital;
    }

    public String getNomePortaria() {
        return nomePortaria;
    }

    public void setNomePortaria(String nomePortaria) {
        this.nomePortaria = nomePortaria;
    }

}
