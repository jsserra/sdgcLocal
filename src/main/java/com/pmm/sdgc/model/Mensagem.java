package com.pmm.sdgc.model;

import com.pmm.sdgc.converter.IntegerConverter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 *
 * @author dreges
 */
@Entity
@Table(name="usermsn")
public class Mensagem implements Serializable {
//VAR
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="id")    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="tipo")
    private String tipo;
    
    @Column(name="titulo")
    private String titulo;
    
    @Column(name="texto")
    private String texto;

    @Column(name="ativo")
    private Boolean ativo;
    
    @Column(name="datahora")
    private LocalDateTime dataHora;
    
    //JOIN    
    @ManyToOne
    @JoinColumn(name = "userLogin", referencedColumnName = "id")
    private UserLogin userLogin;

    public Long getId() {
        return id;
    }

//get set
    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }
    
    
    


//COMPARACAO
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
        final Mensagem other = (Mensagem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Mensagem{" + "id=" + id + ", tipo=" + tipo + ", titulo=" + titulo + ", texto=" + texto + ", ativo=" + ativo + ", dataHora=" + dataHora + ", userLogin=" + userLogin + '}';
    }
    
    
}
