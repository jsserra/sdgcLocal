/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.model;

import java.io.Serializable;
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
@Table(name = "requerimento_funcional")
public class RequerimentoFuncional implements Serializable {
//VAR

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    @JoinColumn(name = "id_requerimento", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Requerimento requerimento;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    @JoinColumn(name = "id_hist_func", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Funcional funcional;

    public Requerimento getRequerimento() {
        return requerimento;
    }

    public void setRequerimento(Requerimento requerimento) {
        this.requerimento = requerimento;
    }

    public Funcional getFuncional() {
        return funcional;
    }

    public void setFuncional(Funcional funcionaç) {
        this.funcional = funcionaç;
    }

    
    
    
}
