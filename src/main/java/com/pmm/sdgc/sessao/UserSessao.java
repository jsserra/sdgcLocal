/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.sessao;
import java.time.LocalDateTime;

/**
 *
 * @author acg
 */
public class UserSessao {
    private String login;
    private String nome;
    private String cpf;
    private String chave;
    private LocalDateTime dataHora;
    private Boolean trocarSenha;

    public UserSessao(String login, String nome, String cpf, LocalDateTime dataHora, String chave) {
        this.login = login;
        this.nome = nome;
        this.cpf = cpf;
        this.dataHora = dataHora;
        this.chave = chave;
    }

    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public Boolean getTrocarSenha() {
        return trocarSenha;
    }

    public void setTrocarSenha(Boolean trocarSenha) {
        this.trocarSenha = trocarSenha;
    }
    
    
    
}
