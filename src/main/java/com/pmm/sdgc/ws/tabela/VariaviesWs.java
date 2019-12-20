/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.tabela;

import com.pmm.sdgc.dao.UserLogDao;
import com.pmm.sdgc.dao.VariaveisDao;
import com.pmm.sdgc.model.Variaveis;
import com.pmm.sdgc.ws.model.ModelVariaveisInclusaoWs;
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
 * @author acg
 */
/*
Para acessar o caminho é: http://localhost:8080/SpvWs/rest/webservice/******
 */
@Named
@Path("variaveisTemp")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VariaviesWs {

    @EJB
    UserLogDao daoUserLog;

    @EJB
    VariaveisDao daoVariaveis;
    
    
    @GET
    @Path("getListaVariaveisFuncionalPorId/{idFuncional}")
    public List<Variaveis> getListaVariaveisFuncionalPorId(@PathParam("idFuncional") String idFuncional, @Context HttpHeaders headers, @Context HttpServletRequest request) {

        try {
            daoUserLog.criarLog("Lista das Variáveis Funcional", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoVariaveis.getVariaveisListaLancamentos(idFuncional);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    @POST
    @Path("postVariaveisIncluirEmLote")
    public Response postVariaveisIncluirEmLote(@Context HttpHeaders headers, List<ModelVariaveisInclusaoWs> variaveis, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Incluir Variaveis", "Incluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            for (ModelVariaveisInclusaoWs var : variaveis) {
               daoVariaveis.postVariaveisIncluirEmLote(var.getIdFuncs(), var.getIdVariaveisDesc(), var.getIdLotacaoSub(), var.getQuantidade(), var.getValor(), headers.getRequestHeader("chave").get(0));
            }
            return Response.ok().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    @POST
    @Path("postVariaveisExcluir")
    public Response postVariaveisExcluir(@Context HttpHeaders headers, List<ModelVariaveisInclusaoWs> variaveis, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Excluir variaveis", "Excluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            for (ModelVariaveisInclusaoWs var : variaveis) {
                Variaveis v = daoVariaveis.getVariaveisPorId(var.getIdVariavel());
                String log = "Excluir variaveis ";
                log+= " Id VarDesc: " + v.getVariaveisDesc().getId() ;
                log+= " PreriodoFolha: " + v.getPeriodoFolha();
                log+= " Matricula: " + v.getFuncional().getMatricula();
                daoUserLog.criarLog(log, "Excluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
                daoVariaveis.postVariaveisExcluir(var.getIdVariavel());
            }
            return Response.ok().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
            //Teste de exclusão
        }
    }
   
}
