/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.Lotacao;
import com.pmm.sdgc.model.Solicitacao;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ajuliano
 */
@Stateless
public class LotacaoDao {
    @PersistenceContext
    EntityManager em;
    public List<Lotacao> getLotacao(){
        Query q=em.createQuery("select lo from Lotacao lo where lo.ativo = true order by trim(lo.nome)");
        //return q.setMaxResults(10).getResultList();
        return q.getResultList();
    }
    
    public List<Lotacao> getLotacaoAtivo(String idFunc){
        Query q=em.createQuery("select lo from Lotacao lo where  lo.ativo = 1 order by trim(lo.nome)");
        return q.getResultList();
    }
    
    public Lotacao getLotacaoPorId(String id) throws Exception {
        Query q = em.createQuery("select l from Lotacao l where l.id = :id");
        q.setParameter("id", id);
        
        List<Lotacao> lotacoes = q.getResultList();
        
        if (lotacoes.isEmpty()) {
            throw new Exception("Lotacao com o id " + id + " nao encontrado");
        }
        
        return lotacoes.get(0);
    }
    

    public void incluir(Lotacao lo) throws Exception {
        em.persist(lo);
    }
       
     public void alterar(Lotacao lo) throws Exception {
        em.merge(lo);
    }
     
    public void remover(Lotacao lo) throws Exception {
    Query q = em.createQuery("select lo from Lotacao lo where lo = :ca");
    q.setParameter("ca", lo);
        
    List<Lotacao> l = q.getResultList();
        
    if (l.isEmpty()) throw new Exception("Lotação não encontrada!");
    Lotacao car = l.get(0);
    em.remove(car);
    }
}
