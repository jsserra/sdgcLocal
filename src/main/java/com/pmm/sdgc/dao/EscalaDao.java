/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.Escala;
import com.pmm.sdgc.model.EscalaTipo;
import com.pmm.sdgc.model.Funcional;
import com.pmm.sdgc.model.UserLogin;
import java.time.LocalDateTime;
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
public class EscalaDao {

    @PersistenceContext
    EntityManager em;



    public List<EscalaTipo> getEscalaPorId(String id) {
        Query q = em.createQuery("select e.escalaTipo from Escala e where e.funcional.id = :id and e.ativo = true");
        q.setParameter("id", id);
        return q.getResultList();
    }
    public void incluirEscala(Funcional funcional, EscalaTipo escalaTipo, UserLogin userLogin) {
           Escala escala = new Escala();
           escala.setDataHora(LocalDateTime.now());
           escala.setEscalaTipo(escalaTipo);
           escala.setFuncional(funcional);
           //escala.setId(Integer.SIZE);
           escala.setAtivo(Boolean.TRUE);
           escala.setUserLogin(userLogin);
           em.persist(escala);
    }
    
    public void alterar(Escala e) throws Exception {
        em.merge(e);
    }
       
       
       
       public List<Escala> getEscalaPorIdFuncional(String idFuncional) {
        Query q = em.createQuery("select e from Escala e where e.funcional.id = :id");
        q.setParameter("id", idFuncional);

        return q.getResultList();
    }    
   
  
}
