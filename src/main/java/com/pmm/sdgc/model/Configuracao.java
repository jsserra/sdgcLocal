package com.pmm.sdgc.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author dreges
 */
@Entity
@Table(name = "parametros")
public class Configuracao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String ipServidorRelatorio;
    private String usuarioRelatorio;
    private String senhaUsuarioRelatorio;
    private String urlBrasao;
    private String urlAssinatura;
    private String urlFoto;
    private String urlPortaria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpServidorRelatorio() {
        return ipServidorRelatorio;
    }

    public void setIpServidorRelatorio(String ipServidorRelatorio) {
        this.ipServidorRelatorio = ipServidorRelatorio;
    }

    public String getUsuarioRelatorio() {
        return usuarioRelatorio;
    }

    public void setUsuarioRelatorio(String usuarioRelatorio) {
        this.usuarioRelatorio = usuarioRelatorio;
    }

    public String getSenhaUsuarioRelatorio() {
        return senhaUsuarioRelatorio;
    }

    public void setSenhaUsuarioRelatorio(String senhaUsuarioRelatorio) {
        this.senhaUsuarioRelatorio = senhaUsuarioRelatorio;
    }

    public String getUrlBrasao() {
        return urlBrasao;
    }

    public void setUrlBrasao(String urlBrasao) {
        this.urlBrasao = urlBrasao;
    }

    public String getUrlAssinatura() {
        return urlAssinatura;
    }

    public void setUrlAssinatura(String urlAssinatura) {
        this.urlAssinatura = urlAssinatura;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getUrlPortaria() {
        return urlPortaria;
    }

    public void setUrlPortaria(String urlPortaria) {
        this.urlPortaria = urlPortaria;
    }

    
    
}
