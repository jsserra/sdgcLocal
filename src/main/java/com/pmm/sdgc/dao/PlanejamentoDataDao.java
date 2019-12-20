package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.PlanejamentoData;
import com.pmm.sdgc.model.UserDataTreinamento;
import com.pmm.sdgc.model.UserLogin;
import java.time.LocalDate;
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
public class PlanejamentoDataDao {

    @PersistenceContext
    EntityManager em;
    
    @EJB
    UserLoginDao daoUserLogin;
    
    public List<PlanejamentoData> getListaPlanejamentoData(){
        Query q = em.createQuery("select pd.dia from PlanejamentoData pd order by pd.dia"); 
        return q.getResultList();
    }
    
    public void incluir(Integer dia, Integer idUserLogin) throws Exception {
        PlanejamentoData pd = new PlanejamentoData();
        UserLogin user = daoUserLogin.getUserLoginPorId(idUserLogin);
        pd.setDia(dia);
        pd.setUserLogin(user);
        pd.setDataHora(LocalDateTime.now());
        em.persist(pd);
    }

    public void incluirPlanejamentoData(Integer dia, String chave) {
        PlanejamentoData pjd = new PlanejamentoData();
        UserLogin user = daoUserLogin.getUserLoginPorChave(chave);
        pjd.setDia(dia);
        pjd.setUserLogin(user);
        pjd.setDataHora(LocalDateTime.now());
        em.persist(pjd);
        
    }
    
    public void limparTabela(){
        Query q = em.createQuery("DELETE FROM PlanejamentoData pjd");
        q.executeUpdate();
    }
}
