/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.EscalaIncompativel;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jrdutra
 */
@Stateless
public class EscalaIncompativelDao {
    @PersistenceContext
    EntityManager em;
    
    public Boolean getComparaEscalaIncompativelPorIdEscalaTipo(String idEt1, String idEt2){
        Query q = em.createQuery("SELECT e FROM EscalaIncompativel e where (e.escalaTipo1.id = :idEt1 AND e.escalaTipo2.id = :idEt2) OR  (e.escalaTipo1.id = :idEt2 AND e.escalaTipo2.id = :idEt1)");
        q.setParameter("idEt1", idEt1);
        q.setParameter("idEt2", idEt2);
        
        return q.getResultList().isEmpty();
    }
    
}
