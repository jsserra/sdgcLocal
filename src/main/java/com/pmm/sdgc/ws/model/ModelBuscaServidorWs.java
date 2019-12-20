/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.Funcional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author setinf
 */
public class ModelBuscaServidorWs {

    private String matricula;
    private String nome;
    private String cpf;
    private LocalDate dataAdmissao;
    private String cargo;

    public ModelBuscaServidorWs(String matricula, String nome, String cpf, LocalDate dataAdmissao, String cargo) {
        this.cpf = cpf;
        this.nome = nome;
        this.matricula = matricula;
        this.dataAdmissao = dataAdmissao;
        this.cargo = cargo;
    }

    public static List<ModelBuscaServidorWs> toModelBuscaServidorWs(List<Funcional> funcionais) {
        List<ModelBuscaServidorWs> mew = new ArrayList();
        for (Funcional f : funcionais) {
            ModelBuscaServidorWs ew = new ModelBuscaServidorWs(
                    f.getMatricula(),
                    f.getPessoa().getNome(),
                    f.getPessoa().getCpf(),
                    f.getDataAdmissao(),
                    f.getCargo().getNome());
            mew.add(ew);
        }
        return mew;
    }

    public static List<ModelBuscaServidorWs> toModelBuscaServidorWs(Set<Funcional> funcionais) {
        List<ModelBuscaServidorWs> mew = new ArrayList();
        for (Funcional f : funcionais) {
            ModelBuscaServidorWs ew = new ModelBuscaServidorWs(
                    f.getMatricula(),
                    f.getPessoa().getNome(),
                    f.getPessoa().getCpf(),
                    f.getDataAdmissao(),
                    f.getCargo().getNome());
            mew.add(ew);
        }
        return mew;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

}
