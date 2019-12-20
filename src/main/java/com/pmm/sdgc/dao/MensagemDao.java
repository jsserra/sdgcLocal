package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.Mensagem;
import com.pmm.sdgc.model.UserLogin;
import com.pmm.sdgc.model.UsermsnPreDefinida;
import com.pmm.sdgc.ws.model.ModelMensagemWs;
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
public class MensagemDao {

    @EJB
    UserLoginDao daoUserLogin;
    
    @PersistenceContext
    EntityManager em;

    public List<Mensagem> getListaMensagem() {
        Query q = em.createQuery("select m from Mensagem m order by m.dataHora desc");
        //return q.setMaxResults(10).getResultList();
        return q.getResultList();
    }

    public void alterarMensagem(ModelMensagemWs mensagemWs, String chaveUsuario) throws Exception {

        //Busca o usuário que está realizando essa operação
        UserLogin ul = daoUserLogin.getUserLoginPorChave(chaveUsuario);

        if (ul == null) {
            throw new Exception("Usuário com essa chave não encontrado");
        }

        Query q = em.createQuery("select m from Mensagem m where m.id = :id");
        q.setParameter("id", mensagemWs.getId());

        List<Mensagem> mensagens = q.getResultList();

        if (mensagens.isEmpty()) {
            throw new Exception("Mensagem não encontrada");
        }

        Mensagem mensagem = mensagens.get(0);
        
        mensagem.setTexto(mensagemWs.getTexto());
        mensagem.setTitulo(mensagemWs.getTitulo());
        mensagem.setTipo(mensagemWs.getTipo());
        mensagem.setUserLogin(ul);
        mensagem.setDataHora(LocalDateTime.now());
        
        em.merge(mensagem);
        
    }
    public void statusMensagem(ModelMensagemWs mensagemWs, String chaveUsuario) throws Exception {

        //Busca o usuário que está realizando essa operação
        UserLogin ul = daoUserLogin.getUserLoginPorChave(chaveUsuario);

        if (ul == null) {
            throw new Exception("Usuário com essa chave não encontrado");
        }

        Query q = em.createQuery("select m from Mensagem m where m.id = :id");
        q.setParameter("id", mensagemWs.getId());

        List<Mensagem> mensagens = q.getResultList();

        if (mensagens.isEmpty()) {
            throw new Exception("Mensagem não encontrada");
        }

        Mensagem mensagem = mensagens.get(0);
        
        mensagem.setAtivo(mensagemWs.getAtivo());
        mensagem.setUserLogin(ul);
        mensagem.setDataHora(LocalDateTime.now());
        
        em.merge(mensagem);
        
    }

    public void removerMensagem(ModelMensagemWs mensagemWs, String chaveUsuario) throws Exception {
        Query q = em.createQuery("select m from Mensagem m where m.id = :id");
        q.setParameter("id", mensagemWs.getId());
        List<Mensagem> mensagens = q.getResultList();

        if (mensagens.isEmpty()) {
            throw new Exception("Mensagem não encontrada");
        }
        Mensagem mensagem = mensagens.get(0);
        em.remove(mensagem);
    }

    public void incluirMensagem(String chaveUsuario, String tipo, String titulo, String texto) throws Exception {
        
       //Busca o usuário que está realizando essa operação
        UserLogin ul = daoUserLogin.getUserLoginPorChave(chaveUsuario);

        if (ul == null) {
            throw new Exception("Usuário com essa chave não encontrado");
        }
        
        Mensagem mensagem = new Mensagem();
        mensagem.setAtivo(Boolean.FALSE);
        mensagem.setDataHora(LocalDateTime.now());
        mensagem.setTipo(tipo);
        mensagem.setTitulo(titulo);
        mensagem.setTexto(texto);
        mensagem.setUserLogin(ul);
        em.persist(mensagem);
    }
    
    public void incluirMensagem(Mensagem msn) throws Exception {
        Mensagem m = msn;
        em.persist(m);
    }

    public List<UsermsnPreDefinida> getUsermsnPreDefinidaTipo(String tipo) {
        Query q = em.createQuery("select umpd from UsermsnPreDefinida umpd where umpd.tipo = :tipo");
        q.setParameter("tipo", tipo);
        return q.getResultList();
    }
    
     public void postUsermsnPreDefinida(String tipo, String chaveUsuario) throws Exception {
        UserLogin ul = daoUserLogin.getUserLoginPorChave(chaveUsuario);
        if (ul == null) {
            throw new Exception("Usuário com essa chave não encontrado");
        }
        Query q = em.createQuery("select umpd from UsermsnPreDefinida umpd where umpd.tipo = :tipo");
        q.setParameter("tipo", tipo);
        List<ModelMensagemWs> mensagens = ModelMensagemWs.toModelMensagemWs(q.getResultList());
        ModelMensagemWs modelMensagemWs = mensagens.get(0);
        modelMensagemWs.getTexto();
        modelMensagemWs.setAtivo(Boolean.TRUE);
        //modelMensagemWs.setUserLogin(ul);
        modelMensagemWs.setData(LocalDateTime.now());
        em.merge(modelMensagemWs);
    }

}
