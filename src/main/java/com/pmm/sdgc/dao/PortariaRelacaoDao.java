package com.pmm.sdgc.dao;
import com.pmm.sdgc.model.PortariaRelacao;
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
public class PortariaRelacaoDao {

    @PersistenceContext
    EntityManager em;

    @EJB
    UserLoginDao daoUserLogin;

    @EJB
    PortariaRelacaoDao daoPortariaRelacao;


    public List<PortariaRelacao> getListaPortariaRelacao(String id_portaria) {
        Query q = em.createQuery("select pr from PortariaRelacao pr where pr.portaria.id = :id");
        q.setParameter("id", id_portaria);
        return q.getResultList();
    }
}
