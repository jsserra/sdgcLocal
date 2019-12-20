/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

//import com.pmm.sdgc.model.Lotacao;
//import com.pmm.sdgc.model.LotacaoSub;
import com.pmm.sdgc.model.Solicitacao;
//import java.time.LocalDateTime;
//import com.pmm.sdgc.model.Solicitacao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author setinf
 */
public class ModelLotacaoWsMat {

    private String matricula;
    private String nome;
    private List<ModelLotacaoWs> solicitacoes;

    public ModelLotacaoWsMat(String matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
    }

    public static ModelLotacaoWsMat toModelLotacaoWsMat(List<Solicitacao> solicitacoes, String matricula, String nome) {

        ModelLotacaoWsMat mlwm = new ModelLotacaoWsMat(matricula, nome);

        List<ModelLotacaoWs> mlw = new ArrayList();
        for (Solicitacao s : solicitacoes) {
            ModelLotacaoWs lw = new ModelLotacaoWs(
                    s.getLotacao().getNome(),
                    s.getLotacao().getId(),
                    s.getData()
            );
            lw.setUserLogin(s.getLogin().getLogin());
            mlw.add(lw);
            mlwm.setSolicitacoes(mlw);

        }
        return mlwm;
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

    public List<ModelLotacaoWs> getSolicitacoes() {
        return solicitacoes;
    }

    public void setSolicitacoes(List<ModelLotacaoWs> solicitacoes) {
        this.solicitacoes = solicitacoes;
    }

}
