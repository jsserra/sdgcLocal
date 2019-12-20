/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.tabela;

import com.pmm.sdgc.dao.LotacaoDao;
import com.pmm.sdgc.dao.UserLogDao;
import com.pmm.sdgc.dao.UserPermissaoAcessoDao;
import com.pmm.sdgc.model.Lotacao;
import com.pmm.sdgc.model.LotacaoSub;
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
 * @author ajuliano
 */
@Named
@Path("lotacao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LotacaoWs {
    
    @EJB
    LotacaoDao daoLot;
    
    @EJB
    UserLogDao daoUserLog;

    @EJB
    UserPermissaoAcessoDao daoUserPermissaoAcesso;

    @GET
    @Path("getListaLotacao")
    public List<Lotacao> getListaLotacao(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaLotacao", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoLot.getLotacao();
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getListaLotacaoUsuario")
    public List<Lotacao> getListaLotacaoUsuario(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaLotacaoUsuario", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoUserPermissaoAcesso.getListLotacaoUsuario(headers.getRequestHeader("chave").get(0));
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }    
    @GET
    @Path("getListaLotacaoUsuarioContabil")
    public List<Lotacao> getListaLotacaoUsuarioContabil(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaLotacaoUsuarioContabil", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoUserPermissaoAcesso.getListLotacaoUsuarioContabil(headers.getRequestHeader("chave").get(0));
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }    
    @GET
    @Path("getListaLotacaoUsuarioOcorrencia")
    public List<Lotacao> getListaLotacaoUsuarioOcorrencia(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaLotacaoUsuarioOcorrencia", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoUserPermissaoAcesso.getListLotacaoUsuarioOcorrencia(headers.getRequestHeader("chave").get(0));
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }    
    
    @GET
    @Path("getListaLotacaoSubUsuario/{idLotacaoSub}")
    public List<LotacaoSub> getListaLotacaoSubUsuario(@PathParam("idLotacaoSub") String idLotacaoSub, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaLotacaoUsuario", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoUserPermissaoAcesso.getListLotacaoSubUsuario(idLotacaoSub, headers.getRequestHeader("chave").get(0));
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @GET
    @Path("getListaLotacaoAtivo/{idFunc}")
    public List<Lotacao> getListaLotacaoAtivo(@PathParam("idFunc") String idFunc, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaLotacaoAtivo", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoLot.getLotacaoAtivo(idFunc);
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @POST
    @Path("postIncluirLotacao")
    public Response postIncluirLotacao(Lotacao userLot, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("IncluirLotacao", "Incluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoLot.incluir(userLot);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    
    @POST
    @Path("postAlterarLotacao")
    public Response postAlterarLotacao(Lotacao lotacao, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("AlterarLotacao", "Alterar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoLot.alterar(lotacao);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    
    @POST
    @Path("postRemoverLotacao")
    public Response postRemoverLotacao(Lotacao lotacao, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("RemoverLotacao", "Remover", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoLot.remover(lotacao);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    
    
}
