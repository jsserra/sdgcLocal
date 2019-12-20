/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.Funcional;
import com.pmm.sdgc.model.VariaveisLotacao;
import com.pmm.sdgc.model.VariaveisPossivelFuncional;
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
public class VariaveisPossivelFuncionalDao {

    @PersistenceContext
    EntityManager em;

    @EJB
    UserLoginDao daoUserLogin;
    
    @EJB
    LotacaoDao daoLotacao;
    
    @EJB
    FuncionalDao daoFuncional;

    public List<VariaveisPossivelFuncional> getListVariaveisPossivelFuncional() {
        Query q = em.createQuery("from VariaveisPossivelFuncional");
        return q.getResultList();
    }
    
    public List<VariaveisPossivelFuncional> getListaVariaveisPossivelFuncionalPorId(String idFuncional) throws Exception{
        Funcional funcional = daoFuncional.getUmFuncionalPorIdFuncional(idFuncional);
        
        if( idFuncional == null){
            throw new Exception("Funcional não informada");
        }
        
        Query q = em.createQuery("select vf from VariaveisPossivelFuncional vf where vf.ativo = 1 and vf.funcional.id = :idFunc");
        q.setParameter("idFunc", funcional.getId());
        return q.getResultList();
        
    }
    
        public List<VariaveisPossivelFuncional> getListaVariaveisPossivelFuncionalPorIda(String idFuncional) throws Exception{
        Funcional funcional = daoFuncional.getUmFuncionalPorIdFuncional(idFuncional);
        
        if( idFuncional == null){
            throw new Exception("Funcional não informada");
        }
        
        Query q = em.createQuery("select vf from VariaveisPossivelFuncional vf where vf.ativo = 1 and vf.funcional.id = :idFunc");
        q.setParameter("idFunc", funcional.getId());
        return q.getResultList();
        
    }

    

}
