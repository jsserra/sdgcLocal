/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.dao;


import com.pmm.sdgc.model.LotacaoSub;
import com.pmm.sdgc.model.VariaveisPossivelLotacaoSub;
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
public class VariaveisPossivelLotacaoSubDao {

    @PersistenceContext
    EntityManager em;

    @EJB
    UserLoginDao daoUserLogin;
    
    @EJB
    LotacaoDao daoLotacao;
    
    @EJB
    LotacaoSubDao daoLotacaoSub;
    
    @EJB
    FuncionalDao daoFuncional;

    public List<VariaveisPossivelLotacaoSub> getListVariaveisPossivelLotacaoSub() {
        Query q = em.createQuery("from VariaveisPossivelLotacaoSub");
        return q.getResultList();
    }
    
    
    public List<VariaveisPossivelLotacaoSub> getListVariaveisPossivelLotacaoSub(String idSetor) throws Exception{
              
        if( idSetor == null){
            throw new Exception("Funcional não informada");
        }
        
        LotacaoSub setor = daoLotacaoSub.getLotacaoSubPorId(idSetor);
        
        Query q = em.createQuery("select v from VariaveisPossivelLotacaoSub v where v.ativo = 1 and v.lotacaoSub = :setor");
        q.setParameter("setor", setor);
        return q.getResultList();
        
    }
    


    

}
