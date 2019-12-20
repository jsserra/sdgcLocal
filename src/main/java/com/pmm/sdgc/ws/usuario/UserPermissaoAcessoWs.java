package com.pmm.sdgc.ws.usuario;

import com.pmm.sdgc.dao.UserDataTreinamentoDao;
import com.pmm.sdgc.dao.UserLogDao;
import com.pmm.sdgc.dao.UserPermissaoAcessoDao;
import com.pmm.sdgc.model.UserDataTreinamento;
import com.pmm.sdgc.model.UserPermissaoAcesso;
import com.pmm.sdgc.ws.model.ModelUserDataTreinamentoWs;
import com.pmm.sdgc.ws.model.ModelUserPermissaoAcessoWs;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
 * @author dreges
 */
@Named
@Path("userPermissaoAcesso")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserPermissaoAcessoWs {
    
    @EJB
    UserPermissaoAcessoDao daoUserPermissapAcesso;
    @EJB
    UserLogDao daoUserLog;
    @EJB
    UserDataTreinamentoDao daoUserDataTreinamento;
    
    @POST
    @Path("postIncluirUserPermissaoAcesso")
    public Response postIncluirUserPermissaoAcesso(UserPermissaoAcesso userOp, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Incluir User Permissao Acesso", "Incluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoUserPermissapAcesso.incluir(userOp);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
 
    @POST
    @Path("postAlterarUserPermissaoAcesso")
    public Response postAlterarUserPermissaoAcesso(UserPermissaoAcesso userOp, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Alterar User Permissao Acesso", "Alterar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoUserPermissapAcesso.alterar(userOp);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    @POST
    @Path("postRemoverUserPermissaoAcesso")
    public Response postRemoverUserPermissaoAcesso(UserPermissaoAcesso userOp, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Remover User Permissao Acesso", "Remover", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoUserPermissapAcesso.remover(userOp);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    @GET
    @Path("getListaPermissaoAcesso/{idFuncional}")
    public List<UserPermissaoAcesso> getListaUserPermissaoAcesso(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("idFuncional") String idFuncional) {
        try {
            daoUserLog.criarLog("Lista Permissao Acesso", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            String chave = headers.getRequestHeader("chave").get(0);
            return daoUserPermissapAcesso.getPermissaoAcessoDeUmFuncional(idFuncional, chave);
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    @GET
    @Path("getPermissaoAcessoDirecao/{direcao}")
    public List<ModelUserPermissaoAcessoWs> getPermissaoAcessoDirecao(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("direcao") Integer direcao) {
        try {
            daoUserLog.criarLog("Lista Permissao Acesso", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            String chave = headers.getRequestHeader("chave").get(0);
            return daoUserPermissapAcesso.getPermissaoAcessoDirecao(direcao, chave);
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }  
}
