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
@Table(name = "userpermissaoacesso")
public class UserPermissaoAcesso implements Serializable {
//VAR

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "incluir")
    private Boolean incluir;

    @Column(name = "alterar")
    private Boolean alterar;

    @Column(name = "excluir")
    private Boolean excluir;

    @Column(name = "listar")
    private Boolean listar;

    @Column(name = "buscar")
    private Boolean buscar;

    @Column(name = "datahora")
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "id_lotacao", referencedColumnName = "id_lotacao")
    @NotFound(action = NotFoundAction.IGNORE)
    private Lotacao lotacao;

    @ManyToOne
    @JoinColumn(name = "id_lotacao_sub", referencedColumnName = "id_lotacao_sub")
    private LotacaoSub lotacaoSub;
    
    @ManyToOne
    @JoinColumn(name = "id_cargo_geral", referencedColumnName = "id")
    private CargoGeral cargoGeral;

    @ManyToOne
    @JoinColumn(name = "iduserlogin", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private UserLogin userlogin;

    @ManyToOne
    @JoinColumn(name = "idusermenu", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private UserMenu userMenu;

    @ManyToOne
    @JoinColumn(name = "idusertemplate", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private UserTemplate userTemplate;

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Boolean getIncluir() {
        return incluir;
    }

    public void setIncluir(Boolean incluir) {
        this.incluir = incluir;
    }

    public Boolean getAlterar() {
        return alterar;
    }

    public void setAlterar(Boolean alterar) {
        this.alterar = alterar;
    }

    public Boolean getExcluir() {
        return excluir;
    }

    public void setExcluir(Boolean excluir) {
        this.excluir = excluir;
    }

    public Boolean getListar() {
        return listar;
    }

    public void setListar(Boolean listar) {
        this.listar = listar;
    }

    public Boolean getBuscar() {
        return buscar;
    }

    public void setBuscar(Boolean buscar) {
        this.buscar = buscar;
    }

    public Lotacao getLotacao() {
        return lotacao;
    }

    public void setLotacao(Lotacao lotacao) {
        this.lotacao = lotacao;
    }

    public LotacaoSub getLotacaoSub() {
        return lotacaoSub;
    }

    public void setLotacaoSub(LotacaoSub lotacaoSub) {
        this.lotacaoSub = lotacaoSub;
    }

    public UserLogin getUserlogin() {
        return userlogin;
    }

    public void setUserlogin(UserLogin userlogin) {
        this.userlogin = userlogin;
    }

    public UserMenu getUserMenu() {
        return userMenu;
    }

    public void setUserMenu(UserMenu userMenu) {
        this.userMenu = userMenu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserTemplate getUserTemplate() {
        return userTemplate;
    }

    public CargoGeral getCargoGeral() {
        return cargoGeral;
    }

    public void setCargoGeral(CargoGeral cargoGeral) {
        this.cargoGeral = cargoGeral;
    }
   
    public void setUserTemplate(UserTemplate userTemplate) {
        this.userTemplate = userTemplate;
    }

    @Override
    public String toString() {
        return "UserPermissaoAcesso{" + "id=" + id + ", incluir=" + incluir + ", alterar=" + alterar + ", excluir=" + excluir + ", listar=" + listar + ", buscar=" + buscar + ", dataHora=" + dataHora + ", lotacao=" + lotacao + ", lotacaoSub=" + lotacaoSub + ", userlogin=" + userlogin + ", userMenu=" + userMenu + ", userTemplate=" + userTemplate + '}';
    }
    
    

}
