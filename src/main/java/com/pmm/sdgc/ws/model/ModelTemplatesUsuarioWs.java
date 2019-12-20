/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import java.util.Objects;

/**
 *
 * @author setinf
 */
public class ModelTemplatesUsuarioWs implements Comparable {

    private Integer idUsuario;
    private String idSecretaria;
    private String nomeSecretaria;
    private String idSetor;
    private String nomeSetor;
    private String idCargoGeral;
    private String nomeCargoGeral;
    private Integer idTemplate;
    private String nomeTemplate;

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdSecretaria() {
        return idSecretaria;
    }

    public void setIdSecretaria(String idSecretaria) {
        this.idSecretaria = idSecretaria;
    }

    public String getNomeSecretaria() {
        return nomeSecretaria;
    }

    public void setNomeSecretaria(String nomeSecretaria) {
        this.nomeSecretaria = nomeSecretaria;
    }

    public String getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(String idSetor) {
        this.idSetor = idSetor;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }

    public Integer getIdTemplate() {
        return idTemplate;
    }

    public void setIdTemplate(Integer idTemplate) {
        this.idTemplate = idTemplate;
    }

    public String getNomeTemplate() {
        return nomeTemplate;
    }

    public void setNomeTemplate(String nomeTemplate) {
        this.nomeTemplate = nomeTemplate;
    }
    
    public String getIdCargoGeral() {
        return idCargoGeral;
    }

    public void setIdCargoGeral(String idCargoGeral) {
        this.idCargoGeral = idCargoGeral;
    }

    public String getNomeCargoGeral() {
        return nomeCargoGeral;
    }

    public void setNomeCargoGeral(String nomeCargoGeral) {
        this.nomeCargoGeral = nomeCargoGeral;
    }

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
        final ModelTemplatesUsuarioWs other = (ModelTemplatesUsuarioWs) obj;
        if (!Objects.equals(this.idSecretaria, other.idSecretaria)) {
            return false;
        }
        if (!Objects.equals(this.idSetor, other.idSetor)) {
            return false;
        }
        if (!Objects.equals(this.idUsuario, other.idUsuario)) {
            return false;
        }
        if (!Objects.equals(this.idTemplate, other.idTemplate)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Object t) {
        //tem que ordenar por secretaria e por setor

        ModelTemplatesUsuarioWs mt = (ModelTemplatesUsuarioWs) t;


        int respSecretaria = 0;

        if (this.getNomeSecretaria() == null) {
            if (mt.getNomeSecretaria() == null) {
                respSecretaria =  0;
            } else {
                respSecretaria =  -1;
            }
        } else {
            if (mt.getNomeSecretaria()==null) {
                respSecretaria = 1;
            } else {
                respSecretaria =  this.getNomeSecretaria().compareTo(mt.getNomeSecretaria());
            }
        }

        int respSetor = 0;

        if (this.getNomeSetor() == null) {
            if (mt.getNomeSetor() == null) {
                respSetor =  0;
            } else {
                respSetor =  -1;
            }
        } else {
            if (mt.getNomeSetor()==null) {
                respSetor = 1;
            } else {
                respSetor =  this.getNomeSetor().compareTo(mt.getNomeSetor());
            }
        }

        int respTemplate = 0;

        if (this.getNomeTemplate() == null) {
            if (mt.getNomeTemplate() == null) {
                respTemplate =  0;
            } else {
                respTemplate =  -1;
            }
        } else {
            if (mt.getNomeTemplate()==null) {
                respTemplate = 1;
            } else {
                respTemplate =  this.getNomeTemplate().compareTo(mt.getNomeTemplate());
            }
        }
        
        
        if (respSecretaria ==0 && respSetor==0 && respTemplate==0) return 0;
        
        if (respSecretaria ==0 && respSetor == 0) return respTemplate;
        
        if (respSecretaria ==0) return respSetor;
        
        return respSecretaria;

    }

    @Override
    public String toString() {
        return "ModelTemplatesUsuarioWs{" + "idUsuario=" + idUsuario + ", idSecretaria=" + idSecretaria + ", nomeSecretaria=" + nomeSecretaria + ", idSetor=" + idSetor + ", nomeSetor=" + nomeSetor + ", idCargoGeral=" + idCargoGeral + ", nomeCargoGeral=" + nomeCargoGeral + ", idTemplate=" + idTemplate + ", nomeTemplate=" + nomeTemplate + '}';
    }
    
}
