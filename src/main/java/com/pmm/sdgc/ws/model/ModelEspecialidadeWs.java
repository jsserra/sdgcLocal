    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.EspecialidadeTipo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author setinf
 */
public class ModelEspecialidadeWs {

    private String nome;
    private Integer idEspecialidade;
    private String idFuncional;
    private Boolean ativo;
    private LocalDateTime data;
    private String userLogin;

    public ModelEspecialidadeWs(String nome, Integer id, Boolean ativo) {
        this.nome = nome;
        this.idEspecialidade = id;
        this.ativo = ativo;
    }

    public ModelEspecialidadeWs(String nome, Integer id, LocalDateTime data) {
        this.nome = nome;
        this.idEspecialidade = id;
        this.data = data;
    }

    public ModelEspecialidadeWs() {
    }



    public static List<ModelEspecialidadeWs> toModelEspecialidadeWs(List<EspecialidadeTipo> especialidades) {
        List<ModelEspecialidadeWs> mew = new ArrayList();
        for (EspecialidadeTipo e : especialidades) {
            ModelEspecialidadeWs ew = new ModelEspecialidadeWs(
                    e.getNome(),
                    e.getId(),
                    false
            );
            mew.add(ew);
        }
        return mew;
    }
    
    
        public static List<ModelEspecialidadeWs> toInfoModelEspecialidadeWs(List<EspecialidadeTipo> especialidades) {
        List<ModelEspecialidadeWs> mew = new ArrayList();
        for (EspecialidadeTipo e : especialidades) {
            ModelEspecialidadeWs ew = new ModelEspecialidadeWs(
                    e.getNome(),
                    e.getId(),
                    e.getDatahora()
            );
            //ew.setUserLogin(e.getUserlog().getUserlogin().getLogin());
            mew.add(ew);
        }
        return mew;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(Integer id) {
        this.idEspecialidade = id;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getIdFuncional() {
        return idFuncional;
    }

    public void setIdFuncional(String idFuncional) {
        this.idFuncional = idFuncional;
    }


}
