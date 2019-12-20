package com.pmm.sdgc.model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author dreges
 */
@Entity
@Table(name = "userlog")
public class UserLog implements Serializable {
//VAR
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "descricao")
    private String descricao; 
    
    @Column(name = "parametros")
    private String parametros;

    @Column(name = "iporigem")
    private String ipOrigem;
    
    @Column(name = "aplicativo")
    private String aplicativo;    
    
    @Column(name = "datahora")
    private LocalDateTime dataHora;  
    
    //Neste campo será armazenado uma string indicando qual o tipo de operacao (inclusao, exclusao,
    //alteracao, listagem ou busca
    @Column(name = "operacao")
    private String operacao;
    
    
    @ManyToOne
    @JoinColumn(name = "iduserlogin", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private UserLogin userlogin;    
    
  

    //SET GET

    public String getAplicativo() {
        return aplicativo;
    }

    public void setAplicativo(String aplicativo) {
        this.aplicativo = aplicativo;
    }
    
    public String getParametros() {
        return parametros;
    }

    public void setParametros(String parametros) {
        this.parametros = parametros;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public UserLogin getUserlogin() {
        return userlogin;
    }

    public void setUserlogin(UserLogin userlogin) {
        this.userlogin = userlogin;
    }



    public String getIpOrigem() {
        return ipOrigem;
    }

    public void setIpOrigem(String ipOrigem) {
        this.ipOrigem = ipOrigem;
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
        final UserLog other = (UserLog) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    
}
