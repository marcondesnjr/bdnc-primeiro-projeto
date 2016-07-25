
package io.github.marcondesnjr.bdnc.primeiroproj.cadastro;

import io.github.marcondesnjr.bdnc.primeiroproj.entidades.Usuario;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * @brief Classe UsuarioRepositorio
 * @author José Marcondes do Nascimento Junior
 * @date   24/07/2016
 */
public class UsuarioRepositorio {
    
    @Inject
    private DataSource dataSource;
    private QueryRunner queryRunner;
    
    @PostConstruct
    private void init(){
        queryRunner = new QueryRunner(dataSource);
    }
    
    public void persistir(Usuario usr) throws SQLException{
        String sql = "INSERT INTO usuario(email, nome, senha, sexo) VALUES (?, ?, ?, ?);";
        queryRunner.update(sql,usr.getEmail(), usr.getNome(), usr.getSenha(), usr.getSexo());
    }
    
    public Usuario encontrarPeloEmail(String email) throws SQLException{
        String sql = "SELECT * FROM USUARIO WHERE email = ?";
        return queryRunner.query(sql, new BeanHandler<>(Usuario.class),email);
    }
    

}
