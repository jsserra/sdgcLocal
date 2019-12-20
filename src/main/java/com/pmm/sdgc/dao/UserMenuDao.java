package com.pmm.sdgc.dao;

import com.pmm.sdgc.model.AppVersao;
import com.pmm.sdgc.model.CargoGeral;
import com.pmm.sdgc.model.Lotacao;
import com.pmm.sdgc.model.LotacaoSub;
import com.pmm.sdgc.model.UserLogin;
import com.pmm.sdgc.model.UserMenu;
import com.pmm.sdgc.model.UserPermissaoAcesso;
import com.pmm.sdgc.model.UserTemplate;
import com.pmm.sdgc.model.UserTemplateAcesso;
import com.pmm.sdgc.model.UserTemplateAcessoPk;
import com.pmm.sdgc.sessao.CadastroAcessoSessao;
import com.pmm.sdgc.ws.model.ModelTemplatesUsuarioWs;
import com.pmm.sdgc.ws.model.ModelUserMenu;
import com.pmm.sdgc.ws.model.ModelUserTemplatePermissaoAcessoWs;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ajuliano
 */
@Stateless
public class UserMenuDao {

    @PersistenceContext
    EntityManager em;
    
    
    public List<UserMenu> getUserInativo(String chave) {
        Query q = em.createQuery("SELECT um.userMenu from UserPermissaoAcesso um where um.userlogin.chave = :chave AND um.userMenu.menuN1 = 'ServidorInativo'");
        q.setParameter("chave", chave);
        
        return q.getResultList();
    }
    

    public List<UserMenu> getUserMenuLeft(String chave, String chaveApp) {
        Query q = em.createQuery("select distinct um.userMenu from UserPermissaoAcesso um where um.userMenu.direcao = 0 and um.userMenu.appVersao.chave=:chaveApp and um.userlogin.chave = :chave order by um.userMenu.ordenar,trim(um.userMenu.menuN2),trim(um.userMenu.link)");
        q.setParameter("chave", chave);
        q.setParameter("chaveApp", chaveApp);
        return q.getResultList();
    }

    public List<UserMenu> getUserMenuRight(String chave, String chaveApp) {
        Query q = em.createQuery("select distinct um.userMenu from UserPermissaoAcesso um where um.userMenu.direcao = 1 and um.userMenu.appVersao.chave=:chaveApp and um.userlogin.chave = :chave order by um.userMenu.ordenar,trim(um.userMenu.menuN1),trim(um.userMenu.link)");
        q.setParameter("chave", chave);
        q.setParameter("chaveApp", chaveApp);
        return q.getResultList();
    }

    public List<UserMenu> getListaMenuItem(Integer idApp) {
        Query q = em.createQuery("select um from UserMenu um where um.appVersao.id = :id and um.pasta <> '' and um.arquivo <> '' and um.ativo = true order by um.menuN1, um.link");
        q.setParameter("id", idApp);
        
        return q.getResultList();
    }

    @EJB
    UserLoginDao daoUserLogin;

    public List<ModelTemplatesUsuarioWs> getListaTemplatePorIdUsuario(Integer idUsuario) throws Exception {
        //tem que ordenar por secretaria e por setor
        UserLogin usuario = daoUserLogin.getUserLoginPorId(idUsuario);

        Query q = em.createQuery("select u from UserPermissaoAcesso u where u.userlogin.id = :idUsuario");
        q.setParameter("idUsuario", idUsuario);
        
        List<UserPermissaoAcesso> utas = q.getResultList();
        Set<ModelTemplatesUsuarioWs> resposta = new HashSet();
        
        //Pega todos os cargos que o usuario tem acesso
        Query q2 = em.createQuery("select distinct u.cargoGeral from UserPermissaoAcesso u where u.userlogin.id = :idUsuario");
        q2.setParameter("idUsuario", idUsuario);
        List<CargoGeral> cgs = q2.getResultList();
        
        for (UserPermissaoAcesso uta : utas) {

            ModelTemplatesUsuarioWs mt = new ModelTemplatesUsuarioWs();
            
            if (!(uta.getLotacao() == null)) {
                mt.setIdSecretaria(uta.getLotacao().getId());
                mt.setNomeSecretaria(uta.getLotacao().getNome());
            }

            if (!(uta.getLotacaoSub() == null)) {
                mt.setIdSetor(uta.getLotacaoSub().getId());
                mt.setNomeSetor(uta.getLotacaoSub().getNome());
            }
            
            if (!(uta.getCargoGeral() == null)) {
                //mt.setIdCargoGeral(uta.getCargoGeral().getId());
                mt.setNomeCargoGeral("");
                for(CargoGeral cg : cgs){
                    mt.setNomeCargoGeral(mt.getNomeCargoGeral() + cg.getNome() + " || ");
                }
                mt.setNomeCargoGeral(mt.getNomeCargoGeral().substring (0, mt.getNomeCargoGeral().length() - 4));
            }

            mt.setIdTemplate(uta.getUserTemplate().getId());
            mt.setNomeTemplate(uta.getUserTemplate().getNome());
            mt.setIdUsuario(usuario.getId());
            resposta.add(mt);
        }

        List<ModelTemplatesUsuarioWs> respostaFinal = new ArrayList();

     
        respostaFinal.addAll(resposta);
        Collections.sort(respostaFinal);
        return respostaFinal;

    }

    public List<ModelUserTemplatePermissaoAcessoWs> getTemplatePermissaoAcesso(Integer idTemplate) {
        Query q = em.createQuery("select uta from UserTemplateAcesso uta where uta.id.userTemplate.id = :id order by uta.id.userMenu.menuN1, uta.id.userMenu.link");
        q.setParameter("id", idTemplate);
        List<UserTemplateAcesso> templates = q.getResultList();

        List<ModelUserTemplatePermissaoAcessoWs> templatesWs = new ArrayList();

        for (UserTemplateAcesso template : templates) {
            ModelUserTemplatePermissaoAcessoWs mu = new ModelUserTemplatePermissaoAcessoWs();
            mu.setAlterar(template.getAlterar());
            mu.setArquivo(template.getId().getUserMenu().getArquivo());
            mu.setBuscar(template.getBuscar());
            mu.setDirecao(template.getId().getUserMenu().getDirecao());
            mu.setExcluir(template.getExcluir());
            mu.setIcon(template.getId().getUserMenu().getIcon());
            mu.setIdUserMenu(template.getId().getUserMenu().getId());
            mu.setIdUserTemplate(template.getId().getUserTemplate().getId());
            mu.setIncluir(template.getIncluir());
            mu.setLink(template.getId().getUserMenu().getLink());
            mu.setListar(template.getListar());
            mu.setMenuN1(template.getId().getUserMenu().getMenuN1());
            mu.setMenuN2(template.getId().getUserMenu().getMenuN2());
            mu.setMenuN3(template.getId().getUserMenu().getMenuN3());
            mu.setMenuN4(template.getId().getUserMenu().getMenuN4());
            mu.setOrdenar(template.getId().getUserMenu().getOrdenar());
            mu.setPasta(template.getId().getUserMenu().getPasta());

            templatesWs.add(mu);
        }

        return templatesWs;

    }

    public void adicionarTemplatePermissaoAcesso(ModelUserTemplatePermissaoAcessoWs valores) throws Exception {

        System.out.println("adicionarTemplatePermissaoAcesso");
        
        Query q = em.createQuery("select t from UserTemplate t where t.id = :id");
        q.setParameter("id", valores.getIdUserTemplate());

        List<UserTemplate> uts = q.getResultList();

        if (uts.isEmpty()) {
            throw new Exception("User Template não encontrado");
        }

        UserTemplate ut = uts.get(0);

        
        
        for (ModelUserMenu mum : valores.getIdsUserMenu()) {
            Integer id = mum.getIdUserMenu();
            q = em.createQuery("select u from UserTemplateAcesso u where u.id.userMenu.id = :id and u.id.userTemplate.id = :idt");
            q.setParameter("id", id);
            q.setParameter("idt", valores.getIdUserTemplate());
            
            List<UserTemplateAcesso> lUta = q.getResultList();
            
            if (lUta.isEmpty()) {
                q = em.createQuery("select u from UserMenu u where u.id = :id");
                q.setParameter("id", id);

                List<UserMenu> menus = q.getResultList();
                
                if (menus.isEmpty()) {
                    continue;
                }
                
                UserTemplateAcesso uta = new UserTemplateAcesso();
                UserTemplateAcessoPk utapk = new UserTemplateAcessoPk();
                utapk.setUserMenu(menus.get(0));
                utapk.setUserTemplate(ut);

                uta.setId(utapk);
                uta.setAlterar(valores.getAlterar());
                uta.setBuscar(valores.getBuscar());
                uta.setExcluir(valores.getExcluir());
                uta.setIncluir(valores.getIncluir());
                uta.setListar(valores.getListar());
                uta.setDataHora(LocalDateTime.now());

                em.persist(uta);

            } else {
                UserTemplateAcesso uta = lUta.get(0);
                uta.setAlterar(valores.getAlterar());
                uta.setBuscar(valores.getBuscar());
                uta.setExcluir(valores.getExcluir());
                uta.setIncluir(valores.getIncluir());
                uta.setListar(valores.getListar());

                em.merge(uta);
            }
        }
        
        atualizarTemplatesNosUsuarios(ut);
        
    }

    public void excluirTemplatePermissaoAcesso(ModelUserTemplatePermissaoAcessoWs valores) throws Exception {
        
        System.out.println("excluirTemplatePermissaoAcesso");
        
        Query q = em.createQuery("select u from UserTemplateAcesso u where u.id.userMenu.id = :idUserMenu and u.id.userTemplate.id = :idTemplate");
        q.setParameter("idUserMenu", valores.getIdUserMenu());
        q.setParameter("idTemplate", valores.getIdUserTemplate());
        
        List<UserTemplateAcesso> utas = q.getResultList();

        if (utas.isEmpty()) {
            return;
        } else {
            //Trecho para remoção das permissões de um usuário
            Query q2 = em.createQuery("select u from UserPermissaoAcesso u where u.userTemplate.id = :idTemplate and u.userMenu.id = :idMenu");

            q2.setParameter("idTemplate", valores.getIdUserTemplate());
            
            q2.setParameter("idMenu", valores.getIdUserMenu());
            List<UserPermissaoAcesso> upas = q2.getResultList();
            
            for (UserPermissaoAcesso upa: upas) {
                
                em.remove(em.merge(upa));
            }
            
            //Trecho para remoção do user template
            UserTemplateAcesso uta = utas.get(0);
            //atualizarTemplatesNosUsuarios(uta.getId().getUserTemplate());
            utas = new ArrayList();
            em.remove(em.merge(uta));
        }

    }

    public List<CadastroAcessoSessao> getListaCadastroAcesso(String menuN1, Integer idUserLogin, Integer appVersao) {
        
        
        
        Query q = em.createQuery("select upa from UserPermissaoAcesso upa where upa.userlogin.id = :idUserLogin and upa.userMenu.appVersao.id = :appVersao and upa.userMenu.menuN1 = :menuN1 and upa.userMenu.pasta <> ''  order by upa.userMenu.link");
        q.setParameter("menuN1", menuN1);
        q.setParameter("idUserLogin", idUserLogin);
        q.setParameter("appVersao", appVersao);

        //List<UserPermissaoAcessoPk> chaves = q.getResultList();
        List<UserPermissaoAcesso> upas = q.getResultList();

        List<CadastroAcessoSessao> resposta = new ArrayList();

        String linkAtual = "";
        CadastroAcessoSessao cad = null;
        for (UserPermissaoAcesso upa : upas) {
            if (!(upa.getUserMenu().getLink().equals(linkAtual))) {

                if (!(cad == null)) {
                    resposta.add(cad);
                }
                linkAtual = upa.getUserMenu().getLink();
                cad = new CadastroAcessoSessao();
                cad.setId(upa.getUserMenu().getId());
                cad.setLink(upa.getUserMenu().getLink());
                cad.setIncluir(upa.getIncluir());
                cad.setAlterar(upa.getAlterar());
                cad.setBuscar(upa.getBuscar());
                cad.setExcluir(upa.getExcluir());
                cad.setListar(upa.getListar());
            }

        }
        resposta.add(cad);

        q = em.createQuery("select um from UserMenu um where um.pasta <> '' and um.appVersao.id = :appVersao and um.menuN1 = :menuN1 and um.id not in (select upa.id.userMenu.id from UserPermissaoAcesso upa where upa.id.userlogin.id = :idUserLogin and upa.id.userMenu.appVersao.id = :appVersao and upa.id.userMenu.pasta <> '' )  order by um.link");
        q.setParameter("idUserLogin", idUserLogin);
        q.setParameter("appVersao", appVersao);
        q.setParameter("menuN1", menuN1);

        List<UserMenu> menusFaltantes = q.getResultList();

        for (UserMenu um : menusFaltantes) {
            CadastroAcessoSessao cadA = new CadastroAcessoSessao();
            cadA.setId(um.getId());
            cadA.setLink(um.getLink());
            resposta.add(cadA);
        }

        return resposta;
    }

    public void incluir(UserMenu um) throws Exception {
        em.persist(um);
    }

    public void alterar(UserMenu um) throws Exception {
        em.merge(um);
    }

    public void remover(UserMenu um) throws Exception {
        Query q = em.createQuery("select um from UserMenu um where um = :ca");
        q.setParameter("ca", um);

        List<UserMenu> l = q.getResultList();

        if (l.isEmpty()) {
            throw new Exception("Menu não encontrada!");
        }
        UserMenu car = l.get(0);
        em.remove(car);
    }

    public List<UserTemplate> getListarUserTemplate() {
        Query q = em.createQuery("select ut from UserTemplate ut order by ut.nome");
        return q.getResultList();
    }

    public void criarTemplate(Integer idAppVersao, String nomeTemplate, String chaveUsuario) throws Exception {
        Query q = em.createQuery("select a from AppVersao a where a.id = :id");
        q.setParameter("id", idAppVersao);
        List<AppVersao> apps = q.getResultList();
        if (apps.isEmpty()) {
            throw new Exception("App não encontrado");
        }

        AppVersao app = apps.get(0);

        q = em.createQuery("select u from UserLogin u where u.chave = :chave");
        q.setParameter("chave", chaveUsuario);

        List<UserLogin> usuarios = q.getResultList();
        if (usuarios.isEmpty()) {
            throw new Exception("Usuário não encontrado");
        }

        UserLogin usuario = usuarios.get(0);

        UserTemplate ut = new UserTemplate();
        ut.setAppVersao(app);
        ut.setAtivo(Boolean.TRUE);
        ut.setDataHora(LocalDateTime.now());
        ut.setNome(nomeTemplate);
        ut.setLogin(usuario);

        em.persist(ut);

    }

    public void desativarTemplate(Integer idTemplate) throws Exception {
        Query q = em.createQuery("select u from UserTemplate u where u.id = :id");
        q.setParameter("id", idTemplate);

        List<UserTemplate> templates = q.getResultList();

        if (templates.isEmpty()) {
            throw new Exception("Template não encontrado");
        }

        UserTemplate template = templates.get(0);

        template.setAtivo(Boolean.FALSE);
        em.merge(template);

    }

    public void clonarTemplate(Integer idAppVersao, Integer idTemplate, String nomeTemplate, String chaveUsuario) throws Exception {
        Query q = em.createQuery("select a from AppVersao a where a.id = :id");
        q.setParameter("id", idAppVersao);
        List<AppVersao> apps = q.getResultList();
        if (apps.isEmpty()) {
            throw new Exception("App não encontrado");
        }

        AppVersao app = apps.get(0);

        q = em.createQuery("select u from UserLogin u where u.chave = :chave");
        q.setParameter("chave", chaveUsuario);

        List<UserLogin> usuarios = q.getResultList();
        if (usuarios.isEmpty()) {
            throw new Exception("Usuário não encontrado");
        }

        UserLogin usuario = usuarios.get(0);

        q = em.createQuery("select t from UserTemplate t where t.id = :idT");
        q.setParameter("idT", idTemplate);

        List<UserTemplate> usersTemplate = q.getResultList();
        if (usersTemplate.isEmpty()) {
            throw new Exception("Template não encontrado");
        }

        UserTemplate templateOrigem = usersTemplate.get(0);

        UserTemplate ut = new UserTemplate();
        ut.setAppVersao(app);
        ut.setAtivo(Boolean.TRUE);
        ut.setDataHora(LocalDateTime.now());
        ut.setNome(nomeTemplate);
        ut.setLogin(usuario);

        em.persist(ut);

        q = em.createQuery("select u from UserTemplateAcesso u where u.id.userTemplate.id = :idTemplate");
        q.setParameter("idTemplate", idTemplate);

        List<UserTemplateAcesso> usersTemplates = q.getResultList();

        for (UserTemplateAcesso utp : usersTemplates) {
            UserTemplateAcesso nutp = new UserTemplateAcesso();
            UserTemplateAcessoPk nutpk = new UserTemplateAcessoPk();
            nutpk.setUserMenu(utp.getId().getUserMenu());
            nutpk.setUserTemplate(ut);
            nutp.setId(nutpk);
            nutp.setAlterar(utp.getAlterar());
            nutp.setBuscar(utp.getBuscar());
            nutp.setDataHora(LocalDateTime.now());
            nutp.setExcluir(utp.getExcluir());
            nutp.setIncluir(utp.getIncluir());
            nutp.setListar(utp.getListar());

            em.persist(nutp);
        }

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void atualizarTemplatesNosUsuarios(UserTemplate template) throws Exception {
        
        Query q = em.createQuery("select up from UserPermissaoAcesso up where up.userTemplate.id = :id");
        q.setParameter("id", template.getId());
        List<UserPermissaoAcesso> lUpa = q.getResultList();

        //pega todas as lotacoes que o usuario tem acesso
        Query q2 = em.createQuery("select distinct up.lotacao from UserPermissaoAcesso up where up.userTemplate.id = :id");
        q2.setParameter("id", template.getId());
        List<Lotacao> lLotacoes = q2.getResultList();
        
        Query q3 = em.createQuery("select distinct up.userlogin from UserPermissaoAcesso up where up.userTemplate.id = :id");
        q3.setParameter("id", template.getId());
        List<UserLogin> lusl = q3.getResultList();
        
        Set<String> idLotsSub = new HashSet();
        Set<String> idsCargoGeral = new HashSet();
        
        Integer idUserLogin = null;
        String idLotacao = null;
        
        for (UserPermissaoAcesso up : lUpa) {
            
            String idLot;
            
            if(up.getLotacao() == null){
                removerPermissoes(up.getUserlogin().getId(), "null", "null");
                idLot = "null";
            }else{
                if (up.getLotacaoSub() == null) {
                    removerPermissoes(up.getUserlogin().getId(), up.getLotacao().getId(), "null");
                } else {
                    removerPermissoes(up.getUserlogin().getId(), up.getLotacao().getId(), up.getLotacaoSub().getId());
                }
                idLot = up.getLotacao().getId();
            }

            if (!(up.getLotacaoSub() == null)) {
                idLotsSub.add(up.getLotacaoSub().getId());
            }
            
            if (!(up.getCargoGeral() == null)) {
                idsCargoGeral.add(up.getCargoGeral().getId());
            }
            idUserLogin = up.getUserlogin().getId();
            idLotacao = idLot;
            //alocarUsuarioAoTemplate(template.getId(), up.getUserlogin().getId(), idLot, idLotsSub, idsCargoGeral);
        }
        //para cada usuario faz o laço de inserção
        for(UserLogin usl: lusl){
            //o laço for é feito para lotacões. Pois a função alocar temlate.. só trabalha com listas de lotsub e cargoGeral
            //para secretaria, temos um templateusuario para cada secretaria.
            if(lLotacoes.isEmpty()){
//                System.out.println("template.getId()" + template.getId());
//                System.out.println("idUserLogin" + usl.getId());
//                System.out.println("idLotacao" + idLotacao);
//                System.out.println("ArrayList(idLotsSub)" + new ArrayList(idLotsSub));
//                System.out.println("template.getId()" + new ArrayList(idsCargoGeral));
                alocarUsuarioAoTemplate(template.getId(), usl.getId(), idLotacao, new ArrayList(idLotsSub), new ArrayList(idsCargoGeral));
            }else{
                for(Lotacao lt : lLotacoes){
//                    System.out.println("LOTACOES NAO É EMPTY");
//                    System.out.println("template.getId()" + template.getId());
//                    System.out.println("idUserLogin" + usl.getId());
//                    System.out.println("idLotacao" + idLotacao);
//                    System.out.println("ArrayList(idLotsSub)" + new ArrayList(idLotsSub));
//                    System.out.println("template.getId()" + new ArrayList(idsCargoGeral));
                    alocarUsuarioAoTemplate(template.getId(), usl.getId(), lt.getId(), new ArrayList(idLotsSub), new ArrayList(idsCargoGeral));
                }
            }
        }
    }

    public void alocarUsuarioAoTemplate(Integer idTemplate, Integer idUserLogin, String idLotacao, List<String> idsLotacaoSub, List<String> idsCargoGeral) throws Exception {
        

        //System.out.println("alocarUsuarioAoTemplate AQUI 1");
        Query q = em.createQuery("select u from UserLogin u where u.id = :idU");
        q.setParameter("idU", idUserLogin);

        List<UserLogin> usuarios = q.getResultList();
        if (usuarios.isEmpty()) {
            throw new Exception("Usuário não encontrado");
        }
        

        UserLogin usuario = usuarios.get(0);

        Lotacao lotacao = null;
        //System.out.println("alocarUsuarioAoTemplate AQUI 2");
        if (!(idLotacao == null || idLotacao.equals("null"))) {
            //System.out.println("alocarUsuarioAoTemplate AQUI 3");//Acesso somente a secretaria
                                           //acesso somente a um setor
                                           //aceso a varios setores
            q = em.createQuery("select l from Lotacao l where l.id = :idLotacao");
            q.setParameter("idLotacao", idLotacao);

            List<Lotacao> lotacoes = q.getResultList();
            if (lotacoes.isEmpty()) {
                //System.out.println("alocarUsuarioAoTemplate AQUI 4");
                throw new Exception("Secretaria não encontrada");
            }
            //System.out.println("alocarUsuarioAoTemplate AQUI 5");//Acesso somente a secretaria
                                           //acesos somente a um setor
                                           //aceso a varios setores
            lotacao = lotacoes.get(0);
        }
        //System.out.println("alocarUsuarioAoTemplate AQUI 6");
        q = em.createQuery("select t from UserTemplate t where t.id = :idT");
        q.setParameter("idT", idTemplate);
        //System.out.println("alocarUsuarioAoTemplate AQUI 7");
        List<UserTemplate> usersTemplate = q.getResultList();
        if (usersTemplate.isEmpty()) {
            //System.out.println("alocarUsuarioAoTemplate AQUI 8");
            throw new Exception("Template não encontrado");
        }
        //System.out.println("alocarUsuarioAoTemplate AQUI 9");
        removerPermissoes(idUserLogin, idLotacao);
        //System.out.println("alocarUsuarioAoTemplate AQUI 10");
        UserTemplate template = usersTemplate.get(0);
        //System.out.println("alocarUsuarioAoTemplate AQUI 11");
        q = em.createQuery("select u from UserTemplateAcesso u where u.id.userTemplate.id = :idTemplate");
        q.setParameter("idTemplate", idTemplate);
        
        List<UserTemplateAcesso> usersTemplates = q.getResultList();
        //System.out.println("alocarUsuarioAoTemplate AQUI 12");
        
        if (idsLotacaoSub.isEmpty()) {
            //System.out.println("alocarUsuarioAoTemplate AQUI 13");//acesso prefeitura municipal macae
                                                                  //Acesso somente a secretaria
            //se não houver cargogeral para o caso de acesso da prefeitura toda
            if(idsCargoGeral.isEmpty()){
                //System.out.println("alocarUsuarioAoTemplate AQUI 13.1");
                for (UserTemplateAcesso utp : usersTemplates) {
                    UserPermissaoAcesso upa = new UserPermissaoAcesso();
                    upa.setUserMenu(utp.getId().getUserMenu());
                    upa.setUserlogin(usuario);
                    upa.setAlterar(utp.getAlterar());
                    upa.setBuscar(utp.getBuscar());
                    upa.setDataHora(LocalDateTime.now());
                    upa.setExcluir(utp.getExcluir());
                    upa.setIncluir(utp.getIncluir());
                    upa.setListar(utp.getListar());
                    upa.setLotacao(lotacao);
                    upa.setCargoGeral(null);
                    upa.setLotacaoSub(null);
                    upa.setUserTemplate(template);

                    em.persist(upa);

                    adicionarSuperMenu(upa);
                }
            } else {
                //System.out.println("alocarUsuarioAoTemplate AQUI 14");
                for (String idCg: idsCargoGeral){
                    CargoGeral cargoGeral = null;
                    q = em.createQuery("select cg from CargoGeral cg where cg.id = :idCargoGeral");
                    q.setParameter("idCargoGeral", idCg);
                    List<CargoGeral> cargosGeral = q.getResultList();
                    if (cargosGeral.isEmpty()) {
                        throw new Exception("CargoGeral nao encontrado");
                    }
                    cargoGeral = cargosGeral.get(0);
                    for (UserTemplateAcesso utp : usersTemplates) {
                        UserPermissaoAcesso upa = new UserPermissaoAcesso();
                        upa.setUserMenu(utp.getId().getUserMenu());
                        upa.setUserlogin(usuario);
                        upa.setAlterar(utp.getAlterar());
                        upa.setBuscar(utp.getBuscar());
                        upa.setDataHora(LocalDateTime.now());
                        upa.setExcluir(utp.getExcluir());
                        upa.setIncluir(utp.getIncluir());
                        upa.setListar(utp.getListar());
                        upa.setLotacao(lotacao);
                        upa.setCargoGeral(cargoGeral);
                        upa.setLotacaoSub(null);
                        upa.setUserTemplate(template);

                        em.persist(upa);

                        adicionarSuperMenu(upa);
                    }
                } 
            }

        } else {
            //System.out.println("alocarUsuarioAoTemplate AQUI 15"); //acesos somente a um setor
                                            //aceso a varios setores
            for (String idLot : idsLotacaoSub) {
                //System.out.println("alocarUsuarioAoTemplate AQUI 16");//acesos somente a um setor
                                                 //aceso a varios setores
                LotacaoSub lotacaoSub = null;

                q = em.createQuery("select ls from LotacaoSub ls where ls.id = :idLotacaoSub");
                q.setParameter("idLotacaoSub", idLot);

                List<LotacaoSub> lotacoesSub = q.getResultList();
                if (lotacoesSub.isEmpty()) {
                    throw new Exception("Setor não encontrado");
                }

                lotacaoSub = lotacoesSub.get(0);
                //System.out.println("alocarUsuarioAoTemplate AQUI 18");//acesos somente a um setor
                                                 //aceso a varios setores
                //se não houver cargogeral para o caso de acesso da prefeitura toda
                if(idsCargoGeral.isEmpty()){
                    for (UserTemplateAcesso utp : usersTemplates) {
                        UserPermissaoAcesso upa = new UserPermissaoAcesso();
                        upa.setUserMenu(utp.getId().getUserMenu());
                        upa.setUserlogin(usuario);
                        upa.setAlterar(utp.getAlterar());
                        upa.setBuscar(utp.getBuscar());
                        upa.setDataHora(LocalDateTime.now());
                        upa.setExcluir(utp.getExcluir());
                        upa.setIncluir(utp.getIncluir());
                        upa.setListar(utp.getListar());
                        upa.setLotacao(lotacao);
                        upa.setCargoGeral(null);
                        upa.setLotacaoSub(lotacaoSub);
                        upa.setUserTemplate(template);

                        em.persist(upa);

                        adicionarSuperMenu(upa);
                    }
                } else {
                    for (String idCg: idsCargoGeral){

                        CargoGeral cargoGeral = null;
                        q = em.createQuery("select cg from CargoGeral cg where cg.id = :idCargoGeral");
                        q.setParameter("idCargoGeral", idCg);
                        List<CargoGeral> cargosGeral = q.getResultList();
                        if (cargosGeral.isEmpty()) {
                            throw new Exception("CargoGeral nao encontrado");
                        }
                        cargoGeral = cargosGeral.get(0);
                        for (UserTemplateAcesso utp : usersTemplates) {
                            UserPermissaoAcesso upa = new UserPermissaoAcesso();
                            upa.setUserMenu(utp.getId().getUserMenu());
                            upa.setUserlogin(usuario);
                            upa.setAlterar(utp.getAlterar());
                            upa.setBuscar(utp.getBuscar());
                            upa.setDataHora(LocalDateTime.now());
                            upa.setExcluir(utp.getExcluir());
                            upa.setIncluir(utp.getIncluir());
                            upa.setListar(utp.getListar());
                            upa.setLotacao(lotacao);
                            upa.setCargoGeral(cargoGeral);
                            upa.setLotacaoSub(lotacaoSub);
                            upa.setUserTemplate(template);

                            em.persist(upa);

                            adicionarSuperMenu(upa);
                        }
                    } 
                }
            }
        }
        System.out.println("Usuario: " + usuario);
        em.merge(usuario);
    }

    public void removerPermissoes(Integer idUserLogin, String idLotacao) throws Exception {
        Query q;

        if (!(idLotacao == null)) {
            q = em.createQuery("select upa from UserPermissaoAcesso upa where upa.userlogin.id = :idLogin and upa.lotacao.id = :idLotacao");
            q.setParameter("idLogin", idUserLogin);
            q.setParameter("idLotacao", idLotacao);
        } else {
            q = em.createQuery("select upa from UserPermissaoAcesso upa where upa.userlogin.id = :idLogin");
            q.setParameter("idLogin", idUserLogin);

        }
        List<UserPermissaoAcesso> upas = q.getResultList();

        for (UserPermissaoAcesso upa : upas) {
            em.remove(em.merge(upa));
        }

    }
    
    public void removerPermissoes(Integer idUserLogin, String idLotacao, String idLotacaoSub) throws Exception {
        Query q;
        if (!(idLotacaoSub.equals("null"))) {

            if (idLotacaoSub.equals("null")) {
                idLotacaoSub = null;
            }
            if (idLotacao.equals("null")) {
                idLotacao = null;
            }
            if (idLotacaoSub.equals("0")) {
                idLotacaoSub = null;
            }
            if (idLotacao.equals("0")) {
                idLotacao = null;
            }
        }

        if (!(idLotacaoSub.equals("null") || idLotacaoSub.isEmpty())) {

            q = em.createQuery("select upa from UserPermissaoAcesso upa where upa.userlogin.id = :idLogin and upa.lotacaoSub.id = :idLotacaoSub");
            q.setParameter("idLogin", idUserLogin);
            q.setParameter("idLotacaoSub", idLotacaoSub);

        } else {
            if (!(idLotacao.isEmpty() || idLotacao.equals("null"))) {

                q = em.createQuery("select upa from UserPermissaoAcesso upa where upa.userlogin.id = :idLogin and upa.lotacao.id = :idLotacao");
                q.setParameter("idLogin", idUserLogin);
                q.setParameter("idLotacao", idLotacao);
            } else {

                q = em.createQuery("select upa from UserPermissaoAcesso upa where upa.userlogin.id = :idLogin");
                q.setParameter("idLogin", idUserLogin);
            }
        }
        
        List<UserPermissaoAcesso> upas = q.getResultList();

        for (UserPermissaoAcesso upa : upas) {
            em.remove(em.merge(upa));
        }
    }

    public void adicionarSuperMenu(UserPermissaoAcesso userPermissaoAcesso) {

        //Query q = em.createQuery("select u from UserPermissaoAcesso u where u.userMenu.menuN1 = :mn1 and u.userMenu.pasta = '' and u.userlogin.id = :idUsuario");
        String strSql = "select u from UserPermissaoAcesso u where u.userMenu.menuN1 = :mn1 and u.userMenu.pasta = '' and u.userlogin.id = :idUsuario ";

        if (!(userPermissaoAcesso.getLotacao() == null)) {
            strSql += " and u.lotacao.id = :ilot";
        }
        if (!(userPermissaoAcesso.getLotacaoSub() == null)) {
            strSql += " and u.lotacaoSub.id = :ils";
        }

        //Query q = em.createQuery("select u from UserPermissaoAcesso u where u.userMenu.menuN1 = :mn1 and u.userMenu.pasta = '' and u.userlogin.id = :idUsuario ");"and u.lotacao.id = :ilot and u.lotacaoSub.id = :ils");
        Query q = em.createQuery(strSql);

        q.setParameter("mn1", userPermissaoAcesso.getUserMenu().getMenuN1());
        q.setParameter("idUsuario", userPermissaoAcesso.getUserlogin().getId());
        if (!(userPermissaoAcesso.getLotacao() == null)) {
            q.setParameter("ilot", userPermissaoAcesso.getLotacao().getId());
        }
        if (!(userPermissaoAcesso.getLotacaoSub() == null)) {
            q.setParameter("ils", userPermissaoAcesso.getLotacaoSub().getId());
        }

        List<UserMenu> menus = q.getResultList();

        if (menus.isEmpty()) {

            q = em.createQuery("select u from UserMenu u where u.menuN1 = :mn and u.pasta = ''");
            q.setParameter("mn", userPermissaoAcesso.getUserMenu().getMenuN1());

            List<UserMenu> todosMenus = q.getResultList();

            if (todosMenus.isEmpty()) {
                return;
            }

            UserPermissaoAcesso u = new UserPermissaoAcesso();
            u.setDataHora(LocalDateTime.now());
            u.setAlterar(Boolean.TRUE);
            u.setBuscar(Boolean.TRUE);
            u.setExcluir(Boolean.TRUE);
            u.setIncluir(Boolean.TRUE);
            u.setListar(Boolean.TRUE);
            u.setLotacao(userPermissaoAcesso.getLotacao());
            u.setLotacaoSub(userPermissaoAcesso.getLotacaoSub());
            u.setUserMenu(todosMenus.get(0));
            u.setUserTemplate(userPermissaoAcesso.getUserTemplate());
            u.setUserlogin(userPermissaoAcesso.getUserlogin());

            em.persist(u);
        }

    }

}
