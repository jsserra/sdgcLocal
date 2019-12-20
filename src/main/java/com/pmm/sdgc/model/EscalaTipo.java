/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ajuliano
 */
@Entity
@Table(name = "escala_tipo")
public class EscalaTipo implements Serializable {
//VAR
        private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "nome")
    private String nomeEscalaTipo;

    @Column(name = "acao")
    private String acaoRegTipo;

    @Column(name = "controle")
    private String controle;

//GET SET

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeEscalaTipo() {
        return nomeEscalaTipo;
    }

    public void setNomeEscalaTipo(String nomeEscalaTipo) {
        this.nomeEscalaTipo = nomeEscalaTipo;
    }

    public String getAcaoRegTipo() {
        return acaoRegTipo;
    }

    public void setAcaoRegTipo(String acaoRegTipo) {
        this.acaoRegTipo = acaoRegTipo;
    }

    public String getControle() {
        return controle;
    }

    public void setControle(String controle) {
        this.controle = controle;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EscalaTipo other = (EscalaTipo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EscalaTipo{" + "id=" + id + ", nomeEscalaTipo=" + nomeEscalaTipo + ", acaoRegTipo=" + acaoRegTipo + ", controle=" + controle + '}';
    }
    
    

  }

