package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.CargoGeral;
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
public class CargoGeralDao {

    @PersistenceContext
    EntityManager em;

    public List<CargoGeral> getCargosGeral() {
        Query q = em.createQuery("select c from CargoGeral c order by trim(c.nome)");
        //return q.setMaxResults(10).getResultList();
        return q.getResultList();
    }
    
    public List<CargoGeral> getListCargosGeralUsuario(String chave) {
        Query q = em.createQuery("select distinct u.cargoGeral from UserPermissaoAcesso u where u.userlogin.chave = :c");
        q.setParameter("c", chave);
        List<CargoGeral> lista =  q.getResultList();
        
        if (lista.isEmpty()) {
            q = em.createQuery("select u from UserPermissaoAcesso u where u.cargoGeral = null and u.userlogin.chave = :c");
            q.setParameter("c", chave);
            
            lista = q.getResultList();
            
            if (!(lista.isEmpty())) {
                lista = this.getCargosGeral();
            }
        }
        
        return lista;
    }
}
