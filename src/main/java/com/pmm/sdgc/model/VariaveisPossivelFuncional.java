/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
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
@Table(name = "variaveisPossivelHistFunc")
public class VariaveisPossivelFuncional implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_hist_func", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Funcional funcional;

    @ManyToOne
    @JoinColumn(name = "id_variaveis_desc", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private VariaveisDesc variaveisDesc;

    @Column(name = "dataHora")
    private LocalDateTime dataHora;

    @Column(name = "ativo")
    private Boolean ativo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Funcional getFuncional() {
        return funcional;
    }

    public void setFuncional(Funcional funcional) {
        this.funcional = funcional;
    }

    public VariaveisDesc getVariaveisDesc() {
        return variaveisDesc;
    }

    public void setVariaveisDesc(VariaveisDesc variaveisDesc) {
        this.variaveisDesc = variaveisDesc;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
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
        final VariaveisPossivelFuncional other = (VariaveisPossivelFuncional) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

   
}
