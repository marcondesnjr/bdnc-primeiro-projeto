package io.github.marcondesnjr.bdnc.primeiroproj.beans;

import io.github.marcondesnjr.bdnc.primeiroproj.cadastro.UsuarioCadastro;
import io.github.marcondesnjr.bdnc.primeiroproj.entidades.Usuario;
import java.io.Serializable;
import java.sql.SQLException;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
@Stateful
@SessionScoped
public class LoginUsuario implements Serializable{

    private Usuario usuarioLogado;
    
    @EJB
    private UsuarioCadastro usuarioCadastro;
    
    public void login(String email, String senha) throws SQLException{
        Usuario usrByEmail = usuarioCadastro.encontrar(email);
        if(usrByEmail != null && usrByEmail.getSenha().equals(senha)){
            usuarioLogado = usrByEmail;
        }
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public UsuarioCadastro getUsuarioCadastro() {
        return usuarioCadastro;
    }

    public void setUsuarioCadastro(UsuarioCadastro usuarioCadastro) {
        this.usuarioCadastro = usuarioCadastro;
    }
    
    
    
}
