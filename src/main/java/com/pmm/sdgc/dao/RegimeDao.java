package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.Regime;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author dreges
 */
@Stateless
public class RegimeDao {
    @PersistenceContext
    EntityManager em;


    public List<Regime> getRegimePorId(String id) {
        Query q = em.createQuery("select r from Regime r where r.id = :id");
        q.setParameter("id", id);
        return q.getResultList();
    }



    public String gerarCodigo() {
        
        while (true) {
            UUID uuid = UUID.randomUUID();
            String myRandom = "00"+uuid.toString().toUpperCase().substring(0, 3);

            List<Regime> regime = getRegimePorId(myRandom);

            if (regime.isEmpty()) {
                return myRandom;
            }
        }
        
    }
}
