/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.LotacaoSub;
import com.pmm.sdgc.model.SolicitacaoSub;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author setinf
 */
@Stateless
public class SolicitacaoSubDao {

    @PersistenceContext
    EntityManager em;



    public List<LotacaoSub> getLotacaoSubPorId(String id) throws Exception {
        Query q = em.createQuery("select ss.lotacaoSub from SolicitacaoSub ss where ss.funcional.id = :id and ss.ativo = true");
        q.setParameter("id", id);
        return q.getResultList();
    }

    public LotacaoSub getUmaLotacaoSubPorId(String id) throws Exception {
        Query q = em.createQuery("select ss.lotacaoSub from SolicitacaoSub ss where ss.funcional.id = :id and ss.ativo = true");
        q.setParameter("id", id);
        List<LotacaoSub> lotacoes =  q.getResultList();
        if (lotacoes.isEmpty()) throw new Exception("Lotação sub com o id " + id + " não encontrado");
        
        return lotacoes.get(0);
        
    }

    public LotacaoSub getLotacaoSubPorUmId(String id) throws Exception {
        Query q = em.createQuery("select l from LotacaoSub l where l.id = :id");
        q.setParameter("id", id);
        List<LotacaoSub> lotacoes =  q.getResultList();
        if (lotacoes.isEmpty()) throw new Exception("Lotação sub com o id " + id + " não encontrado");
        
        return lotacoes.get(0);
        
    }
    
    public List<SolicitacaoSub> getSolicitacaoSubPorIdAtivo(String id) throws Exception {
        Query q = em.createQuery("select ss from SolicitacaoSub ss where ss.funcional.id = :id and ss.ativo = true");
        q.setParameter("id", id);
        return q.getResultList();
    }

    
    public List<SolicitacaoSub> getFuncionalPorIdSetor(String id) throws Exception {
        Query q = em.createQuery("select ss from SolicitacaoSub ss where ss.lotacaoSub.id = :id and ss.ativo = true order by ss.funcional.pessoa.nome"
                + "");
        q.setParameter("id", id);
        return q.getResultList();
    }

    
  


    public void incluir(SolicitacaoSub ls) throws Exception {
        if (ls.getControle() == null) {
            ls.setControle("2");
        }
        em.persist(ls);
    }

    public void alterar(SolicitacaoSub ls) throws Exception {
        if (ls.getControle() == null) {
            ls.setControle("2");
        }
        em.merge(ls);
    }

  

}
