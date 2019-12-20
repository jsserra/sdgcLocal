/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.BiometriaCadastrada;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ajuliano
 */
@Stateless
public class BiometriaCadastradaDao {
    @PersistenceContext
    EntityManager em;
    
    @EJB
    FuncionalDao daoFuncional;
    
    public BiometriaCadastrada getBiometriaCadastrada(String cpf) {
   
        Query q=em.createQuery("select bc from BiometriaCadastrada bc where bc.pessoa.cpf = :cpf");
        q.setParameter("cpf", cpf);
        
        BiometriaCadastrada b = new BiometriaCadastrada();
        
        b.setBiometria(Boolean.FALSE);
        
        List l = q.getResultList();
        
        if(!(l.isEmpty())){
            b = (BiometriaCadastrada) l.get(0);
        }

        return b;
    }
}
