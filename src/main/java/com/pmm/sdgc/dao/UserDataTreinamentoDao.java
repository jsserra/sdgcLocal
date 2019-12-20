package com.pmm.sdgc.dao;

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
public class UserDataTreinamentoDao {

    @PersistenceContext
    EntityManager em;
    
    @EJB
    FuncionalDao daoFuncional;
    
    @EJB
    UserLoginDao daoUserLogin;
    
    public List<UserDataTreinamento> getListaUserDataTreinamento(Integer id) {
        Query q = em.createQuery("select udt.data, udt.dataHora from UserDataTreinamento udt where udt.userLogin.id = :id order by udt.data").setFirstResult(0).setMaxResults(10); 
        q.setParameter("id", id);
        return q.getResultList();
    }
    
    public void incluir(Integer IdUserLogin, LocalDate data) throws Exception {
        UserDataTreinamento treinamento = new UserDataTreinamento();
        UserLogin user = daoUserLogin.getUserLoginPorId(IdUserLogin);
        treinamento.setUserLogin(user);
        treinamento.setData(data);
        treinamento.setDataHora(LocalDateTime.now());
        em.persist(treinamento);
    }
    
    public void remover(UserDataTreinamento udt) throws Exception {
        em.remove(udt);
    }
}
