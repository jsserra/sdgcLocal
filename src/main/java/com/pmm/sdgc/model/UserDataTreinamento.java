package com.pmm.sdgc.model;

import com.pmm.sdgc.converter.LocalDateConverter1;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
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
@Table(name = "userDataTreinamento")
public class UserDataTreinamento implements Serializable {
//VAR
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "data")
    @Convert(converter = LocalDateConverter1.class)
    private LocalDate data;

    @Column(name = "dataHora")
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "idUserLogin", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private UserLogin userLogin;


//SET GET

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
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

    @Override
    public String toString() {
        return "UserDataTreinamento{" + "id=" + id + ", data=" + data + ", dataHora=" + dataHora + ", userLogin=" + userLogin + '}';
    }

    

}