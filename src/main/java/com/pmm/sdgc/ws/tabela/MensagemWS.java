/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.tabela;

import com.pmm.sdgc.dao.MensagemDao;
import com.pmm.sdgc.dao.UserLogDao;
import com.pmm.sdgc.model.Mensagem;
import com.pmm.sdgc.model.UsermsnPreDefinida;
import com.pmm.sdgc.ws.model.ModelMensagemWs;
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
@Path("mensagemws")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MensagemWS {
    @EJB
    MensagemDao daoMensagem;
    @EJB
    UserLogDao daoUserLog;
    /**
     * Esse webservice lista as mensagens predefinidas
     *
     *
     *
     * @param headers
     * @param mensagem
     * @return Response
     * @autor dreges
     */    
    @GET
    @Path("getListaUsermsnPreDefinidaTipo/{tipo}")
    public List<UsermsnPreDefinida> getListaUsermsnPreDefinidaTipo(@PathParam("tipo") String tipo, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaLotacaoAtivo", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoMensagem.getUsermsnPreDefinidaTipo(tipo);
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    /**
     * Esse webservice lista as mensagens ativas
     *
     *
     *
     * @param headers
     * @param mensagem
     * @return Response
     * @autor Raphael
     */
    @GET
    @Path("getListaMensagem")
    public List<ModelMensagemWs> getListaMensagem(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaMensagem", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            List<Mensagem> m = daoMensagem.getListaMensagem();
            return ModelMensagemWs.toModelMensagemWs(m);
        } catch (Exception ex) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build());
        }
    }
    /**
     * Esse webservice altera uma mensagem
     *
     *
     *
     * @param headers
     * @param mensagem
     * @return Response
     * @autor Raphael
     */
    @POST
    @Path("postAlterarMensagem")
    public Response postAlterarMensagem(@Context HttpHeaders headers, ModelMensagemWs mensagem, @Context HttpServletRequest request) {

        try {
            daoUserLog.criarLog("Alterar", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoMensagem.alterarMensagem(mensagem, headers.getRequestHeader("chave").get(0));
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    /**
     * Esse webservice altera uma mensagem
     *
     *
     *
     * @param headers
     * @param mensagem
     * @return Response
     * @autor Raphael
     */
    @POST
    @Path("PostInsereUsermsnPreDefinida")
    public Response PostInsereUsermsnPreDefinida(@Context HttpHeaders headers, ModelMensagemWs usermsnPreDefinida, @Context HttpServletRequest request) {

        try {
            daoUserLog.criarLog("Alterar", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoMensagem.postUsermsnPreDefinida(usermsnPreDefinida.getTipo(), headers.getRequestHeader("chave").get(0));
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    /**
     * Esse webservice altera status da menssagem
     *
     *
     *
     * @param headers
     * @param mensagem
     * @return Response
     * @autor Reges
     */
    @POST
    @Path("postStatusMensagem")
    public Response postStatusMensagem(@Context HttpHeaders headers, ModelMensagemWs mensagem, @Context HttpServletRequest request) {

        try {
            daoUserLog.criarLog("Alterar", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoMensagem.statusMensagem(mensagem, headers.getRequestHeader("chave").get(0));
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    /**
     * Esse webservice remove a menssagem
     *
     *
     *
     * @param headers
     * @param mensagem
     * @return Response
     * @autor Reges
     */
    @POST
    @Path("postRemoverMensagem")
    public Response postRemoverMensagem(@Context HttpHeaders headers, ModelMensagemWs mensagem, @Context HttpServletRequest request) {
        try {
            daoMensagem.removerMensagem(mensagem, headers.getRequestHeader("chave").get(0));
            daoUserLog.criarLog("Remover Messagem", "Remover", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    /**
     * Esse webservice inclui uma mensagem
     *
     *
     *
     * @param headers
     * @param mensagem
     * @return Response
     * @autor Raphael
     */
    @POST
    @Path("postIncluirMensagem")
    public Response postIncluirMensagem(@Context HttpHeaders headers, ModelMensagemWs mensagens, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("IncluirMensagem", "Incluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            daoMensagem.incluirMensagem(headers.getRequestHeader("chave").get(0), mensagens.getTipo(), mensagens.getTitulo(), mensagens.getTexto());
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }

    }
    
}
