package io.github.marcondesnjr.bdnc.primeiroproj.managedbean;

import io.github.marcondesnjr.bdnc.primeiroproj.cadastro.IncidenteCadastro;
import io.github.marcondesnjr.bdnc.primeiroproj.entidades.Incidente;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
@ManagedBean(name = "initMap")
@Dependent
public class InitMap {

    @Inject
    private IncidenteCadastro incidenteCadastro;

    /**
     * Creates a new instance of InitMap
     */
    public InitMap() {
    }

    public void loadmap() throws SQLException {
        List<Incidente> incidentes = incidenteCadastro.findAll();
        RequestContext.getCurrentInstance().execute("deleteMarkers()");
        for (Incidente incidente : incidentes) {
            String localjs = "new google.maps.LatLng(" + incidente.getLocalizacao().getCoordinate().x + "," + incidente.getLocalizacao().getCoordinate().y + ")";
            String script = "placemarker(" + localjs + ");";
            RequestContext.getCurrentInstance().execute(script);
        }
    }

}
