/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.Escala;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author setinf
 */
public class ModelEscalaWs {

    private String nome;
    private String idRegime;
    private LocalDateTime data;
    private String idFuncional;
    private String idEscala;
    private String userLogin;

    public ModelEscalaWs(String nome, String id, LocalDateTime data) {
        this.nome = nome;
        this.idRegime = id;
        this.data = data;
    }

    public ModelEscalaWs() {
    }

    public static List<ModelEscalaWs> toModelEscalaWs(List<Escala> escalas) {
        List<ModelEscalaWs> mew = new ArrayList();
        for (Escala r : escalas) {
            ModelEscalaWs rw = new ModelEscalaWs(
                    r.getEscalaTipo().getNomeEscalaTipo(),
                    r.getEscalaTipo().getId(),
                    r.getDataHora()
            );
            rw.setUserLogin(r.getUserLogin().getLogin());
            mew.add(rw);
        }
        return mew;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdRegime() {
        return idRegime;
    }

    public void setIdRegime(String idRegime) {
        this.idRegime = idRegime;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getIdFuncional() {
        return idFuncional;
    }

    public void setIdFuncional(String idFuncional) {
        this.idFuncional = idFuncional;
    }

    public String getIdEscala() {
        return idEscala;
    }

    public void setIdEscala(String idEscala) {
        this.idEscala = idEscala;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

}
