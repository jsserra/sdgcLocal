package com.pmm.sdgc.dao;
import com.pmm.sdgc.model.Portaria;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class PortariaDao {

    @PersistenceContext
    EntityManager em;

    @EJB
    UserLoginDao daoUserLogin;

    @EJB
    PortariaDao daoPortaria;


    public List<Portaria> getListaPortaria(String nome, String dataPublicacao) {
        

//        Query q = em.createQuery("select p from Portaria p where p.nome like :Nnome and p.dataPublicacao = :Nld1");
//        q.setParameter("Nnome", nome);
//        q.setParameter("Nld1", ld1);
//        return q.getResultList();
        String strSql = "select p from Portaria p ";
        boolean where = false;
        
        if(!(nome.isEmpty())){
            strSql+=" where ";
            where = true;
            strSql+= "p.nome like :Nnome";
        }
        if(!(dataPublicacao.isEmpty())){
            if(where == false){
               strSql+=" where ";
            }else{
                strSql+=" and ";
            }
            strSql+= "p.dataPublicacao like :Nld1";     
        }
        //System.out.println(strSql);
        Query q = em.createQuery(strSql);
        if(!(nome.isEmpty()))q.setParameter("Nnome", nome);
        
        if(!(dataPublicacao.isEmpty())){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dataPublicacao,formatter);
            q.setParameter("Nld1", date);
             //System.out.println(date);
        }
        
       
        //System.out.println(q.getResultList());
        
        return q.getResultList();
    }
}
