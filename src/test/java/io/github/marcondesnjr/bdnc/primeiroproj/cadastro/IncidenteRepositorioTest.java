package io.github.marcondesnjr.bdnc.primeiroproj.cadastro;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import io.github.marcondesnjr.bdnc.primeiroproj.entidades.Incidente;
import io.github.marcondesnjr.bdnc.primeiroproj.entidades.TipoIncidente;
import io.github.marcondesnjr.bdnc.primeiroproj.entidades.TipoPresenca;
import io.github.marcondesnjr.bdnc.primeiroproj.entidades.Usuario;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
@RunWith(CdiRunner.class)
@AdditionalClasses(DBManager.class)
public class IncidenteRepositorioTest {
    
    @Inject
    private IncidenteRepositorio instance;
    
    public IncidenteRepositorioTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSalvar() throws Exception{
        System.out.println("salvar");
        instance.salvar(null, new Incidente(TipoIncidente.ESTUPRO, TipoPresenca.TESTEMUNHA, 
                new GeometryFactory().createPoint(new Coordinate(10, 10)), "Informação", LocalDate.now()));
    }
    
    @Test
    public void testRecuperar() throws Exception{
        System.out.println("salvar");
        List<Incidente> incidentes = instance.recuperarTodos();
        for (Incidente incidente : incidentes) {
            System.out.println(incidente.getLocalizacao().getCoordinate().x);
        }
        
    }
    
}
