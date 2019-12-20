package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.Pessoa;
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
public class PessoaDao {
    @PersistenceContext
    EntityManager em;
    public List<Pessoa> getPessoa() {
        Query q = em.createQuery("select p from Pessoa p order by trim(p.nome)");
        return q.getResultList();
    }
    public Pessoa getPessoaPorCpf(String cpf) {
        Query q = em.createQuery("select p from Pessoa p where p.cpf = :c");
        q.setParameter("c", cpf);
        List<Pessoa> pessoas = q.getResultList();
        
        if (pessoas.isEmpty()) return null;
        
        return pessoas.get(0);
    }

    public List<String> getMatriculas(String cpf) {
        Query q = em.createQuery("Select f.matricula from Funcional f where f.pessoa.cpf = :cpf");
        q.setParameter("cpf", cpf);
        List<String> matriculas = q.getResultList();
        if (matriculas.isEmpty()) return null;
        
        return matriculas;
    }
}
