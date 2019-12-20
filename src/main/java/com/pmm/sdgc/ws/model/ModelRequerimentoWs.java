/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.Requerimento;
import com.pmm.sdgc.model.RequerimentoHistorico;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ajuliano
 */
public class ModelRequerimentoWs {

    private Integer id;
    private String protocolo;
    private String status;
    private String solicitacao;
    private LocalDateTime data;

    public ModelRequerimentoWs(Integer id, String protocolo, String status, String solicitacao) {
        this.id = id;
        this.protocolo = protocolo;
        this.status = status;
        this.solicitacao = solicitacao;
    }
    
    private ModelRequerimentoWs(Integer id, String protocolo, String status, String solicitacao, LocalDateTime data) {
        this.id = id;
        this.protocolo = protocolo;
        this.status = status;
        this.solicitacao = solicitacao;
        this.data = data;
    }

    public static List<ModelRequerimentoWs> toModelRequerimentoWs(List<Requerimento> requerimento) {
        List<ModelRequerimentoWs> mrw = new ArrayList();
        for (Requerimento r : requerimento) {
            ModelRequerimentoWs wrm = new ModelRequerimentoWs(
                    r.getId(),
                    r.getProtocolo(),
                    r.getReqStatus().getNome(),
                    r.getReqSolicitacao().getItem());

            mrw.add(wrm);
        }

        return mrw;

    }
    
        public static List<ModelRequerimentoWs> toModelRequerimentoProtocolosWs(List<RequerimentoHistorico> requerimento) {
        List<ModelRequerimentoWs> mrw = new ArrayList();
        for (RequerimentoHistorico r : requerimento) {
            ModelRequerimentoWs wrm = new ModelRequerimentoWs(
                    r.getRequerimento().getId(),
                    r.getRequerimento().getProtocolo(),
                    r.getReqStatus().getNome(),
                    r.getRequerimento().getReqSolicitacao().getItem(),
                    r.getData());

            mrw.add(wrm);
        }

        return mrw;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(String solicitacao) {
        this.solicitacao = solicitacao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }



  
}
