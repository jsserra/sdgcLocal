/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.Especialidade;
import com.pmm.sdgc.model.EspecialidadeTipo;
import com.pmm.sdgc.model.Funcional;
import com.pmm.sdgc.model.UserLogin;
import java.time.LocalDate;
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
public class EspecialidadeDao {

    @PersistenceContext
    EntityManager em;

    public List<Especialidade> getEspecialidade() {
        Query q = em.createQuery("select es from Especialidade es order by trim(es.id)");
        //return q.setMaxResults(10).getResultList();
        return q.getResultList();
    }

    public List<Especialidade> getEspecialidadePorIdFuncional(String idFuncional) {
        Query q = em.createQuery("select es from Especialidade es where es.funcional.id = :id");
        q.setParameter("id", idFuncional);

        return q.getResultList();
    }

    public List<EspecialidadeTipo> getEspecialidadePorId(String id) throws Exception {
        Query q = em.createQuery("select e.especialidadeTipo from Especialidade e where e.funcional.id = :id and e.ativo = true");
        q.setParameter("id", id);
        return q.getResultList();
    }



    public void incluirEspecialidade(Funcional funcional, EspecialidadeTipo especialidadeTipo, UserLogin login) {
        Especialidade especialidade = new Especialidade();
        especialidade.setAtivo(Boolean.TRUE);
        especialidade.setDatahora(LocalDateTime.now());
        especialidade.setFuncional(funcional);
        especialidade.setEspecialidadeTipo(especialidadeTipo);
        especialidade.setUserlogin(login);
        especialidade.setControle("2");
        
        em.persist(especialidade);

    }    public void alterar(Especialidade es) throws Exception {
        em.merge(es);
    }

}
