package io.github.marcondesnjr.bdnc.primeiroproj.managedbean;

import io.github.marcondesnjr.bdnc.primeiroproj.cadastro.UsuarioCadastro;
import io.github.marcondesnjr.bdnc.primeiroproj.entidades.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginMB implements Serializable{

    private String email;
    private String senha;

    @EJB
    private UsuarioCadastro usuarioCadastro;

    /**
     * Creates a new instance of CheckLogin
     */
    public LoginMB() {
    }

    private Usuario usuarioLogado;

    public boolean getLogado() {
        return usuarioLogado != null;
    }

    public void login() throws IOException, SQLException {
        Usuario usrByEmail = usuarioCadastro.encontrar(email);
        if (usrByEmail != null && usrByEmail.getSenha().equals(senha)) {
            usuarioLogado = usrByEmail;
        }
        this.email = null;
        this.senha = null;
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
    }
    
    public void logoff() throws IOException{
        usuarioLogado = null;
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
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

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
    
    

}
