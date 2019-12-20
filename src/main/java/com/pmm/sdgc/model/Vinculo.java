package com.pmm.sdgc.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author dreges
 */
@Entity
@Table(name="vinculo")
public class Vinculo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="id_vinculo")
    private String id;
    @Column(name="nome_vinculo")
    private String nome;
    @Column(name="controle")
    private String controle;

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

    public String getControle() {
        return controle;
    }

    public void setControle(String controle) {
        this.controle = controle;
    }
}
