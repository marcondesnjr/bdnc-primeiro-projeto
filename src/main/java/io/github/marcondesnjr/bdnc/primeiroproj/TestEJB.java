
package io.github.marcondesnjr.bdnc.primeiroproj;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.WKTReader;
import io.github.marcondesnjr.bdnc.primeiroproj.entidades.Incidente;
import io.github.marcondesnjr.bdnc.primeiroproj.entidades.TipoIncidente;
import io.github.marcondesnjr.bdnc.primeiroproj.entidades.TipoPresenca;
import io.github.marcondesnjr.bdnc.primeiroproj.entidades.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * @brief Classe TestEJB
 * @author José Marcondes do Nascimento Junior
 * @date   21/07/2016
 */
@Stateless
public class TestEJB {
    
    @PersistenceContext(unitName = "bdnc-primeiro-proj-PU")
    private EntityManager em;
    
    public void test(){
       
    }
    
}
