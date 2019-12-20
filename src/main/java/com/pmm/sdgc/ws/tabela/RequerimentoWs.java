/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.tabela;


import com.pmm.sdgc.dao.RequerimentoDao;
import com.pmm.sdgc.dao.RequerimentoInfoDao;
import com.pmm.sdgc.dao.RequerimentoSolicitacaoDao;
import com.pmm.sdgc.dao.RequerimentoStatusDao;
import com.pmm.sdgc.dao.UserLogDao;
import com.pmm.sdgc.dao.UserPermissaoAcessoDao;
import com.pmm.sdgc.model.Requerimento;
import com.pmm.sdgc.model.RequerimentoHistorico;
import com.pmm.sdgc.model.RequerimentoInfo;
import com.pmm.sdgc.model.RequerimentoSolicitacao;
import com.pmm.sdgc.model.RequerimentoStatus;
import com.pmm.sdgc.ws.model.ModelRequerimentoWs;
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
@Path("requerimento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RequerimentoWs {
    
    @EJB
    RequerimentoStatusDao daoReqStatus;

    @EJB
    RequerimentoSolicitacaoDao daoReqSolicitacao;
    
    @EJB
    RequerimentoDao daoReq;

    @EJB
    RequerimentoInfoDao daoReqInfo;
    
    @EJB
    UserLogDao daoUserLog;

    @EJB
    UserPermissaoAcessoDao daoUserPermissaoAcesso;

    @GET
    @Path("getListaRequerimento")
    public List<Requerimento> getListaRequerimento(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaRequerimento", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoReq.getRequerimento();
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @GET
    @Path("getListaRequerimentoPorId/{id}")
    public List<Requerimento> getListaRequerimento(@PathParam("id") Integer id, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaRequerimento", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoReq.getRequerimentoPorId(id);
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @GET
    @Path("getListaRequerimentoPorProtocolo/{protocolo}")
    public List<ModelRequerimentoWs> getListaRequerimentoProtocolo(@PathParam("protocolo") String protocolo, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaRequerimento", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            List<Requerimento> req = daoReq.getRequerimentoPorProtocolo(protocolo);
            return ModelRequerimentoWs.toModelRequerimentoWs(req);
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @GET
    @Path("getListaRequerimentoPorProtocoloStatus/{protocolo}")
    public List<ModelRequerimentoWs> getListaRequerimentoPorProtocolo(@PathParam("protocolo") String protocolo, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaRequerimento", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            List<RequerimentoHistorico> req = daoReq.getRequerimentoPorProtocoloStatus(protocolo);
            return ModelRequerimentoWs.toModelRequerimentoProtocolosWs(req);
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @GET
    @Path("getListaRequerimentoInfo")
    public List<RequerimentoInfo> getListaRequerimentoInfo(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaRequerimentoInfo", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoReqInfo.getRequerimentoInfo();
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @GET
    @Path("getListaRequerimentoStatus")
    public List<RequerimentoStatus> getListaRequerimentoStatus(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaRequerimentoStatus", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoReqStatus.getRequerimentoStatus();
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @GET
    @Path("getListaRequerimentoHistorico")
    public List<RequerimentoHistorico> getListaRequerimentoHistorico(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaRequerimentoStatus", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoReq.getRequerimentoHistorico();
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @GET
    @Path("getListaRequerimentoStatusPorId/{id}")
    public RequerimentoStatus getListaRequerimentoStatusPorId(@PathParam("id") Integer id, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaRequerimentoStatus", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoReqStatus.getRequerimentoStatusPorId(id);
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @GET
    @Path("getListaRequerimentoSolicitacao")
    public List<RequerimentoSolicitacao> getListaRequerimentoSolicitacao(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaRequerimentoSolicitacao", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoReqSolicitacao.getRequerimentoSolicitacao();
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @GET
    @Path("getListaRequerimentoSolicitacaoPorId/{id}")
    public RequerimentoSolicitacao getListaRequerimentoSolicitacaoPorId(@PathParam("id") Integer id, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaRequerimentoStatus", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoReqSolicitacao.getRequerimentoSolicitacaoPorId(id);
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    
     @GET
    @Path("getListaRequerimentoAtivoPorFuncional/{idFuncional}")
    public List<Requerimento> getListaRequerimentoAtivoPorFuncional(@PathParam("idFuncional") String idFuncional, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaRequerimentoStatus", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoReq.getRequerimentoPorFuncional(idFuncional);
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @POST
    @Path("postIncluirRequerimento/{id}/{protocolo}/{ReqStatus}/{ReqSolicitacao}")
    public Response postIncluirRequerimento(@Context HttpHeaders headers, @PathParam("id") String id, @PathParam("protocolo") String protocolo, @PathParam("ReqStatus") Integer ReqStatus, @PathParam("ReqSolicitacao") Integer ReqSolicitacao, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("IncluirRequerimento", "Incluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoReq.incluirRequerimento(id, protocolo, ReqStatus, ReqSolicitacao, headers.getRequestHeader("chave").get(0));
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    
    @POST
    @Path("postIncluirRequerimentoHistorico/{idReq}/{ReqStatus}")
    public Response postIncluirRequerimento(@Context HttpHeaders headers, @PathParam("idReq") Integer idReq, @PathParam("ReqStatus") Integer ReqStatus, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("IncluirRequerimento", "Incluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoReq.incluirRequerimentoHistorico(idReq, ReqStatus);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    
    @POST
    @Path("postIncluirRequerimentoInfo/{id}/{cep}/{estado}/{bairro}/{end}/{complemento}/{celular}/{telefone}/{email}")
    public Response postIncluirRequerimentoInfo(@Context HttpHeaders headers, 
            @PathParam("id") String id,
            @PathParam("cep") String cep,
            @PathParam("estado") Integer estado,
            @PathParam("bairro") Integer bairro,
            @PathParam("end") String end,
            @PathParam("complemento") String complemento,
            @PathParam("celular") Integer celular,
            @PathParam("telefone") Integer telefone,
            @PathParam("email") String email,           
            @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("IncluirRequerimentoInfo", "Incluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoReqInfo.incluirRequerimentoInfo(id, cep, estado, bairro, end, complemento, celular, telefone, email, headers.getRequestHeader("chave").get(0));
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    
    /*    @GET
    @Path("getListaLotacaoUsuario")
    public List<Lotacao> getListaLotacaoUsuario(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaLotacaoUsuario", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoUserPermissaoAcesso.getListLotacaoUsuario(headers.getRequestHeader("chave").get(0));
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
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
    */
    
}
