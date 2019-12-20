package com.pmm.sdgc.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "ocorrencia_desc")
public class OcorrenciaDesc implements Serializable {
//VAR

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private String idOcorrencia;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "diasMax")
    private Integer diasMax;

    @Column(name = "diasMim")
    private Integer diasMim;

    @Column(name = "definirDias")
    private Integer definirDias;

    @Column(name = "definirRelatorio")
    private Integer definirRelatorio;
    
    @Column(name = "definirPeriodo")
    private Integer definirPeriodo;
    
    @Column(name = "datahora")
    private LocalDateTime dataHr;

    @Column(name = "ativo")
    private Integer ativo;

    @Column(name = "controle")
    private String controle;

    @ManyToOne
    @JoinColumn(name = "userlog", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private UserLogin userLogin;

    public Integer getDefinirPeriodo() {
        return definirPeriodo;
    }

    //GET  e SET
    public void setDefinirPeriodo(Integer definirPeriodo) {
        this.definirPeriodo = definirPeriodo;
    }

    public String getIdOcorrencia() {
        return idOcorrencia;
    }

    public void setIdOcorrencia(String idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getDiasMax() {
        return diasMax;
    }

    public void setDiasMax(Integer diasMax) {
        this.diasMax = diasMax;
    }

    public Integer getDiasMim() {
        return diasMim;
    }

    public void setDiasMim(Integer diasMim) {
        this.diasMim = diasMim;
    }

    public Integer getDefinirDias() {
        return definirDias;
    }

    public void setDefinirDias(Integer definirDias) {
        this.definirDias = definirDias;
    }

    public Integer getDefinirRelatorio() {
        return definirRelatorio;
    }

    public void setDefinirRelatorio(Integer definirRelatorio) {
        this.definirRelatorio = definirRelatorio;
    }

    public LocalDateTime getDataHr() {
        return dataHr;
    }

    public void setDataHr(LocalDateTime dataHr) {
        this.dataHr = dataHr;
    }

    public Integer getAtivo() {
        return ativo;
    }

    public void setAtivo(Integer ativo) {
        this.ativo = ativo;
    }

    public String getControle() {
        return controle;
    }

    public void setControle(String controle) {
        this.controle = controle;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    //COMPARAÇÃO
   @Override
    public int hashCode() {
        int hash = 5;
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
        final OcorrenciaDesc other = (OcorrenciaDesc) obj;
        if (!Objects.equals(this.idOcorrencia, other.idOcorrencia)) {
            return false;
        }
        return true;
    }

}
