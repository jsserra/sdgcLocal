/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author setinf
 */
public class ModelAlterarSolicitacaoSubWs {
    
    private String id;
    private String id_hist_funcional;
    
    
    public ModelAlterarSolicitacaoSubWs( String id, String id_hist_funcional){
        this.id = id;
        this.id_hist_funcional = id_hist_funcional;        
    }
    public static List<ModelAlterarSolicitacaoSubWs> ssw = new ArrayList();
    {
     ModelAlterarSolicitacaoSubWs sw = new ModelAlterarSolicitacaoSubWs(id, id_hist_funcional);
     sw.setId(id);
     sw.setId_hist_funcional(id_hist_funcional);
    
    
} 
    /*ModelAlterarSolicitacaoSubWs ssw = new ModelAlterarSolicitacaoSubWs(id, id_hist_funcional);*/
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_hist_funcional() {
        return id_hist_funcional;
    }

    public void setId_hist_funcional(String id_hist_funcional) {
        this.id_hist_funcional = id_hist_funcional;
    }
    
    
}
