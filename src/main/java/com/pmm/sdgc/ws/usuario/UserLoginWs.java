/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.usuario;

import com.pmm.sdgc.dao.AppVersaoDao;
import com.pmm.sdgc.dao.UserLogDao;
import com.pmm.sdgc.dao.UserLoginDao;
import com.pmm.sdgc.model.UserLog;
import com.pmm.sdgc.model.UserLogin;
import com.pmm.sdgc.sessao.UserSessao;
import com.pmm.sdgc.ws.model.ModelUserLoginWs;
import java.util.ArrayList;
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
@Named
@Path("userloginws")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserLoginWs {

    @EJB
    UserLoginDao userLoginDao;
    @EJB
    UserLogDao daoUserLog;
    @EJB
    AppVersaoDao daoAppVersao;

    @GET
    @Path("getListaUserLogin")
    public List<ModelUserLoginWs> getListaUserLogin(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Lista de User Login", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            List<UserLogin> usuarios =  userLoginDao.getLogins();
            List<ModelUserLoginWs> musuarios = new ArrayList();
            
            for (UserLogin u: usuarios) {
                ModelUserLoginWs mu = new ModelUserLoginWs();
                mu.setCpf(u.getCpf());
                mu.setId(u.getId());
                mu.setLogin(u.getLogin());
                mu.setNomeCompleto(u.getNome());
                mu.setStatus(u.getStatus().toString());
                
                musuarios.add(mu);
            }
            
            return musuarios;
            
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    @GET
    @Path("getListaUserLoginMult/{login: [^/]*?}/{cpf: [^/]*?}/{nome: [^/]*?}")
    public List<UserLogin> getListaUserLoginMult(@PathParam("login") String login, @PathParam("cpf") String cpf, @PathParam("nome") String nome, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Lista de User Login Mult", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return userLoginDao.getListaUserLoginMult(login, cpf, nome);
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getListaUserLogin/{login: [^/]*?}/{senha: [^/]*?}")
    public UserSessao getListaUserLogin(@Context HttpHeaders headers, @PathParam("login") String login, @PathParam("senha") String senha, @Context HttpServletRequest request) {

        try {
            UserSessao us =  userLoginDao.getListaUserLogin(login, senha);
            daoUserLog.criarLog("Autenticação", "Listar", us.getChave(), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return us;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }
    
    @GET
    @Path("getDataHoraUltimoLog/{idUsuario}")
    public List<UserLog> getDataHoraUltimoLog(@PathParam("idUsuario") Integer idUsuario, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Mostrar data hora ultimo login", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            return daoUserLog.getDataHoraUltimoLog(idUsuario);
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }


    @POST
    @Path("postAlterarStatusUsuario")
    public Response postAlterarStatusUsuario(ModelUserLoginWs usuario, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Alterar Status Usuario", "Alterar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            userLoginDao.trocarStatusUsuario(usuario.getIdUserLogin(), usuario.getStatus());
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }    

    @POST
    @Path("postAlterarSenhaUsuario")
    public Response postAlterarSenhaUsuario(ModelUserLoginWs usuario, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Alterar Senha Usuario", "Alterar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            userLoginDao.alterarSenha(usuario.getIdUserLogin(), usuario.getNovaSenha());
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }    

    @POST
    @Path("postAlterarSenha")
    public Response postAlterarSenha(@Context HttpHeaders headers, @Context HttpServletRequest request, ModelUserLoginWs usuario) {
        String chave = headers.getRequestHeader("chave").get(0);
        try {
            daoUserLog.criarLog("Alterar Senha", "Alterar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            userLoginDao.alterarSenhaUsuario(chave, usuario.getNovaSenha());
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }    
    
    @POST
    @Path("postIncluirUsuario")
    public Response postIncluirUsuario(ModelUserLoginWs usuario, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Incluir Usuario", "Incluir", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            userLoginDao.criarUsuario(usuario.getLogin(), usuario.getNomeCompleto(), usuario.getCpf());
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }    
}
