
package io.github.marcondesnjr.bdnc.primeiroproj.cadastro;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import io.github.marcondesnjr.bdnc.primeiroproj.entidades.Incidente;
import io.github.marcondesnjr.bdnc.primeiroproj.entidades.TipoIncidente;
import io.github.marcondesnjr.bdnc.primeiroproj.entidades.TipoPresenca;
import io.github.marcondesnjr.bdnc.primeiroproj.entidades.Usuario;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @brief Classe IncidenteCadastro
 * @author José Marcondes do Nascimento Junior
 * @date   22/07/2016
 */
@Stateless
public class IncidenteCadastro {
        
    @Inject
    private IncidenteRepositorio incidenteRepositorio;
    
    @PersistenceContext
    private EntityManager em;
    
    public void persistir(String tipo, String tipoPre, String local, String info, LocalDate data, Usuario usr) throws SQLException{
        TipoIncidente tipoIncidente = TipoIncidente.valueOf(tipo);
        TipoPresenca tipoPresenca = TipoPresenca.valueOf(tipoPre);
        local = local.substring(1, local.length()-1);
        String[] latlng = local.split(",");
        Point localizacao = new GeometryFactory().createPoint(new Coordinate(Double.parseDouble(latlng[0].trim()), 
                Double.parseDouble(latlng[1].trim())));
        Incidente incidente = new Incidente(tipoIncidente, tipoPresenca, localizacao, info, data);
        incidenteRepositorio.salvar(usr, incidente);
    }
    
    public List<Incidente> findAll() throws SQLException{
        try {
            return incidenteRepositorio.recuperarTodos();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IncidenteCadastro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }
    
    public List<Incidente> findByDate(LocalDate fDate, LocalDate sDate) throws SQLException{
        List<Incidente> all = findAll();
        all.removeIf((Incidente t) -> {
            int a = t.getData().compareTo(fDate);
            int b = t.getData().compareTo(sDate);
            return t.getData().compareTo(fDate) < 0 || t.getData().compareTo(sDate) > 0; 
        });
        return all;
    }
    
    public List<Incidente> findByTipo(TipoIncidente tipo) throws SQLException{
        List<Incidente> all = findAll();
        all.removeIf((Incidente t) -> {
            return !t.getTipo().equals(tipo);
        });
        return all;
    }
    
    public List<Incidente> findByDataAndTipo(LocalDate fDate, LocalDate sDate, TipoIncidente tipo) throws SQLException{
        List<Incidente> all = findByDate(fDate, sDate);
        all.removeIf((Incidente t) -> {
            return !t.getTipo().equals(tipo);
        });
        return all;
    }

}
