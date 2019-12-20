/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.PortariaRelacao;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author setinf
 */
public class ModelPortariaWsMat {
    
    private String matricula;
    private String nome;
    private List<ModelPortariaWs> portarias;

    public ModelPortariaWsMat(String matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
    }
    
    public static ModelPortariaWsMat toModelPortariaWsMat(List<PortariaRelacao> portarias, String matricula, String nome){
        
        ModelPortariaWsMat mpwm = new ModelPortariaWsMat(matricula, nome);
        
        List<ModelPortariaWs> mpw = new ArrayList();
        for (PortariaRelacao pr : portarias){
            ModelPortariaWs pw = new ModelPortariaWs(
                    pr.getPortaria().getNome(),
                    pr.getPortaria().getDataPublicacao(),
                    pr.getPortariaTpa().getNome(),
                    pr.getPortariaTransmissao().getNome(),
                    pr.getPortaria().getArquivoNome()
            );
            mpw.add(pw);
            mpwm.setPortarias(mpw);
        }
        return mpwm;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ModelPortariaWs> getPortarias() {
        return portarias;
    }

    public void setPortarias(List<ModelPortariaWs> portarias) {
        this.portarias = portarias;
    }
    
    
    
}
