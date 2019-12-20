/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws;

import com.pmm.sdgc.dao.AppVersaoDao;
import com.pmm.sdgc.model.AppVersao;
import java.util.TreeMap;
import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author acg
 */
@Provider
public class CrossOriginResourceSharingFilter implements ContainerResponseFilter {

    @EJB
    AppVersaoDao daoAppVersao;

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext response) {
        response.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
        response.getHeaders().putSingle("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE");
        response.getHeaders().putSingle("Access-Control-Allow-Headers", "Content-Type, appv, chave");

        /*
        String apiKey = requestContext.getHeaders().getFirst("appv");
        System.out.println(apiKey);

        AppVersao appV = daoAppVersao.getListaAppChave(apiKey);

        
        if (appV == null) {
            System.out.println("App nao encontrado");
            requestContext.abortWith(Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("API de App não encontrada")
                    .build());
        }
*/
    }

}
