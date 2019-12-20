/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author ajuliano
 */
@Entity
@Table(name = "biometriaCadastrada")
public class BiometriaCadastrada implements Serializable {
//VAR

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cpf", referencedColumnName = "cpf")
    @NotFound(action = NotFoundAction.IGNORE)
    private Pessoa pessoa;

    @Column(name = "datahora")
    private LocalDateTime data;

    @Column(name = "biometria")
    private Boolean biometria;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    
    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Boolean getBiometria() {
        return biometria;
    }

    public void setBiometria(Boolean biometria) {
        this.biometria = biometria;
    }

    @Override
    public String toString() {
        return "BiometriaCadastrada{" + "id=" + id + ", pessoa=" + pessoa + ", data=" + data + ", biometria=" + biometria + '}';
    }
    
    
    
}
