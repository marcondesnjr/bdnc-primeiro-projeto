
package io.github.marcondesnjr.bdnc.primeiroproj.cadastro;

import io.github.marcondesnjr.bdnc.primeiroproj.entidades.Usuario;
import java.sql.SQLException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @brief Classe UsuarioCadastro
 * @author José Marcondes do Nascimento Junior
 * @date   23/07/2016
 */
@Stateless
public class UsuarioCadastro {
    
    @Inject
    private UsuarioRepositorio usuarioRepositorio;
    
    public void persistir(String nome, String email, String senha, char sexo){
//        Usuario usr = new Usuario(email, senha, nome, sexo);
//        em.persist(usr);
    }
    
    public Usuario encontrar(String email) throws SQLException{
        return usuarioRepositorio.encontrarPeloEmail(email);
    }
    
    
    
}
