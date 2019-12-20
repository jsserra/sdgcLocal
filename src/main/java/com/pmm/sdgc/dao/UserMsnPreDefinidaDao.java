/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.UserLogin;
import com.pmm.sdgc.model.UsermsnPreDefinida;
import com.pmm.sdgc.ws.model.ModelMensagemWs;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author setinf
 */
@Stateless
public class UserMsnPreDefinidaDao {
    
    @PersistenceContext
    EntityManager em;
    
    @EJB
    UserLoginDao daoUserLogin;
    
    public List<UsermsnPreDefinida> getUsermsnPreDefinidaTipo(String tipo) {
        Query q = em.createQuery("select umpd from UsermsnPreDefinida umpd where umpd.tipo = :tipo");
        q.setParameter("tipo", tipo);
        return q.getResultList();
    }
    
    public UsermsnPreDefinida getUmaUsermsnPreDefinidaTipo(String tipo) {
        Query q = em.createQuery("select umpd from UsermsnPreDefinida umpd where umpd.tipo = :tipo");
        q.setParameter("tipo", tipo);
        return (UsermsnPreDefinida) q.getSingleResult();
    }
    
    public List<UsermsnPreDefinida> getUmaUsermsnPreDefinidaReferencia(String referencia) {
        Query q = em.createQuery("select umpd from UsermsnPreDefinida umpd where umpd.referencia = :referencia");
        q.setParameter("referencia", referencia);
        //System.out.println(q.getResultList());
        return q.getResultList();
    }
    
    public void incluir(UsermsnPreDefinida usmpd ) throws Exception {
       em.persist(usmpd);
    }
    
}
