package com.pmm.sdgc.model;

import com.pmm.sdgc.converter.IntegerConverter;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author dreges
 */
@Entity
@Table(name = "regime")
public class Regime implements Serializable {
//VAR

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_regime")
    private String id;
    
    @Column(name = "nome_regime")
    private String nome;
    
    @Column(name = "controle", columnDefinition = "default 2")
    @Convert(converter = IntegerConverter.class)
    private Integer controle;

//GET SET
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getControle() {
        return controle;
    }

    public void setControle(Integer controle) {
        this.controle = controle;
    }

//COMPARACAO
    @Override
    public int hashCode() {
        int hash = 7;
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
        final Regime other = (Regime) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public void setControle(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
