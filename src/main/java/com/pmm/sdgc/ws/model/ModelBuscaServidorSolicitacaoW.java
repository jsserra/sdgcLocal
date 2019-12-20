/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;


import com.pmm.sdgc.model.Solicitacao;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author setinf
 */
public class ModelBuscaServidorSolicitacaoW {

    private Integer horaSemanal;
    private String nomeLotacao;
    private String matricula;

    public ModelBuscaServidorSolicitacaoW(String matricula, String lotacao, Integer horaSemanal) {
        this.matricula = matricula;
        this.nomeLotacao = lotacao;
        this.horaSemanal = horaSemanal;
    }

    public static List<ModelBuscaServidorSolicitacaoWs> toModelBuscaServidorSolicitacaoWs(List<Solicitacao> solicitacoes) {
        List<ModelBuscaServidorSolicitacaoWs> mew = new ArrayList();
        for (Solicitacao s : solicitacoes) {
            ModelBuscaServidorSolicitacaoWs ew = new ModelBuscaServidorSolicitacaoWs(
                    s.getFuncional().getMatricula(),
                    s.getLotacao().getNome(),
                    s.getFuncional().getCargo().getHoraSemanal());
            mew.add(ew);
        }
        return mew;
    }

    public Integer getHoraSemanal() {
        return horaSemanal;
    }

    public void setHoraSemanal(Integer horaSemanal) {
        this.horaSemanal = horaSemanal;
    }

    public String getNomeLotacao() {
        return nomeLotacao;
    }

    public void setNomeLotacao(String nomeLotacao) {
        this.nomeLotacao = nomeLotacao;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    

}
