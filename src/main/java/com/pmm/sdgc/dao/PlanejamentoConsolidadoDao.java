/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.Funcional;
import com.pmm.sdgc.model.LotacaoSub;
import com.pmm.sdgc.model.PlanejamentoConsolidado;
import com.pmm.sdgc.model.UserLogin;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author dreges
 */
@Stateless
public class PlanejamentoConsolidadoDao {

    @PersistenceContext
    EntityManager em;

    public List<PlanejamentoConsolidado> getPlanejamentoConsolidadoPorId(String cpf) throws Exception {
        Query q = em.createQuery("select pc from PlanejamentoConsolidado pc where pc.userlogin.cpf = :cpf");
        q.setParameter("cpf", cpf);
        return q.getResultList();
    }

    @EJB
    LotacaoSubDao daoLotacaoSub;
    @EJB
    UserLoginDao daoUserLogin;
    public void incluirPlanejamentoConsolidado(String idLotacaoSub, String cpf) throws Exception {
        
        LotacaoSub l = daoLotacaoSub.getLotacaoSubPorId(idLotacaoSub);
        UserLogin u = daoUserLogin.getUserLoginPorCPf(cpf);
        PlanejamentoConsolidado planejamentoConsolidado = new PlanejamentoConsolidado();
        planejamentoConsolidado.setUserlogin(u);
        planejamentoConsolidado.setLotacaoSub(l);
        planejamentoConsolidado.setDataHora(LocalDateTime.now());
        em.persist(planejamentoConsolidado);
    }

}
