/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.SolicitacaoSub;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author setinf
 */
public class ModelSolicitacaoSubWs {

    private String idSetor;
    private String nome;
    private String matricula;
    private LocalDateTime data;
    private String userLogin;
    private String idFuncional;
    
    public ModelSolicitacaoSubWs(String id, String nome, LocalDateTime data) {
        this.idSetor = id;
        this.data = data;
        this.nome = nome;

    }

    public ModelSolicitacaoSubWs(String idFuncional, String matricula, String nome) {
        this.idFuncional = idFuncional;
        this.matricula = matricula;
        this.nome = nome;        
    }
    
    
    

    public static List<ModelSolicitacaoSubWs> toModelSolicitacaoSubWs(List<SolicitacaoSub> solicitacoesSub) {
        List<ModelSolicitacaoSubWs> mss = new ArrayList();
        for (SolicitacaoSub s : solicitacoesSub) {
            ModelSolicitacaoSubWs ssw = new ModelSolicitacaoSubWs(
                    s.getId(),
                    s.getLotacaoSub().getNome(),
                    s.getData()
            );
            mss.add(ssw);
        }
        return mss;
    }
    public static List<ModelSolicitacaoSubWs> toModelSolicitacaoSubFuncionalWs(List<SolicitacaoSub> solicitacoesSub) {
        List<ModelSolicitacaoSubWs> mss = new ArrayList();
        for (SolicitacaoSub s : solicitacoesSub) {
            ModelSolicitacaoSubWs ssw = new ModelSolicitacaoSubWs(
                    s.getFuncional().getId(),
                    s.getFuncional().getMatricula(),
                    s.getFuncional().getPessoa().getNome()                 
                    
            );
            mss.add(ssw);
        }
        return mss;
    }

    public String getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(String id) {
        this.idSetor = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    public String getIdFuncional() {
        return idFuncional;
    }

    public void setIdFuncional(String idFuncional) {
        this.idFuncional = idFuncional;
    }
    
    

}
