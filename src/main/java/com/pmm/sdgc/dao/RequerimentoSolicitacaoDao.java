/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.RequerimentoSolicitacao;
//import com.pmm.sdgc.model.RequerimentoStatus;
//import com.pmm.sdgc.model.Solicitacao;
//import com.pmm.sdgc.model.UserLogin;
//import java.time.LocalDateTime;import java.time.LocalDateTime;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
//import javax.ws.rs.PathParam;

/**
 *
 * @author ajuliano
 */
@Stateless
public class RequerimentoSolicitacaoDao {

    @PersistenceContext
    EntityManager em;

    public List<RequerimentoSolicitacao> getRequerimentoSolicitacao() {
        Query q = em.createQuery("select r from RequerimentoSolicitacao r");
        return q.getResultList();
    }

    public List<RequerimentoSolicitacao> getRequerimentoSolicitacaoSesmt() {
        Query q = em.createQuery("select r from RequerimentoSolicitacao r where r.compativel = 1");
        return q.getResultList();
    }

    public RequerimentoSolicitacao getRequerimentoSolicitacaoPorId(Integer id) throws Exception {

        if (id == null) {
            throw new Exception("Insira solicitação!");
        }
        
        if (id != 4 && id != 15 && id != 16 && id != 21 && id != 23){
            throw new Exception("Solicitação inválida");
        }

        RequerimentoSolicitacao reqs = null;
        for (RequerimentoSolicitacao rs : getRequerimentoSolicitacao()) {
            if (rs.getId() == id) {
                reqs = rs;
            }
        }
        
        if (reqs.equals(null)) {
            throw new Exception("Insira uma solicitação");
        } else {
            return reqs;
        }
    }

    /*  
        public RequerimentoSolicitacao getRequerimentoSolicitacaoPorId(Integer id) {
        Query q = em.createQuery("select r from RequerimentoSolicitacao r where r.id = :id");
        q.setParameter("id", id);
        List<RequerimentoSolicitacao> rs = q.getResultList();

        if (rs.isEmpty()) {
            return null;
        } else {
            return rs.get(0);
        }
    }
    
    public void alterar(Solicitacao solicitacao){// throws Exception {
        em.merge(solicitacao);
    }
    
    
    public void incluirSolicitacao(Funcional funcional, Lotacao lotacao, UserLogin login) {
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setAtivo(Boolean.TRUE);
        solicitacao.setControle("2");
        solicitacao.setData(LocalDateTime.now());
        solicitacao.setFuncional(funcional);
        solicitacao.setLotacao(lotacao);
        solicitacao.setPender(0);
        solicitacao.setLogin(login);
        
        em.persist(solicitacao);
        
    }*/
}
