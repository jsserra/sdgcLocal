    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.Especialidade;
import com.pmm.sdgc.model.EspecialidadeTipo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author setinf
 */
public class ModelEspecialidadeWsMat {

private String matricula;
    private String nome;
    private List<ModelEspecialidadeWs> especialidades;

    public ModelEspecialidadeWsMat(String matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
      
    }

    public static ModelEspecialidadeWsMat toModelEspecialidadeWsMat(List<Especialidade> especialidades, String matricula, String nome){
    
        ModelEspecialidadeWsMat mewm = new ModelEspecialidadeWsMat(matricula, nome);
        
        List<ModelEspecialidadeWs> mew = new ArrayList();
        for(Especialidade e : especialidades){
            ModelEspecialidadeWs me = new ModelEspecialidadeWs(
                    e.getEspecialidadeTipo().getNome(),
                    e.getEspecialidadeTipo().getId(),
                    e.getDatahora()
            );
            me.setUserLogin(e.getUserlogin().getLogin());
            mew.add(me);
            mewm.setEspecialidades(mew);
        }
        return mewm;
    }
    
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ModelEspecialidadeWs> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<ModelEspecialidadeWs> especialidades) {
        this.especialidades = especialidades;
    }

  



    

}
