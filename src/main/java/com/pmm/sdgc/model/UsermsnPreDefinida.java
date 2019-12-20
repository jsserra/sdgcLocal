package com.pmm.sdgc.model;
import java.io.Serializable;
import java.time.LocalDateTime;
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
 * @author dreges
 */
@Entity
@Table(name = "usermsnPreDefinida")
public class UsermsnPreDefinida implements Serializable {
//VAR
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "referencia")
    private String referencia;
    
    @Column(name = "tipo")
    private String tipo;    
    
    @Column(name = "titulo")
    private String titulo;    
    
    @Column(name = "texto")
    private String texto;
    
    @Column(name = "dataHora")
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "userlogin", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private UserLogin userLogin;
    
//SET GET

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    @Override
    public String toString() {
        return "UsermsnPreDefinida{" + "id=" + id + ", referencia=" + referencia + ", tipo=" + tipo + ", titulo=" + titulo + ", texto=" + texto + ", userLogin=" + userLogin + '}';
    }
    
}
