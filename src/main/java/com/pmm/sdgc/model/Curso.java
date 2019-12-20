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
 * @author dreges
 */
@Entity
@Table(name="cursos")
public class Curso implements Serializable {
//VAR
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="id_curso")
    private String id;
    @Column(name="nome_curso")
    private String nome;
    @Column(name="controle")
    private String controle;
//JOIN    
    @ManyToOne
    @JoinColumn(name = "id_curso_grau_curso", referencedColumnName = "id_curso_grau")
    private CursoGrau grau;

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

    public CursoGrau getGrau() {
        return grau;
    }

    public void setGrau(CursoGrau grau) {
        this.grau = grau;
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
        final Curso other = (Curso) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
 
}
