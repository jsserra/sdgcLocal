/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.EspecialidadeTipo;
import com.pmm.sdgc.model.Funcional;
import java.util.ArrayList;
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
public class EspecialidadeTipoDao {
    @PersistenceContext
    EntityManager em;
    
    @EJB
    FuncionalDao daoFuncional;
    public List<EspecialidadeTipo> getEspecialidadeTipo(String id) throws Exception{
        Funcional f = daoFuncional.getUmFuncionalPorIdFuncional(id);
        //Query q=em.createQuery("select et from EspecialidadeTipo et order by trim(et.nome )");
        if (f.getCargo().getCargoGeral() == null) return new ArrayList();
        if (f.getCargo().getCargoGeral().getCargoRelacao() == null) return new ArrayList();
        Query q=em.createQuery("select ep.especialidadeTipo from EspecialidadePossivel ep where ep.cargoRelacao.id = :id");
        q.setParameter("id", f.getCargo().getCargoGeral().getCargoRelacao().getId());
        return q.getResultList();
    }

    public EspecialidadeTipo getEspecialidadeTipoPorId(Integer id){
        Query q=em.createQuery("select et from EspecialidadeTipo et where et.id = :id");
        q.setParameter("id", id);
        List<EspecialidadeTipo> especialidades =  q.getResultList();
        if (especialidades.isEmpty()) return null;
        
        return especialidades.get(0);
    }
    
   
}
