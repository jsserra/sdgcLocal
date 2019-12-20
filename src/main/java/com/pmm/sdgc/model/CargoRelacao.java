package com.pmm.sdgc.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author dreges
 */
@Entity
@Table(name="cargos_relacao")
public class CargoRelacao implements Serializable {
//VAR    
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="id")
    private String id;
    @Column(name = "nome")
    private String nome;
    @Column(name="dataHora")
    private String dataHora;
    @Column(name="ativo")
    private String ativo;
    
    @ManyToOne
    @JoinColumn(name = "userlog", referencedColumnName = "id")
    private UserLogin userlogin;
    
//    @OneToMany
//    @JoinColumn(name = "id", referencedColumnName = "id_cargo_relacao")
//    private List <EspecialidadePossivel> especialidadesPossivel;
    
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

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public UserLogin getUserlogin() {
        return userlogin;
    }

    public void setUserlogin(UserLogin userlogin) {
        this.userlogin = userlogin;
    }

    
}
