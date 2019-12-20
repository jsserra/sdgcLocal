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
@Table(name = "lotacao")
public class Lotacao implements Serializable{
    
    //VAR
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_lotacao")
    private String id;

    @Column(name = "nome_lotacao")
    private String nome;
    
    @Column(name = "qtd_max_servidores")
    private Integer max;
    
    @Column(name = "qtd_atual_servidores")
    private Integer atual;

    @Column(name = "ativo")
    private Boolean ativo;
    
    @Column(name = "controle")
    private String controle;
    
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

   public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getAtual() {
        return atual;
    }

    public void setAtual(Integer atual) {
        this.atual = atual;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getControle() {
        return controle;
    }

    public void setControle(String controle) {
        this.controle = controle;
    }

    //COMPARACAO
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public String toString() {
        return "Lotacao{" + "id=" + id + ", nome=" + nome + ", max=" + max + ", atual=" + atual + ", ativo=" + ativo + ", controle=" + controle + '}';
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
        final Lotacao other = (Lotacao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
