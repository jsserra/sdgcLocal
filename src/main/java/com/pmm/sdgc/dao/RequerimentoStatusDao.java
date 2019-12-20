/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.RequerimentoStatus;
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
public class RequerimentoStatusDao {

    @PersistenceContext
    EntityManager em;

    public List<RequerimentoStatus> getRequerimentoStatus() {
        Query q = em.createQuery("select r from RequerimentoStatus r");
        //q.setParameter("idfunc", idfunc);
        return q.getResultList();
    }

    public RequerimentoStatus getRequerimentoStatusPorId(Integer id) {
        Query q = em.createQuery("select r from RequerimentoStatus r where r.id = :id");
        q.setParameter("id", id);
        RequerimentoStatus rs = (RequerimentoStatus) q.getResultList().get(0);
        return rs;
    }

    /* public void alterar(Solicitacao solicitacao){// throws Exception {
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
