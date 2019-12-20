package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.AppVersao;
import com.pmm.sdgc.model.UserTemplate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author dreges
 */
@Stateless
public class AppVersaoDao {

    @PersistenceContext
    EntityManager em;

    public List<AppVersao> getAppVersao() {
        Query q = em.createQuery("select av from AppVersao av order by trim(av.nome)");
        return q.setMaxResults(10).getResultList();
    }
    
    public void validarApp(String appv) throws Exception {
        AppVersao app = getListaAppChave(appv);
        
        if (app ==null) throw new Exception("Aplicativo não informado");
    }

    public List<AppVersao> getListaAppVersaoMult(String nome, String versao) {
        String strSQL = "select av from AppVersao av where ";
        boolean tAnd = false;
        if (!(nome.isEmpty())) {
            strSQL += "av.nome like :nome";
            tAnd = true;
        }
        if (!(versao.isEmpty())) {
            if (tAnd == true) {
                strSQL += " and ";
            }
            strSQL += " av.versao = :versao";
        }
        Query q = em.createQuery(strSQL);

        if (!(nome.isEmpty())) {
            q.setParameter("nome", nome.toUpperCase());
        }
        if (!(versao.isEmpty())) {
            q.setParameter("versao", versao);
        }
        return q.getResultList();
    }

    public AppVersao getListaAppChave(String chave) {
        Query q = em.createQuery("select av from AppVersao av where av.chave = :chave");
        q.setParameter("chave", chave);
        List l = q.getResultList();
        if (l.isEmpty()) {
            return null;
        }
        return (AppVersao) l.get(0);
    }
    
    
    public List<UserTemplate> getListUserTemplate(Integer idAppVersao) {
        Query q = em.createQuery("select ut from UserTemplate ut where ut.ativo = true and ut.appVersao.id = :idAppVersao order by ut.nome");
        q.setParameter("idAppVersao", idAppVersao);
        return q.getResultList();
    }

    public List<UserTemplate> getListUserTemplate(Integer idAppVersao, String nomeTemplate) {
        Query q = em.createQuery("select ut from UserTemplate ut where ut.ativo = true and ut.appVersao.id = :idAppVersao and ut.nome like :nomeTemplate order by ut.nome");
        q.setParameter("idAppVersao", idAppVersao);
        q.setParameter("nomeTemplate", "%" + nomeTemplate + "%");
        return q.getResultList();
    }
}
