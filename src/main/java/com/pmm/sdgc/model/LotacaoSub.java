package com.pmm.sdgc.model;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ajuliano
 */
@Entity
@Table(name="lotacao_sub")
public class LotacaoSub implements Serializable {
//VAR
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="id_lotacao_sub")
    private String id;
    
    @Column(name="nome_lotacao_sub")
    private String nome;
    
    @Column(name="ativo")
    private Boolean ativo;
    
    @Column(name="controle")
    private String controle;
    
//JOIN    
    @ManyToOne
    @JoinColumn(name = "id_lotacao", referencedColumnName = "id_lotacao")
    private Lotacao lotacao;
//GETSET
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

    public Lotacao getLotacao() {
        return lotacao;
    }

    public void setLotacao(Lotacao lotacao) {
        this.lotacao = lotacao;
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

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public String toString() {
        return "LotacaoSub{" + "id=" + id + ", nome=" + nome + ", ativo=" + ativo + ", controle=" + controle + ", lotacao=" + lotacao + '}';
    }
    
    
    
//COMPARACAO
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
        final LotacaoSub other = (LotacaoSub) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
 
}