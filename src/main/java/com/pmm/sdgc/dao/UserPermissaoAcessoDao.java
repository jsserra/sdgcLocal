package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.CargoGeral;
import com.pmm.sdgc.model.Lotacao;
import com.pmm.sdgc.model.LotacaoSub;
import com.pmm.sdgc.model.Solicitacao;
import com.pmm.sdgc.model.SolicitacaoSub;
import com.pmm.sdgc.model.UserLogin;
import com.pmm.sdgc.model.UserPermissaoAcesso;
import com.pmm.sdgc.model.UsuarioAcesso;
import com.pmm.sdgc.ws.model.ModelPermissaoAcessoWs;
import com.pmm.sdgc.ws.model.ModelUserPermissaoAcessoWs;
import java.util.ArrayList;
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
public class UserPermissaoAcessoDao {

    @PersistenceContext
    EntityManager em;

    @EJB
    FuncionalDao daoFuncional;

    @EJB
    UserLoginDao daoUserLogin;

    @EJB
    SolicitacaoDao daoSolicitacao;
    @EJB
    SolicitacaoSubDao daoSolicitacaoSub;

    public void incluir(UserPermissaoAcesso upa) throws Exception {
        em.persist(upa);
    }

    public void alterar(UserPermissaoAcesso upa) throws Exception {
        em.merge(upa);
    }

    public void remover(UserPermissaoAcesso upa) throws Exception {
        em.remove(upa);
    }

    public List<UserPermissaoAcesso> getPermissaoAcessoDeUmFuncional(String id, String chave) throws Exception {

        Query q = em.createQuery("select u from UserPermissaoAcesso u where u.userlogin.chave = :chave and u.userMenu.ativo = true and u.userMenu.direcao = 3 and ( (u.lotacao is null and u.lotacaoSub is null) or (u.lotacao.id in (select so.lotacao.id from Solicitacao so where so.ativo = 1 and so.funcional.id =:idfunc)) or (u.lotacaoSub.id in (select ss.lotacaoSub.id from SolicitacaoSub ss where ss.funcional.id = :idfunc and ss.ativo = true))) order by u.userMenu.ordenar");
        q.setParameter("idfunc", id);
        q.setParameter("chave", chave);

        return q.getResultList();

    }
    
    public List<ModelUserPermissaoAcessoWs> getPermissaoAcessoDirecao(Integer direcao, String chave) throws Exception {
        String e = "select u from UserPermissaoAcesso u where u.userlogin.chave = :pChave and u.userMenu.ativo = true and u.userMenu.direcao = :pDirecao"; 
        Query q = em.createQuery(e);
        q.setParameter("pDirecao", direcao);
        q.setParameter("pChave", chave);
        return ModelUserPermissaoAcessoWs.toModelUserPermissaoAcessoWs(q.getResultList());
    }

    public ModelPermissaoAcessoWs getPermissaoAcessoDeUmFuncionalDoUsuario(String id, String chave) throws Exception {
        // Funcional func = daoFuncional.getUmFuncionalPorIdFuncional(id);
        UserLogin usuario = daoUserLogin.getUserLoginPorChave(chave);

        Query q = em.createQuery("select p from UserPermissaoAcesso p where p.userlogin.id = :idUsuario");
        q.setParameter("idUsuario", usuario.getId());
        List<UserPermissaoAcesso> upas = q.getResultList();

        ModelPermissaoAcessoWs mpaw = new ModelPermissaoAcessoWs();
        mpaw.setAlterar(Boolean.FALSE);
        mpaw.setBuscar(Boolean.FALSE);
        mpaw.setExcluir(Boolean.FALSE);
        mpaw.setIncluir(Boolean.FALSE);
        mpaw.setListar(Boolean.FALSE);
        boolean secretaria = false;

        for (UserPermissaoAcesso upa : upas) {
            if (upa.getLotacao() == null && upa.getLotacaoSub() == null) {
                //System.out.println("Administrador");
                mpaw.setAlterar(upa.getAlterar());
                mpaw.setBuscar(upa.getBuscar());
                mpaw.setExcluir(upa.getExcluir());
                mpaw.setIncluir(upa.getIncluir());
                mpaw.setListar(upa.getListar());
                return mpaw;
            }

            if (!(upa.getLotacao() == null)) {
                List<Solicitacao> solicitacoes = daoSolicitacao.getSolicitacaoAtiva(id);

                for (Solicitacao s : solicitacoes) {
                    if (s.getLotacao().getId().equals(upa.getLotacao().getId())) {
                        //System.out.println("Secretaria");
                        secretaria = true;
                        mpaw.setAlterar(upa.getAlterar());
                        mpaw.setBuscar(upa.getBuscar());
                        mpaw.setExcluir(upa.getExcluir());
                        mpaw.setIncluir(upa.getIncluir());
                        mpaw.setListar(upa.getListar());

                    }
                }
            } else {
                if (secretaria == false) {
                    List<SolicitacaoSub> solicitacoesSub = daoSolicitacaoSub.getSolicitacaoSubPorIdAtivo(id);

                    for (SolicitacaoSub ss : solicitacoesSub) {
                        if (ss.getLotacaoSub().getId().equals(upa.getLotacaoSub().getId())) {
                            //System.out.println("Setor");
                            mpaw.setAlterar(upa.getAlterar());
                            mpaw.setBuscar(upa.getBuscar());
                            mpaw.setExcluir(upa.getExcluir());
                            mpaw.setIncluir(upa.getIncluir());
                            mpaw.setListar(upa.getListar());
                        }
                    }

                }
            }
        }

        return mpaw;
    }

    public Boolean getUsuarioPodeCriarUsuario(String chave) throws Exception {

        Query q = em.createQuery("select u from UserPermissaoAcesso u where u.userlogin.chave = :chave and u.userMenu.pasta = :pasta and u.userMenu.arquivo = :arquivo and u.userMenu.ativo = true");
        q.setParameter("chave", chave);
        q.setParameter("pasta", "usuario");
        q.setParameter("arquivo", "acesso");

        List<UserPermissaoAcesso> acessos = q.getResultList();

        if (acessos.isEmpty()) {
            return false;
        }

        return true;
    }

    public UsuarioAcesso getAcessosUmUsuario(String chave, String menuN1, String link) {
        Query q = em.createQuery("select u from UserPermissaoAcesso u where u.userlogin.chave = :chave and u.userMenu.menuN1 = :mn1 and u.userMenu.link = :lnk");
        q.setParameter("chave", chave);
        q.setParameter("mn1", menuN1);
        q.setParameter("lnk", link);

        UsuarioAcesso acesso = new UsuarioAcesso();

        List<UserPermissaoAcesso> permissoes = q.getResultList();

        if (permissoes.isEmpty()) {
            acesso.setCompleto(Boolean.FALSE);
            acesso.setLotacoes(new ArrayList());
            acesso.setSetores(new ArrayList());
            return acesso;
        }

        //UserPermissaoAcesso permissao = permissoes.get(0);
        for (UserPermissaoAcesso permissao : permissoes) {

            if (permissao.getLotacao() == null && permissao.getLotacaoSub() == null) {
                acesso.setLotacoes(new ArrayList());
                acesso.setSetores(new ArrayList());
                acesso.setCompleto(Boolean.TRUE);
                return acesso;
            }
            
            if (!(permissao.getLotacaoSub()== null)) {
                acesso.getSetores().add(permissao.getLotacaoSub());
                acesso.getLotacoes().add(null);
            } else {
                acesso.getLotacoes().add(permissao.getLotacao());
                acesso.getSetores().add(null);
            }
        }

        return acesso;
    }
    
    @EJB
    LotacaoDao daoLotacao;
    public List<Lotacao> getListLotacaoUsuario(String chave) {
        Query q = em.createQuery("select distinct u.lotacao from UserPermissaoAcesso u where u.userlogin.chave = :c");
        q.setParameter("c", chave);
        List<Lotacao> lista =  q.getResultList();
        
        if (lista.isEmpty()) {
            q = em.createQuery("select u from UserPermissaoAcesso u where u.lotacao = null and u.userlogin.chave = :c");
            q.setParameter("c", chave);
            
            lista = q.getResultList();
            
            if (!(lista.isEmpty())) {
                lista = daoLotacao.getLotacao();
            }
        }
        
        return lista;
    }
    
    
    public List<Lotacao> getListLotacaoUsuarioContabil(String chave) {
        Query q = em.createQuery("select distinct u.lotacao from UserPermissaoAcesso u where u.userlogin.chave = :c and u.userMenu.id = 7");
        q.setParameter("c", chave);
        List<Lotacao> lista =  q.getResultList();
        
        if (lista.isEmpty()) {
            q = em.createQuery("select u from UserPermissaoAcesso u where u.lotacao = null and u.userlogin.chave = :c");
            q.setParameter("c", chave);
            
            lista = q.getResultList();
            
            if (!(lista.isEmpty())) {
                lista = daoLotacao.getLotacao();
            }
        }
        
        return lista;
    }
    public List<Lotacao> getListLotacaoUsuarioOcorrencia(String chave) {
        Query q = em.createQuery("select distinct u.lotacao from UserPermissaoAcesso u where u.userlogin.chave = :c and u.userMenu.id = 77");
        q.setParameter("c", chave);
        List<Lotacao> lista =  q.getResultList();
        
        if (lista.isEmpty()) {
            q = em.createQuery("select u from UserPermissaoAcesso u where u.lotacao = null and u.userlogin.chave = :c");
            q.setParameter("c", chave);
            
            lista = q.getResultList();
            
            if (!(lista.isEmpty())) {
                lista = daoLotacao.getLotacao();
            }
        }
        
        return lista;
    }
    

    public List<Lotacao> getListLotacaoUsuarioSemAdicao(String chave) {
        Query q = em.createQuery("select distinct u.lotacao from UserPermissaoAcesso u where u.userlogin.chave = :c and u.lotacaoSub is null");
        q.setParameter("c", chave);
        List<Lotacao> lista =  q.getResultList();
                
        return lista;
    }
    
    @EJB
    LotacaoSubDao daoLotacaoSub;
    public List<LotacaoSub> getListLotacaoSubUsuario(String id,String chave) {
        Query q = em.createQuery("select distinct u.lotacaoSub from UserPermissaoAcesso u where u.userlogin.chave = :c");
        q.setParameter("c", chave);

        List<LotacaoSub> lista =  q.getResultList();
        if (lista.isEmpty()) {
                lista = daoLotacaoSub.getListaLotacaoSub(id);
        }
        
        return lista;
    }

    
    public List<LotacaoSub> getListLotacaoSubUsuario(String chave) {
        Query q = em.createQuery("select distinct u.lotacaoSub from UserPermissaoAcesso u where u.userlogin.chave = :c");
        q.setParameter("c", chave);

        List<LotacaoSub> lista =  q.getResultList();
        
        return lista;
    }
    
    public List<CargoGeral> getListCargoGeralUsuario(String chave) {
        Query q = em.createQuery("select distinct u.cargoGeral from UserPermissaoAcesso u where u.userlogin.chave = :c");
        q.setParameter("c", chave);

        List<CargoGeral> lista =  q.getResultList();
        
        return lista;
    }
    

}
