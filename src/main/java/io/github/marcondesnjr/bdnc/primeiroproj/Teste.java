
package io.github.marcondesnjr.bdnc.primeiroproj;

import io.github.marcondesnjr.bdnc.primeiroproj.entidades.Usuario;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @brief Classe Teste
 * @author José Marcondes do Nascimento Junior
 * @date   21/07/2016
 */
@Named
public class Teste implements Serializable{
    
    @EJB
    private TestEJB testEJB;

    public Teste() {
    }
    
    
    public void teste(){
        testEJB.test();
    }

    public TestEJB getTestEJB() {
        return testEJB;
    }

    public void setTestEJB(TestEJB testEJB) {
        this.testEJB = testEJB;
    }
   
    
    
    
}
