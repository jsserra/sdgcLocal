package com.pmm.sdgc.ws.model;

import com.pmm.sdgc.model.PortariaRelacao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jrdutra
 */
public class ModelPortariaRelacao {
    private String nome;
    private String matricula;
    
    public static List<ModelPortariaRelacao> toModelPortariaRelacao(List<PortariaRelacao> portariasRelacao){
        List<ModelPortariaRelacao> lista = new ArrayList();
        
        for (PortariaRelacao p: portariasRelacao) {
            ModelPortariaRelacao m = new ModelPortariaRelacao();
            m.setMatricula(p.getFuncional().getMatricula());
            m.setNome(p.getFuncional().getPessoa().getNome());
            
            
            lista.add(m);
            
        }
        
        return lista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    
    
}
