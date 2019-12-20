/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.Mensagem;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author setinf
 */
public class ModelMensagemWs {

    private Long id;
    private String tipo;
    private String titulo;
    private String texto;
    private LocalDateTime data;
    private String userLogin;
    private Boolean ativo;

    public ModelMensagemWs(Long id, String tipo, String titulo, String texto, LocalDateTime data, String userLogin, Boolean ativo) {
        this.id = id;
        this.tipo = tipo;
        this.titulo = titulo;
        this.texto = texto;
        this.data = data;
        this.userLogin = userLogin;
        this.ativo = ativo;
    }

    public ModelMensagemWs() {
    }

    public static List<ModelMensagemWs> toModelMensagemWs(List<Mensagem> mensagens) {
        List<ModelMensagemWs> mm = new ArrayList();
        for (Mensagem m : mensagens) {
            ModelMensagemWs mw = new ModelMensagemWs(
                    m.getId(),
                    m.getTipo(),
                    m.getTitulo(),
                    m.getTexto(),
                    m.getDataHora(),
                    m.getUserLogin().getLogin(),
                    m.getAtivo()
            );
            mm.add(mw);
        }
        return mm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
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

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

}
