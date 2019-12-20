package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.Funcional;
import com.pmm.sdgc.model.Lotacao;
import com.pmm.sdgc.model.LotacaoSub;
import com.pmm.sdgc.model.SolicitacaoSub;
import com.pmm.sdgc.model.UserLogin;
import java.time.LocalDateTime;
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
public class LotacaoSubDao {

    @PersistenceContext
    EntityManager em;

    public List<LotacaoSub> getListaLotacaoSub(String id) {
        Query q = em.createQuery("select lo from LotacaoSub lo where lo.lotacao.id = :id and lo.ativo = true order by trim(lo.nome)");
        q.setParameter("id", id);
        return q.getResultList();
    }

    public LotacaoSub getLotacaoSubPorId(String id) {
        Query q = em.createQuery("select ls from LotacaoSub ls where ls.id = :id");
        q.setParameter("id", id);

        List<LotacaoSub> lotacoesSub = q.getResultList();
        if (lotacoesSub.isEmpty()) {
            return null;
        }

        return lotacoesSub.get(0);
    }

    public List<LotacaoSub> getSetores(String id) {
        Query q = em.createQuery("select ls from LotacaoSub ls where ls.lotacao.id = :id and ls.ativo = true order by trim(ls.nome)");
        q.setParameter("id", id);
        return q.getResultList();
    }

    public List<LotacaoSub> getLotacaoSub(Funcional funcional) {
        Query q = em.createQuery("select so.lotacao from Solicitacao so where so.ativo = 1 and so.funcional.id =:idfunc");
        q.setParameter("idfunc", funcional.getId());
        List<Lotacao> lotacoes = q.getResultList();

        if (lotacoes.isEmpty()) {
            return new ArrayList();
        }

        q = em.createQuery("select ls from LotacaoSub ls where ls.ativo = true and ls.lotacao in :list order by ls.nome");
        q.setParameter("list", lotacoes);
        return q.getResultList();
    }

    public void incluirLotacaoSub(Funcional funcional, LotacaoSub lotacaoSub, UserLogin login) {
        SolicitacaoSub solicitacaoSub = new SolicitacaoSub();
        solicitacaoSub.setAtivo(Boolean.TRUE);
        solicitacaoSub.setControle("2");
        solicitacaoSub.setData(LocalDateTime.now());
        solicitacaoSub.setFuncional(funcional);
        solicitacaoSub.setLotacaoSub(lotacaoSub);
        solicitacaoSub.setUserlogin(login);

        em.persist(solicitacaoSub);

    }

    public List<Funcional> getListaFuncionalLotacao(Funcional funcional, Lotacao lotacao) {
        Query q = em.createQuery("select distinct lo.funcional from Solicitacao lo where lo.lotacao.id = :id and lo.ativo = true and lo.funcional.id =:f");
        q.setParameter("id", lotacao.getId());
        q.setParameter("f", funcional.getId());
        return q.getResultList();
    }

    public List<Funcional> getListaFuncionalLotacaoSub(Funcional funcional, LotacaoSub lotacaoSub) {
        Query q = em.createQuery("select distinct lo.funcional from SolicitacaoSub lo where lo.lotacaoSub.id = :id and lo.ativo = true and lo.funcional.id =:f");
        q.setParameter("id", lotacaoSub.getId());
        q.setParameter("f", funcional.getId());
        return q.getResultList();
    }
}
