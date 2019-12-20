/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.EscalaTipo;
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
public class EscalaTipoDao {

    @PersistenceContext
    EntityManager em;

    public List<EscalaTipo> getEscalaTipo(String id) {
        //Query q = em.createQuery("select et from EscalaTipo et order by trim(et.id)");
        Query q = em.createQuery("SELECT ep.escalaTipo FROM EscalaPossivel ep WHERE ep.funcional.id = :id");
        q.setParameter("id", id);
        //return q.setMaxResults(10).getResultList();
        return q.getResultList();
    }



    public EscalaTipo getEscalaTipoPorIdEscala(String id) throws Exception {
        Query q = em.createQuery("select et from EscalaTipo et where et.id = :id");
        q.setParameter("id", id);
        List<EscalaTipo> escalas = q.getResultList();
        if (escalas.isEmpty()) {
            return null;
        }
        return escalas.get(0);
    }


}
