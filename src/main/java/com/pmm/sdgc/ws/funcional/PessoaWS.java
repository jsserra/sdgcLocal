/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.funcional;

import com.pmm.sdgc.dao.MarcacaoPontoDao;
import com.pmm.sdgc.dao.PessoaDao;
import com.pmm.sdgc.dao.UserLogDao;
import com.pmm.sdgc.model.MarcacaoPonto;
import com.pmm.sdgc.model.Pessoa;
import com.pmm.sdgc.model.UserLog;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
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
/*
Para acessar o caminho é: http://localhost:8080/SpvWs/rest/webservice/******
 */
@Named
@Path("pessoa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaWS {

    @EJB
    PessoaDao daoPessoa;

    @EJB
    MarcacaoPontoDao daoMarcacaoPonto;

    @EJB
    UserLogDao daoUserLog;

    @GET
    @Path("getListaPessoa")
    public List<Pessoa> getListaPessoa(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaPessoa", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoPessoa.getPessoa();
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getMarcacoesPonto/{cpf}/{mes}/{ano}")
    public List<MarcacaoPonto> getListaMarcacaoPonto(@Context HttpHeaders headers, @PathParam("cpf") String cpf, @PathParam("mes") Integer mes, @PathParam("ano") Integer ano, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaMarcacaoPonto", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoMarcacaoPonto.getMarcacoesPorCpfMes(cpf, mes, ano);
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    @GET
    @Path("getMatriculaPorCpf/{cpf}")
    public List<String> getMatriculaPorCpf(@Context HttpHeaders headers, @PathParam("cpf") String cpf, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("ListaMarcacaoPonto", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoPessoa.getMatriculas(cpf);
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getListaLog/{cpf: [^/]*?}/{dI: [^/]*?}/{dF: [^/]*?}")
    public List<UserLog> getListaLog(@Context HttpHeaders headers, @Context HttpServletRequest request, @PathParam("cpf") String cpf, @PathParam("dI") String dI, @PathParam("dF") String dF) {
        try {
            daoUserLog.criarLog("ListaLog", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoUserLog.getListaLog(cpf, dI, dF);
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
}
