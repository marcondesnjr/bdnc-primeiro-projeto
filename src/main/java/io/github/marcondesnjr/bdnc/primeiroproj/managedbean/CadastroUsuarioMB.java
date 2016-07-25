package io.github.marcondesnjr.bdnc.primeiroproj.managedbean;

import io.github.marcondesnjr.bdnc.primeiroproj.cadastro.UsuarioCadastro;
import java.io.IOException;
import java.sql.SQLException;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
@ManagedBean(name = "cadastroUsuarioMB")
@RequestScoped
public class CadastroUsuarioMB {

    private String nome;
    private String email;
    private String senha;
    private char sexo;
    
    @EJB
    private UsuarioCadastro usuarioCadastro;
    
    @ManagedProperty(value = "#{loginMB}")
    private LoginMB loginMb;
    
    /**
     * Creates a new instance of CadastroPessoaMB
     */
    public CadastroUsuarioMB() {
    }
    
    public void cadastrarPessoa() throws IOException, SQLException{
        usuarioCadastro.persistir(nome, email, senha, sexo);
        loginMb.setEmail(email);
        loginMb.setSenha(senha);
        loginMb.login();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public UsuarioCadastro getUsuarioCadastro() {
        return usuarioCadastro;
    }

    public void setUsuarioCadastro(UsuarioCadastro usuarioCadastro) {
        this.usuarioCadastro = usuarioCadastro;
    }

    public LoginMB getLoginMb() {
        return loginMb;
    }

    public void setLoginMb(LoginMB loginMb) {
        this.loginMb = loginMb;
    }
    
    
    
    
    
    
}
