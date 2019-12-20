/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.Lotacao;
import com.pmm.sdgc.model.LotacaoSub;
import com.pmm.sdgc.model.UserLogin;
import com.pmm.sdgc.model.VariaveisDesc;
import com.pmm.sdgc.model.VariaveisLotacao;
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
public class VariaveisLotacaoDao {

    @PersistenceContext
    EntityManager em;

    @EJB
    UserLoginDao daoUserLogin;

    @EJB
    LotacaoDao daoLotacao;

    @EJB
    LotacaoSubDao daoLotacaoSub;

    @EJB
    DataFrequenciaDao daoDataFreq;

    @EJB
    VariaveisPossivelLotacaoSubDao daoVarPossivelSetor;

    @EJB
    VariaveisDescDao daoVarDesc;

    public List<VariaveisLotacao> getListVariaveisLotacao() {
        Query q = em.createQuery("from VariaveisLotacao");
        return q.getResultList();
    }

    public List<VariaveisLotacao> getListVariaveisLotacaoAberto() {
        Query q = em.createQuery("select v from VariaveisLotacao v where v.fechado = 0");
        return q.getResultList();
    }

    public List<VariaveisLotacao> getListaVariaveisLotacaoPorLotacao(String idLotacao) throws Exception {
        Lotacao lotacao = daoLotacao.getLotacaoPorId(idLotacao);

        if (idLotacao == null) {
            throw new Exception("Lotação não localizada");
        }

        Query q = em.createQuery("select vl from VariaveisLotacao vl where vl.ativo = 1 and vl.lotacao.id = :idLot");
        q.setParameter("idLot", lotacao.getId());
        return q.getResultList();

    }

    public List<VariaveisLotacao> getListaVariaveisSecretaria(String idLotacao) throws Exception {

        Lotacao lotacao = daoLotacao.getLotacaoPorId(idLotacao);

        if (lotacao == null) {
            throw new Exception("Secretaria não encontrada!");
        }

        List<LotacaoSub> lotacaoSub = daoLotacaoSub.getSetores(lotacao.getId());
        List<VariaveisDesc> vd = new ArrayList<>();
        for (LotacaoSub ls : lotacaoSub) {
            for (VariaveisPossivelLotacaoSub vpls : daoVarPossivelSetor.getListVariaveisPossivelLotacaoSub(ls.getId())) {
                vd.add(vpls.getVariaveisDesc());
            }

        }

        vd.retainAll(daoVarDesc.getListVariaveisDesc());

        List<VariaveisLotacao> vlaberto = getListVariaveisLotacaoAberto();

        List<VariaveisLotacao> vl = vlaberto.stream().filter(x -> x.getLotacao().equals(lotacao)).collect(Collectors.toList());

        List<VariaveisLotacao> vlPossivel = new ArrayList<>();
        for (VariaveisLotacao vLot : vl) {
            for (VariaveisDesc vDesc : vd) {
                if (vLot.getVariaveisDesc().equals(vDesc)) {
                    vlPossivel.add(vLot);
                }
            }
        }
        return vlPossivel;

    }

    public void postVariaveisFecharLotacao(String idLotacao, Integer idVarDesc, String login) throws Exception {

        UserLogin ul = daoUserLogin.getUserLoginPorChave(login);
        if (ul == null) {
            throw new Exception("Usuário com a chave indicada não encontrada");
        }

        List<VariaveisLotacao> varLotacao = getListaVariaveisLotacaoPorLotacao(idLotacao);

        if (varLotacao == null) {
            throw new Exception("Secretaria não encontrada!");
        }

        List<VariaveisLotacao> varPeriodo = varLotacao.stream().filter(x -> x.getPeriodoFolha().equals(daoDataFreq.getDataFrequenciaAtual().getDataFrequencia())).collect(Collectors.toList());

        List<VariaveisLotacao> varDesc = varPeriodo.stream().filter(x -> x.getVariaveisDesc() == daoVarDesc.getVariavelDesc(idVarDesc)).collect(Collectors.toList());

        for (VariaveisLotacao vl : varDesc) {
            if (vl.getFechado().equals(Boolean.FALSE)) {
                vl.setFechado(Boolean.TRUE);
                vl.setFechadoUserLogin(ul);
                em.merge(vl);
            }
        }

    }

}
