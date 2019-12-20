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
/**
 *
 * @author dreges
 */
@Entity
@Table(name = "planejamento_consolidado")
public class PlanejamentoConsolidado implements Serializable {
//VAR
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;  
    
    @Column(name = "dataHora")
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "userLogin", referencedColumnName = "id")
    private UserLogin userlogin;
    
    @ManyToOne
    @JoinColumn(name = "id_lotacao_sub", referencedColumnName = "id_lotacao_sub")
    private LotacaoSub lotacaoSub;
    
//SET GET
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

    public LotacaoSub getLotacaoSub() {
        return lotacaoSub;
    }

    public void setLotacaoSub(LotacaoSub lotacaoSub) {
        this.lotacaoSub = lotacaoSub;
    }



  
}
