/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.OcorrenciaDesc;
//import com.pmm.sdgc.model.UserLogin;
import java.util.List;
//import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ajuliano
 */
@Stateless
public class OcorrenciaDescDao {

    @PersistenceContext
    EntityManager em;



    public List<OcorrenciaDesc> getOcorrenciaDescAtivo() {
        Query q = em.createQuery("select od from OcorrenciaDesc od where od.ativo = 1 order by trim(od.nome)");
        //return q.setMaxResults(10).getResultList();
        return q.getResultList();
    }
    
        public OcorrenciaDesc getOcorrenciaDescPorId(String id) {
        Query q = em.createQuery("select od from OcorrenciaDesc od where od.ativo = 1 and od.idOcorrencia = :id");
        q.setParameter("id", id);
        
        List<OcorrenciaDesc> ocorrencias = q.getResultList();
        
        if (ocorrencias.isEmpty()){
            return null;
        }
        
        return ocorrencias.get(0);
    }

}
