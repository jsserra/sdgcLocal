/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.Funcional;
import com.pmm.sdgc.model.Lotacao;
import com.pmm.sdgc.model.Solicitacao;
import com.pmm.sdgc.model.UserLogin;
import java.time.LocalDateTime;
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
public class SolicitacaoDao {

    @PersistenceContext
    EntityManager em;



    public List<Solicitacao> getSolicitacaoAtiva(String idfunc) {
       Query q = em.createQuery("select so from Solicitacao so where so.ativo = 1 and so.funcional.id =:idfunc");// by trim(so.id)");
       q.setParameter("idfunc", idfunc);
       return q.setMaxResults(10).getResultList();
    }
    
    public List<Lotacao> getLotacaoPorId(String id) throws Exception{
        Query q=em.createQuery("select l.lotacao from Solicitacao l where l.funcional.id = :id and l.ativo = true");
	q.setParameter("id", id);
        return q.getResultList();	
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
        
    }
 
}
