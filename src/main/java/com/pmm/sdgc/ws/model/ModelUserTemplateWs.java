    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.UserTemplate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author setinf
 */
public class ModelUserTemplateWs {

    private Integer id;
    private String nome;
    private String login;
    private LocalDateTime dataHora;
    private Integer idAppVersao;
    private Integer idUserLogin;
    private List<ModelSetorWs> idSetor;
    private List<ModelCargoGeralWs> idCargoGeral;
    private String idSecretaria;

    public ModelUserTemplateWs(Integer id, String nome, String login, LocalDateTime dataHora) {
        this.nome = nome;
        this.id = id;
        this.dataHora = dataHora;
        this.login = login;
    }

    public ModelUserTemplateWs() {
    }

    public static List<ModelUserTemplateWs> toModelUserTemplateWs(List<UserTemplate> usersTemplate) {
        List<ModelUserTemplateWs> mws = new ArrayList();
        for (UserTemplate u : usersTemplate) {
            ModelUserTemplateWs ew = new ModelUserTemplateWs(
                    u.getId(),
                    u.getNome(),
                    u.getLogin().getLogin(),
                    u.getDataHora()
            );
            mws.add(ew);
        }
        return mws;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Integer getIdAppVersao() {
        return idAppVersao;
    }

    public void setIdAppVersao(Integer idAppVersao) {
        this.idAppVersao = idAppVersao;
    }

    public Integer getIdUserLogin() {
        return idUserLogin;
    }

    public void setIdUserLogin(Integer idUserLogin) {
        this.idUserLogin = idUserLogin;
    }

    public List<ModelSetorWs> getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(List<ModelSetorWs> idSetor) {
        this.idSetor = idSetor;
    }

    public List<ModelCargoGeralWs> getIdCargoGeral() {
        return idCargoGeral;
    }

    public void setIdCargoGeral(List<ModelCargoGeralWs> idCargoGeral) {
        this.idCargoGeral = idCargoGeral;
    }

    

    public String getIdSecretaria() {
        return idSecretaria;
    }

    public void setIdSecretaria(String idSecretaria) {
        this.idSecretaria = idSecretaria;
    }
    
   
}
