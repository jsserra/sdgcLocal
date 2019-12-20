/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.VariaveisPossivelFuncional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juliano
 */
public class ModelVariaveisFuncionalWs {

    private String nomePessoal;
    private String variaveisDesc;
    private LocalDateTime dataHora;

    public ModelVariaveisFuncionalWs(String nomePessoal, String variaveisDesc, LocalDateTime dataHora) {
        this.nomePessoal = nomePessoal;
        this.variaveisDesc = variaveisDesc;
        this.dataHora = dataHora;
    }

    public static List<ModelVariaveisFuncionalWs> toModelVariaviesFuncional(List<VariaveisPossivelFuncional> variaveisFunc) {
        List<ModelVariaveisFuncionalWs> mlw = new ArrayList();
        for (VariaveisPossivelFuncional v : variaveisFunc) {
            ModelVariaveisFuncionalWs lw = new ModelVariaveisFuncionalWs(
                    v.getFuncional().getPessoa().getNome(),
                    v.getVariaveisDesc().getNome(),
                    v.getDataHora()
            );
            mlw.add(lw);
        }
        return mlw;
    }

    public String getNomePessoal() {
        return nomePessoal;
    }

    public void setNomePessoal(String nomePessoal) {
        this.nomePessoal = nomePessoal;
    }

    public String getVariaveisDesc() {
        return variaveisDesc;
    }

    public void setVariaveisDesc(String variaveisDesc) {
        this.variaveisDesc = variaveisDesc;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

}
