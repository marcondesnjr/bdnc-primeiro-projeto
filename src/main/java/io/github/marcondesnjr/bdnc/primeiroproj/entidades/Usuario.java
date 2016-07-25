package io.github.marcondesnjr.bdnc.primeiroproj.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * @brief Classe Usuario
 * @author José Marcondes do Nascimento Junior
 * @date 21/07/2016
 */
@Entity
public class Usuario implements Serializable {

    @Id
    private String email;
    private String senha;
    private String nome;
    private String sexo;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private Set<Incidente> incidentes;

    public Usuario() {
        incidentes = new HashSet<>();
    }

    public Usuario(String email, String senha, String nome, String sexo) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.sexo = sexo;
        incidentes = new HashSet<>();
    }
    
    public void addIncidente(Incidente incidente){
        this.incidentes.add(incidente);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Set<Incidente> getIncidentes() {
        return incidentes;
    }

    public void setIncidentes(Set<Incidente> incidentes) {
        this.incidentes = incidentes;
    }

}
