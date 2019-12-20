/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.Escala;
//import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author setinf
 */
public class ModelEscalaWsMat {

    private String nome;
    private String matricula;
   private List<ModelEscalaWs> escalas;

    public ModelEscalaWsMat(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
        }

    public static ModelEscalaWsMat toModelEscalaWs(List<Escala> escalas, String matricula, String nome) {
        ModelEscalaWsMat mewm = new ModelEscalaWsMat(nome, matricula);
        
        List<ModelEscalaWs> mew = new ArrayList();
        for (Escala r : escalas) {
            ModelEscalaWs rw = new ModelEscalaWs(
                    r.getEscalaTipo().getNomeEscalaTipo(),
                    r.getEscalaTipo().getId(),
                    r.getDataHora()
            );
            rw.setUserLogin(r.getUserLogin().getLogin());
            mew.add(rw);
            mewm.setEscalas(mew);
        }
        return mewm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<ModelEscalaWs> getEscalas() {
        return escalas;
    }

    public void setEscalas(List<ModelEscalaWs> escalas) {
        this.escalas = escalas;
    }

 

  

}
