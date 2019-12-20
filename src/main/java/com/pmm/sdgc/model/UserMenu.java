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
 * @author dreges
 */
@Entity
@Table(name = "usermenu")
public class UserMenu implements Serializable {
//VAR
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Integer id;   
    
    @Column(name = "ordenar")
    private Integer ordenar;    
    
    @Column(name = "direcao")
    private Integer direcao;
    
    @Column(name = "icon")
    private String icon;   
    
    @Column(name = "menuN1")
    private String menuN1;    

    @Column(name = "menuN2")
    private String menuN2;   
  
    @Column(name = "menuN3")
    private String menuN3; 

    @Column(name = "menuN4")
    private String menuN4;     

    @Column(name = "link")
    private String link;     
    
    @Column(name = "pasta")
    private String pasta;
    
    @Column(name = "arquivo")
    private String arquivo;

    @Column(name = "ativo")
    private Boolean ativo;

    @Column(name = "datahora")
    private LocalDateTime dataHora;
    @ManyToOne(optional = true)
    @JoinColumn(name = "idAppversao", referencedColumnName = "id", nullable = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private AppVersao appVersao;

//SET GET

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrdenar() {
        return ordenar;
    }

    public void setOrdenar(Integer ordenar) {
        this.ordenar = ordenar;
    }

    public Integer getDirecao() {
        return direcao;
    }

    public void setDirecao(Integer direcao) {
        this.direcao = direcao;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMenuN1() {
        return menuN1;
    }

    public void setMenuN1(String menuN1) {
        this.menuN1 = menuN1;
    }

    public String getMenuN2() {
        return menuN2;
    }

    public void setMenuN2(String menuN2) {
        this.menuN2 = menuN2;
    }

    public String getMenuN3() {
        return menuN3;
    }

    public void setMenuN3(String menuN3) {
        this.menuN3 = menuN3;
    }

    public String getMenuN4() {
        return menuN4;
    }

    public void setMenuN4(String menuN4) {
        this.menuN4 = menuN4;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPasta() {
        return pasta;
    }

    public void setPasta(String pasta) {
        this.pasta = pasta;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
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

    public AppVersao getAppVersao() {
        return appVersao;
    }

    public void setAppVersao(AppVersao appVersao) {
        this.appVersao = appVersao;
    }

    @Override
    public String toString() {
        return "UserMenu{" + "id=" + id + ", ordenar=" + ordenar + ", direcao=" + direcao + ", icon=" + icon + ", menuN1=" + menuN1 + ", menuN2=" + menuN2 + ", menuN3=" + menuN3 + ", menuN4=" + menuN4 + ", link=" + link + ", pasta=" + pasta + ", arquivo=" + arquivo + ", ativo=" + ativo + ", dataHora=" + dataHora + ", appVersao=" + appVersao + '}';
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
        final UserMenu other = (UserMenu) obj;
        if(!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
