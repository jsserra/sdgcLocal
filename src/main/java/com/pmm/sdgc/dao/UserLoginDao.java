package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.StatusUserLogin;
import com.pmm.sdgc.model.UserLogin;
import com.pmm.sdgc.sessao.UserSessao;
import java.time.LocalDateTime;
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
public class UserLoginDao {

    @PersistenceContext
    EntityManager em;

    public List<UserLogin> getLogins() {
        Query q = em.createQuery("select ul from UserLogin ul");
        return q.getResultList();
    }

    public List<UserLogin> getListaUserLoginMult(String login, String cpf, String nome) {
        String strSQL = "select ul from UserLogin ul where ";
        boolean tAnd = false;
        if (!(login.isEmpty())) {
            strSQL += "ul.login like :login";
            tAnd = true;
        }
        if (!(cpf.isEmpty())) {
            if (tAnd == true) {
                strSQL += " and ";
            }
            strSQL += " ul.cpf = :cpf";
        }
        if (!(nome.isEmpty())) {
            if (tAnd == true) {
                strSQL += " and ";
            }
            strSQL += " ul.nome like :nome";
        }
        Query q = em.createQuery(strSQL);

        if (!(login.isEmpty())) {
            q.setParameter("login", login);
        }
        if (!(cpf.isEmpty())) {
            q.setParameter("cpf", cpf);
        }
        if (!(nome.isEmpty())) {
            q.setParameter("nome", "%" + nome.toUpperCase().replaceAll(" ", "%") + "%");
        }
        return q.getResultList();
    }

    public void alterarUserLogin(UserLogin userLogin) {
        em.merge(userLogin);
    }

    public UserSessao getListaUserLogin(String login, String senha) {
        String passwd = UserLogin.encrypt(senha);
        Query q = em.createQuery("select upa.userlogin from UserPermissaoAcesso upa where upa.userlogin.login = :login and upa.userlogin.senha = :senha and upa.userlogin.status = :status and upa.userMenu.ativo = true");
        q.setParameter("login", login);
        q.setParameter("senha", passwd);
        q.setParameter("status", StatusUserLogin.Ativo);

        List l = q.getResultList();
        if (l.isEmpty()) {
            return null;
        }
        UserLogin ul = (UserLogin) l.get(0);
        ul.atualizarChaveAcesso();
        em.merge(ul);
        UserSessao us = new UserSessao(ul.getLogin(), ul.getNome(), ul.getCpf(), ul.getDataHora(), ul.getChave());
        
        us.setTrocarSenha(ul.getTrocaSenha());
        return us;
    }

    public UserSessao getListaUserLoginPorChave(String chave) {
        Query q = em.createQuery("select upa.id.userlogin from UserPermissaoAcesso upa where upa.id.userlogin.login = :login and upa.id.userlogin.senha = :senha and upa.id.userlogin.status = :ativo and upa.id.userMenu.ativo = true");
        q.setParameter("ativo", StatusUserLogin.Ativo);
        q.setParameter("chave", chave);
        List l = q.getResultList();
        if (l.isEmpty()) {
            return null;
        }
        UserLogin ul = (UserLogin) l.get(0);
        UserSessao us = new UserSessao(ul.getLogin(), ul.getNome(), ul.getCpf(), ul.getDataHora(), ul.getChave());
        return us;
    }

    public UserLogin getUserLoginPorChave(String chave) {
        //Query q = em.createQuery("select upa.id.userlogin from UserPermissaoAcesso upa where upa.id.userlogin.chave = :chave and upa.id.userlogin.status = 1 and upa.id.userMenu.ativo = true");
        Query q = em.createQuery("select u from UserLogin u where u.chave = :chave");
        q.setParameter("chave", chave);
        List l = q.getResultList();
        if (l.isEmpty()) {
            return null;
        }
        return (UserLogin) l.get(0);

    }

    public UserLogin getUserLoginPorCPf(String cpf) {
        Query q = em.createQuery("select u from UserLogin u where u.cpf = :cpf");
        q.setParameter("cpf", cpf);
        List l = q.getResultList();
        if (l.isEmpty()) {
            return null;
        }
        return (UserLogin) l.get(0);

    }

    public UserLogin getUserLoginPorLogin(String login) {
        Query q = em.createQuery("select u from UserLogin u where u.login = :login");
        q.setParameter("login", login);
        List l = q.getResultList();
        if (l.isEmpty()) {
            return null;
        }
        return (UserLogin) l.get(0);

    }

    public UserLogin getUserLoginPorId(Integer idUserLogin) throws Exception {
        Query q = em.createQuery("select u from UserLogin u where u.id = :idU");
        q.setParameter("idU", idUserLogin);

        List<UserLogin> usuarios = q.getResultList();
        if (usuarios.isEmpty()) {
            throw new Exception("Usuário não encontrado");
        }

        UserLogin usuario = usuarios.get(0);

        return usuario;
    }

    public void trocarStatusUsuario(Integer idUsuario, String novoStatus) throws Exception {
        UserLogin usuario = getUserLoginPorId(idUsuario);

        usuario.setStatus(StatusUserLogin.valueOf(novoStatus));
        em.merge(usuario);

    }

    public void alterarSenha(Integer idUsuario, String novaSenha) throws Exception {
        UserLogin usuario = getUserLoginPorId(idUsuario);

        if (novaSenha.isEmpty()) {
            novaSenha = usuario.getCpf();
        }

        usuario.setSenha(UserLogin.encrypt(novaSenha));
        usuario.setTrocaSenha(Boolean.TRUE);
        em.merge(usuario);

    }

    public void alterarSenhaUsuario(String chave, String novaSenha) throws Exception {
        UserLogin usuario = getUserLoginPorChave(chave);

        if (usuario==null) {
            throw new Exception("Usuário não encontrado. Verifique se está devidamente logado no sistema");
        }
        
        if (novaSenha.isEmpty()) {
            novaSenha = usuario.getCpf();
        } else {
            if (!(novaSenha.matches(".*[0-9]+.*"))) {
                throw new Exception("A senha deve conter pelo menos um número");
            }

            if (!(novaSenha.matches(".*[a-zA-Z]+.*"))) {
                throw new Exception("A senha deve conter pelo menos uma letra");
            }
            
            if (novaSenha.length()<8) {
                throw new Exception("A senha deve ter pelo menos 8 caracteres");
            }
        }

        usuario.setSenha(UserLogin.encrypt(novaSenha));
        usuario.setTrocaSenha(Boolean.FALSE);
        em.merge(usuario);

    }

    public void criarUsuario(String login, String nomeCompleto, String cpf) throws Exception {
        UserLogin usuario = getUserLoginPorLogin(login);
        if (!(usuario==null)) throw new Exception("Já existe um usuário com o login " + login);
        
        
        if (!(UserLogin.isCPF(cpf))) {
            throw new Exception("O CPF Informado é inválido");
        }
        
        
        usuario = getUserLoginPorCPf(cpf);
        if (!(usuario==null)) throw new Exception("Já existe um usuário com o cpf " + cpf);
        
        usuario = new UserLogin();
        usuario.setNome(nomeCompleto);
        usuario.setLogin(login);
        usuario.setCpf(cpf);
        usuario.setDataHora(LocalDateTime.now());
        usuario.setSenha(UserLogin.encrypt(cpf));
        usuario.setStatus(StatusUserLogin.Inativo);
        usuario.atualizarChaveAcesso();
        usuario.setTrocaSenha(Boolean.TRUE);
        em.merge(usuario);

    }
    
    
    
    
    
}
