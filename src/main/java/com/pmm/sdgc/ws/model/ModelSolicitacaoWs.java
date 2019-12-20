/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.Solicitacao;
//import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ajuliano
 */
public class ModelSolicitacaoWs {    
    
    private Integer id;
    private String id_lotacao;
    private Boolean ativo;

    //contrutor
      public ModelSolicitacaoWs(Integer id, String id_lotacao, Boolean ativo) {
        this.id = id;
        this.id_lotacao = id_lotacao;
        this.ativo = ativo;
    }

    public static List<ModelSolicitacaoWs> toSolicitacaoModelWs(List<Solicitacao> solicitacoes){
        
        List<ModelSolicitacaoWs> mss = new ArrayList();
        for (Solicitacao s : solicitacoes) {
            ModelSolicitacaoWs ssw = new ModelSolicitacaoWs(
                    s.getId(),
                    s.getLotacao().getId(),
                    //s.getLotacao().getNome(),
                    s.getAtivo()

            );
            mss.add(ssw);
        }
        return mss;
    }

     
    //GET SET

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getId_lotacao() {
        return id_lotacao;
    }

    public void setId_lotacao(String id_lotacao) {
        this.id_lotacao = id_lotacao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }


    
    
}
