package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.Cargo;
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
public class CargoDao {

    @PersistenceContext
    EntityManager em;

    public List<Cargo> getCargos() {
        Query q = em.createQuery("select c from Cargo c order by trim(c.nome)");
        //return q.setMaxResults(10).getResultList();
        return q.getResultList();
    }
    public List<Cargo> getListaCargoMult(String nome, Integer horaSemanal) {
        String strSQL="select c from Cargo c where ";
        boolean tAnd = false;
        if (!(nome==null)){
            strSQL+="c.nome like :nome";
            tAnd = true;
        }
        if (!(horaSemanal==null)){
            if(tAnd == true){
                strSQL+=" and ";
            }
            strSQL+=" c.horaSemanal = :horaSemanal";
        }
        Query q = em.createQuery(strSQL);
        
        if(nome!=null){
            q.setParameter("nome", "%" + nome.toUpperCase() + "%");
        }
        if(horaSemanal!=null){
            q.setParameter("horaSemanal",horaSemanal);
        }
        return q.getResultList();
    }
    
    public List<Cargo> getCargosPorId(String id) {
        Query q = em.createQuery("select c from Cargo c where c.id = :id");
        q.setParameter("id", id);
        return q.getResultList();
    }
    
    public void incluir(Cargo c) throws Exception {
        Query q = em.createQuery("select c from Cargo c where c.nome = :nome");
        q.setParameter("nome", c.getNome().toUpperCase());
        List<Cargo> l = q.getResultList();
        if (l.isEmpty()) {
            if (c.getControle() == null) {
                c.setControle(2);
            }
            c.setNome(c.getNome().toUpperCase());
            c.setId(gerarCodigo());
            em.persist(c);
        }else{
            throw new Exception("Cargo já existe");
        }
    }

    public void alterar(Cargo c) throws Exception {
        if (c.getControle() == null) {
            c.setControle(2);
        }
        c.setNome(c.getNome().toUpperCase());
        em.merge(c);
    }

    public void remover(Cargo c) throws Exception {
        Query q = em.createQuery("select c from Cargo c where c = :ca");
        q.setParameter("ca", c);

        List<Cargo> l = q.getResultList();

        if (l.isEmpty()) {
            throw new Exception("Cargo não encontrado");
        }
        Cargo car = l.get(0);
        em.remove(car);
    }

    public String gerarCodigo() {
        
        while (true) {
            UUID uuid = UUID.randomUUID();
            String myRandom = "00"+uuid.toString().toUpperCase().substring(0, 3);

            List<Cargo> cargos = getCargosPorId(myRandom);

            if (cargos.isEmpty()) {
                return myRandom;
            }
        }
        
    }
}
