package com.pmm.sdgc.ws.usuario;

import com.pmm.sdgc.dao.AppVersaoDao;
import com.pmm.sdgc.dao.UserLogDao;
import com.pmm.sdgc.model.AppVersao;
import com.pmm.sdgc.ws.model.ModelAppVersaoWs;
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
@Path("appversao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AppVersaoWs {

    @EJB
    AppVersaoDao daoAppVersao;
    @EJB
    UserLogDao daoUserLog;

    @GET
    @Path("getListaAppVersao")
    public List<ModelAppVersaoWs> getListaAppVersao(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Lista de AppVersao", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            List<AppVersao> appVersoes = daoAppVersao.getAppVersao();

            List<ModelAppVersaoWs> mAppVersao = new ArrayList();
            for (AppVersao app : appVersoes) {
                mAppVersao.add(new ModelAppVersaoWs(app.getId(), app.getNome(), app.getVersao()));
            }

            return mAppVersao;
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

    @GET
    @Path("getListaUserTemplatePorAppVersao/{id}/{nome: [^/]*?}")
    public List<ModelUserTemplateWs> getListaUserTemplatePorIdAppVersao(@PathParam("id") Integer id, @PathParam("nome") String nome, @Context HttpHeaders headers, @Context HttpServletRequest request) {
        try {
            daoUserLog.criarLog("Lista de AppVersao", "Listar", headers.getRequestHeader("chave").get(0), request.getPathInfo(), headers.getRequestHeader("appv").get(0), request.getRemoteAddr());
            if (nome.isEmpty()) {
                return ModelUserTemplateWs.toModelUserTemplateWs(daoAppVersao.getListUserTemplate(id));
            } else {
                return ModelUserTemplateWs.toModelUserTemplateWs(daoAppVersao.getListUserTemplate(id, nome));
            }
        } catch (Exception exception) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build());
        }
    }

}
