/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.LotacaoSub;
import com.pmm.sdgc.model.UserLogin;
import com.pmm.sdgc.model.VariaveisLotacaoSub;
import com.pmm.sdgc.model.VariaveisPossivelLotacaoSub;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
public class VariaveisLotacaoSubDao {

    @PersistenceContext
    EntityManager em;

    @EJB
    UserLoginDao daoUserLogin;

    @EJB
    LotacaoSubDao daoLotacaoSub;

    @EJB
    VariaveisPossivelLotacaoSubDao daoVarPossivelSetor;

    @EJB
    DataFrequenciaDao daoDataFreq;

    @EJB
    VariaveisDescDao daoVarDesc;

    public List<VariaveisLotacaoSub> getListVariaveisLotacaoSub() {
        Query q = em.createQuery("from VariaveisLotacaoSub");
        return q.getResultList();

    }

    public List<VariaveisLotacaoSub> getListaVariaveisLotacaoSubPorId(String idLotacaoSub) throws Exception {
        LotacaoSub lotacaoSub = daoLotacaoSub.getLotacaoSubPorId(idLotacaoSub);

        if (idLotacaoSub == null) {
            throw new Exception("Setor não informado");
        }

        Query q = em.createQuery("select vl from VariaveisLotacaoSub vl where vl.ativo = 1 and vl.lotacaoSub.id = :idLot");
        q.setParameter("idLot", lotacaoSub.getId());
        return q.getResultList();

    }

    public List<VariaveisLotacaoSub> getListaVariaveisSetor(String idLotacaoSub) throws Exception {
        LotacaoSub lotacaoSub = daoLotacaoSub.getLotacaoSubPorId(idLotacaoSub);

        if (idLotacaoSub == null) {
            throw new Exception("Setor não informado");
        }

        Query q = em.createQuery("select vl from VariaveisLotacaoSub vl where vl.ativo = 1 and vl.fechado = 0 and vl.lotacaoSub.id = :idLot");
        q.setParameter("idLot", lotacaoSub.getId());
        List<VariaveisLotacaoSub> varLotacaoSub = q.getResultList();
     
        List<VariaveisPossivelLotacaoSub> varPossivelSetor = daoVarPossivelSetor.getListVariaveisPossivelLotacaoSub(idLotacaoSub);

        List<VariaveisLotacaoSub> vls = new ArrayList<>();
        for (VariaveisPossivelLotacaoSub vpls : varPossivelSetor) {
            for (VariaveisLotacaoSub varLotSub : varLotacaoSub) {
                if (vpls.getVariaveisDesc().equals(varLotSub.getVariaveisDesc())) {
                    vls.add(varLotSub);
                }
            }
        }
       
        return vls;
    }
    
    public void postVariaveisFecharLotacaoSub(String idLotacaoSub, Integer idVarDesc, String login) throws Exception {

        UserLogin ul = daoUserLogin.getUserLoginPorChave(login);
        if (ul == null) {
            throw new Exception("Usuário com a chave indicada não encontrada");
        }

        List<VariaveisLotacaoSub> varSetor = getListaVariaveisLotacaoSubPorId(idLotacaoSub);

        if (varSetor == null) {
            throw new Exception("Setor não encontrado!");
        }

        List<VariaveisLotacaoSub> varSetorPeriodo = varSetor.stream().filter(x -> x.getPeriodoFolha() == daoDataFreq.getDataFrequenciaAtual().getDataFrequencia()).collect(Collectors.toList());

        List<VariaveisLotacaoSub> varSetorDesc = varSetorPeriodo.stream().filter(x -> x.getVariaveisDesc() == daoVarDesc.getVariavelDesc(idVarDesc)).collect(Collectors.toList());

        for (VariaveisLotacaoSub vls : varSetorDesc) {
            if (vls.getFechado().equals(Boolean.FALSE)) {
                vls.setFechado(Boolean.TRUE);
                em.merge(vls);
            }
        }

    }

}
