/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.SolicitacaoSub;
//import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author setinf
 */
public class ModelSolicitacaoSubWsMat {

    private String matricula;
    private String nome;
    private List<ModelSolicitacaoSubWs> solicitacoesSub;

    public ModelSolicitacaoSubWsMat(String matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
    }

    public static ModelSolicitacaoSubWsMat toModelSolicitacaoSubWsMat(List<SolicitacaoSub> solicitacoesSub, String matricula, String nome) {
        
        ModelSolicitacaoSubWsMat mssm = new ModelSolicitacaoSubWsMat(matricula, nome);
        
        List<ModelSolicitacaoSubWs> mss = new ArrayList();
        for (SolicitacaoSub s : solicitacoesSub) {
            ModelSolicitacaoSubWs ssw = new ModelSolicitacaoSubWs(
                    s.getLotacaoSub().getId(),
                    s.getLotacaoSub().getNome(),
                    s.getData() 
            );
            ssw.setUserLogin(s.getUserlogin().getLogin());
            mss.add(ssw);
            mssm.setSolicitacoesSub(mss);
        }
        return mssm;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ModelSolicitacaoSubWs> getSolicitacoesSub() {
        return solicitacoesSub;
    }

    public void setSolicitacoesSub(List<ModelSolicitacaoSubWs> solicitacoesSub) {
        this.solicitacoesSub = solicitacoesSub;
    }




}
