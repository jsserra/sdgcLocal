package com.pmm.sdgc.ws.usuario;

import com.pmm.sdgc.dao.UserLogDao;
import com.pmm.sdgc.dao.UserMenuDao;
import com.pmm.sdgc.model.UserMenu;
import com.pmm.sdgc.ws.model.ModelCargoGeralWs;
import com.pmm.sdgc.ws.model.ModelSetorWs;
import com.pmm.sdgc.ws.model.ModelTemplatesUsuarioWs;
import com.pmm.sdgc.ws.model.ModelUserTemplateExcluirWs;
import com.pmm.sdgc.ws.model.ModelUserTemplatePermissaoAcessoWs;
import com.pmm.sdgc.ws.model.ModelUserTemplateWs;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author acg
 */
@Named
@Path("userMenu")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserMenuWs {

    @EJB
    UserMenuDao daoUserMenu;
    @EJB
    UserLogDao daoUserLog;

    @GET
    @Path("getListaUserMenuLeft")
    public List<UserMenu> getListaUserMenuLeft(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("User Menu Left", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            String chave = headers.getRequestHeader("chave").get(0);
            String chaveApp = headers.getRequestHeader("appv").get(0);
            return daoUserMenu.getUserMenuLeft(chave, chaveApp);
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getListaUserMenuRight")
    public List<UserMenu> getListaUserMenuRight(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("User Menu Right", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            String chave = headers.getRequestHeader("chave").get(0);
            String chaveApp = headers.getRequestHeader("appv").get(0);
            return daoUserMenu.getUserMenuRight(chave, chaveApp);
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getListaTemplatePermissaoAccesso/{idTemplate}")
    public List<ModelUserTemplatePermissaoAcessoWs> getListaTemplatePermissaoAccesso(@PathParam("idTemplate") Integer idTemplate, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Lista Template Permissao Acesso", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoUserMenu.getTemplatePermissaoAcesso(idTemplate);
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getListaMenuItem/{idApp}")
    public List<UserMenu> getListaMenuItem(@PathParam("idApp") Integer idApp, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Menu Item", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoUserMenu.getListaMenuItem(idApp);
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getTemplatesUsuario/{idUsuario}")
    public List<ModelTemplatesUsuarioWs> getListaTemplatePorIdUsuario(@PathParam("idUsuario") Integer idUsuario, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Template Por Id Usuario", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoUserMenu.getListaTemplatePorIdUsuario(idUsuario);
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @POST
    @Path("postAdicionarTemplatePermissaoAcesso")
    public Response postAdicionarTemplatePermissaoAcesso(ModelUserTemplatePermissaoAcessoWs valores, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Adicionar Template Permissao Acesso", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoUserMenu.adicionarTemplatePermissaoAcesso(valores);
            return Response.ok().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Path("postExcluirTemplatePermissaoAcesso")
    public Response postExcluirTemplatePermissaoAcesso(ModelUserTemplatePermissaoAcessoWs valores, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Excluir Template Permissao Acesso", "Excluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoUserMenu.excluirTemplatePermissaoAcesso(valores);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Path("postExcluirTemplateDoUsuario")
    public Response postExcluirTemplateDoUsuario(ModelUserTemplateExcluirWs template, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        //System.out.println(template);
        try {
            daoUserLog.criarLog("Excluir Template do Usuario", "Excluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoUserMenu.removerPermissoes(template.getIdUserLogin(), template.getIdlotacao(), template.getIdlotacaosub());
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Path("postIncluirTemplate")
    public Response postIncluirTemplate(@Context HttpHeaders headers, ModelUserTemplateWs template, @Context HttpServletRequest request) {
        String chaveUsuario = headers.getRequestHeader("chave").get(0);
        try {
            daoUserLog.criarLog("Incluir Template", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoUserMenu.criarTemplate(template.getIdAppVersao(), template.getNome(), chaveUsuario);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Path("postClonarTemplate")
    public Response postClonarTemplate(@Context HttpHeaders headers, ModelUserTemplateWs template, @Context HttpServletRequest request) {
        String chaveUsuario = headers.getRequestHeader("chave").get(0);
        try {
            daoUserLog.criarLog("Clonar Template", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoUserMenu.clonarTemplate(template.getIdAppVersao(), template.getId(), template.getNome(), chaveUsuario);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Path("postDesativarTemplate")
    public Response postDesativarTemplate(@Context HttpHeaders headers, ModelUserTemplateWs template, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Desativar Template", "Alterar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoUserMenu.desativarTemplate(template.getId());
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Path("postAlocarTemplateAoUsuario")
    public Response postAlocarTemplateAoUsuario(@Context HttpHeaders headers, ModelUserTemplateWs template, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Alocar Template Usuario", "Alterar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            
            List<String> idsLotacaoSub = new ArrayList();
            if (!(template.getIdSetor() == null)) {
                for (ModelSetorWs mw : template.getIdSetor()) {
                    idsLotacaoSub.add(mw.getIdSetor());
                }
            }
            
            List<String> idsCargoGeral = new ArrayList();
            if (!(template.getIdCargoGeral() == null)) {
                for (ModelCargoGeralWs mw : template.getIdCargoGeral()) {
                    idsCargoGeral.add(mw.getIdCargoGeral());
                }
            }
            
           // System.out.println(idsCargoGeral);
            
            daoUserMenu.alocarUsuarioAoTemplate(template.getId(), template.getIdUserLogin(), template.getIdSecretaria(), idsLotacaoSub, idsCargoGeral);
            return Response.ok().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

}
